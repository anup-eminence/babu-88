package com.sona.babu88.ui.home

import MySharedPreferences
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.databinding.FragmentHomeBinding
import com.sona.babu88.model.FishingList
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
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.OnAccountListener
import com.sona.babu88.util.autoScroll
import com.sona.babu88.util.hide
import com.sona.babu88.util.onBackPress
import com.sona.babu88.util.provideViewPagerList
import com.sona.babu88.util.replaceFragment
import com.sona.babu88.util.show
import com.sona.babu88.util.showExitAlert

class HomeFragment : Fragment(), HomeTabAdapter.OnTabItemClickListener,
    FeaturedGamesAdapter.OnFeaturedItemClickListener, CasinoGamesAdapter.OnCasinoItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterVP: ViewPagerAdapter
    private lateinit var homeTabAdapter: HomeTabAdapter
    private lateinit var casinoGamesAdapter: CasinoGamesAdapter
    private lateinit var featuredGamesAdapter: FeaturedGamesAdapter
    private var homeTabList = arrayListOf<HomeTab>()
    private var fishingList = arrayListOf<FishingList>()
    private var featuredGameList = arrayListOf<FeaturedGameList>()
    private var listener: OnAccountListener? = null
    private var userData : UserData?=null

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
        setFeaturedGamesAdapter()
        setFeaturedGamesData()
        setCasinoGamesAdapter()
        setCasinoGamesData()
        homeTabAdapter.setTabData(homeTabList)
        initView()
        setOnClickListener()
        view.onBackPress { requireContext().showExitAlert(positiveClick = { activity?.finish() }) }

        if (MySharedPreferences.readString(AppConstant.TOKEN,"").isNullOrEmpty().not()){
            binding.clHome.show()
        } else {
            binding.clHome.hide()
        }
    }

    private fun initView() {
        userData = MySharedPreferences.getSavedObjectFromPreference(requireContext(), AppConstant.USER_DATA)
        binding.tvMarquee.isSelected = true
        val hotGamesFragment = HotGamesFragment()
        this.replaceFragment(binding.container.id, hotGamesFragment, false, "hot_games")
        binding.userName.text = userData?.user?.userName
    }

    private fun setOnClickListener() {
        binding.apply {
            imgBettingPass.setOnClickListener { listener?.onAccountClick("Betting Pass") }
            imgRewards.setOnClickListener { listener?.onAccountClick("Rewards") }
            imgBetHistory.setOnClickListener { listener?.onAccountClick("Bet History") }
            imgWithdrawal.setOnClickListener { listener?.onAccountClick("Withdrawal") }
        }
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
        homeTabList.clear()
        homeTabList.add(HomeTab(R.drawable.ic_hot_home, "Hot Games"))
        homeTabList.add(HomeTab(R.drawable.ic_cricket, "Cricket"))
        homeTabList.add(HomeTab(R.drawable.ic_casino, "Casino"))
        homeTabList.add(HomeTab(R.drawable.ic_slot, "Slot"))
        homeTabList.add(HomeTab(R.drawable.ic_table, "Table"))
        homeTabList.add(HomeTab(R.drawable.ic_sports, "Sports"))
        homeTabList.add(HomeTab(R.drawable.ic_fishing, "Fishing"))
        homeTabList.add(HomeTab(R.drawable.ic_crash, "Crash"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeTabList.clear()
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

    private fun setFeaturedGamesAdapter() {
        binding.featuredRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        featuredGamesAdapter = FeaturedGamesAdapter()
        featuredGamesAdapter.setOnFeaturedItemClickListener(this@HomeFragment)
        binding.featuredRecyclerView.adapter = featuredGamesAdapter
    }

    private fun setFeaturedGamesData() {
        featuredGameList.add(FeaturedGameList(R.drawable.img_fea_game1))
        featuredGameList.add(FeaturedGameList(R.drawable.img_fea_game2))
        featuredGameList.add(FeaturedGameList(R.drawable.img_fea_game3))
        featuredGameList.add(FeaturedGameList(R.drawable.img_fea_game4))
        featuredGamesAdapter.setFeaturedGamesData(featuredGameList)
    }

    override fun onFeaturedItemClickListener() {

    }

    private fun setCasinoGamesAdapter() {
        binding.casinoRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        casinoGamesAdapter = CasinoGamesAdapter()
        casinoGamesAdapter.setOnCasinoItemClickListener(this@HomeFragment)
        binding.casinoRecyclerView.adapter = casinoGamesAdapter
    }

    private fun setCasinoGamesData() {
        fishingList.add(FishingList(R.drawable.img_home_1))
        fishingList.add(FishingList(R.drawable.img_home_2))
        fishingList.add(FishingList(R.drawable.img_home_3))
        fishingList.add(FishingList(R.drawable.img_home_4))
        fishingList.add(FishingList(R.drawable.img_home_5))
        fishingList.add(FishingList(R.drawable.img_home_6))
        fishingList.add(FishingList(R.drawable.img_home_7))
        fishingList.add(FishingList(R.drawable.img_home_8))
        fishingList.add(FishingList(R.drawable.img_home_9))
        fishingList.add(FishingList(R.drawable.img_home_10))
        fishingList.add(FishingList(R.drawable.img_home_11))
        fishingList.add(FishingList(R.drawable.img_home_12))
        casinoGamesAdapter.setCasinoGamesData(fishingList)
    }

    override fun onCasinoItemClickListener() {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAccountListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnAccountListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}