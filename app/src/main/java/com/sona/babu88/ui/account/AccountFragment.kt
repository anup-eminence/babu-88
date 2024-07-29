package com.sona.babu88.ui.account

import MySharedPreferences
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.data.viewmodel.AuthViewModel
import com.sona.babu88.databinding.FragmentAccountBinding
import com.sona.babu88.util.AppConstant.USER_DATA
import com.sona.babu88.util.OnAccountListener
import com.sona.babu88.util.hide
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.show
import com.sona.babu88.util.showProgress

class AccountFragment : Fragment(), AccountParentAdapter.OnItemClickListener {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var parentAdapter: AccountParentAdapter
    private var listener: OnAccountListener? = null
    private val authViewModel: AuthViewModel by viewModels()
    private var userData : UserData?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setParentAdapter()
        setParentAdapterData()
        setOnClickListener()
    }

    private fun initView() {
        userData = MySharedPreferences.getSavedObjectFromPreference<UserData>(requireContext(),USER_DATA)
        setUserData(userData?.user?.myBalance)
        observerUserVipDetails()
    }

    private fun setOnClickListener() {
        binding.apply {
            binding.ivRefresh.setOnClickListener { observerUserDetails() }
            imgBettingPass.setOnClickListener { listener?.onAccountClick("Betting Pass") }
            imgRewards.setOnClickListener { listener?.onAccountClick("Rewards") }
            imgReferral.setOnClickListener { listener?.onAccountClick("Referral") }
            imgDeposit.setOnClickListener { listener?.onAccountClick("Deposit") }
            imgWithdrawal.setOnClickListener { listener?.onAccountClick("Withdrawal") }
            btnLogout.setOnClickListener {
                listener?.onAccountClick("LOG_OUT")
            }
            tvMyVip.setOnClickListener { listener?.onAccountClick("My VIP") }
            arrow.setOnClickListener { tvMyVip.performClick() }
        }
    }

    private fun setParentAdapter() {
        binding.parentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        parentAdapter = AccountParentAdapter()
        parentAdapter.setOnItemClickListener(this@AccountFragment)
        binding.parentRecyclerView.adapter = parentAdapter
    }

    private fun setParentAdapterData() {
        val parentList = arrayListOf<ParentList>()
        parentList.add(ParentList("History", R.drawable.ic_history, listOf(ChildList("Betting Records"), ChildList("Turnover"), ChildList("Transaction Records"), ChildList("Profit Loss"))))
        parentList.add(ParentList("Special", R.drawable.ic_special, listOf(ChildList("Refer and Earn"), ChildList("Betting Pass"), ChildList("Agent Affiliate"))))
        parentList.add(ParentList("Rewards", R.drawable.ic_rewards2, listOf(ChildList("Claim Voucher"), ChildList("Lucky Spin"), ChildList("Daily Check In "), ChildList("Coin Grab"))))
        parentList.add(ParentList("Social", R.drawable.ic_social, listOf(ChildList("LIVE CHAT"), ChildList("Facebook"), ChildList("Instagram"), ChildList("Telegram"), ChildList("Twitter"), ChildList("YouTube"))))
        parentList.add(ParentList("Settings", R.drawable.ic_settings, listOf(ChildList("Bank Details"), ChildList("Profile"), ChildList("Change password"))))
        parentAdapter.setPAData(parentList)
    }

    override fun onItemClickListener(item: ChildList?) {
        listener?.onAccountClick(item?.title ?: "")
    }

    private fun observerUserDetails() {
        authViewModel.getUserDetails()
        authViewModel.userDetails.observe(viewLifecycleOwner) {
            binding.apply {
                when (it) {
                    is ApiResult.Loading -> {
                        progressBar.show()
                        amount.hide()
                    }

                    is ApiResult.Success -> {
                        progressBar.hide()
                        amount.show()
                        setUserData(it.data?.balance)
                    }

                    is ApiResult.Error -> {
                        progressBar.hide()
                        amount.show()
                        setUserData(userData?.user?.myBalance)
                    }
                }
            }
        }
    }

    private fun observerUserVipDetails() {
        authViewModel.getUserVipDetails()
        authViewModel.userVipDetails.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    binding.tvVipPoint.text =
                        StringBuilder().append(getString(R.string.vip_points_vp)).append(" ")
                            .append(
                                Html.fromHtml(
                                    userData?.user?.symbol ?: "",
                                    Html.FROM_HTML_MODE_LEGACY
                                )
                            ).append(" ").append(String.format("%.2f", it.data?.points?.toDouble() ?: 0.00))
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                }
            }
        }
    }

    private fun setUserData(myBalance: Double?) {
        binding.userName.text = userData?.user?.userName
        binding.amount.text = StringBuilder().append(
            Html.fromHtml(
                userData?.user?.symbol ?: "",
                Html.FROM_HTML_MODE_LEGACY
            )
        ).append(" ").append(String.format("%.2f", myBalance ?: 0.00))
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