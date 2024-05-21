package com.sona.babu88.ui.multimarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentMultiMarketsBinding
import com.sona.babu88.ui.sports__.cricket.CricketSportAdapter
import com.sona.babu88.ui.sports__.cricket.CricketSportList

class MultiMarketsFragment : Fragment() {
    private lateinit var binding: FragmentMultiMarketsBinding
    private lateinit var multiMarketAdapter: MultiMarketAdapter
    private val multiMarketList = arrayListOf<MultiMarketList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMultiMarketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setUpAdapter()
        setData()
    }


    private fun setUpAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        multiMarketAdapter = MultiMarketAdapter()
//        multiMarketAdapter.setOnItemClickListener(this@MultiMarketsFragment)
        binding.recyclerView.adapter = multiMarketAdapter
    }

    private fun setData() {

        multiMarketList.add(MultiMarketList("Islamabad United SRL T20 v Quetta Gladiator Quetta", "Islamabad United SRL T20", "Quetta Gladiator Quetta"))
        multiMarketList.add(MultiMarketList("Islamabad United SRL T20 v Quetta Gladiator Quetta", "Islamabad United SRL T20", "Quetta Gladiator Quetta"))
        multiMarketList.add(MultiMarketList("Islamabad United SRL T20 v Quetta Gladiator Quetta", "Islamabad United SRL T20", "Quetta Gladiator Quetta"))
        multiMarketList.add(MultiMarketList("Islamabad United SRL T20 v Quetta Gladiator Quetta", "Islamabad United SRL T20", "Quetta Gladiator Quetta"))
        multiMarketAdapter.setData(multiMarketList)
    }
}