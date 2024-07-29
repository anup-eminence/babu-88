package com.sona.babu88.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.viewmodel.TransactionViewModel
import com.sona.babu88.databinding.FragmentProfitLossBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class ProfitLossFragment : Fragment(), HistoryTabAdapter.OnTabItemClickListener {
    private lateinit var binding: FragmentProfitLossBinding
    private lateinit var historyTabAdapter: HistoryTabAdapter
    private var homeTabList = arrayListOf<HomeTab>()
    private lateinit var profitLossAdapter: ProfitLossAdapter
    private val transactionViewModel: TransactionViewModel by viewModels()
    private var pageNo = 1
    private var type = ""

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
        setProfitLossAdapter()
        observerTotalUserAccountLogs()
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        historyTabAdapter = HistoryTabAdapter()
        historyTabAdapter.setOnTabListener(this@ProfitLossFragment)
        binding.recyclerViewTab.adapter = historyTabAdapter
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(R.drawable.ic_history_all, "All"))
        homeTabList.add(HomeTab(R.drawable.ic_pl, "P&L"))
        homeTabList.add(HomeTab(R.drawable.ic_history_account, "Account"))
        historyTabAdapter.setTabData(homeTabList)
    }

    override fun onTabItemClickListener(item: HomeTab?) {
        type = when (item?.text) {
            "All" -> "1"
            "P&L" -> "2"
            "Account" -> "3"
            else -> ""
        }
        observerTotalUserAccountLogs()
    }

    private fun setProfitLossAdapter() {
        binding.rvProfitLoss.layoutManager = LinearLayoutManager(requireContext())
        profitLossAdapter = ProfitLossAdapter()
        binding.rvProfitLoss.adapter = profitLossAdapter
    }

    private fun observerTotalUserAccountLogs() {
        if (type.isEmpty()) type = "1"
        transactionViewModel.getTotalUserAccountLogs(pageNo = pageNo, type = type)
        transactionViewModel.totalUserAccountLogs.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    profitLossAdapter.setProfitLossData(it.data?.getData())
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                }
            }
        }
    }
}