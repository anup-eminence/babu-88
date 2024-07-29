package com.sona.babu88.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sona.babu88.R
import com.sona.babu88.api.model.response.TurnoverDataItem
import com.sona.babu88.databinding.FragmentDialogTurnoverBinding

class DialogTurnoverFragment(val data: TurnoverDataItem?) : DialogFragment() {
    private lateinit var binding: FragmentDialogTurnoverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogTurnoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            val percentage = ((data?.turnOver ?: 0.0) / (data?.requireTurnOver ?: 0.0)) * 100
            tvTitle.text = data?.promotionName ?: ""
            tvTransactionAmount.text = (data?.transactionAmt ?: 0.0).toInt().toString()
            tvBonus.text = (data?.bonus ?: 0.0).toInt().toString()
            tvTurnoverRequirement.text = (data?.requireTurnOver ?: 0.0).toInt().toString()
            tvTurnoverCompleted.text = (data?.turnOver ?: 0.0).toInt().toString()
            tvCompletedRatio.text =
                StringBuilder().append(String.format("%.2f", percentage)).append("%").toString()
            tvTurnoverCreateTime.text = data?.createdOn ?: ""
        }
    }

    private fun setOnClickListener() {
        binding.ivClose.setOnClickListener { dialog?.dismiss() }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}