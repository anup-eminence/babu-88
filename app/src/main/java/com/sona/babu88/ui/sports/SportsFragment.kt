package com.sona.babu88.ui.sports

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentSportsBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.OnSelectedFragmentListener

class SportsFragment : Fragment(), SportsAdapter.OnItemClickListener {
    private lateinit var binding: FragmentSportsBinding
    private lateinit var sportsAdapter: SportsAdapter
    private var fishingList = arrayListOf<FishingList>()
    private var listener: OnSelectedFragmentListener?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFishingAdapter()
        setFishingData()
        sportsAdapter.setSportsData(fishingList)
    }

    private fun setFishingAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        sportsAdapter = SportsAdapter()
        sportsAdapter.setOnItemClickListener(this@SportsFragment)
        binding.recyclerView.adapter = sportsAdapter
    }

    private fun setFishingData() {
        fishingList.add(FishingList(R.drawable.img_sports_2))
        fishingList.add(FishingList(R.drawable.img_sports_1))
    }

    override fun onItemClickListener(item: FishingList?) {
        item?.image?.let { listener?.onFragmentClickListener(arrayListOf("Sports", "", "ALL", "4")) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSelectedFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSelectedFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}