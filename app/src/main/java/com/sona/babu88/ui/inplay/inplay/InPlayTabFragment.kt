package com.sona.babu88.ui.inplay.inplay

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
import com.sona.babu88.databinding.FragmentInPlayTabBinding
import com.sona.babu88.util.OnSportsInteractionListener
import com.sona.babu88.util.hideProgress1
import com.sona.babu88.util.showProgress1
import com.sona.babu88.util.showToast

class InPlayTabFragment : Fragment(), InPlayAdapter.OnInPlayClickListener {
    private lateinit var binding: FragmentInPlayTabBinding
    private lateinit var inPlayAdapter: InPlayAdapter
    private lateinit var inPlayAdapter1: InPlayAdapter
    private lateinit var inPlayAdapter2: InPlayAdapter
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
        binding = FragmentInPlayTabBinding.inflate(inflater, container, false)
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
        observerInPlay()
    }

    private fun setCricketAdapter() {
        binding.rvCricket.layoutManager = LinearLayoutManager(requireContext())
        inPlayAdapter = InPlayAdapter()
        inPlayAdapter.setOnItemClickListener(this@InPlayTabFragment)
        binding.rvCricket.adapter = inPlayAdapter
    }

    private fun setSoccerAdapter() {
        binding.rvSoccer.layoutManager = LinearLayoutManager(requireContext())
        inPlayAdapter1 = InPlayAdapter()
        inPlayAdapter1.setOnItemClickListener(this@InPlayTabFragment)
        binding.rvSoccer.adapter = inPlayAdapter1
    }

    private fun setTennisAdapter() {
        binding.rvTennis.layoutManager = LinearLayoutManager(requireContext())
        inPlayAdapter2 = InPlayAdapter()
        inPlayAdapter2.setOnItemClickListener(this@InPlayTabFragment)
        binding.rvTennis.adapter = inPlayAdapter2
    }

    override fun onInPlayClickListener(item: ResultItem?) {
        listener?.onSportsClick(item?.id)
    }

    private fun observerInPlay() {
        sportsViewModel.getInPlayMatches()
        sportsViewModel.inPlayMatches.observe(viewLifecycleOwner) { it ->
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress1()
                }

                is ApiResult.Success -> {
                    this.hideProgress1()
                    sportsViewModel.cricketData.observe(viewLifecycleOwner) {
                        inPlayAdapter.setInPlayData(it)
                    }

                    sportsViewModel.soccerData.observe(viewLifecycleOwner) {
                        inPlayAdapter1.setInPlayData(it)
                    }

                    sportsViewModel.tennisData.observe(viewLifecycleOwner) {
                        inPlayAdapter2.setInPlayData(it)
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