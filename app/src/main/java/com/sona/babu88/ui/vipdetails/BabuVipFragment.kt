package com.sona.babu88.ui.vipdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.VipLevelsResponse
import com.sona.babu88.data.viewmodel.HomeViewModel
import com.sona.babu88.databinding.FragmentBabuVipBinding
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class BabuVipFragment : Fragment() {
    private lateinit var binding: FragmentBabuVipBinding
    private lateinit var vipLevellingAdapter: VipLevellingAdapter
    private lateinit var rewardsBenefitsAdapter: RewardsBenefitsAdapter
    private lateinit var pointsAdapter: PointsAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private val vipLevelList = arrayListOf<VipLevels>()
    private val nameList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBabuVipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        setVipLevelAdapter()
        setRewardsBenefitsAdapter()
        setPointsAdapter()
        observerVipLevels()
        observerRewardsBenefits()
    }

    private fun setOnClickListener() {
        binding.apply {
            tvLearnMore.setOnClickListener {
                val dialog = DialogRewardsBenefitsFragment()
                dialog.isCancelable = false
                dialog.show(childFragmentManager, "DialogRewardsBenefits")
            }

            tvHowToEarnVip.setOnClickListener {
                val dialog = DialogPointsFragment()
                dialog.isCancelable = false
                dialog.show(childFragmentManager, "DialogPoints")
            }
        }
    }

    private fun setVipLevelAdapter() {
        binding.rvVipLevelling.layoutManager = LinearLayoutManager(requireContext())
        vipLevellingAdapter = VipLevellingAdapter()
        binding.rvVipLevelling.adapter = vipLevellingAdapter
    }

    private fun setRewardsBenefitsAdapter() {
        binding.rvRewardsBenefits.layoutManager = LinearLayoutManager(requireContext())
        rewardsBenefitsAdapter = RewardsBenefitsAdapter()
        binding.rvRewardsBenefits.adapter = rewardsBenefitsAdapter
    }

    private fun setPointsAdapter() {
        binding.rvPoints.layoutManager = LinearLayoutManager(requireContext())
        pointsAdapter = PointsAdapter()
        binding.rvPoints.adapter = pointsAdapter
    }

    private fun observerVipLevels() {
        homeViewModel.getVipLevels()
        homeViewModel.vipLevels.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    val data = getVipLevelList(it.data)
                    vipLevellingAdapter.setVipLevelData(data)
                    pointsAdapter.setPointsData(data)
                    setImages(data)
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                }
            }
        }
    }

    private fun observerRewardsBenefits() {
        homeViewModel.getRewAndBen()
        homeViewModel.rewardsBenefits.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    rewardsBenefitsAdapter.setRewardsBenefitsData(it.data, nameList)
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                }
            }
        }
    }

    private fun getVipLevelList(response: List<VipLevelsResponse?>?): List<VipLevels?> {
        response?.forEachIndexed { index, it ->
            val fullName = it?.fullName ?: ""
            vipLevelList.add(
                VipLevels(
                    image = findImage(fullName),
                    fullName = fullName,
                    color = findTextColor(fullName),
                    reqPoints = (it?.reqPoints ?: 0.0).toInt(),
                    vipToCashRatio = (it?.vipToCashRatio ?: 0.0).toInt()
                )
            )
            if (index != 0) {
                nameList.add(it?.name ?: "")
            }
        }
        return vipLevelList
    }

    private fun findImage(fullName: String): Int {
        return when (fullName) {
            "Silver" -> R.drawable.silver
            "Elite" -> R.drawable.elite
            "Master" -> R.drawable.master
            "Grand Master" -> R.drawable.grandmaster
            "Legend" -> R.drawable.legend
            "Mythic" -> R.drawable.mythic
            else -> -1
        }
    }

    private fun findTextColor(fullName: String): Int {
        return when (fullName) {
            "Silver" -> R.color.silver
            "Elite" -> R.color.elite
            "Master" -> R.color.master
            "Grand Master" -> R.color.grand_master
            "Legend" -> R.color.legend
            "Mythic" -> R.color.mythic
            else -> -1
        }
    }

    private fun setImages(data: List<VipLevels?>) {
        binding.apply {
            Glide.with(requireContext()).load(data[1]?.image).into(ivImage1)
            Glide.with(requireContext()).load(data[2]?.image).into(ivImage2)
            Glide.with(requireContext()).load(data[3]?.image).into(ivImage3)
            Glide.with(requireContext()).load(data[4]?.image).into(ivImage4)
            Glide.with(requireContext()).load(data[5]?.image).into(ivImage5)
        }
    }
}