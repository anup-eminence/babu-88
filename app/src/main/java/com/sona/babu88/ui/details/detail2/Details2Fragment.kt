package com.sona.babu88.ui.details.detail2

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.sona.babu88.api.model.response.DetailsBookMakerResponse
import com.sona.babu88.api.model.response.DetailsResponse
import com.sona.babu88.api.model.response.FancyResponse
import com.sona.babu88.api.model.response.GetUserMatchDetailResponse
import com.sona.babu88.api.model.response.PSelection
import com.sona.babu88.api.model.response.PreMatchMarket
import com.sona.babu88.data.socket.SocketHandler
import com.sona.babu88.data.socket.SocketListener
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
import kotlinx.serialization.json.Json

class Details2Fragment : Fragment(), DetailsHorizontalAdapter.OnTabClickListener, SocketListener {
    private lateinit var binding: FragmentDetails2Binding
    private lateinit var detailsAdapter: DetailsAdapter
    private val detailList = arrayListOf<DetailsList>()
    private var pointClicked = false
    private val handler = Handler(Looper.getMainLooper())
    private val hideRunnable = Runnable {
        binding.layoutBet.root.hide()
    }
    private val sportsViewModel: SportsViewModel by viewModels()
    private var eventId = ""
    private lateinit var detailsHorizontalAdapter: DetailsHorizontalAdapter
    private var preMarketList = arrayListOf<PreMatchMarket>()
    private var getUserMatchDetailResponse: GetUserMatchDetailResponse? = null
    private var detailsResponse: DetailsResponse? = null
    private var detailsBookMakerResponse: DetailsBookMakerResponse? = null
    private lateinit var detailsFancyAdapter: DetailsFancyAdapter
    private lateinit var detailsPremiumAdapter: DetailsPremiumAdapter
    private var fancyResponse: FancyResponse? = null
    private lateinit var socket: SocketHandler
    private lateinit var socket2: SocketHandler
    private lateinit var socket3: SocketHandler
    private lateinit var socket4: SocketHandler
    private val socketEvent1 = "Event/Auto"
    private val socketEvent2 = "BookM/Auto"
    private val socketToss = "Toss/Auto"
    private val socketFancy = "Fancy/Auto"
    private lateinit var popupWindow: PopupWindow

    private fun getPSelectionList(pos: Int): List<PSelection> {
        return preMarketList[pos].getPSelectionList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventId = it.getString("id").toString()
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
    }

