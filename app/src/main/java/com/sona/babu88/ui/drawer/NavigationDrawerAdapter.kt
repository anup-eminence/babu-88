package com.sona.babu88.ui.drawer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemDrawerMenuBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class NavigationDrawerAdapter :
    ListAdapter<NavigationItem, NavigationDrawerAdapter.NavDrawerVH>(NavDrawerDiffUtil()) {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    private var navDrawerAdapterClickListener: NavDrawerAdapterClickListener? = null

    fun setItemAdapterListener(navDrawerAdapterClickListener: NavDrawerAdapterClickListener) {
        this.navDrawerAdapterClickListener = navDrawerAdapterClickListener
    }

    class NavDrawerVH(val binding: ItemDrawerMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavDrawerVH {
        return NavDrawerVH(
            ItemDrawerMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NavDrawerVH, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            itemName.text = item.name
            itemImage.setImageDrawable(item.image)

            if (item.header.isNullOrEmpty().not()) {
                itemHeader.show()
                itemView.show()
                itemHeader.text = item.header
            } else {
                itemHeader.hide()
                itemView.hide()
            }

            if (item.hot == true) {
                itemHot.show()
                itemNew.hide()
            } else if (item.new == true) {
                itemHot.hide()
                itemNew.show()
            }
            if (item.name == "Download App") {
                itemView.show()
            }
        }

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            navDrawerAdapterClickListener?.onAdapterItemClick(item)
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == holder.adapterPosition) {
            holder.binding.clItem.setBackgroundDrawable(ContextCompat.getDrawable(holder.binding.root.context, R.drawable.bg_8_shape_grey_solid))
        } else {
            holder.binding.clItem.setBackgroundDrawable(null)
        }
    }

    interface NavDrawerAdapterClickListener {
        fun onAdapterItemClick(item: NavigationItem)
    }
}