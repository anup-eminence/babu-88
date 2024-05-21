package com.sona.babu88.ui.rewards

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentClaimVoucherBinding

class ClaimVoucherFragment : Fragment() {
    private lateinit var binding: FragmentClaimVoucherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClaimVoucherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.tvApplyVoucher.text = getFormattedText(getString(R.string.apply_voucher))
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }
}