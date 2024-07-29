package com.sona.babu88.ui.deposit_withdrawal

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.ResultsItem
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.data.viewmodel.WithdrawViewModel
import com.sona.babu88.databinding.FragmentWithdrawalBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.ui.deposit_withdrawal.deposit.PaymentMethodsAdapter
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.OnAccountListener
import com.sona.babu88.util.hide
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.show
import com.sona.babu88.util.showProgress
import com.sona.babu88.util.showToast

class WithdrawalFragment : Fragment(), DepositAmountAdapter.OnAmountClickListener,
    PaymentMethodsAdapter.OnTabItemClickListener {
    private lateinit var binding: FragmentWithdrawalBinding
    private lateinit var depositAmountAdapter: DepositAmountAdapter
    private var depositAmountList = arrayListOf<DepositAmountList>()
    private var totalAmount = 0
    private var listener: OnAccountListener? = null
    private var userData: UserData? = null
    private lateinit var paymentMethodsAdapter: PaymentMethodsAdapter
    private val withdrawViewModel: WithdrawViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWithdrawalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        userData = MySharedPreferences.getSavedObjectFromPreference(requireContext(), AppConstant.USER_DATA)
        binding.apply {
            tvPaymentMethods.text = getFormattedText(getString(R.string.payment_methods))
            tvDepositChannel.text = getFormattedText(getString(R.string.deposit_channel))
            tvWithdrawAmount.text = getFormattedText(getString(R.string.withdraw_amount))
            tvMobNum.text = getFormattedText(getString(R.string.mobile_number))
            tvBankName.text = getFormattedText(getString(R.string.bank_name))
            tvBranchName.text = getFormattedText(getString(R.string.branch_name))
            tvBankHolderName.text = getFormattedText(getString(R.string.bank_holder_name))
            tvAccNumber.text = getFormattedText(getString(R.string.account_number))
            tvSwiftIfsc.text = getFormattedText(getString(R.string.swift_ifsc_code))

            val spannableBuilder = SpannableStringBuilder()
            spannableBuilder.append(
                Html.fromHtml(
                    userData?.user?.symbol,
                    Html.FROM_HTML_MODE_LEGACY
                )
            ).append(" ").append("%.2f".format(userData?.user?.myBalance))

            tvAmount.text = spannableBuilder
            txtMobNum.text = userData?.user?.number.toString()
        }
        setPaymentMethodsAdapter()
        setDepositAmountAdapter()
        setDepositAmountData()
        observerWithdrawMethods()
    }

    private fun setOnClickListener() {
        binding.apply {
            imgClear.setOnClickListener {
                etWithdrawAmount.text?.clear()
                totalAmount = 0
            }
            btnWithdraw.setOnClickListener { requireContext().showToast(getString(R.string.email_is_not_verified_kindly_proceed_verification_before_withdrawal)) }
            tvVerify.setOnClickListener { listener?.onAccountClick("Profile") }
        }
    }

    private fun setDepositAmountAdapter() {
        binding.amountRecyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        depositAmountAdapter = DepositAmountAdapter()
        depositAmountAdapter.setAmountClickListener(this@WithdrawalFragment)
        binding.amountRecyclerView.adapter = depositAmountAdapter
    }

    private fun setDepositAmountData() {
        depositAmountList.add(DepositAmountList("1000"))
        depositAmountList.add(DepositAmountList("1500"))
        depositAmountList.add(DepositAmountList("2000"))
        depositAmountList.add(DepositAmountList("30000"))
        depositAmountList.add(DepositAmountList("50000"))
        depositAmountList.add(DepositAmountList("100"))
        depositAmountList.add(DepositAmountList("200"))
        depositAmountList.add(DepositAmountList("500"))

        depositAmountAdapter.setDepositAmountData(depositAmountList)
    }

    override fun onAmountClickListener(item: DepositAmountList?) {
        totalAmount += item?.amount?.toInt()!!
        binding.etWithdrawAmount.text =
            Editable.Factory.getInstance().newEditable(totalAmount.toString())
    }

    private fun setPaymentMethodsAdapter() {
        binding.rvPaymentMethods.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        paymentMethodsAdapter = PaymentMethodsAdapter()
        paymentMethodsAdapter.setOnTabListener(this@WithdrawalFragment)
        binding.rvPaymentMethods.adapter = paymentMethodsAdapter
    }

    override fun onTabItemClickListener(item: HomeTab?) {
        when(item?.text) {
            "" -> {
                binding.clMobile.hide()
                binding.clAddBankDetails.show()
            }
        }
    }

    private fun observerWithdrawMethods() {
        withdrawViewModel.getWithdrawMethods(websiteId = "665488a9be104576f70989e5")
        withdrawViewModel.withdrawMethods.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    println("WithdrawMethods>>> ${it.data}")
                    paymentMethodsAdapter.setPaymentMethodsData(getMethodsList(it.data?.results?.get(0)))
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                    requireContext().showToast(it.message.toString())
                }
            }
        }
    }

    private fun getMethodsList(item: ResultsItem?): List<HomeTab> {
        val tabList = ArrayList<HomeTab>()
        if (item?.isBkash == true) tabList.add(HomeTab(findImage("Bkash"), "Bkash"))
        if (item?.isNagad == true) tabList.add(HomeTab(findImage("Nagad"), "Nagad"))
        if (item?.isUpay == true) tabList.add(HomeTab(findImage("Upay"), "Upay"))
        if (item?.isRocket == true) tabList.add(HomeTab(findImage("Rocket"), "Rocket"))
        tabList.add(tabList.size, HomeTab(R.drawable.img_bank, ""))
        return tabList
    }

    private fun findImage(type: String): Int {
        return when (type) {
            "Bkash" -> R.drawable.img_bkash
            "Nagad" -> R.drawable.img_bkash
            "Upay" -> R.drawable.img_bkash
            "Rocket" -> R.drawable.img_bkash
            else -> -1
        }
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAccountListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnAccountListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}