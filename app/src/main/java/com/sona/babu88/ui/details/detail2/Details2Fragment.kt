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
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.GetUserMatchDetailResponse
import com.sona.babu88.api.model.response.PSelection
import com.sona.babu88.api.model.response.PreMatchMarket
import com.sona.babu88.data.viewmodel.SportsViewModel
import com.sona.babu88.databinding.FragmentDetails2Binding
import com.sona.babu88.util.hide
import com.sona.babu88.util.hideProgress1
import com.sona.babu88.util.show
import com.sona.babu88.util.showProgress1
import com.sona.babu88.util.showToast

class Details2Fragment : Fragment(), DetailsHorizontalAdapter.OnTabClickListener {
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
        setUpAdapter()
        setUpTabAdapter()
        setDataFancy()
        observerMatchDetails()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setOnClickListener() {
        binding.apply {
            tvFancyBet.setOnClickListener {
                tvFancyBet.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg3))
                recyclerView.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.bg3
                    )
                )
                tvPremiumCricket.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.bg_color
                    )
                )
                setDataFancy()
            }
            tvPremiumCricket.setOnClickListener {
                tvPremiumCricket.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.bg5
                    )
                )
                recyclerView.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.bg5
                    )
                )
                setDataPremium()
            }
            btnClose.setOnClickListener { view.hide() }
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
                setTextViewSelected(root.context, binding.tvMatchOdds, R.color.black, R.color.text_yellow)
                layout1.tvMatchTitle.text = getUserMatchDetailResponse?.team1
                layout1.tvMatchTitle2.text = getUserMatchDetailResponse?.team2
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
        detailList.clear()
        detailList.add(DetailsList("All"))
        detailList.add(DetailsList("Normal"))
        detailList.add(DetailsList("Fancy1"))
        detailList.add(DetailsList("Over"))
        detailList.add(DetailsList("Ball by Ball"))
        detailList.add(DetailsList("Khadda"))
        detailList.add(DetailsList("Lottery"))
        detailList.add(DetailsList("Odd/Even"))
        detailsAdapter.setData(detailList)
    }

    private fun setDataPremium() {
        detailList.clear()
        detailList.add(DetailsList("All"))
        detailList.add(DetailsList("Popular"))
        detailList.add(DetailsList("Match"))
        detailList.add(DetailsList("Innings"))
        detailList.add(DetailsList("Over"))
        detailList.add(DetailsList("Batsman"))
        detailList.add(DetailsList("Partnership"))
        detailList.add(DetailsList("Group"))
        detailList.add(DetailsList("Range"))
        detailList.add(DetailsList("Head to Head"))
        detailList.add(DetailsList("Player"))
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
            tvMatchTitle.text = pList[0].selectionName
            tvMatchTitle2.text = pList[1].selectionName
        }
    }

    private fun setTextViewSelected(context: Context, textView: TextView, backgroundColor: Int, textColor: Int) {
        textView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, backgroundColor))
        textView.setTextColor(ContextCompat.getColor(context, textColor))
    }
}