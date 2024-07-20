package com.sona.babu88.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentProfitLossBinding
import com.sona.babu88.model.HomeTab

class ProfitLossFragment : Fragment() {
    private lateinit var binding: FragmentProfitLossBinding
    private lateinit var historyTabAdapter: HistoryTabAdapter
    private var homeTabList = arrayListOf<HomeTab>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfitLossBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setTabAdapter()
        setTabData()
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        historyTabAdapter = HistoryTabAdapter()
//        historyTabAdapter.setOnTabListener(this@ProfitLossFragment)
        binding.recyclerViewTab.adapter = historyTabAdapter
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(R.drawable.ic_history_all, "All"))
        homeTabList.add(HomeTab(R.drawable.ic_pl, "P&L"))
        homeTabList.add(HomeTab(R.drawable.ic_history_account, "Account"))
        historyTabAdapter.setTabData(homeTabList)
    }
}