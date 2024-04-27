package com.sona.babu88.ui.slot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentSlotBinding
import com.sona.babu88.model.SlotList
import com.sona.babu88.util.showToast

class SlotFragment : Fragment(), SlotAdapter.OnItemClickListener {
    private lateinit var binding: FragmentSlotBinding
    private lateinit var slotAdapter: SlotAdapter
    private var slotList = arrayListOf<SlotList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSlotAdapter()
        setSlotData()
        slotAdapter.setSlotData(slotList)
    }

    private fun setSlotAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        slotAdapter = SlotAdapter()
        binding.recyclerView.adapter = slotAdapter
    }

    private fun setSlotData() {
        slotList.add(SlotList(R.drawable.img_slot_1, "Pragmatic",R.drawable.ic_hot))
        slotList.add(SlotList(R.drawable.img_slot_2, "JILI", R.drawable.ic_new))
        slotList.add(SlotList(R.drawable.img_slot_3, "JDB",R.drawable.ic_hot))
        slotList.add(SlotList(R.drawable.img_slot_4, "PT", R.drawable.ic_new))
        slotList.add(SlotList(R.drawable.img_slot_5, "Habanero",R.drawable.ic_hot))
        slotList.add(SlotList(R.drawable.img_slot_6, "Red Tiger", R.drawable.ic_new))
        slotList.add(SlotList(R.drawable.img_slot_7, "Play N'Go",R.drawable.ic_hot))
        slotList.add(SlotList(R.drawable.img_slot_1, "spade", R.drawable.ic_new))
        slotList.add(SlotList(R.drawable.img_slot_2, "One Game",R.drawable.ic_hot))
        slotList.add(SlotList(R.drawable.img_slot_3, "NetEnt", R.drawable.ic_new))
        slotList.add(SlotList(R.drawable.img_slot_4, "PG Soft", R.drawable.ic_hot))
        slotList.add(SlotList(R.drawable.img_slot_5, "NoLimit",R.drawable.ic_new))
        slotList.add(SlotList(R.drawable.img_slot_6, "Relax Gaming", R.drawable.ic_hot))
        slotList.add(SlotList(R.drawable.img_slot_7, "Booongo",R.drawable.ic_new))
    }

    override fun onItemClickListener() {
        requireContext().showToast("Clicked...")
    }
}