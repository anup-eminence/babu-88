package com.sona.babu88.ui.auth.register

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentRegister2Binding
import com.sona.babu88.ui.auth.adapter.RegisterVPAdapter

class RegisterFragment2(
    private val registerDialogListener: RegisterVPAdapter.RegisterDialogListener
) : Fragment() {

    private lateinit var binding : FragmentRegister2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegister2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextItem()

        binding.completeBtn.setOnClickListener {
            if (validateInputFields()){
                registerDialogListener.makeApiCall(
                    selectedPhone = binding.etPhoneNumber.text.toString().trim(),
                    selectedReferral = binding.etReferalCode.text.toString().trim(),
                    email = binding.etEmail.text?.trim().toString()
                )
            }
        }
    }

    private fun validateInputFields(): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if (binding.etPhoneNumber.text.isNullOrEmpty() || binding.etPhoneNumber.text!!.length < 9) {
            binding.etPhoneNumber.error = "Invalid Phone Number"
            return false
        }
        if (binding.etVerificationCode.text.isNullOrEmpty()) {
            binding.etVerificationCode.error = "Enter Otp"
            return false
        }

        if (binding.etEmail.text.isNullOrEmpty() || !binding.etEmail.text!!.matches(emailRegex)) {
            binding.etEmail.error = "Enter Valid Email"
            return false
        }

        return true
    }

    private fun setTextItem() {
        binding.tvPhone.text = getFormattedText(getString(R.string.phone))
        binding.tvVerification.text = getFormattedText(getString(R.string.verification_code))
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }
}