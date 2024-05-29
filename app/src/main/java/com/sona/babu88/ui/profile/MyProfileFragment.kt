package com.sona.babu88.ui.profile

import MySharedPreferences
import android.app.DatePickerDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.core.text.underline
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.User
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.api.model.response.UserDetailsResponse
import com.sona.babu88.data.viewmodel.AuthViewModel
import com.sona.babu88.databinding.CustomVerifiedDialogBinding
import com.sona.babu88.databinding.FragmentMyProfileBinding
import com.sona.babu88.util.AppConstant.USER_DATA
import com.sona.babu88.util.hide
import com.sona.babu88.util.show
import com.sona.babu88.util.showToast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MyProfileFragment : Fragment() {
    private lateinit var binding: FragmentMyProfileBinding

    private val authViewModel : AuthViewModel by viewModels()
    private var userData : UserData?=null

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
        observer()
        userData = MySharedPreferences.getSavedObjectFromPreference(requireContext(),USER_DATA)
        setData(userData?.user)
        setOnClickListener()
    }

    private fun observer() {
        authViewModel.getUserDetails()
        authViewModel.userDetails.observe(viewLifecycleOwner) {
            setVisibleOrNot(it.data)
        }
    }

    private fun setData(user: User?) {
        binding.apply {
            tvText.text = getFormattedText(getString(R.string.to_protect_your_privacy_please_contact))
            txtUsername.text = user?.userName
            txtMobile.text = user?.number.toString()
            txtEmail.text = user?.email
            txtCurrency.text = user?.currency
        }
    }

    private fun setVisibleOrNot(data: UserDetailsResponse?) {
        binding.apply {
            if (data?.birthday.isNullOrEmpty().not()) {
                txtBirthday.text = data?.birthday
                tvAddBirthday.hide()
            } else {
                tvAddBirthday.show()
                txtBirthday.text = ""
            }
            if (data?.email == true) {
                txtEmailVerified.show()
                txtEmailNotVerified.hide()
            } else {
                txtEmailNotVerified.show()
                txtEmailVerified.hide()
            }
            if (data?.mobile == true) {
                txtPrimaryNumberVerified.show()
                txtPrimaryNumberNotVerified.hide()
            } else {
                txtPrimaryNumberNotVerified.show()
                txtPrimaryNumberVerified.hide()
            }
        }
    }

    private fun setOnClickListener() {
        binding.apply {
            txtEmailNotVerified.setOnClickListener { showCustomVerifyDialog(userData?.user?.email, "email") }
//            txtPrimaryNumberNotVerified.setOnClickListener { showCustomVerifyDialog(userData?.user?.number.toString(), "number") }
            tvAddBirthday.setOnClickListener { showDatePickerDialog() }
            ivEditProfile.setOnClickListener {  }
        }
    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->
            formatDateString("$year-${monthOfYear + 1}-$dayOfMonth")?.let { authViewModel.updateBirthDay(birthDay = it) }
            authViewModel.updateBirthday.observe(requireActivity()){
                when (it) {
                    is ApiResult.Loading -> {}
                    is ApiResult.Success -> {
                        it.data?.message?.let { it1 -> requireContext().showToast(it1) }
                        binding.txtBirthday.text = formatDateString("$year-${monthOfYear+1}-$dayOfMonth")
                    }

                    is ApiResult.Error -> {
                        it.message?.let { it1 -> requireContext().showToast(it1) }
                    }
                }
            }
        }, year, month, day)
        dpd.datePicker.maxDate = cal.timeInMillis
        dpd.show()
    }

    private fun showCustomVerifyDialog(email: String?, type: String) {
        binding.apply {
            CustomVerifiedDialogBinding.inflate(LayoutInflater.from(requireContext())).apply {
                if (type == "email") {
                    tvVerify.text = getString(R.string.verify_email)
                    tvEmail.text = getFormatText(getString(R.string.email))
                } else {
                    tvVerify.text = getString(R.string.verify_mobile_number)
                    tvEmail.text = getFormatText(getString(R.string.mobile_number))
                }

                txtEmail.text = email
                btnReqOtp.setOnClickListener {
                    authViewModel.getEmailVerificationCode(email = email)
                    authViewModel.requestEmailCode.observe(requireActivity()){
                        when (it) {
                            is ApiResult.Loading -> { progressBar.show() }
                            is ApiResult.Success -> {
                                progressBar.hide()
                                it.data?.message?.let { it1 -> requireContext().showToast(it1) }
                                startCountdown(this)
                                btnReqOtp.hide()
                                clVerification.show()
                            }

                            is ApiResult.Error -> {
                                progressBar.hide()
                                it.message?.let { it1 -> requireContext().showToast(it1) }
                            }
                        }
                    }
                }

                val builder = AlertDialog.Builder(requireContext())
                    .setView(root)
                    .setCancelable(false)
                    .create()

                ivClose.setOnClickListener { builder.dismiss() }
                btnSubmit.setOnClickListener {
                    if (txtVerificationCode.text.isNullOrEmpty().not()) {
                        authViewModel.verifyEmail(code= txtVerificationCode.text.toString())
                        authViewModel.verifyEmail.observe(requireActivity()) {
                            when (it) {
                                is ApiResult.Loading -> { progressBar.show() }
                                is ApiResult.Success -> {
                                    progressBar.hide()
                                    it.data?.message?.let { it1 -> requireContext().showToast(it1) }
                                    builder.dismiss()
                                }

                                is ApiResult.Error -> {
                                    progressBar.hide()
                                    it.message?.let { it1 -> requireContext().showToast(it1) }
                                }
                            }
                        }
                    } else {
                        requireContext().showToast("Please enter OTP")
                    }
                }
                builder.show()
            }
        }
    }

    private fun startCountdown(dialogBinding: CustomVerifiedDialogBinding) {
        object : CountDownTimer(5 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                dialogBinding.tvTime.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                dialogBinding.clVerification.hide()
                dialogBinding.btnReqOtp.show()
            }
        }.start()
    }

    private fun getFormatText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .append(" ")
            .underline { color(ContextCompat.getColor(requireContext(), R.color.not_verified)) { append(getString(R.string._live_chat)) }}
            .append(" ")
            .append(getString(R.string._to_change_your_profile_details))
    }

    private fun formatDateString(inputDate: String): String? {
        val possibleFormats = listOf("yyyy-M-d", "yyyy-MM-d", "yyyy-M-dd")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        for (format in possibleFormats) {
            try {
                val inputFormat = SimpleDateFormat(format, Locale.getDefault())
                val date = inputFormat.parse(inputDate)
                if (date != null) {
                    return outputFormat.format(date)
                }
            } catch (e: ParseException) {
                println(e.printStackTrace())
            }
        }
        return inputDate
    }
}