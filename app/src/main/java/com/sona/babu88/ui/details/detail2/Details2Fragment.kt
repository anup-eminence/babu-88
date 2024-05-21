package com.sona.babu88.ui.details.detail2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentDetails2Binding
import com.sona.babu88.util.hide

class Details2Fragment : Fragment() {
    private lateinit var binding: FragmentDetails2Binding
    private lateinit var detailsAdapter: DetailsAdapter
    private val detailList = arrayListOf<DetailsList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
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
        setDataFancy()
    }

    private fun setOnClickListener() {
        binding.apply {
            tvFancyBet.setOnClickListener {
                tvFancyBet.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg3))
                recyclerView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg3))
                tvPremiumCricket.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg_color))
                setDataFancy()
            }
            tvPremiumCricket.setOnClickListener {
                tvPremiumCricket.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg5))
                recyclerView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg5))
                setDataPremium()
            }
            btnClose.setOnClickListener { view.hide() }
        }
    }

    private fun setUpAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
}