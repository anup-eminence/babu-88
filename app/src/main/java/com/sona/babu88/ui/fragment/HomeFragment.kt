package com.sona.babu88.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentHomeBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.ui.adapter.ViewPagerAdapter
import com.sona.babu88.ui.casino.CasinoFragment
import com.sona.babu88.ui.crash.CrashFragment
import com.sona.babu88.ui.cricket.CricketFragment
import com.sona.babu88.ui.fishing.FishingFragment
import com.sona.babu88.ui.hotgames.HotGamesFragment
import com.sona.babu88.ui.slot.SlotFragment
import com.sona.babu88.ui.sports.SportsFragment
import com.sona.babu88.ui.table.TableFragment
import com.sona.babu88.util.autoScroll
import com.sona.babu88.util.provideViewPagerList
import com.sona.babu88.util.replaceFragment

class HomeFragment : Fragment(), HomeTabAdapter.OnTabItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterVP: ViewPagerAdapter
    private lateinit var homeTabAdapter: HomeTabAdapter
    private var homeTabList = arrayListOf<HomeTab>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterVP = ViewPagerAdapter(provideViewPagerList())
        setViewPager()
        setTabAdapter()
        setTabData()
        homeTabAdapter.setTabData(homeTabList)
        initView()
    }

    private fun initView() {
        binding.tvMarquee.isSelected = true
        val hotGamesFragment = HotGamesFragment()
        this.replaceFragment(binding.container.id, hotGamesFragment, false, "hot_games")
    }

    private fun setViewPager() {
        binding.viewpager.adapter = adapterVP

        /**
         * Start automatic scrolling with an
         * interval of 3 seconds.
         */
        binding.viewpager.autoScroll(3000)
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeTabAdapter = HomeTabAdapter()
        homeTabAdapter.setOnTabListener(this@HomeFragment)
        binding.recyclerViewTab.adapter = homeTabAdapter
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(R.drawable.ic_hot_home, "Hot Games"))
        homeTabList.add(HomeTab(R.drawable.ic_cricket, "Cricket"))
        homeTabList.add(HomeTab(R.drawable.ic_casino, "Casino"))
        homeTabList.add(HomeTab(R.drawable.ic_slot, "Slot"))
        homeTabList.add(HomeTab(R.drawable.ic_table, "Table"))
        homeTabList.add(HomeTab(R.drawable.ic_sports, "Sports"))
        homeTabList.add(HomeTab(R.drawable.ic_fishing, "Fishing"))
        homeTabList.add(HomeTab(R.drawable.ic_crash, "Crash"))
    }

    override fun onTabItemClickListener(position: Int) {
        when (position) {
            0 -> {
                val hotGamesFragment = HotGamesFragment()
                this.replaceFragment(binding.container.id, hotGamesFragment, false, "hot_games")
            }

            1 -> {
                val cricketFragment = CricketFragment()
                this.replaceFragment(binding.container.id, cricketFragment, false, "cricket")
            }

            2 -> {
                val casinoFragment = CasinoFragment()
                this.replaceFragment(binding.container.id, casinoFragment, false, "casino")
            }

            3 -> {
                val slotFragment = SlotFragment()
                this.replaceFragment(binding.container.id, slotFragment, false, "slot")
            }

            4 -> {
                val tableFragment = TableFragment()
                this.replaceFragment(binding.container.id, tableFragment, false, "table")
            }

            5 -> {
                val sportsFragment = SportsFragment()
                this.replaceFragment(binding.container.id, sportsFragment, false, "sports")
            }

            6 -> {
                val fishingFragment = FishingFragment()
                this.replaceFragment(binding.container.id, fishingFragment, false, "fishing")
            }

            7 -> {
                val crashFragment = CrashFragment()
                this.replaceFragment(binding.container.id, crashFragment, false, "crash")
            }
        }
    }
}