package com.sona.babu88.ui.refer_earn

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ReferEarnVPAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyReferralsFragment()
            1 -> MyCommissionFragment()
            else -> throw IndexOutOfBoundsException("Invalid position $position")
        }
    }
}