package com.sona.babu88.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sona.babu88.R
import com.sona.babu88.databinding.LayoutCurrencyLanguageBinding

class CurrLangDialogFragment : DialogFragment() {
    private lateinit var binding: LayoutCurrencyLanguageBinding
    private var onItemClick: OnItemClick? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = LayoutCurrencyLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclickListener()
    }

    private fun setOnclickListener() {
        binding.apply {
            imageWrong.setOnClickListener {
                onItemClick?.onCloseCLick()
            }
            bdtEnglish.setOnClickListener { setLanguage(R.drawable.ic_country_bdt) }
            bdtBengali.setOnClickListener { setLanguage(R.drawable.ic_country_bdt) }

            inrEnglish.setOnClickListener { setLanguage(R.drawable.ic_country_inr) }
            inrHindi.setOnClickListener { setLanguage(R.drawable.ic_country_inr) }

            nprEnglish.setOnClickListener { setLanguage(R.drawable.ic_country_npr) }
            nprNepalese.setOnClickListener { setLanguage(R.drawable.ic_country_npr) }
        }
    }

    private fun setLanguage(image: Int) {
        onItemClick?.onSelectItem(image)
    }

    fun clickListener(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }

    interface OnItemClick {
        fun onCloseCLick()
        fun onSelectItem(image: Int)
    }
}