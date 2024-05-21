package com.sona.babu88.ui.changepassword

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            tvCurrentPass.text = getFormattedText(getString(R.string.current_password))
            tvNewPass.text = getFormattedText(getString(R.string.new_password))
            tvCnfmNewPass.text = getFormattedText(getString(R.string.confirm_new_password))
        }
    }

    private fun setOnClickListener() {
        binding.btnSubmit.setOnClickListener { }
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }
}