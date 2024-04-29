package com.sona.babu88.ui.auth.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sona.babu88.ui.auth.register.RegisterFragment1
import com.sona.babu88.ui.auth.register.RegisterFragment2

class RegisterVPAdapter(
    fragmentManager: FragmentManager,
    private val registerDialogListener: RegisterDialogListener
) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RegisterFragment1(registerDialogListener)
            1 -> RegisterFragment2(registerDialogListener)
            else -> RegisterFragment1(registerDialogListener)
        }
    }

    interface RegisterDialogListener {
        fun dismissRegisterDialog()
        fun moveToLoginPage()
        fun moveToNextRegisterPage()
    }
}