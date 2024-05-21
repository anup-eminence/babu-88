package com.sona.babu88.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentBetHistoryBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.ui.home.HomeTabAdapter

class BetHistoryFragment : Fragment() {
    private lateinit var binding: FragmentBetHistoryBinding
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
        binding = FragmentBetHistoryBinding.inflate(inflater, container, false)
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
//        homeTabAdapter.setOnTabListener(this@BetHistoryFragment)
        binding.recyclerViewTab.adapter = homeTabAdapter
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(R.drawable.ic_all, "All"))
        homeTabList.add(HomeTab(R.drawable.ic_cricket, "Cricket"))
        homeTabList.add(HomeTab(R.drawable.ic_casino, "Casino"))
        homeTabList.add(HomeTab(R.drawable.ic_slot, "Slot Games"))
        homeTabList.add(HomeTab(R.drawable.ic_table, "Table Games"))
        homeTabList.add(HomeTab(R.drawable.ic_sports, "Sportsbook"))
        homeTabList.add(HomeTab(R.drawable.ic_fishing, "Fishing"))
        homeTabList.add(HomeTab(R.drawable.ic_crash, "Crash"))
        homeTabAdapter.setTabData(homeTabList)
    }
}