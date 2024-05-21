package com.sona.babu88.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentWalletHistoryBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.ui.home.HomeTabAdapter

class WalletHistoryFragment : Fragment() {
    private lateinit var binding: FragmentWalletHistoryBinding
    private lateinit var homeTabAdapter: HomeTabAdapter
    private var homeTabList = arrayListOf<HomeTab>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabAdapter()
        setTabData()
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeTabAdapter = HomeTabAdapter()
//        homeTabAdapter.setOnTabListener(this@WalletHistoryFragment)
        binding.recyclerViewTab.adapter = homeTabAdapter
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(R.drawable.ic_deposit, "Deposit"))
        homeTabList.add(HomeTab(R.drawable.ic_withdrawal, "Withdrawal"))
        homeTabList.add(HomeTab(R.drawable.ic_transfer_history, "Adjustment"))
        homeTabAdapter.setTabData(homeTabList)
    }
}