package com.sona.babu88.ui.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentAccountBinding
import com.sona.babu88.util.OnAccountListener

class AccountFragment : Fragment(), AccountParentAdapter.OnItemClickListener {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var parentAdapter: AccountParentAdapter
    private var listener: OnAccountListener? = null

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
        setParentAdapter()
        setParentAdapterData()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.apply {
            binding.ivRefresh.setOnClickListener { }
            imgBettingPass.setOnClickListener { listener?.onAccountClick("Betting Pass") }
            imgRewards.setOnClickListener { listener?.onAccountClick("Rewards") }
            imgReferral.setOnClickListener { listener?.onAccountClick("Referral") }
            imgDeposit.setOnClickListener { listener?.onAccountClick("Deposit") }
            imgWithdrawal.setOnClickListener { listener?.onAccountClick("Withdrawal") }
            btnLogout.setOnClickListener { }
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
        parentList.add(ParentList("History", R.drawable.ic_history, listOf(ChildList("Bet History"), ChildList("Turnover History"), ChildList("Wallet History"))))
        parentList.add(ParentList("Special", R.drawable.ic_special, listOf(ChildList("Refer and Earn"), ChildList("Betting Pass"), ChildList("Agent Affiliate"))))
        parentList.add(ParentList("Rewards", R.drawable.ic_rewards2, listOf(ChildList("Claim Voucher"), ChildList("Lucky Spin"), ChildList("Daily Check In "), ChildList("Coin Grab"))))
        parentList.add(ParentList("Social", R.drawable.ic_social, listOf(ChildList("LIVE CHAT"), ChildList("Facebook"), ChildList("Instagram"), ChildList("Telegram"), ChildList("Twitter"), ChildList("YouTube"))))
        parentList.add(ParentList("Settings", R.drawable.ic_settings, listOf(ChildList("Bank Details"), ChildList("Profile"), ChildList("Change password"))))
        parentAdapter.setPAData(parentList)
    }

    override fun onItemClickListener(item: ChildList?) {
        listener?.onAccountClick(item?.title ?: "")
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