    override fun onPause() {
        super.onPause()
        destroySocket()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setOnClickListener() {
        binding.apply {
            premimum1.root.setOnClickListener {
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
            layout1.cl1.setOnClickListener { showLayoutBet(R.color.bg_bet1) }
            layout1.cl2.setOnClickListener { showLayoutBet(R.color.bg_bet2) }
            layout1.cl3.setOnClickListener { showLayoutBet(R.color.bg_bet1) }
            layout1.cl4.setOnClickListener { showLayoutBet(R.color.bg_bet2) }
            setNumberClick()

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

    private fun setNumberClick() {
        binding.layoutBet.apply {
            tvNum1.setOnClickListener {
                tvText.text = ""
                append(tvNum1.text.toString())
            }
            tvNum2.setOnClickListener {
                tvText.text = ""
                append(tvNum2.text.toString())
            }
            tvNum3.setOnClickListener {
                tvText.text = ""
                append(tvNum3.text.toString())
            }
            tvNum4.setOnClickListener {
                tvText.text = ""
                append(tvNum4.text.toString())
            }
            tvNum5.setOnClickListener {
                tvText.text = ""
                append(tvNum5.text.toString())
            }
            tvNum6.setOnClickListener {
                tvText.text = ""
                append(tvNum6.text.toString())
            }

            tv0.setOnClickListener { append("0") }
            tv1.setOnClickListener { append("1") }
            tv2.setOnClickListener { append("2") }
            tv3.setOnClickListener { append("3") }
            tv4.setOnClickListener { append("4") }
            tv5.setOnClickListener { append("5") }
            tv6.setOnClickListener { append("6") }
            tv7.setOnClickListener { append("7") }
            tv8.setOnClickListener { append("8") }
            tv9.setOnClickListener { append("9") }
            tv00.setOnClickListener { append("00") }
            tvPoint.setOnClickListener {
                if (!pointClicked) {
                    append(".")
                    pointClicked = true
                }
            }
            tvBackSpace.setOnClickListener {
                val text = binding.layoutBet.tvText.text
                if (!text.isNullOrEmpty()) {
                    if (text.last() == '.') pointClicked = false
                    binding.layoutBet.tvText.text = text.subSequence(0, text.length - 1)
                }
            }
            btnMinus.setOnClickListener {
                if (tvText.text.toString()
                        .isNotEmpty() && tvText.text.toString() != "0"
                ) updateText(-1)
            }
            btnPlus.setOnClickListener { updateText(1) }
            btnCancel.setOnClickListener { root.hide() }
            btnPlaceBet.setOnClickListener { root.hide() }
        }
    }

    private fun append(num: String) {
        val currentText = binding.layoutBet.tvText.text.toString()
        binding.layoutBet.tvText.text = StringBuilder(currentText).append(num)
        handler.removeCallbacks(hideRunnable)
        handler.postDelayed(hideRunnable, 10000)
    }

    private fun updateText(value: Int) {
        val currentText = binding.layoutBet.tvText.text.toString()
        if (currentText.isNullOrEmpty().not()) {
            try {
                binding.layoutBet.tvText.text = (currentText.toInt() + value).toString()
            } catch (e: NumberFormatException) {
                binding.layoutBet.tvText.text = (currentText.toDouble() + value).toString()
            }
        } else if (currentText.isNullOrEmpty() && value > 0) {
            binding.layoutBet.tvText.text = value.toString()
        }
    }

    private fun showLayoutBet(color: Int) {
        binding.layoutBet.root.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                color
            )
        )
        binding.layoutBet.root.show()
        pointClicked = false
        binding.layoutBet.tvText.text = ""
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

    private fun callSocket(socket: SocketHandler, socketEvent: String, eventId: String) {
        socket.setSocket()
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

            }

            socketFancy -> {
                CoroutineScope(Dispatchers.Main).launch {
                    fancyResponse = json.decodeFromString<FancyResponse>(data.toString())
                    detailsFancyAdapter.setDetailsFancyData(fancyResponse?.diamond)
                }
            }
        }
        println(">>>>>socket onSocketResponseReceived: $eventName $data")
    }

    private fun setOddsData() {
        binding.layout1.apply {
            detailsResponse?.data?.get(0)?.apply {
                if (!runners.isNullOrEmpty()) {
                    tvPte.text = StringBuilder().append(getString(R.string.pte)).append(" ")
                        .append(totalMatched)
                    text1.text = runners[0]?.ex?.availableToBack?.get(0)?.price.toString()
                    text2.text = runners[0]?.ex?.availableToBack?.get(0)?.size.toString()
                    text3.text = runners[0]?.ex?.availableToLay?.get(0)?.price.toString()
                    text4.text = runners[0]?.ex?.availableToLay?.get(0)?.size.toString()
                    text5.text = runners[1]?.ex?.availableToBack?.get(0)?.price.toString()
                    text6.text = runners[1]?.ex?.availableToBack?.get(0)?.size.toString()
                    text7.text = runners[1]?.ex?.availableToLay?.get(0)?.price.toString()
                    text8.text = runners[1]?.ex?.availableToLay?.get(0)?.size.toString()
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
        binding.rvFancy.adapter = detailsFancyAdapter
        binding.rvFancy.isNestedScrollingEnabled = false
    }

    private fun setUpPremiumAdapter() {
        binding.rvPremium.layoutManager = LinearLayoutManager(requireContext())
        detailsPremiumAdapter = DetailsPremiumAdapter()
//        detailsPremiumAdapter.setOnItemClickListener(this@Details2Fragment)
        binding.rvPremium.adapter = detailsPremiumAdapter
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