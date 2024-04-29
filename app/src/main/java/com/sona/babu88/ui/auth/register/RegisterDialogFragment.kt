package com.sona.babu88.ui.auth.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import com.sona.babu88.databinding.RegisterDialogFragmentBinding
import com.sona.babu88.ui.auth.adapter.RegisterVPAdapter
import com.sona.babu88.util.setWidthPercent

class RegisterDialogFragment(
    private val registerDialogListener: RegisterDialogListener
) : DialogFragment(), RegisterVPAdapter.RegisterDialogListener {

    private lateinit var binding: RegisterDialogFragmentBinding
    private lateinit var vpAdapter: RegisterVPAdapter
    var PAGE = 0

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
                if (position == 0){
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

    override fun moveToNextRegisterPage() {
        PAGE = 2
        binding.vp.setCurrentItem(1, true)
    }

    interface RegisterDialogListener {
        fun moveToLogin()
    }
}