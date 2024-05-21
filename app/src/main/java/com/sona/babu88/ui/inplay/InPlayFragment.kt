package com.sona.babu88.ui.inplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentInPlayBinding

class InPlayFragment : Fragment() {
    private lateinit var binding: FragmentInPlayBinding
    private lateinit var inPlayViewPager: InPlayViewPager
    private var currentViewPagerItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setViewPagerAdapter()
        binding.viewPager.isUserInputEnabled = false
    }

    private fun setViewPagerAdapter() {
        currentViewPagerItem = binding.viewPager.currentItem
        inPlayViewPager = InPlayViewPager(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = inPlayViewPager
        binding.viewPager.setCurrentItem(currentViewPagerItem, false)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "In-Play"
                1 -> tab.text = "Today"
                2 -> tab.text = "Tomorrow"
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { binding.viewPager.currentItem = it }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.position?.let { binding.viewPager.currentItem = it }
            }
        })
    }
}