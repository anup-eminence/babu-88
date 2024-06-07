package com.sona.babu88.ui.sports__.tennis

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
import com.sona.babu88.databinding.FragmentTennisSportBinding
import com.sona.babu88.ui.sports__.soccer.SoccerSportAdapter
import com.sona.babu88.util.OnSportsInteractionListener
import com.sona.babu88.util.hideProgress1
import com.sona.babu88.util.showProgress1
import com.sona.babu88.util.showToast

class TennisSportFragment : Fragment(), SoccerSportAdapter.OnSoccerClickListener {
    private lateinit var binding: FragmentTennisSportBinding
    private lateinit var soccerSportAdapter: SoccerSportAdapter
    private var listener: OnSportsInteractionListener? = null
    private val sportsViewModel: SportsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTennisSportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setSoccerAdapter()
        observerGetUserSideBarMatches()
    }

    private fun setSoccerAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        soccerSportAdapter = SoccerSportAdapter()
        soccerSportAdapter.setOnItemClickListener(this@TennisSportFragment)
        binding.recyclerView.adapter = soccerSportAdapter
    }

    override fun onSoccerClickListener(item: ResultItem?) {
        listener?.onSportsClick(item?.id)
    }

    private fun observerGetUserSideBarMatches() {
        sportsViewModel.getUserSideBarMatches(
            sportId = "2"
        )

        sportsViewModel.userSideBarMatches.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress1()
                }

                is ApiResult.Success -> {
                    this.hideProgress1()
                    soccerSportAdapter.setSoccerData(it.data?.results)
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