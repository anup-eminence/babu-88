package com.sona.babu88.ui.refer_earn

import MySharedPreferences
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.TierCommDetailsResponse
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.data.viewmodel.HomeViewModel
import com.sona.babu88.databinding.FragmentMyReferralsBinding
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress
import com.sona.babu88.util.showToast

class MyReferralsFragment : Fragment() {
    private lateinit var binding: FragmentMyReferralsBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private var response: TierCommDetailsResponse? = null
    private var userData: UserData? = null
    private var currentIndex = 0
    private var size = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyReferralsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        getTierCommDetails()
        userData = MySharedPreferences.getSavedObjectFromPreference(
            requireContext(),
            AppConstant.USER_DATA
        )
        binding.tvUsername.text = userData?.user?.userName ?: ""
        binding.tvCode.text = userData?.user?.refCode ?: ""
    }

    private fun setOnClickListener() {
        binding.btnShare.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Refer a friend now and earn commission olddata.in"
                )
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "https://olddata.in/Dregister/reg?refCode=${userData?.user?.refCode ?: ""}"
                )
                startActivity(Intent.createChooser(intent, "Share Via"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        binding.leftArrow.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateData()
            }
        }

        binding.rightArrow.setOnClickListener {
            if (currentIndex < size - 1) {
                currentIndex++
                updateData()
            }
        }
    }

    private fun getTierCommDetails() {
        homeViewModel.getTierCommDetails()
        homeViewModel.tierCommDetails.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    response = it.data
                    updateData()
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                    requireContext().showToast(it.message.toString())
                }
            }
        }
    }

    private fun updateData() {
        binding.apply {
            when (currentIndex) {
                0 -> {
                    tvTitle.text = titleString(response?.minTurn ?: 0.0)
                    tvTier1.text = tierString(getString(R.string.tier_1), response?.t1Comm ?: 0.0)
                    tvTier2.text = tierString(getString(R.string.tier_2), response?.t2Comm ?: 0.0)
                    tvTier3.text = tierString(getString(R.string.tier_3), response?.t3Comm ?: 0.0)
                }

                1 -> {
                    tvTitle.text = titleString(response?.minTurn2 ?: 0.0)
                    tvTier1.text = tierString(getString(R.string.tier_1), response?.t21Comm ?: 0.0)
                    tvTier2.text = tierString(getString(R.string.tier_2), response?.t22Comm ?: 0.0)
                    tvTier3.text = tierString(getString(R.string.tier_3), response?.t23Comm ?: 0.0)
                }

                2 -> {
                    tvTitle.text = titleString(response?.minTurn3 ?: 0.0)
                    tvTier1.text = tierString(getString(R.string.tier_1), response?.t31Comm ?: 0.0)
                    tvTier2.text = tierString(getString(R.string.tier_2), response?.t32Comm ?: 0.0)
                    tvTier3.text = tierString(getString(R.string.tier_3), response?.t33Comm ?: 0.0)
                }

                3 -> {
                    tvTitle.text = titleString(response?.minTurn4 ?: 0.0)
                    tvTier1.text = tierString(getString(R.string.tier_1), response?.t41Comm ?: 0.0)
                    tvTier2.text = tierString(getString(R.string.tier_2), response?.t42Comm ?: 0.0)
                    tvTier3.text = tierString(getString(R.string.tier_3), response?.t43Comm ?: 0.0)
                }

                4 -> {
                    tvTitle.text = titleString(response?.minTurn5 ?: 0.0)
                    tvTier1.text = tierString(getString(R.string.tier_1), response?.t51Comm ?: 0.0)
                    tvTier2.text = tierString(getString(R.string.tier_2), response?.t52Comm ?: 0.0)
                    tvTier3.text = tierString(getString(R.string.tier_3), response?.t53Comm ?: 0.0)
                }
            }
        }
    }

    private fun titleString(minTurn: Double?): String {
        return StringBuilder()
            .append(getString(R.string.turnover_range_more_than))
            .append(" ")
            .append(minTurn?.toInt())
            .toString()
    }

    private fun tierString(tierString: String, tierComm: Double): String {
        return StringBuilder()
            .append(tierString)
            .append(" (")
            .append(tierComm)
            .append("%)")
            .toString()
    }
}