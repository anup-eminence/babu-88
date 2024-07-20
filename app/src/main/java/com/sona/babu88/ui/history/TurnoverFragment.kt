package com.sona.babu88.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentTurnoverBinding
import com.sona.babu88.model.HomeTab

class TurnoverFragment : Fragment() {
    private lateinit var binding: FragmentTurnoverBinding
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
        binding = FragmentTurnoverBinding.inflate(inflater, container, false)
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
        historyTabAdapter = HistoryTabAdapter()
//        homeTabAdapter.setOnTabListener(this@TurnoverHistoryFragment)
        binding.recyclerViewTab.adapter = historyTabAdapter
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(R.drawable.ic_running, "Running"))
        homeTabList.add(HomeTab(R.drawable.ic_completed, "Completed"))
        historyTabAdapter.setTabData(homeTabList)
    }
}