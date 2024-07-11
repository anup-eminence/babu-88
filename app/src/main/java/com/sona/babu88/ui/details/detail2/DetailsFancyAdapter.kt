package com.sona.babu88.ui.details.detail2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.DiamondItem
import com.sona.babu88.api.model.response.EventsFancy
import com.sona.babu88.databinding.ItemDetails2FancyBinding

class DetailsFancyAdapter : RecyclerView.Adapter<DetailsFancyAdapter.ViewHolder>() {
    var list = emptyList<DiamondItem?>()
    private var eventData: EventsFancy? = null

    private var onFancyItemClickListener: OnFancyItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setDetailsFancyData(itemList: List<DiamondItem?>?, events: EventsFancy?) {
        if (itemList != null) {
            val sortedList = itemList.sortedByDescending { item ->
                when (item?.ballsEss) {
                    "1" -> 1 // Higher priority for "ballsEss" value of "1"
                    else -> 0 // Default priority for other values (if any)
                }
            }
            list = sortedList
        }

        if (events != null) {
            eventData = events
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onFancyItemClickListener: OnFancyItemClickListener) {
        this.onFancyItemClickListener = onFancyItemClickListener
    }

    class ViewHolder(val binding: ItemDetails2FancyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemDetails2FancyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
//            if (holder.adapterPosition == 0) {
//                clTop.visibility = View.VISIBLE
//                tvHeader.text = "Normal Fancy"
//            } else {
//                clTop.visibility = View.GONE
//            }

            if (holder.adapterPosition == 0 || item?.ballsEss != list[position - 1]?.ballsEss) {
                clTop.visibility = View.VISIBLE
                if (item?.ballsEss == "1") {
                    tvHeader.text = "Normal Fancy"
                } else if (item?.ballsEss == "2") {
                    tvHeader.text = "Fancy Over"
                }
            } else {
                clTop.visibility = View.GONE
            }

            if (item?.gStatus?.lowercase() == "ball running") {
                tvSuspend.visibility = View.VISIBLE
                tvSuspend.text = root.context.getString(R.string.ball_running)
            } else if (item?.gStatus?.lowercase() == "suspended") {
                tvSuspend.visibility = View.VISIBLE
                tvSuspend.text = root.context.getString(R.string.suspend)
            } else {
                tvSuspend.visibility = View.GONE
            }

            tvTitle.text = item?.nat
            tvMatchTitle.text = ""
            text1.text = item?.l1?.toDouble()?.toInt().toString()
            text2.text = item?.ls1?.toDouble()?.toInt().toString()
            text3.text = item?.b1?.toDouble()?.toInt().toString()
            text4.text = item?.bs1?.toDouble()?.toInt().toString()

            cl1.setOnClickListener { onFancyItemClickListener?.onFancyClickListener(item, false) }
            cl2.setOnClickListener { onFancyItemClickListener?.onFancyClickListener(item, true) }
            ivI.setOnClickListener { onFancyItemClickListener?.onIBtnClickListener(view, eventData) }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnFancyItemClickListener {
        fun onFancyClickListener(item: DiamondItem?, back: Boolean)
        fun onIBtnClickListener(view: View, eventData: EventsFancy?)
    }
}