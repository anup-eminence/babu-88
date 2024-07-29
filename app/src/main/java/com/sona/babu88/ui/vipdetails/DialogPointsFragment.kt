package com.sona.babu88.ui.vipdetails

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.viewmodel.HomeViewModel
import com.sona.babu88.databinding.FragmentDialogPointsBinding
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class DialogPointsFragment : DialogFragment() {
    private lateinit var binding: FragmentDialogPointsBinding
    private lateinit var dialogPointsAdapter: DialogPointsAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogPointsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            tvExample1.text =
                setString(getString(R.string.example_1), getString(R.string.example1_text))
            tvExample2.text =
                setString(getString(R.string.example_2), getString(R.string.example2_text))
        }
        setDialogPointsAdapter()
        observerPointList()
    }

    private fun setOnClickListener() {
        binding.ivClose.setOnClickListener { dialog?.dismiss() }
    }

    private fun setDialogPointsAdapter() {
        binding.rvPoints.layoutManager = LinearLayoutManager(requireContext())
        dialogPointsAdapter = DialogPointsAdapter()
        binding.rvPoints.adapter = dialogPointsAdapter
    }

    private fun observerPointList() {
        homeViewModel.getPointList()
        homeViewModel.pointList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    dialogPointsAdapter.setPointsData(it.data)
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                }
            }
        }
    }

    private fun setString(text1: String, text2: String): SpannableString {
        val spannableString = SpannableString("$text1 $text2")
        spannableString.setSpan(
            ForegroundColorSpan(requireContext().getColor(R.color.vip_text)),
            0,
            text1.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            1000
        )
    }
}