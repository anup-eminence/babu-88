package com.sona.babu88.ui.fishing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentFishingBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.showToast

class FishingFragment : Fragment(), FishingAdapter.OnItemClickListener {
    private lateinit var binding: FragmentFishingBinding
    private lateinit var fishingAdapter: FishingAdapter
    private var fishingList = arrayListOf<FishingList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFishingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFishingAdapter()
        setFishingData()
        fishingAdapter.setFishingData(fishingList)
    }

    private fun setFishingAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fishingAdapter = FishingAdapter()
        fishingAdapter.setOnItemClickListener(this@FishingFragment)
        binding.recyclerView.adapter = fishingAdapter
    }

    private fun setFishingData() {
        fishingList.add(FishingList(R.drawable.img_fishing_1))
        fishingList.add(FishingList(R.drawable.img_fishing_2))
    }

    override fun onItemClickListener() {
        requireContext().showToast("Clicked")
    }
}