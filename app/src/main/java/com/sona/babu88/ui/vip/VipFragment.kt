package com.sona.babu88.ui.vip

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.viewmodel.AuthViewModel
import com.sona.babu88.databinding.FragmentVipBinding
import com.sona.babu88.util.OnAccountListener
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class VipFragment : Fragment() {
    private lateinit var binding: FragmentVipBinding
    private val authViewModel: AuthViewModel by viewModels()
    private var listener: OnAccountListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        startArrowAnimation(binding.ivDownArrow)
        observerUserVipDetails()
        binding.textVp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val value = s.toString().toDoubleOrNull() ?: 0.0
                binding.textRealMoney.text = (value / 10).toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setOnClickListener() {
        binding.apply {
            tvVipDetails.setOnClickListener { listener?.onAccountClick("VipDetails") }
        }
    }


    private fun startArrowAnimation(arrowImageView: AppCompatImageView) {
        val translateY = ObjectAnimator.ofFloat(arrowImageView, "translationY", 0f, 20f).apply {
            duration = 1500
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            interpolator = AccelerateDecelerateInterpolator()
        }
        translateY.start()
    }

    private fun observerUserVipDetails() {
        authViewModel.getUserVipDetails()
        authViewModel.userVipDetails.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    val string =
                        getString(R.string.vp_conversion_ratio) + " " + (it.data?.ratio ?: 0)
                    val spannable = SpannableString(string)
                    val colorSpan = ForegroundColorSpan(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.yellow_vip
                        )
                    )
                    val start = string.indexOf((it.data?.ratio ?: 0).toString())
                    val end = start + (it.data?.ratio ?: 0).toString().length
                    spannable.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                    binding.apply {
                        tvLevel.text = it.data?.currLevel ?: ""
                        tvPoints.text = (it.data?.points ?: 0).toString()
                        tvMinVipReq.text =
                            StringBuilder().append(getString(R.string.minimum_vp_required))
                                .append(" ").append(it.data?.minReq ?: 0)
                        tvVpConversionRatio.text = spannable
                    }
                }

                is ApiResult.Error -> {
                    this.hideProgress()
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