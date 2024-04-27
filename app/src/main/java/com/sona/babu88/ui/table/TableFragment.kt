package com.sona.babu88.ui.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentTableBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.showToast

class TableFragment : Fragment(), TableAdapter.OnItemClickListener {
    private lateinit var binding: FragmentTableBinding
    private lateinit var tableAdapter: TableAdapter
    private var fishingList = arrayListOf<FishingList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFishingAdapter()
        setFishingData()
        tableAdapter.setTableData(fishingList)
    }

    private fun setFishingAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        tableAdapter = TableAdapter()
        tableAdapter.setOnItemClickListener(this@TableFragment)
        binding.recyclerView.adapter = tableAdapter
    }

    private fun setFishingData() {
        fishingList.add(FishingList(R.drawable.img_table_1))
        fishingList.add(FishingList(R.drawable.img_table_2))
        fishingList.add(FishingList(R.drawable.img_table_3))
    }

    override fun onItemClickListener() {
        requireContext().showToast("Clicked...")
    }
}