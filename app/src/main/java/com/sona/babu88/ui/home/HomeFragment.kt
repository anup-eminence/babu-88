package com.sona.babu88.ui.home

import MySharedPreferences
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.data.viewmodel.HomeViewModel
import com.sona.babu88.data.socket.SocketHandler
import com.sona.babu88.data.socket.SocketListener
import com.sona.babu88.data.socket.SocketUrl
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
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.OnAccountListener
import com.sona.babu88.util.autoScroll
import com.sona.babu88.util.hide
import com.sona.babu88.util.onBackPress
import com.sona.babu88.util.provideViewPagerList
import com.sona.babu88.util.replaceFragment
import com.sona.babu88.util.show
import com.sona.babu88.util.showExitAlert
import com.sona.babu88.util.showToast

class HomeFragment : Fragment(), HomeTabAdapter.OnTabItemClickListener,
    FeaturedGamesAdapter.OnFeaturedItemClickListener, CasinoGamesAdapter.OnCasinoItemClickListener,
    SocketListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterVP: ViewPagerAdapter
    private lateinit var homeTabAdapter: HomeTabAdapter
    private lateinit var casinoGamesAdapter: CasinoGamesAdapter
    private lateinit var featuredGamesAdapter: FeaturedGamesAdapter
    private var homeTabList = arrayListOf<HomeTab>()
    private var listener: OnAccountListener? = null
    private var userData : UserData?=null
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var socket : SocketHandler
    private lateinit var socket2 : SocketHandler
    private val socketEvent1 = "Event/Auto"
    private val socketEvent2 = "BookM/Auto"

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
        socket = SocketHandler()
        socket2 = SocketHandler()
        setViewPager()
        setTabAdapter()
        setTabData()
        observerMessageWebsite()
        setFeaturedGamesAdapter()
        setCasinoGamesAdapter()
        observerSpecialGameList()
        homeTabAdapter.setTabData(homeTabList)
        initView()
        setOnClickListener()
        view.onBackPress { requireContext().showExitAlert(positiveClick = { activity?.finish() }) }

        if (MySharedPreferences.readString(AppConstant.TOKEN,"").isNullOrEmpty().not()){
            binding.clHome.show()
        } else {
            binding.clHome.hide()
        }

        binding.ivRefresh.setOnClickListener {
            /*val d = MyWebSocketClient()
            d.start()*/
           callSocket()
        }

        binding.userName.setOnClickListener {
            requireContext().showToast("clicked")
            socket.removeEventListener(socketEvent1,"33353999")
            socket2.removeEventListener(socketEvent2,"33353999")
        }
    }


    private fun callSocket() {
        socket.setSocket(SocketUrl.Node7)
        socket.establishConnection(this@HomeFragment)
        socket.setSocketEvent(socketEvent1,"33353999")
        callSocket2()
    }

    private fun callSocket2(){
        socket2.setSocket(SocketUrl.Node7)
        socket2.establishConnection(this@HomeFragment)
        socket2.setSocketEvent(socketEvent2,"33353999")
    }

    private fun initView() {
        userData = MySharedPreferences.getSavedObjectFromPreference(requireContext(), AppConstant.USER_DATA)
        val hotGamesFragment = HotGamesFragment()
        this.replaceFragment(binding.container.id, hotGamesFragment, false, "hot_games")
        binding.userName.text = userData?.user?.userName
        binding.amount.text = StringBuilder().append(
            Html.fromHtml(
                userData?.user?.symbol ?: "",
                Html.FROM_HTML_MODE_LEGACY
            )
        ).append(" ").append(String.format("%.2f", userData?.user?.myBalance ?: 0.00))
    }

    private fun setOnClickListener() {
        binding.apply {
            imgBettingPass.setOnClickListener { listener?.onAccountClick("Betting Pass") }
            imgRewards.setOnClickListener { listener?.onAccountClick("Rewards") }
            imgBetHistory.setOnClickListener { listener?.onAccountClick("Bet History") }
            imgWithdrawal.setOnClickListener { listener?.onAccountClick("Withdrawal") }
            textBettingPass.setOnClickListener { imgBettingPass.performClick() }
            textRewards.setOnClickListener { imgRewards.performClick() }
            textBetHistory.setOnClickListener { imgBetHistory.performClick() }
            textWithdrawal.setOnClickListener { imgWithdrawal.performClick() }
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

    override fun onFeaturedItemClickListener() {

    }

    private fun setCasinoGamesAdapter() {
        binding.casinoRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        casinoGamesAdapter = CasinoGamesAdapter()
        casinoGamesAdapter.setOnCasinoItemClickListener(this@HomeFragment)
        binding.casinoRecyclerView.adapter = casinoGamesAdapter
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

    private fun observerMessageWebsite() {
        binding.tvMarquee.isSelected = true
        homeViewModel.getMessageWebsite()

        homeViewModel.messageWebsite.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {}

                is ApiResult.Success -> {
                    val text = StringBuilder()
                    if (it.data?.data?.isNullOrEmpty() == true) {
                        text.append("--            --             --             --                --             --             --             --")
                    }
                    else {
                        it.data?.data?.forEachIndexed { _, item ->
                            text.append("${item?.day} ${item?.month} ${item?.year} ${item?.title}: ${item?.message}       ")
                        }
                    }
                    binding.tvMarquee.text = text
                }

                is ApiResult.Error -> {}
                else -> {}
            }
        }
    }

    override fun onSocketErrorOccured(error: String) {
       // TODO("Not yet implemented")
    }

    override fun onSocketDisConnected() {
       // TODO("Not yet implemented")
    }

    override fun onSocketConnected() {
        //TODO("Not yet implemented")
    }

    override fun onSocketResponseReceived(eventName: String, data: Any) {
        when(eventName){
            socketEvent1 -> {

            }
            socketEvent2 -> {

            }
        }
        println(">>>>>socket onSocketResponseReceived$eventName $data")
    }

    private fun observerSpecialGameList() {
        homeViewModel.getSpecialGameList()
        homeViewModel.specialGameList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {}
                is ApiResult.Success -> {
                    if (it.data?.matchImages?.isNullOrEmpty() == false) {
                        binding.tvFeaturedGames.show()
                        binding.featuredRecyclerView.show()
                        featuredGamesAdapter.setFeaturedGamesData(it.data.matchImages)
                    } else {
                        binding.tvFeaturedGames.hide()
                        binding.featuredRecyclerView.hide()
                    }
                    if (it.data?.casinoImages?.isNullOrEmpty() == false) {
                        binding.tvCasinoGames.show()
                        binding.casinoRecyclerView.show()
                        casinoGamesAdapter.setCasinoGamesData(it.data.casinoImages)
                    } else {
                        binding.tvCasinoGames.hide()
                        binding.casinoRecyclerView.hide()
                    }
                }

                is ApiResult.Error -> {}
                else -> {}
            }
        }
    }
}