package com.sona.babu88.ui.promotion

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.PromoFilterResponse
import com.sona.babu88.databinding.ItemPromotionTabBinding

class PromotionTabAdapter : RecyclerView.Adapter<PromotionTabAdapter.ViewHolder>() {
    private var selectedPosition = 0
    private var lastSelectedPosition = -1
    var list = emptyList<PromoFilterResponse?>()
    private var onTabItemClickListener: OnTabItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setFilterData(itemList: List<PromoFilterResponse?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnTabListener(onTabItemClickListener: OnTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener
    }

    class ViewHolder(val binding: ItemPromotionTabBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPromotionTabBinding.inflate(
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
            tvTab.text = item?.productName
        }

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            onTabItemClickListener?.onTabItemClickListener(position)
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == holder.adapterPosition) {
            holder.binding.tvTab.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.base_color_yellow))
            holder.binding.root.setBackgroundDrawable(ContextCompat.getDrawable(holder.binding.root.context, R.drawable.bg_20_rounded_dark))
        } else {
            holder.binding.tvTab.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.black))
            holder.binding.root.setBackgroundDrawable(ContextCompat.getDrawable(holder.binding.root.context, R.drawable.bg_20_rounded_grey))
        }
    }

    interface OnTabItemClickListener {
        fun onTabItemClickListener(position: Int)
    }
}