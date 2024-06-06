package com.sona.babu88.ui.inplay

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sona.babu88.ui.inplay.inplay.InPlayTabFragment
import com.sona.babu88.ui.inplay.today.TodayFragment
import com.sona.babu88.ui.inplay.tomorrow.TomorrowFragment

class InPlayViewPager(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InPlayTabFragment()
            1 -> TodayFragment()
            2 -> TomorrowFragment()
            else -> throw IndexOutOfBoundsException("Invalid position $position")
        }
    }
}