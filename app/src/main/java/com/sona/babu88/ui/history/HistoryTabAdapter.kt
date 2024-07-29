package com.sona.babu88.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemHistoryTabBinding
import com.sona.babu88.model.HomeTab

class HistoryTabAdapter : RecyclerView.Adapter<HistoryTabAdapter.ViewHolder>() {
    private var selectedPosition = 0
    private var lastSelectedPosition = -1
    var list = emptyList<HomeTab?>()
    private var onTabItemClickListener: OnTabItemClickListener? = null

    fun setTabData(itemList: List<HomeTab?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnTabListener(onTabItemClickListener: OnTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener
    }

    class ViewHolder(val binding: ItemHistoryTabBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryTabBinding.inflate(
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
            Glide.with(root.context).load(item?.image).into(imageTab)
            textTab.text = item?.text
        }

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            onTabItemClickListener?.onTabItemClickListener(item)
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == holder.adapterPosition) {
            holder.binding.textTab.setTextColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.black
                )
            )
            holder.binding.imageTab.setColorFilter(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.black
                )
            )
            holder.binding.root.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    holder.binding.root.context,
                    R.drawable.bg_4_corner_shape_yellow
                )
            )
        } else {
            holder.binding.textTab.setTextColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.white
                )
            )
            holder.binding.imageTab.setColorFilter(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.white
                )
            )
            holder.binding.root.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    holder.binding.root.context,
                    R.drawable.bg_4_corner_shape_dark_grey
                )
            )
        }
    }

    interface OnTabItemClickListener {
        fun onTabItemClickListener(item: HomeTab?)
    }
}