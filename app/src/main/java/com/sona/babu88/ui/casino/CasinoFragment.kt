package com.sona.babu88.ui.casino

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentCasinoBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.showToast

class CasinoFragment : Fragment(), CasinoAdapter.OnItemClickListener {
    private lateinit var binding: FragmentCasinoBinding
    private lateinit var casinoAdapter: CasinoAdapter
    private var fishingList = arrayListOf<FishingList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCasinoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCasinoAdapter()
        setCasinoData()
        casinoAdapter.setCasinoData(fishingList)
    }

    private fun setCasinoAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        casinoAdapter = CasinoAdapter()
        binding.recyclerView.adapter = casinoAdapter
    }

    private fun setCasinoData() {
        fishingList.add(FishingList(R.drawable.img_casino_1))
        fishingList.add(FishingList(R.drawable.img_casino_2))
        fishingList.add(FishingList(R.drawable.img_casino_3))
        fishingList.add(FishingList(R.drawable.img_casino_4))
        fishingList.add(FishingList(R.drawable.img_casino_5))
        fishingList.add(FishingList(R.drawable.img_casino_6))
        fishingList.add(FishingList(R.drawable.img_casino_7))
    }

    override fun onItemClickListener() {
        requireContext().showToast("Clicked...")
    }
}