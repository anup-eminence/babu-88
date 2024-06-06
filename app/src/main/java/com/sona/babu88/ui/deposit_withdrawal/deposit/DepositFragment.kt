package com.sona.babu88.ui.deposit_withdrawal.deposit

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.viewmodel.DepositViewModel
import com.sona.babu88.databinding.CustomDepositDialogBinding
import com.sona.babu88.databinding.FragmentDepositBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.ui.deposit_withdrawal.DepositAmountAdapter
import com.sona.babu88.ui.deposit_withdrawal.DepositAmountList
import com.sona.babu88.util.hide
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.show
import com.sona.babu88.util.showProgress
import com.sona.babu88.util.showToast

class DepositFragment : Fragment(), DepositAmountAdapter.OnAmountClickListener,
    PaymentMethodsAdapter.OnTabItemClickListener {
    private lateinit var binding: FragmentDepositBinding
    private lateinit var depositAmountAdapter: DepositAmountAdapter
    private var depositAmountList = arrayListOf<DepositAmountList>()
    private var totalAmount = 0
    private var selectedTextView: TextView? = null
    private val depositViewModel : DepositViewModel by viewModels()
    private lateinit var paymentMethodsAdapter: PaymentMethodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDepositBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            tvPaymentMethods.text = getFormattedText(getString(R.string.payment_methods))
            tvDepositChannel.text = getFormattedText(getString(R.string.deposit_channel))
            tvDepositAmount.text = getFormattedText(getString(R.string.deposit_amount))
            tvDepositBonus.text = getFormattedText(getString(R.string.deposit_bonus))
        }
        observerBankingMethods()
        observerBankingChannelList()
        observerDepositPromotionsList()
        setDepositAmountAdapter()
        setDepositAmountData()
        setPaymentMethodsAdapter()
    }

    private fun setOnClickListener() {
        binding.apply {
            imgClear.setOnClickListener {
                etDepositAmount.text?.clear()
                totalAmount = 0
            }
            btnCashout.setOnClickListener { selectTextView(btnCashout) }
            btnSendMoney.setOnClickListener { selectTextView(btnSendMoney) }
            btnDeposit.setOnClickListener {
                if (binding.etDepositAmount.text?.trim().toString().isEmpty()) {
                    requireContext().showToast("Please enter deposit amount!")
                } else showCustomDialog()
            }
        }
    }

    private fun observerBankingMethods() {
        depositViewModel.getBankingMethods(
            websiteId = "665488a9be104576f70989e5"
        )

        depositViewModel.bankingMethods.observe(requireActivity()) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    println("BankingMethods>>> ${it.data}")
                    paymentMethodsAdapter.setPaymentMethodsData(getMethodsList(it.data?.results?.get(0)?.id.toString()))
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                    requireContext().showToast(it.message.toString())
                }
            }
        }
    }

    private fun observerBankingChannelList() {
        depositViewModel.getBankingChannelList(
            websiteId = "665488a9be104576f70989e5",
            method = "Bkash"
        )

        depositViewModel.bankingChannel.observe(requireActivity()) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    println("BankingChannelList>>> ${it.data}")
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                }
            }
        }
    }

    private fun observerDepositPromotionsList() {
        depositViewModel.depositPromotions.observe(viewLifecycleOwner) { it ->
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    println("DepositPromotionsList>>> ${it.data?.data}")
                    this.hideProgress()
                    val list = ArrayList<String>()
                    list.add("No Bonus")
                    it.data?.data?.forEach {
                        list.add(it.name.toString())
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_dropdown_item, list
                        )
                        binding.spinner.adapter = adapter
                        binding.imgClearBonus.setOnClickListener { binding.spinner.setSelection(0) }
                        binding.spinner.onItemSelectedListener = object :
                            AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {}

                            override fun onNothingSelected(parent: AdapterView<*>?) {}
                        }
                    }
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                    val list = ArrayList<String>()
                    list.add("No Bonus")
                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, list)
                    binding.spinner.adapter = adapter
                    requireContext().showToast(it.message.toString())
                }
            }
        }
        depositViewModel.getDepositPromotionsList()
    }

    private fun setDepositAmountAdapter() {
        binding.amountRecyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        depositAmountAdapter = DepositAmountAdapter()
        depositAmountAdapter.setAmountClickListener(this@DepositFragment)
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
        binding.etDepositAmount.text =
            Editable.Factory.getInstance().newEditable(totalAmount.toString())
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }

    private fun selectTextView(textView: TextView) {
        selectedTextView?.setBackgroundResource(R.drawable.bg_8_black_corner_border)
        if (selectedTextView == textView) {
            selectedTextView = null
        } else {
            textView.setBackgroundResource(R.drawable.bg_8_yellow_border)
            selectedTextView = textView
        }
    }

    private fun showCustomDialog() {
        CustomDepositDialogBinding.inflate(LayoutInflater.from(requireContext())).apply {
            val depositFloat = binding.etDepositAmount.text.toString().toFloatOrNull() ?: 0.0f
//            val bonusFloat = binding.txtDepositBonus.text.toString().toFloatOrNull() ?: 0.0f
            val bonusFloat = 0.0f
            depositAmount.text = "₹ %.2f".format(depositFloat)
            bonusAmount.text = "₹ %.2f".format(bonusFloat)
            targetAmount.text = "₹ %.2f".format(depositFloat + bonusFloat)

            val builder = AlertDialog.Builder(requireContext())
                .setView(root)
                .setCancelable(false)
                .create()
            ivClose.setOnClickListener { builder.dismiss() }
            btnConfirm.setOnClickListener {
                depositViewModel.createDepositRequest(
                    selectedAmount = depositFloat.toInt(),
                    selectedCat = "UPI",
                    selectedChannel = "shanu",
                    selectedMethod = "Bkash",
                    selectedMode = "number",
                    selectedPromoId = 0
                )

                depositViewModel.depositRequest.observe(requireActivity()) {
                    when (it) {
                        is ApiResult.Loading -> {
                            progressBar.show()
                        }

                        is ApiResult.Success -> {
                            progressBar.hide()
                            it.data?.message?.let { it1 -> requireContext().showToast(it1) }
                            builder.dismiss()
                        }

                        is ApiResult.Error -> {
                            progressBar.hide()
                            it.message?.let { it1 -> requireContext().showToast(it1) }
                        }
                    }
                }
            }
            builder.show()
        }
    }

    private fun setPaymentMethodsAdapter() {
            binding.rvPaymentMethods.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            paymentMethodsAdapter = PaymentMethodsAdapter()
            paymentMethodsAdapter.setOnTabListener(this@DepositFragment)
            binding.rvPaymentMethods.adapter = paymentMethodsAdapter
    }

    override fun onTabItemClickListener(item: HomeTab?) {}

    private fun getMethodsList(method: String?): List<HomeTab> {
        val tabList = ArrayList<HomeTab>()
            tabList.add(HomeTab(findImage(method.toString()), method.toString()))
        return tabList
    }

    private fun findImage(type: String): Int {
        return when (type) {
            "Bkash" -> R.drawable.img_bkash
            else -> -1
        }
    }
}