package com.sona.babu88.ui.refer_earn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sona.babu88.databinding.FragmentReferEarnBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class ReferEarnFragment : Fragment() {
    private lateinit var binding: FragmentReferEarnBinding
    private lateinit var referEarnVPAdapter: ReferEarnVPAdapter
    private var toggle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReferEarnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPagerAdapter()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.clTop.setOnClickListener {
            toggle = !toggle
            if (toggle) {
                binding.tvReferralText.show()
                binding.btnDown.rotation = 180F
            } else {
                binding.tvReferralText.hide()
                binding.btnDown.rotation = 360F
            }
        }
    }

    private fun setViewPagerAdapter() {
        referEarnVPAdapter = ReferEarnVPAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = referEarnVPAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "My Referrals"
                1 -> tab.text = "My Commission"
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