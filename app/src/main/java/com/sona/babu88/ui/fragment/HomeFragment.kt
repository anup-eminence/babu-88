package com.sona.babu88.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sona.babu88.databinding.FragmentHomeBinding
import com.sona.babu88.ui.adapter.ViewPagerAdapter
import com.sona.babu88.util.autoScroll
import com.sona.babu88.util.provideViewPagerList

class HomeFragment : Fragment() {

 private lateinit var binding : FragmentHomeBinding
    private lateinit var adapterVP: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterVP = ViewPagerAdapter(provideViewPagerList())
        setViewPager()
    }

    private fun setViewPager() {
        binding.viewpager.adapter = adapterVP

        /**
         * Start automatic scrolling with an
         * interval of 3 seconds.
         */
        binding.viewpager.autoScroll(3000)
    }



}