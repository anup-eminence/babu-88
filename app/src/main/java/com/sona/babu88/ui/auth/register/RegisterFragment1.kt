package com.sona.babu88.ui.auth.register

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.sona.babu88.R
import com.sona.babu88.databinding.RegisterPage1Binding
import com.sona.babu88.ui.auth.adapter.RegisterVPAdapter
import com.sona.babu88.util.hide
import com.sona.babu88.util.show
import com.sona.babu88.util.showToast


class RegisterFragment1(
    private val registerDialogListener: RegisterVPAdapter.RegisterDialogListener
) : Fragment() {

    private lateinit var binding: RegisterPage1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterPage1Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSpinner()
        setTextStar()
        validateFormData()
        initClickListener()
    }

    private fun initClickListener() {
        binding.btnLogin.setOnClickListener {
            registerDialogListener.moveToLoginPage()
        }
    }


    private fun validateFormData() {
        binding.etUsername.addTextChangedListener {
            if (it?.length!! > 5 && checkValidUserName(it.toString())) {
                binding.userNamError.show()
                binding.userNamError.text = getString(R.string.user_name_invalid)
            } else {
                binding.userNamError.hide()
            }
        }

        binding.etPassword.addTextChangedListener {
            binding.passwordError.hide()
        }

        binding.etCnfPassword.addTextChangedListener {
            binding.cnfPassError.hide()
        }

        binding.nextBtn.setOnClickListener {
            if (isValidUserName() && isValidPassword() && isValidCnfPassWord()) {
                registerDialogListener.moveToNextRegisterPage()
            }
        }
    }


    private fun isValidUserName(): Boolean {
        return if (!binding.etUsername.text.isNullOrEmpty() && ((binding.etUsername.text!!.length in 6..11) && checkValidUserName())) {
            binding.userNamError.hide()
            true
        } else if (binding.etUsername.text.isNullOrEmpty()) {
            binding.userNamError.show()
            binding.userNamError.text = getString(R.string.mendatory_field)
            false
        } else if (binding.etUsername.text!!.length < 6) {
            binding.userNamError.show()
            binding.userNamError.text = getString(R.string.error_field)
            false
        } else if (checkValidUserName()) {
            binding.userNamError.show()
            binding.userNamError.text = getString(R.string.user_name_invalid)
            return false
        } else if (binding.etUsername.text!!.contains(" ")) {
            binding.userNamError.show()
            binding.userNamError.text = getString(R.string.user_name_invalid)
            false
        } else {
            binding.userNamError.hide()
            return true
        }
    }

    private fun checkValidUserName(it: String? = null): Boolean {
        val regex = Regex("^[a-zA-Z0-9]*\$")
        if (!it.isNullOrEmpty()) {
            return !it.matches(regex)
        } else {
            return !binding.etUsername.text!!.matches(regex) && !binding.etUsername.text!!.contains(
                " "
            )
        }
    }

    private fun isValidPassword(): Boolean {
        if (binding.etPassword.text.isNullOrEmpty()
                .not() && (binding.etPassword.text!!.length in 6..11)
        ) {
            binding.passwordError.hide()
            return true
        } else if (binding.etPassword.text.isNullOrEmpty()) {
            binding.passwordError.show()
            binding.passwordError.text = getString(R.string.mendatory_field)
            return false
        } else if (binding.etPassword.text!!.length < 6) {
            binding.passwordError.show()
            binding.passwordError.text = getString(R.string.error_field)
            return false
        } else {
            binding.passwordError.hide()
            return true
        }

    }

    private fun isValidCnfPassWord(): Boolean {
        val cnfPasswordText = binding.etCnfPassword.text.toString()
        val passwordText = binding.etPassword.text.toString()

        return when {
            cnfPasswordText.isEmpty() -> {
                binding.cnfPassError.show()
                binding.cnfPassError.text = getString(R.string.mendatory_field)
                false
            }

            cnfPasswordText != passwordText -> {
                binding.cnfPassError.show()
                binding.cnfPassError.text = getString(R.string.password_match_error)
                false
            }

            else -> {
                binding.cnfPassError.hide()
                true
            }
        }
    }


    private fun setTextStar() {
        binding.tvUsername.text = getFormattedText(getString(R.string.username))
        binding.tvPassword.text = getFormattedText(getString(R.string.password))
        binding.tvCnfPassword.text = getFormattedText(getString(R.string.password_cnf))
        binding.currency.text = getFormattedText(getString(R.string.currency))
    }

    private fun setUpSpinner() {
        val list = ArrayList<String>()
        provideSpinnerList().forEach {
            list.add(it.title!!)
        }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item, list
        )
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.flag.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        provideSpinnerList()[position].img!!
                    )
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }

    private fun provideSpinnerList(): List<SpinnerListRegister> {
        return listOf(
            SpinnerListRegister(
                img = R.drawable.ic_country_inr,
                title = "INR"
            ),
            SpinnerListRegister(
                img = R.drawable.ic_country_bdt,
                title = "BDT"
            ),
            SpinnerListRegister(
                img = R.drawable.ic_country_npr,
                title = "NPR"
            )
        )
    }
}

data class SpinnerListRegister(
    val img: Int?,
    val title: String?
)