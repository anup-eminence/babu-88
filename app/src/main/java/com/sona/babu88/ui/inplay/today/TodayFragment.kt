package com.sona.babu88.ui.inplay.today

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.data.viewmodel.SportsViewModel
import com.sona.babu88.databinding.FragmentTodayBinding
import com.sona.babu88.util.OnSportsInteractionListener
import com.sona.babu88.util.hideProgress1
import com.sona.babu88.util.showProgress1
import com.sona.babu88.util.showToast

class TodayFragment : Fragment(), TodayAdapter.OnTodayClickListener {
    private lateinit var binding: FragmentTodayBinding
    private lateinit var todayAdapter: TodayAdapter
    private lateinit var todayAdapter1: TodayAdapter
    private lateinit var todayAdapter2: TodayAdapter
    private val sportsViewModel: SportsViewModel by viewModels()
    private var listener: OnSportsInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setCricketAdapter()
        setSoccerAdapter()
        setTennisAdapter()
        observerToday()
    }

    private fun setCricketAdapter() {
        binding.rvCricket.layoutManager = LinearLayoutManager(requireContext())
        todayAdapter = TodayAdapter()
        todayAdapter.setOnItemClickListener(this@TodayFragment)
        binding.rvCricket.adapter = todayAdapter
    }

    private fun setSoccerAdapter() {
        binding.rvSoccer.layoutManager = LinearLayoutManager(requireContext())
        todayAdapter1 = TodayAdapter()
        todayAdapter1.setOnItemClickListener(this@TodayFragment)
        binding.rvSoccer.adapter = todayAdapter1
    }

    private fun setTennisAdapter() {
        binding.rvTennis.layoutManager = LinearLayoutManager(requireContext())
        todayAdapter2 = TodayAdapter()
        todayAdapter2.setOnItemClickListener(this@TodayFragment)
        binding.rvTennis.adapter = todayAdapter2
    }

    override fun onTodayClickListener(item: ResultItem?) {
        listener?.onSportsClick(item?.id)
    }

    private fun observerToday() {
        sportsViewModel.getTodayMatches()
        sportsViewModel.todayMatches.observe(viewLifecycleOwner) { it ->
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress1()
                }

                is ApiResult.Success -> {
                    this.hideProgress1()
                    sportsViewModel.cricketData.observe(viewLifecycleOwner) {
                        todayAdapter.setTodayData(it)
                    }

                    sportsViewModel.soccerData.observe(viewLifecycleOwner) {
                        todayAdapter1.setTodayData(it)
                    }

                    sportsViewModel.tennisData.observe(viewLifecycleOwner) {
                        todayAdapter2.setTodayData(it)
                    }
                }

                is ApiResult.Error -> {
                    this.hideProgress1()
                    requireContext().showToast(it.message.toString())
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSportsInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSportsInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}