package com.sona.babu88.ui.vipdetails

import android.annotation.SuppressLint
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemFaqVipBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class FaqVipAdapter : RecyclerView.Adapter<FaqVipAdapter.ViewHolder>() {
    var list = emptyList<FaqList?>()
    private var currentPosition: Int? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setFaqData(itemList: List<FaqList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemFaqVipBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFaqVipBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            if (item?.isHeading == true) {
                tvHeading.show()
                tvHeading.text = item.heading
            } else tvHeading.hide()
            if (item?.question.isNullOrEmpty() && item?.answer.isNullOrEmpty()) {
                clLayout.hide()
            } else {
                clLayout.show()
                tvQues.text = item?.question
                if (item?.link != null) tvAns.text = setString(root.context, item.answer, item.link)
                else tvAns.text = item?.answer
            }

            if (currentPosition == position) {
                tvAns.show()
                clLayout.setBackgroundResource(R.drawable.bg_vip_faq)
            } else {
                tvAns.hide()
                clLayout.setBackgroundResource(R.drawable.bg_vip_faq_solid)
            }

            tvQues.setOnClickListener {
                val previousPosition = currentPosition
                currentPosition = if (previousPosition == position) {
                    null
                } else {
                    position
                }
                notifyItemChanged(previousPosition ?: -1)
                notifyItemChanged(position)
            }
            ivPlus.setOnClickListener { tvQues.performClick() }
        }
    }

    private fun setString(context: Context, text1: String, text2: String): SpannableString {
        val spannableString = SpannableString("$text1$text2")
        val colorSpan = ForegroundColorSpan(context.getColor(R.color.vip_text))
        spannableString.setSpan(colorSpan, text1.length, text1.length + text2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(UnderlineSpan(), text1.length, text1.length + text2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}

data class FaqList(
    val heading: String, val isHeading: Boolean, val question: String, val answer: String, val link: String ?= null
)