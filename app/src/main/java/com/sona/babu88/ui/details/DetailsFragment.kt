package com.sona.babu88.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentDetailsBinding
import com.sona.babu88.model.DetailsList
import com.sona.babu88.model.HomeTab

class DetailsFragment : Fragment(), DetailsTabAdapter.OnTabItemClickListener,
    DetailsAdapter.OnItemClickListener {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var detailsTabAdapter: DetailsTabAdapter
    private lateinit var detailsAdapter: DetailsAdapter
    private var homeTabList = arrayListOf<HomeTab>()
    private var detailsList = arrayListOf<DetailsList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        setTabAdapter()
        setTabData()
        detailsTabAdapter.setTabData(homeTabList)
        setDetailsAdapter()
        setDetailsData()
        detailsAdapter.setDetailsData(detailsList)

        binding.tvTitle.text = "Table Games"
    }

    private fun setOnClickListener() {
        binding.apply {
            leftArrow.setOnClickListener {  }
            rightArrow.setOnClickListener {  }
        }
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        detailsTabAdapter = DetailsTabAdapter()
        detailsTabAdapter.setOnTabListener(this@DetailsFragment)
        binding.recyclerViewTab.adapter = detailsTabAdapter
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(0, ""))
        homeTabList.add(HomeTab(R.drawable.img_slot_1, "JILI"))
        homeTabList.add(HomeTab(R.drawable.img_slot_2, "KingMaker"))
        homeTabList.add(HomeTab(R.drawable.img_slot_3, "Spade"))
        homeTabList.add(HomeTab(R.drawable.img_slot_3, "JILI"))
        homeTabList.add(HomeTab(R.drawable.img_slot_4, "KingMaker"))
        homeTabList.add(HomeTab(R.drawable.img_slot_5, "Spade"))
        homeTabList.add(HomeTab(R.drawable.img_slot_6, "Spade"))
        homeTabList.add(HomeTab(R.drawable.img_slot_7, "JILI"))
    }

    override fun onTabItemClickListener(position: Int) {

    }

    private fun setDetailsAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        detailsAdapter = DetailsAdapter()
        detailsAdapter.setOnItemClickListener(this@DetailsFragment)
        binding.recyclerView.adapter = detailsAdapter
    }

    private fun setDetailsData() {
        detailsList.add(DetailsList(R.drawable.img_home_1, "7up7down", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_2, "Sic Bo", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_3, "Teen Patti", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_4, "Call Break", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_5, "iRIch Bingo", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_6, "Rummy", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_7, "Dragon & ", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_8, "Ander BAh", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_9, "Poker King", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_10, "AK47", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_11, "Dice", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_12, "Tongits Go", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_1, "7up7down", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_2, "Sic Bo", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_3, "Teen Patti", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_4, "Call Break", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_5, "iRIch Bingo", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_6, "Rummy", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_7, "Dragon & ", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_8, "Ander BAh", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_9, "Poker King", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_10, "AK47", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_11, "Dice", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_12, "Tongits Go", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_1, "7up7down", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_2, "Sic Bo", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_3, "Teen Patti", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_4, "Call Break", hot = false, new = true))
        detailsList.add(DetailsList(R.drawable.img_home_5, "iRIch Bingo", hot = true, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_6, "Rummy", hot = false, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_7, "Dragon & ", hot = false, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_8, "Ander BAh", hot = false, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_9, "Poker King", hot = false, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_10, "AK47", hot = false, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_11, "Dice", hot = false, new = false))
        detailsList.add(DetailsList(R.drawable.img_home_12, "Tongits Go", hot = false, new = false))
    }

    override fun onItemClickListener() {

    }
}