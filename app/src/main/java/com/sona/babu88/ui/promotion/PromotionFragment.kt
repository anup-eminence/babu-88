package com.sona.babu88.ui.promotion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.PromoFilterResponse
import com.sona.babu88.api.model.response.Promotion
import com.sona.babu88.data.viewmodel.HomeViewModel
import com.sona.babu88.databinding.FragmentPromotionBinding
import com.sona.babu88.ui.promotiondetails.PromotionDetailsFragment
import com.sona.babu88.util.OnAccountListener
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class PromotionFragment : Fragment(), PromotionAdapter.OnItemClickListener {
    private lateinit var binding: FragmentPromotionBinding
    private lateinit var promotionAdapter: PromotionAdapter
    private lateinit var promotionTabAdapter: PromotionTabAdapter
    private var promotionFilterList: MutableList<PromoFilterResponse> = mutableListOf()
    private var promotionList: MutableList<Promotion> = mutableListOf()
    private var listener: OnAccountListener? = null
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromotionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPromotionAdapter()
        setTabAdapter()
        observerPromoFilters()
        observerPromotionsList()
    }

    private fun setPromotionAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        promotionAdapter = PromotionAdapter()
        promotionAdapter.setOnItemClickListener(this@PromotionFragment)
        binding.recyclerView.adapter = promotionAdapter
    }

    override fun onBtnDetailsClickListener(position: Int) {
        val dialog = PromotionDetailsFragment(promotionList[position])
        dialog.isCancelable = false
        dialog.show(childFragmentManager, "promotion")
    }

    override fun onBtnApplyNowClickListener() {
        listener?.onAccountClick("Deposit")
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        promotionTabAdapter = PromotionTabAdapter()
//        promotionTabAdapter.setOnTabListener(this@PromotionFragment)
        binding.recyclerViewTab.adapter = promotionTabAdapter
    }

    private fun observerPromoFilters() {
        homeViewModel.getPromoFilters()
        homeViewModel.promotionFilter.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {}
                is ApiResult.Success -> {
                    promotionFilterList.add(0, PromoFilterResponse("", "", "All", ""))
                    it.data?.let { it1 -> promotionFilterList.addAll(it1) }
                    promotionTabAdapter.setFilterData(promotionFilterList)
                }
                is ApiResult.Error -> {}
            }
        }
    }

    private fun observerPromotionsList() {
        homeViewModel.getPromotionList(pageNo = 1)
        homeViewModel.promotionList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    promotionAdapter.setPromotionData(it.data?.getData())
                    it.data?.getData()?.let { it1 -> promotionList.addAll(it1) }
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                    println(">>>>> ${it.message}")
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAccountListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnAccountListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}