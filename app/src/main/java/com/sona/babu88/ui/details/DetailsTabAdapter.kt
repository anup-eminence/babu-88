package com.sona.babu88.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemDetailsFragmentBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class DetailsTabAdapter : RecyclerView.Adapter<DetailsTabAdapter.ViewHolder>() {
    var selectedPosition = -1
    private var lastSelectedPosition = -1
    var list = emptyList<HomeTab?>()
    private var onTabItemClickListener: OnTabItemClickListener? = null

    fun findPosition(title: String): Int {
        val item = list.find { it?.text == title }
        return if (title.equals("ALL", ignoreCase = true)) 0 else list.indexOf(item)
    }

    fun setTabData(itemList: List<HomeTab?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnTabListener(onTabItemClickListener: OnTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener
    }

    class ViewHolder(val binding: ItemDetailsFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDetailsFragmentBinding.inflate(
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
            if (holder.adapterPosition == 0) tvAll.show() else tvAll.hide()
            Glide.with(root.context).load(item?.image).into(image)
            text.text = item?.text
        }

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            onTabItemClickListener?.onTabItemClickListener(item)
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == holder.adapterPosition) {
            holder.binding.clImage.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    holder.binding.root.context,
                    R.drawable.bg_20_rounded_yellow
                )
            )
        } else {
            holder.binding.clImage.setBackgroundDrawable(null)
        }
    }

    interface OnTabItemClickListener {
        fun onTabItemClickListener(item: HomeTab?)
    }
}