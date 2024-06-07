package com.sona.babu88.ui.changepassword

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.viewmodel.AuthViewModel
import com.sona.babu88.databinding.FragmentChangePasswordBinding
import com.sona.babu88.util.hideKeyboard
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress
import com.sona.babu88.util.showToast

class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding
    private val authViewModel : AuthViewModel by viewModels()

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
        binding.btnSubmit.setOnClickListener {
            if (isValidPassword()) {
                this.hideKeyboard()
                observerChangePassword()
            }
        }
    }

    private fun observerChangePassword() {
        authViewModel.changePassword(
            oldPass = binding.etCurrentPass.text.toString(),
            newPass = binding.etNewPass.text.toString(),
            conPass = binding.etCnfmNewPass.text.toString()
        )

        authViewModel.changePassword.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    it.data?.message?.let { it1 -> requireContext().showToast(it1) }
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                    it.message?.let { it1 -> requireContext().showToast(it1) }
                }
            }
        }
    }

    private fun isValidPassword(): Boolean {
        val currPassword = binding.etCurrentPass.text.toString()
        val newPassword = binding.etNewPass.text.toString()
        val confPassword = binding.etCnfmNewPass.text.toString()
        when {
            currPassword.isEmpty() -> {
                requireContext().showToast("Please enter current password!")
                return false
            }

            newPassword.isEmpty() -> {
                requireContext().showToast("Please enter new password!")
                return false
            }

            newPassword.length < 6 -> {
                requireContext().showToast(getString(R.string.error_field))
                return false
            }

            confPassword.isEmpty() -> {
                requireContext().showToast("Please enter confirm password!")
                return false
            }

            newPassword != confPassword -> {
                requireContext().showToast("New password and confirm password not matched!")
                return false
            }

            else -> return true
        }
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }
}