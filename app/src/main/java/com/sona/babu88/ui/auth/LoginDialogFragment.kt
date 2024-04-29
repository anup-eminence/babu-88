package com.sona.babu88.ui.auth

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.DialogFragment
import com.sona.babu88.R
import com.sona.babu88.databinding.LayoutLoginBinding
import com.sona.babu88.util.setWidthPercent

class LoginDialogFragment : DialogFragment() {
    private lateinit var binding: LayoutLoginBinding
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LayoutLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setWidthPercent(95)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnclickListener()
    }

    private fun initView() {
        binding.apply {
            tvUsername.text = getFormattedText(getString(R.string.username))
            tvPassword.text = getFormattedText(getString(R.string.password))
        }
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }

    private fun setOnclickListener() {
        binding.apply {
            imageWrong.setOnClickListener {
                onItemClickListener?.dialogDismiss()
                etUsername.text?.clear()
                etPassword.text?.clear()
            }

            btnLogin.setOnClickListener {
                if (isValidate()) {
                    onItemClickListener?.onLoginClick()
                } else {
                    isValidate()
                }
            }

            btnSignUp.setOnClickListener { onItemClickListener?.onSignUpClick() }
            tvForgotPassword.setOnClickListener { onItemClickListener?.onForgotPasswordClick() }
        }
    }

    fun onItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    private fun isValidate(): Boolean {
        binding.apply {
            if (etUsername.text.isNullOrEmpty() && etPassword.text.isNullOrEmpty()) {
                etUsername.error = "This is a mandatory field"
                etPassword.error = "This is a mandatory field"
                etUsername.requestFocus()
            }
            return true
        }
    }

    interface OnItemClickListener {
        fun dialogDismiss()
        fun onLoginClick()
        fun onSignUpClick()
        fun onForgotPasswordClick()
    }
}