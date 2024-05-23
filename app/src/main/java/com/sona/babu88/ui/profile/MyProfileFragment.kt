package com.sona.babu88.ui.profile

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.core.text.underline
import androidx.fragment.app.Fragment
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment() {
    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.tvText.text = getFormattedText(getString(R.string.to_protect_your_privacy_please_contact))
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .append(" ")
            .underline { color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(getString(R.string._live_chat)) }}
            .append(" ")
            .append(getString(R.string._to_change_your_profile_details))
    }
}