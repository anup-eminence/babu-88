package com.sona.babu88.ui.vipdetails

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentDialogRewardsBenefitsBinding

class DialogRewardsBenefitsFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogRewardsBenefitsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogRewardsBenefitsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            tvText2.text =
                setString(getString(R.string.text_vip_21), getString(R.string.text_vip_22))
            tvText3.text =
                setString(getString(R.string.text_vip_31), getString(R.string.text_vip_32))
            tvText4.text =
                setString(getString(R.string.text_vip_41), getString(R.string.text_vip_42))
            tvText5.text =
                setString(getString(R.string.text_vip_51), getString(R.string.text_vip_52))
        }
    }

    private fun setOnClickListener() {
        binding.ivClose.setOnClickListener { dialog?.dismiss() }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            1000
        )
    }

    private fun setString(text1: String, text2: String): SpannableString {
        val spannableString = SpannableString("$text1$text2")
        spannableString.setSpan(
            ForegroundColorSpan(requireContext().getColor(R.color.vip_text)),
            0,
            text1.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }
}