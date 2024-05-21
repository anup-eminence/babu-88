package com.sona.babu88.ui.history

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HistoryVPAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BetHistoryFragment()
            1 -> TurnoverHistoryFragment()
            2 -> WalletHistoryFragment()
            else -> throw IndexOutOfBoundsException("Invalid position $position")
        }
    }
}