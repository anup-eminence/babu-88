package com.sona.babu88.ui.luckyspin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.databinding.FragmentLuckySpinBinding

class LuckySpinFragment : Fragment() {
    private lateinit var binding: FragmentLuckySpinBinding
    private lateinit var spinAdapter: SpinAdapter
    private val luckySpinList = arrayListOf<LuckySpinList>()
    private lateinit var winnerListAdapter: WinnerListAdapter
    private val winnerList = arrayListOf<WinnerList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLuckySpinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setAdapter()
        setData()
        setWinnerListAdapter()
        setWinnerData()
    }

    private fun setAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        spinAdapter = SpinAdapter()
//        spinAdapter.setOnItemClickListener(this@LuckySpinFragment)
        binding.recyclerView.adapter = spinAdapter
    }

    private fun setData() {
        luckySpinList.add(LuckySpinList(1, "", "iPhone 15 Pro Max"))
        luckySpinList.add(LuckySpinList(2, "₹1000", "₹1000"))
        luckySpinList.add(LuckySpinList(3, "₹580", "₹580"))
        luckySpinList.add(LuckySpinList(4, "₹288", "₹288"))
        luckySpinList.add(LuckySpinList(5, "₹168", "₹168"))
        luckySpinList.add(LuckySpinList(6, "₹68", "₹68"))
        luckySpinList.add(LuckySpinList(7, "₹48", "₹48"))
        luckySpinList.add(LuckySpinList(8, "₹38", "₹38"))
        spinAdapter.setData(luckySpinList)
    }

    private fun setWinnerListAdapter() {
        binding.rvWinnerList.layoutManager = LinearLayoutManager(requireContext())
        winnerListAdapter = WinnerListAdapter()
        binding.rvWinnerList.adapter = winnerListAdapter
    }

    private fun setWinnerData() {
        winnerList.add(WinnerList("2024-03-02", "01852****", "68.00"))
        winnerList.add(WinnerList("2024-03-02", "01852****", "30.00"))
        winnerList.add(WinnerList("2024-03-02", "01das2****", "78.00"))
        winnerList.add(WinnerList("2024-03-02", "018a***", "95.00"))
        winnerList.add(WinnerList("2024-03-02", "as852****", "20.00"))
        winnerList.add(WinnerList("2024-03-02", "daa52****", "68.00"))
        winnerList.add(WinnerList("2024-03-02", "dcbchb****", "68.00"))
        winnerList.add(WinnerList("2024-03-02", "021ncd****", "68.00"))
        winnerList.add(WinnerList("2024-03-02", "mkol****", "02.00"))
        winnerList.add(WinnerList("2024-03-02", "asvcfv****", "60.00"))
        winnerList.add(WinnerList("2024-03-02", "b52eu****", "21.00"))
        winnerList.add(WinnerList("2024-03-02", "casac****", "10.00"))
        winnerListAdapter.setData(winnerList)
    }
}