package com.sona.babu88.ui.details.detail2

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.DetailTossResponse
import com.sona.babu88.api.model.response.DetailsBookMakerResponse
import com.sona.babu88.api.model.response.DetailsResponse
import com.sona.babu88.api.model.response.DiamondItem
import com.sona.babu88.api.model.response.EventsFancy
import com.sona.babu88.api.model.response.FancyResponse
import com.sona.babu88.api.model.response.GetUserMatchDetailResponse
import com.sona.babu88.api.model.response.PSelection
import com.sona.babu88.api.model.response.PreMatchMarket
import com.sona.babu88.api.model.response.PremiumFancyResponse
import com.sona.babu88.data.socket.SocketHandler
import com.sona.babu88.data.socket.SocketListener
import com.sona.babu88.data.socket.SocketUrl
import com.sona.babu88.data.viewmodel.SportsViewModel
import com.sona.babu88.databinding.FragmentDetails2Binding
import com.sona.babu88.util.hide
import com.sona.babu88.util.hideProgress1
import com.sona.babu88.util.show
import com.sona.babu88.util.showProgress1
import com.sona.babu88.util.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class Details2Fragment : Fragment(), DetailsHorizontalAdapter.OnTabClickListener, SocketListener, DetailsFancyAdapter.OnFancyItemClickListener,
DetailsPremiumAdapter.OnItemClickListener {
    private lateinit var binding: FragmentDetails2Binding
    private lateinit var detailsAdapter: DetailsAdapter
    private val detailList = arrayListOf<DetailsList>()
    private val sportsViewModel: SportsViewModel by viewModels()
    private lateinit var detailsHorizontalAdapter: DetailsHorizontalAdapter
    private var preMarketList = arrayListOf<PreMatchMarket>()
    private var getUserMatchDetailResponse: GetUserMatchDetailResponse? = null
    private var detailsResponse: DetailsResponse? = null
    private var detailsBookMakerResponse: DetailsBookMakerResponse? = null
    private var detailTossResponse: DetailTossResponse? = null
    private lateinit var detailsFancyAdapter: DetailsFancyAdapter
    private lateinit var detailsPremiumAdapter: DetailsPremiumAdapter
    private var fancyResponse: FancyResponse? = null
    private var premiumFancyResponse: PremiumFancyResponse? = null
    private lateinit var socket: SocketHandler
    private lateinit var socket2: SocketHandler
    private lateinit var socket3: SocketHandler
    private lateinit var socket4: SocketHandler
    private lateinit var socketPremium: SocketHandler
    private val socketEvent1 = "Event/Auto"
    private val socketEvent2 = "BookM/Auto"
    private val socketToss = "Toss/Auto"
    private val socketFancy = "Fancy/Auto"
    private val socketPRMFancy = "PRMFancy/Auto"
    private lateinit var popupWindow: PopupWindow
    private var eventId = "33418775"

    private fun getPSelectionList(pos: Int): List<PSelection> {
        return preMarketList[pos].getPSelectionList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            eventId = it.getString("id").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetails2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        socket = SocketHandler()
        socket2 = SocketHandler()
        socket3 = SocketHandler()
        socket4 = SocketHandler()
        socketPremium = SocketHandler()
        setUpAdapter()
        setUpTabAdapter()
        setUpFancyAdapter()
        setUpPremiumAdapter()
        setDataFancy()
        observerMatchDetails()
    }

    override fun onResume() {
        super.onResume()
        callSocket(socket, socketEvent1, eventId)
        callSocket(socket2, socketEvent2, eventId)
        callSocket(socket3, socketToss, eventId)
        callSocket(socket4, socketFancy, eventId)
        callSocketPRM(socketPremium, socketPRMFancy, eventId)
    }

    override fun onPause() {
        super.onPause()
        destroySocket()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setOnClickListener() {
        binding.apply {
            premimum1.root.setOnClickListener {
                rvFancy.visibility = View.VISIBLE
                rvPremium.visibility = View.GONE
                topLayout2.hide()
                topLayout.show()
                recyclerView.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.bg3
                    )
                )
                setDataFancy()
            }

            premimum.root.setOnClickListener {
                rvPremium.visibility = View.VISIBLE
                rvFancy.visibility = View.GONE
                topLayout2.show()
                topLayout.hide()
                recyclerView.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.bg5
                    )
                )
                setDataPremium()
            }


            btnClose.setOnClickListener {
                view.hide()
                btnClose.hide()
            }

            layout1.cl1.setOnClickListener { handleOddsLayoutClick(0, true, R.color.bg_bet1) }
            layout1.cl2.setOnClickListener { handleOddsLayoutClick(0, false, R.color.bg_bet2) }
            layout1.cl3.setOnClickListener { handleOddsLayoutClick(1, true, R.color.bg_bet1) }
            layout1.cl4.setOnClickListener { handleOddsLayoutClick(1, false, R.color.bg_bet2) }

            layout2.cl1.setOnClickListener { handleBookMakerLayoutClick(1, true, R.color.bg_bet1) }
            layout2.cl2.setOnClickListener { handleBookMakerLayoutClick(1, false, R.color.bg_bet2) }
            layout2.cl3.setOnClickListener { handleBookMakerLayoutClick(0, true, R.color.bg_bet1) }
            layout2.cl4.setOnClickListener { handleBookMakerLayoutClick(0, false, R.color.bg_bet2) }

            tvMatchOdds.setOnClickListener {
                layout1.root.show()
                layoutMatchOdds.root.hide()
                detailsHorizontalAdapter.selectedPosition = -1
                detailsHorizontalAdapter.notifyDataSetChanged()
                setTextViewSelected(
                    root.context,
                    binding.tvMatchOdds,
                    R.color.black,
                    R.color.text_yellow
                )
                layout1.tvMatchTitle.text = getUserMatchDetailResponse?.team1
                layout1.tvMatchTitle2.text = getUserMatchDetailResponse?.team2
            }

            feature.rlQues.setOnClickListener {
                DetailDialogFragment().show(childFragmentManager, "DetailDialogFragment")
            }

            layout2.ivI.setOnClickListener {
                showPopup(it)
            }
        }
    }

    private fun handleOddsLayoutClick(
        index: Int,
        isBack: Boolean,
        color: Int
    ) {
        if (detailsResponse?.data?.isNullOrEmpty() == false && detailsResponse?.data?.get(0)?.runners?.isNullOrEmpty() == false) {
            val runner = detailsResponse?.data?.get(0)?.runners?.get(index)
            if (runner != null) {
                val price = if (isBack) {
                    runner.ex?.availableToBack?.getOrNull(0)?.price
                } else {
                    runner.ex?.availableToLay?.getOrNull(0)?.price
                }
                showLayoutBet(color, (price ?: "").toString())
            }
        }
    }

    private fun handleBookMakerLayoutClick(
        index: Int,
        isBack: Boolean,
        color: Int
    ) {
        if (detailsBookMakerResponse?.data?.isNullOrEmpty() == false) {
            val bm = detailsBookMakerResponse?.data?.get(0)?.bm1?.getOrNull(index)
            if (bm?.s == "ACTIVE") {
                val price = if (isBack) bm.b1 else bm.l1
                showLayoutBet(color, price ?: "")
            }
        }
    }

    private fun setUpAdapter() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        detailsAdapter = DetailsAdapter()
