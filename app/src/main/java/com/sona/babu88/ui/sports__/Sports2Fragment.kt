package com.sona.babu88.ui.sports__

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentSports2Binding

class Sports2Fragment : Fragment() {
    private lateinit var binding: FragmentSports2Binding
    private lateinit var sportViewPagerAdapter: SportViewPagerAdapter
    private var currentViewPagerItem = 0
    private var tabSelectedTextColor = 0
    private var tabUnselectedTextColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSports2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tabSelectedTextColor = ContextCompat.getColor(requireContext(), R.color.text_yellow)
        tabUnselectedTextColor = ContextCompat.getColor(requireContext(), R.color.black)
        setViewPagerAdapter()
        binding.viewPager.isUserInputEnabled = false
    }

    private fun setViewPagerAdapter() {
        currentViewPagerItem = binding.viewPager.currentItem
        sportViewPagerAdapter =
            SportViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = sportViewPagerAdapter
        binding.viewPager.setCurrentItem(currentViewPagerItem, false)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val customTab = layoutInflater.inflate(R.layout.custom_tab_layout, null)
            val tabText = customTab.findViewById<TextView>(R.id.tab_text)
            val tabIcon = customTab.findViewById<ImageView>(R.id.tab_icon)
            when (position) {
                0 -> {
                    tabText.text = getString(R.string.cricket)
                    tabIcon.setImageResource(R.drawable.ic_cricket_black)
                }

                1 -> {
                    tabText.text = getString(R.string.soccer)
                    tabIcon.setImageResource(R.drawable.ic_soccer_black)
                }

                2 -> {
                    tabText.text = getString(R.string.tennis)
                    tabIcon.setImageResource(R.drawable.ic_tennis_black)
                }
            }
            tab.customView = customTab
        }.attach()

        binding.tabLayout.getTabAt(0)?.view?.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_top_rounded_shape)
        binding.tabLayout.getTabAt(0)?.customView?.apply {
            findViewById<TextView>(R.id.tab_text)?.setTextColor(tabSelectedTextColor)
            findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_cricket_active)
        }

        binding.viewPager.offscreenPageLimit = 2

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { binding.viewPager.currentItem = it }
                tab?.customView?.apply {
                    findViewById<TextView>(R.id.tab_text)?.setTextColor(tabSelectedTextColor)
                    when (tab.position) {
                        0 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_cricket_active)
                        1 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_soccer_active)
                        2 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_tennis_active)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.background = null
                tab?.customView?.apply {
                    findViewById<TextView>(R.id.tab_text)?.setTextColor(tabUnselectedTextColor)
                    when (tab.position) {
                        0 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_cricket_black)
                        1 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_soccer_black)
                        2 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_tennis_black)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.position?.let { binding.viewPager.currentItem = it }
                tab?.customView?.apply {
                    findViewById<TextView>(R.id.tab_text)?.setTextColor(tabSelectedTextColor)
                    when (tab.position) {
                        0 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_cricket_active)
                        1 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_soccer_active)
                        2 -> findViewById<ImageView>(R.id.tab_icon).setImageResource(R.drawable.ic_tennis_active)
                    }
                }
            }
        })
    }
}