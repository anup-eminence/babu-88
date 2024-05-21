package com.sona.babu88.ui.inplay

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sona.babu88.ui.sports__.cricket.CricketSportFragment
import com.sona.babu88.ui.sports__.soccer.SoccerSportFragment
import com.sona.babu88.ui.sports__.tennis.TennisSportFragment

class InPlayViewPager(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CricketSportFragment()
            1 -> SoccerSportFragment()
            2 -> TennisSportFragment()
            else -> throw IndexOutOfBoundsException("Invalid position $position")
        }
    }
}