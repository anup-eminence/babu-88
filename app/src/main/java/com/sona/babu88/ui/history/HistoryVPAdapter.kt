package com.sona.babu88.ui.history

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HistoryVPAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BettingRecordsFragment()
            1 -> TurnoverFragment()
            2 -> TransactionRecordsFragment()
            3 -> ProfitLossFragment()
            else -> throw IndexOutOfBoundsException("Invalid position $position")
        }
    }
}