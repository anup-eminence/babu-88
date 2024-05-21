package com.sona.babu88.ui.bank

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentBankDetailsBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class BankDetailsFragment : Fragment() {
    private lateinit var binding: FragmentBankDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBankDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            tvBank.text = getFormattedText(getString(R.string.bank))
            tvBankHolderName.text = getFormattedText(getString(R.string.bank_holder_name))
            tvAccNumber.text = getFormattedText(getString(R.string.account_number))
            tvIfsc.text = getFormattedText(getString(R.string.ifsc_code))
            tvUpi.text = getFormattedText(getString(R.string.upi_address))
        }
    }

    private fun setOnClickListener() {
        binding.apply {
            btnAdd.setOnClickListener {
                tvNoBank.hide()
                clLayout.show()
            }
        }
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }
}