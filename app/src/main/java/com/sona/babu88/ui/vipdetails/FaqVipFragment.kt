package com.sona.babu88.ui.vipdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentFaqVipBinding

class FaqVipFragment : Fragment() {
    private lateinit var binding: FragmentFaqVipBinding
    private lateinit var faqVipAdapter: FaqVipAdapter
    private val faqList = arrayListOf<FaqList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFaqVipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setFaqAdapter()
        setAdapterData()
    }

    private fun setFaqAdapter() {
        binding.rvFaq.layoutManager = LinearLayoutManager(requireContext())
        faqVipAdapter = FaqVipAdapter()
        binding.rvFaq.adapter = faqVipAdapter
    }

    private fun setAdapterData() {
        faqList.add(FaqList(isHeading = true, heading = getString(R.string.program_information), question = "", answer = ""))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.ques_1), answer = getString(R.string.ans_1)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.ques_2), answer = getString(R.string.ans_2)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.ques_3), answer = getString(R.string.ans_3)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.ques_4), answer = getString(R.string.ans_4), link = getString(R.string.ans_41)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.ques_5), answer = getString(R.string.ans_5)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.ques_6), answer = getString(R.string.ans_6)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.ques_7), answer = getString(R.string.ans_7)))

        faqList.add(FaqList(isHeading = true, heading = getString(R.string.vip_points_redeem), question = "", answer = ""))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.q_1), answer = getString(R.string.a_1), link = getString(R.string.a_11)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.q_2), answer = getString(R.string.a_2)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.q_3), answer = getString(R.string.a_3)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.q_4), answer = getString(R.string.a_4)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.q_5), answer = getString(R.string.a_5)))
        faqList.add(FaqList(isHeading = false, heading = "", question = getString(R.string.q_6), answer = getString(R.string.a_6)))
        faqVipAdapter.setFaqData(faqList)
    }
}