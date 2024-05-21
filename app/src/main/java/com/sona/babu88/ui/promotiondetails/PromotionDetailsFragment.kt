package com.sona.babu88.ui.promotiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentPromotionDetailsBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class PromotionDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPromotionDetailsBinding
    private lateinit var promotionDetailsAdapter: PromotionDetailsAdapter
    private var fishingList = arrayListOf<FishingList>()
    private var toggle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromotionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPromotionDetailsAdapter()
        setPromotionDetailsData()
        promotionDetailsAdapter.setPromotionDetailsData(fishingList)
        initView()
    }

    private fun initView() {
        binding.tvTermCondition.setOnClickListener {
            toggle = !toggle
            if (toggle){
                binding.clTermCondition.show()
            } else {
                binding.clTermCondition.hide()
            }
        }
    }

    private fun setPromotionDetailsAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        promotionDetailsAdapter = PromotionDetailsAdapter()
//        promotionDetailsAdapter.setOnItemClickListener(this@PromotionDetailsFragment)
        binding.recyclerView.adapter = promotionDetailsAdapter
    }

    private fun setPromotionDetailsData() {
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
        fishingList.add(FishingList(R.drawable.ic_multan_sultans))
    }
}