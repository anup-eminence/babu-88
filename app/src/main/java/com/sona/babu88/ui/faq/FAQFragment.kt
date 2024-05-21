package com.sona.babu88.ui.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentFAQBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class FAQFragment : Fragment() {
    private lateinit var binding: FragmentFAQBinding
    private var toggle = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFAQBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        setData(getString(R.string.faq))
        binding.tvHeading.setOnClickListener {
            toggle = !toggle
            if (toggle) {
                binding.llDetail.show()
                binding.btnDown.rotation = 360F
            } else {
                binding.llDetail.hide()
                binding.btnDown.rotation = 180F
            }
        }
    }

    private fun setOnClickListener() {
        binding.apply {
            tvFAQ.setOnClickListener { setData(getString(R.string.faq)) }
            tvAboutUs.setOnClickListener { setData(getString(R.string.about_us)) }
            tvContactUs.setOnClickListener { setData(getString(R.string.contact_us)) }
            tvResponsibleGaming.setOnClickListener { setData(getString(R.string.responsible_gaming)) }
            tvTermCondition.setOnClickListener { setData(getString(R.string.terms_conditions)) }
        }
    }

    private fun setData(title: String) {
        binding.apply {
            tvTitle.text = title
            tvHeading.text = title
        }
    }
}