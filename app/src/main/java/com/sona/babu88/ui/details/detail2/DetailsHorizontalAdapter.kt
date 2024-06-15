package com.sona.babu88.ui.details.detail2

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.PreMatchMarket
import com.sona.babu88.databinding.ItemDetailsOdds2Binding

class DetailsHorizontalAdapter : RecyclerView.Adapter<DetailsHorizontalAdapter.ViewHolder>() {
    var selectedPosition = -1
    private var lastSelectedPosition = -1

    var list = emptyList<PreMatchMarket?>()

    private var onTabClickListener: OnTabClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(itemList: List<PreMatchMarket?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onTabClickListener: OnTabClickListener) {
        this.onTabClickListener = onTabClickListener
    }

    class ViewHolder(val binding: ItemDetailsOdds2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDetailsOdds2Binding.inflate(
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
            if (item != null) {
                tvMatchOdds.text = item.name
            }
        }

        holder.binding.apply {
            tvMatchOdds.setOnClickListener {
                lastSelectedPosition = selectedPosition
                selectedPosition = holder.adapterPosition
                onTabClickListener?.onTabClickListener(selectedPosition)
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }

            if (selectedPosition == holder.adapterPosition) {
                tvMatchOdds.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.black))
                tvMatchOdds.setTextColor(ContextCompat.getColor(root.context, R.color.text_yellow))
            } else {
                tvMatchOdds.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(root.context, R.color.bg_color))
                tvMatchOdds.setTextColor(ContextCompat.getColor(root.context, R.color.white))
            }
        }
    }

    interface OnTabClickListener {
        fun onTabClickListener(selectedPosition: Int)
    }
}