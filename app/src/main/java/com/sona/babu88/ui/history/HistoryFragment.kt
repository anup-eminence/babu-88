package com.sona.babu88.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyVPAdapter: HistoryVPAdapter
    private var currentViewPagerItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPagerAdapter()
        binding.viewPager.isUserInputEnabled = false
    }

    private fun setViewPagerAdapter() {
        currentViewPagerItem = binding.viewPager.currentItem
        historyVPAdapter = HistoryVPAdapter(
            requireActivity().supportFragmentManager, lifecycle
        )
        binding.viewPager.adapter = historyVPAdapter
        binding.viewPager.setCurrentItem(currentViewPagerItem, false)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Bet History"
                1 -> tab.text = "TurnOver"
                2 -> tab.text = "Wallet History"
            }
        }.attach()

        binding.tabLayout.getTabAt(0)?.view?.background = ContextCompat.getDrawable(
            requireContext(), R.drawable.bg_grey_shape
        )

        binding.viewPager.offscreenPageLimit = 2
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { binding.viewPager.currentItem = it }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.background = null
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.position?.let { binding.viewPager.currentItem = it }
            }
        })
    }
}