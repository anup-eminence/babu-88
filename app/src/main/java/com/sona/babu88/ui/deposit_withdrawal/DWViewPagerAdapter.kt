package com.sona.babu88.ui.deposit_withdrawal

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sona.babu88.ui.deposit_withdrawal.deposit.DepositFragment

class DWViewPagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DepositFragment()
            1 -> WithdrawalFragment()
            else -> throw IndexOutOfBoundsException("Invalid position $position")
        }
    }
}