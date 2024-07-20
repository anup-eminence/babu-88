package com.sona.babu88.ui.promotiondetails

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.sona.babu88.api.model.response.Promotion
import com.sona.babu88.databinding.FragmentPromotionDetailsBinding

class PromotionDetailsFragment(val promotion: Promotion?) : DialogFragment() {
    private lateinit var binding: FragmentPromotionDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromotionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            tvName.text = promotion?.name?.split(" ")?.joinToString(" ") { it ->
                it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            }
            Glide.with(root.context).load(promotion?.poster).into(ivImage)
            tvDate.text = StringBuilder().append(promotion?.pstartDate).append(" - ")
                .append(promotion?.pdrawDate)
            tvDescription.text = Html.fromHtml(promotion?.description, Html.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun setOnClickListener() {
        binding.ivClose.setOnClickListener { dialog?.dismiss() }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}