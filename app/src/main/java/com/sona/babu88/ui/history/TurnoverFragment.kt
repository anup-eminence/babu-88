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
import com.sona.babu88.api.model.response.TurnoverDataItem
import com.sona.babu88.data.viewmodel.TransactionViewModel
import com.sona.babu88.databinding.FragmentTurnoverBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class TurnoverFragment : Fragment(), HistoryTabAdapter.OnTabItemClickListener,
    TurnOverAdapter.OnItemClickListener {
    private lateinit var binding: FragmentTurnoverBinding
    private lateinit var historyTabAdapter: HistoryTabAdapter
    private lateinit var turnOverAdapter: TurnOverAdapter
    private var homeTabList = arrayListOf<HomeTab>()
    private val transactionViewModel: TransactionViewModel by viewModels()
    private var turnoverList: MutableList<TurnoverDataItem?> = mutableListOf()
    private var status = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTurnoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setTabAdapter()
        setTabData()
        setTurnOverAdapter()
        observerUsersPromotions()
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        historyTabAdapter = HistoryTabAdapter()
        historyTabAdapter.setOnTabListener(this@TurnoverFragment)
        binding.recyclerViewTab.adapter = historyTabAdapter
    }

    override fun onTabItemClickListener(item: HomeTab?) {
        if (item?.text == "Running") status = 1
        if (item?.text == "Completed") status = 2
        observerUsersPromotions()
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(R.drawable.ic_running, "Running"))
        homeTabList.add(HomeTab(R.drawable.ic_completed, "Completed"))
        historyTabAdapter.setTabData(homeTabList)
    }

    private fun setTurnOverAdapter() {
        binding.rvTurnOver.layoutManager = LinearLayoutManager(requireContext())
        turnOverAdapter = TurnOverAdapter()
        turnOverAdapter.setOnTabListener(this@TurnoverFragment)
        binding.rvTurnOver.adapter = turnOverAdapter
    }

    override fun onDetailClickListener(position: Int) {
        val dialog = DialogTurnoverFragment(turnoverList[position])
        dialog.isCancelable = false
        dialog.show(childFragmentManager, "turnover")
    }

    private fun observerUsersPromotions() {
        transactionViewModel.getUsersPromotions(pageNo = 1, status = status)
        transactionViewModel.userPromotions.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    turnOverAdapter.setTurnOverData(it.data?.getData())
                    it.data?.getData()?.let { it1 -> turnoverList.addAll(it1) }
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                }
            }
        }
    }
}