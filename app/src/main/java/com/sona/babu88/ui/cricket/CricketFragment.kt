package com.sona.babu88.ui.cricket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentCricketBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.showToast

class CricketFragment : Fragment(), CricketAdapter.OnItemClickListener {
    private lateinit var binding: FragmentCricketBinding
    private lateinit var cricketAdapter: CricketAdapter
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
        binding = FragmentCricketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFishingAdapter()
        setFishingData()
        cricketAdapter.setCricketData(fishingList)
    }

    private fun setFishingAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cricketAdapter = CricketAdapter()
        cricketAdapter.setOnItemClickListener(this@CricketFragment)
        binding.recyclerView.adapter = cricketAdapter
    }

    private fun setFishingData() {
        fishingList.add(FishingList(R.drawable.img_cricket_1))
        fishingList.add(FishingList(R.drawable.img_cricket_2))
        fishingList.add(FishingList(R.drawable.img_cricket_3))
    }

    override fun onItemClickListener() {
        requireContext().showToast("Clicked...")
    }
}