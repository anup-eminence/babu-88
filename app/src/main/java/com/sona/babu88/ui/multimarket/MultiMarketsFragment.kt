package com.sona.babu88.ui.multimarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.DataItem
import com.sona.babu88.data.viewmodel.SportsViewModel
import com.sona.babu88.databinding.FragmentMultiMarketsBinding
import com.sona.babu88.util.hideProgress1
import com.sona.babu88.util.showProgress1
import com.sona.babu88.util.showToast

class MultiMarketsFragment : Fragment(), MultiMarketAdapter.OnItemClickListener {
    private lateinit var binding: FragmentMultiMarketsBinding
    private lateinit var multiMarketAdapter: MultiMarketAdapter
    private val sportsViewModel: SportsViewModel by viewModels()

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
        observerActiveMultiMarket()
    }


    private fun setUpAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        multiMarketAdapter = MultiMarketAdapter()
        multiMarketAdapter.setOnItemClickListener(this@MultiMarketsFragment)
        binding.recyclerView.adapter = multiMarketAdapter
    }

    private fun observerActiveMultiMarket() {
        sportsViewModel.getActiveMultiMarket()

        sportsViewModel.activeMultiMarket.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress1()
                }

                is ApiResult.Success -> {
                    this.hideProgress1()
                    multiMarketAdapter.setData(it.data?.data)
                }

                is ApiResult.Error -> {
                    this.hideProgress1()
                    requireContext().showToast(it.message.toString())
                }
            }
        }
    }

    override fun pinMatchClickListener(
        item: DataItem?,
        holder: MultiMarketAdapter.ViewHolder,
        adapterPosition: Int
    ) {
        sportsViewModel.getMultiMatchUser(matchId = item?.matchId)

        sportsViewModel.multiMatchUser.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
//                    this.showProgress1()
                }

                is ApiResult.Success -> {
//                    this.hideProgress1()
                    if (adapterPosition == multiMarketAdapter.selectedPos) {
                        multiMarketAdapter.notifyItemUpdated(
                            adapterPosition, item!!.copy(isPinned = true)
                        )
                    }
                }

                is ApiResult.Error -> {
//                    this.hideProgress1()
                    if (adapterPosition == multiMarketAdapter.selectedPos) {
                        multiMarketAdapter.notifyItemUpdated(
                            adapterPosition, item!!.copy(isPinned = false)
                        )
//                        multiMarketAdapter.removeData(adapterPosition)
                    }
                }
            }
        }
    }
}