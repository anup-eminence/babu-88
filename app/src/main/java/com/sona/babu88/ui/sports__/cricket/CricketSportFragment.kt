package com.sona.babu88.ui.sports__.cricket

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.data.viewmodel.SportsViewModel
import com.sona.babu88.databinding.FragmentCricketSportBinding
import com.sona.babu88.util.OnSportsInteractionListener
import com.sona.babu88.util.hideProgress1
import com.sona.babu88.util.showProgress1
import com.sona.babu88.util.showToast

class CricketSportFragment : Fragment(), CricketSportAdapter.OnCricketSportClickListener {
    private lateinit var binding: FragmentCricketSportBinding
    private lateinit var cricketSportAdapter: CricketSportAdapter
    private var listener: OnSportsInteractionListener? = null
    private val sportsViewModel: SportsViewModel by viewModels()

    companion object {
        var isPinnedMatch = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCricketSportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setCricketSportAdapter()
        observerGetUserSideBarMatches()
    }

    private fun setCricketSportAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cricketSportAdapter = CricketSportAdapter()
        cricketSportAdapter.setOnItemClickListener(this@CricketSportFragment)
        binding.recyclerView.adapter = cricketSportAdapter
    }

    override fun onCricketSportClickListener(item: ResultItem?) {
        listener?.onSportsClick(item?.id)
    }

    override fun pinMatch(item: ResultItem?, holder: CricketSportAdapter.ViewHolder, pos: Int) {
        sportsViewModel.multiMatchUser.observe(requireActivity()) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress1()
                }

                is ApiResult.Success -> {
                    this.hideProgress1()
                    isPinnedMatch = true
                    if (pos == cricketSportAdapter.selectedPos) {
                        cricketSportAdapter.notifyItemUpdated(pos, item!!.copy(isPinned = true))
                    }
                }

                is ApiResult.Error -> {
                    this.hideProgress1()
                    if (pos == cricketSportAdapter.selectedPos) {
                        println(">>>error $pos")
                        cricketSportAdapter.notifyItemUpdated(pos, item!!.copy(isPinned = false))
                    }
                    //requireContext().showToast(it.message.toString())
                }
            }
        }
        sportsViewModel.getMultiMatchUser(
            matchId = item?.id
        )
    }

    private fun observerGetUserSideBarMatches() {
        sportsViewModel.getUserSideBarMatches(
            sportId = "4"
        )

        sportsViewModel.userSideBarMatches.observe(requireActivity()) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress1()
                }

                is ApiResult.Success -> {
                    this.hideProgress1()
                    cricketSportAdapter.setCricketSportData(it.data?.results)
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