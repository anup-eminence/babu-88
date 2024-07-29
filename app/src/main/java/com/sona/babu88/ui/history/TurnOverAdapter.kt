package com.sona.babu88.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.TurnoverDataItem
import com.sona.babu88.databinding.ItemTurnoverBinding
import kotlin.math.roundToInt

class TurnOverAdapter : RecyclerView.Adapter<TurnOverAdapter.ViewHolder>() {
    var list = emptyList<TurnoverDataItem?>()
    private var onItemClickListener: OnItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setTurnOverData(itemList: List<TurnoverDataItem?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnTabListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemTurnoverBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTurnoverBinding.inflate(
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
            tvTitle.text = item?.promotionName ?: ""
            tvEventEnd.text =
                StringBuilder().append(root.context.getString(R.string.event_ends_in)).append(" ")
                    .append(item?.endsOn ?: "")
            tvTurnoverAmount.text =
                StringBuilder().append("₹ ").append((item?.turnOver ?: 0.0).toInt())
            tvReqTurnoverAmount.text =
                StringBuilder().append(" / ₹ ").append((item?.requireTurnOver ?: 0.0).toInt())
            val percentage = ((item?.turnOver ?: 0.0) / (item?.requireTurnOver ?: 0.0)) * 100
            progress.progress = percentage.roundToInt()
            tvPercentage.text =
                StringBuilder().append(String.format("%.2f", percentage)).append("%").toString()

            btnDetail.setOnClickListener { onItemClickListener?.onDetailClickListener(position) }
        }
    }

    interface OnItemClickListener {
        fun onDetailClickListener(position: Int)
    }
}