//        detailsAdapter.setOnItemClickListener(this@Details2Fragment)
        binding.recyclerView.adapter = detailsAdapter
    }

    private fun setDataFancy() {
        val fancyItems = listOf(
            "All",
            "Normal",
            "Fancy1",
            "Over",
            "Ball by Ball",
            "Khadda",
            "Lottery",
            "Odd/Even"
        )
        setData(fancyItems)
    }

    private fun setDataPremium() {
        val premiumItems = listOf(
            "All",
            "Popular",
            "Match",
            "Innings",
            "Over",
            "Batsman",
            "Partnership",
            "Group",
            "Range",
            "Head to Head",
            "Player"
        )
        setData(premiumItems)
    }

    private fun setData(items: List<String>) {
        detailList.clear()
        items.forEach { item ->
            detailList.add(DetailsList(item))
        }
        detailsAdapter.setData(detailList)
    }

    private fun showLayoutBet(color: Int, odds: String) {
        val fragment = BetDialogFragment().apply {
            arguments = Bundle().apply {
                putInt("color", color)
                putString("odds", odds)
            }
            isCancelable = false
        }
        fragment.show(childFragmentManager, "BetDialogFragment")
    }

    private fun observerMatchDetails() {
        sportsViewModel.getUserMatchDetail(
            eventId = eventId
        )

        sportsViewModel.userMatchDetail.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress1()
                }

                is ApiResult.Success -> {
                    this.hideProgress1()
                    binding.apply {
                        layout1.tvMatchTitle.text = it.data?.team1
                        layout1.tvMatchTitle2.text = it.data?.team2
                        layout2.tvMatchTitle.text = it.data?.team1
                        layout2.tvMatchTitle2.text = it.data?.team2
                        layoutToss.tvMatchTitle.text = it.data?.team1
                        layoutToss.tvMatchTitle2.text = it.data?.team2
                    }
                    println("> getUserMatchDetail >>>>>> ${it.data}")
                    preMarketList.clear()
                    it.data?.preMatchMarketList?.let { it1 -> preMarketList.addAll(it1) }
                    detailsHorizontalAdapter.setData(it.data?.preMatchMarketList)
                    getUserMatchDetailResponse = it.data
                }

                is ApiResult.Error -> {
                    this.hideProgress1()
                    println("> getUserMatchDetail >>>>>> ${it.message}")
                    requireContext().showToast(it.message.toString())
                }
            }
        }
    }

    private fun setUpTabAdapter() {
        binding.recyclerViewOdds.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        detailsHorizontalAdapter = DetailsHorizontalAdapter()
        detailsHorizontalAdapter.setOnItemClickListener(this@Details2Fragment)
        binding.recyclerViewOdds.adapter = detailsHorizontalAdapter
    }

    override fun onTabClickListener(selectedPosition: Int) {
        val pList = getPSelectionList(selectedPosition)
        binding.layout1.root.hide()
        binding.layoutMatchOdds.root.show()
        binding.layoutMatchOdds.apply {
            setTextViewSelected(root.context, binding.tvMatchOdds, R.color.bg_color, R.color.white)
            tvTitle.text = preMarketList[0].name
            tvMatchTitle.text = pList[0].selectionName
            tvMatchTitle2.text = pList[1].selectionName
        }
    }

    private fun setTextViewSelected(
        context: Context,
        textView: TextView,
        backgroundColor: Int,
        textColor: Int
    ) {
        textView.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(context, backgroundColor))
        textView.setTextColor(ContextCompat.getColor(context, textColor))
    }

    private fun callSocketPRM(socket: SocketHandler, socketEvent: String, eventId: String){
        socket.setSocket(SocketUrl.CricketPremium)
        socket.establishConnection(this@Details2Fragment)
        socket.setSocketEvent(socketEvent, eventId)
    }

    private fun callSocket(socket: SocketHandler, socketEvent: String, eventId: String) {
        socket.setSocket(SocketUrl.Node7)
        socket.establishConnection(this@Details2Fragment)
        socket.setSocketEvent(socketEvent, eventId)
    }

    override fun onSocketErrorOccured(error: String) {
        println(">>>>>error Occurred $error")
    }

    override fun onSocketDisConnected() {
        println(">>>>>socket Disconnected")
    }

    override fun onSocketConnected() {
        println(">>>>>socket onSocketConnected")
    }

    override fun onSocketResponseReceived(eventName: String, data: Any) {
        val json = Json { ignoreUnknownKeys = true }
        when (eventName) {
            socketEvent1 -> {
                detailsResponse = json.decodeFromString<DetailsResponse>(data.toString())
                setOddsData()
            }

            socketEvent2 -> {
                detailsBookMakerResponse =
                    json.decodeFromString<DetailsBookMakerResponse>(data.toString())
                setBookmakerData()
            }

            socketToss -> {
                detailTossResponse = json.decodeFromString<DetailTossResponse>(data.toString())
                if (detailTossResponse?.data?.status == "OPEN") {
                    binding.layoutToss.apply {
                        root.show()
                        tvName.text = detailTossResponse?.data?.runnersname ?: ""
                        tvMatchTitle.text = detailTossResponse?.data?.btype ?: ""
                        tvMatchTitle2.text = detailTossResponse?.data?.mtype ?: ""
                    }
                } else binding.layoutToss.root.hide()
            }

            socketFancy -> {
                CoroutineScope(Dispatchers.Main).launch {
                    fancyResponse = json.decodeFromString<FancyResponse>(data.toString())
                    detailsFancyAdapter.setDetailsFancyData(fancyResponse?.diamond, fancyResponse?.events)
                }
            }

            socketPRMFancy -> {
                CoroutineScope(Dispatchers.IO).launch {
                    premiumFancyResponse =
                        json.decodeFromString<PremiumFancyResponse>(data.toString())
                    withContext(Dispatchers.Main) {
                        premiumFancyResponse?.data?.sportsBookMarket?.let {
                            detailsPremiumAdapter.setDetailsPremiumData(
                                it
                            )
                        }
                    }
                }
            }
        }
        println(">>>>>socket onSocketResponseReceived: $eventName $data")
    }

    private fun setOddsData() {
        binding.layout1.apply {
            if (detailsResponse?.data.isNullOrEmpty()) return
            detailsResponse?.data?.get(0)?.apply {
                tvPte.text = StringBuilder().append(getString(R.string.pte)).append(" ")
                    .append(String.format("%.2f", totalMatched))

                if (!runners?.get(0)?.ex?.availableToBack.isNullOrEmpty()) {
                    text1.text =
                        (runners?.get(0)?.ex?.availableToBack?.get(0)?.price ?: "-").toString()
                    text2.text =
                        (runners?.get(0)?.ex?.availableToBack?.get(0)?.size ?: "--").toString()
                }
                if (!runners?.get(0)?.ex?.availableToLay.isNullOrEmpty()) {
                    text3.text =
                        (runners?.get(0)?.ex?.availableToLay?.get(0)?.price ?: "-").toString()
                    text4.text =
                        (runners?.get(0)?.ex?.availableToLay?.get(0)?.size ?: "--").toString()
                }
                if (!runners?.get(1)?.ex?.availableToBack.isNullOrEmpty()) {
                    text5.text =
                        (runners?.get(1)?.ex?.availableToBack?.get(0)?.price ?: "-").toString()
                    text6.text =
                        (runners?.get(1)?.ex?.availableToBack?.get(0)?.size ?: "--").toString()
                }
                if (!runners?.get(1)?.ex?.availableToLay.isNullOrEmpty()) {
                    text7.text =
                        (runners?.get(1)?.ex?.availableToLay?.get(0)?.price ?: "-").toString()
                    text8.text =
                        (runners?.get(1)?.ex?.availableToLay?.get(0)?.size ?: "--").toString()
                }
            }
        }
    }

    private fun setBookmakerData() {
        binding.layout2.apply {
            if (detailsBookMakerResponse?.data?.isNullOrEmpty() == false) {
                detailsBookMakerResponse?.data?.get(0)?.apply {
                    if (!bm1.isNullOrEmpty()) {
                        text1.text = bm1[1]?.b1
                        text2.text = bm1[1]?.l1
                        text3.text = bm1[0]?.b1
                        text4.text = bm1[0]?.l1
                        if (bm1[1]?.s == "SUSPENDED") tvSuspend1.show() else tvSuspend1.visibility =
                            View.INVISIBLE
                        if (bm1[0]?.s == "ACTIVE") tvSuspend2.visibility =
                            View.INVISIBLE else tvSuspend2.show()
                    }
                }
            }
        }
    }

    private fun setUpFancyAdapter() {
        binding.rvFancy.layoutManager = LinearLayoutManager(requireContext())
        detailsFancyAdapter = DetailsFancyAdapter()
        detailsFancyAdapter.setOnItemClickListener(this@Details2Fragment)
        binding.rvFancy.adapter = detailsFancyAdapter
        binding.rvFancy.isNestedScrollingEnabled = false
    }

    override fun onFancyClickListener(item: DiamondItem?, back: Boolean) {
        if (back) {
            showLayoutBet(
                R.color.bg_bet1,
                item?.b1 ?: ""
            )
        } else {
            showLayoutBet(
                R.color.bg_bet2,
                item?.l1 ?: ""
            )
        }
    }

    override fun onIBtnClickListener(view: View, eventData: EventsFancy?) {

    }

    private fun setUpPremiumAdapter() {
        binding.rvPremium.layoutManager = LinearLayoutManager(requireContext())
        detailsPremiumAdapter = DetailsPremiumAdapter()
        detailsPremiumAdapter.setOnItemClickListener(this@Details2Fragment)
        binding.rvPremium.adapter = detailsPremiumAdapter
        binding.rvPremium.isNestedScrollingEnabled = false
    }

    override fun onItemClickListener(odds: String) {
        showLayoutBet(
            R.color.bg_bet1,
            odds
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        destroySocket()
    }

    private fun destroySocket() {
        socket.removeEventListener(socketEvent1, eventId)
        socket2.removeEventListener(socketEvent2, eventId)
        socket3.removeEventListener(socketToss, eventId)
        socket4.removeEventListener(socketFancy, eventId)
        socketPremium.removeEventListener(socketPRMFancy, eventId)
    }

    private fun showPopup(view: View) {
        val popupView = LayoutInflater.from(requireContext()).inflate(R.layout.popup_layout, null)
        val closeButton = popupView.findViewById<AppCompatImageView>(R.id.iv_dismiss)
        val minMaxText = popupView.findViewById<AppCompatTextView>(R.id.tv_text_min_max)
        if (detailsBookMakerResponse?.events?.markets?.isNullOrEmpty() == false && !detailsBookMakerResponse?.events?.markets?.get(
                0
            )?.limit.isNullOrEmpty()
        ) {
            minMaxText.text =
                StringBuilder().append(
                    detailsBookMakerResponse?.events?.markets?.get(0)?.limit?.get(
                        0
                    )?.preMinStake
                )
                    .append(" / ")
                    .append(detailsBookMakerResponse?.events?.markets?.get(0)?.limit?.get(0)?.preMaxStake)
        }
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }
        popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val x = location[0]
        val y = location[1] - popupView.measuredHeight

        view.post {
            popupWindow.showAtLocation(view, 0, x, y)
        }
    }
}