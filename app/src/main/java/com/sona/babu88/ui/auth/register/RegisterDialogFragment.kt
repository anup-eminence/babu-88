package com.sona.babu88.ui.auth.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.RegisterUserRequest
import com.sona.babu88.data.viewmodel.AuthViewModel
import com.sona.babu88.databinding.RegisterDialogFragmentBinding
import com.sona.babu88.ui.auth.adapter.RegisterVPAdapter
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.setWidthPercent
import com.sona.babu88.util.showToast

class RegisterDialogFragment(
    private val registerDialogListener: RegisterDialogListener
) : DialogFragment(), RegisterVPAdapter.RegisterDialogListener {

    private lateinit var binding: RegisterDialogFragmentBinding
    private lateinit var vpAdapter: RegisterVPAdapter
    private val authViewModel : AuthViewModel by viewModels()
    var PAGE = 0

    var selectedUserName = ""
    var selectedPassword = ""
    var selectedCurrency = ""
    var selectedPhoneNumber = ""
    var selectedReffalCode = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterDialogFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setWidthPercent(95)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpAdapter = RegisterVPAdapter(childFragmentManager, this@RegisterDialogFragment)
        setAdapter()
        binding.close.setOnClickListener {
            dismiss()
        }

        setUpVp()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpVp() {
        binding.vp.isSaveEnabled = false
        binding.vp.setOnTouchListener { v, event ->
            if (binding.vp.currentItem == PAGE) {
                binding.vp.setCurrentItem(PAGE - 1, false)
                binding.vp.setCurrentItem(PAGE, false)
                return@setOnTouchListener true
            }
            false
        }

        binding.vp?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 0) {
                    PAGE = 0
                }
            }

            override fun onPageSelected(position: Int) {

            }

        })

    }

    private fun setAdapter() {
        binding.vp.adapter = vpAdapter
    }

    override fun dismissRegisterDialog() {
        dismiss()
    }

    override fun moveToLoginPage() {
        dismiss()
        registerDialogListener.moveToLogin()
    }

    override fun makeApiCall(selectedPhone: String, selectedReferral: String, email: String) {

        authViewModel.registerUser.observe(viewLifecycleOwner){
            when(it){
                is ApiResult.Loading -> {}
                is ApiResult.Success -> {
                    dismiss()
                    it.data?.message?.let { msg ->
                        requireContext().showToast(msg)
                    }
                }
                is ApiResult.Error -> {
                    requireContext().showToast("Registration Failed")
                }
            }
        }

        val request = AppConstant.getTimeStamp()
        val registerUserRequest = RegisterUserRequest(
            userId = selectedUserName.lowercase(),
            userName = selectedUserName.lowercase(),
            passWord = selectedPassword,
            mobileNumber = selectedPhone,
            email = email,
            refCode = "",
            currency = selectedCurrency,
            timeStamp = request[AppConstant.TIMESTAMP].toString(),
            secretKey =  request[AppConstant.SECRET_KEY].toString()
        )
        authViewModel.registerUser(
            registerUserRequest
        )

    }

    override fun moveToNextRegisterPage(
        selectedUserName: String,
        selectedPassword: String,
        selectedCurrency: String
    ) {
        this.selectedUserName = selectedUserName
        this.selectedPassword = selectedPassword
        this.selectedCurrency = selectedCurrency
        PAGE = 2
        binding.vp.setCurrentItem(1, true)
    }

    interface RegisterDialogListener {
        fun moveToLogin()
    }
}