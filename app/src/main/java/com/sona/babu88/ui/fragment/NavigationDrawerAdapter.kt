package com.sona.babu88.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.databinding.ItemDrawerMenuBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class NavigationDrawerAdapter :
    ListAdapter<NavigationItem, NavigationDrawerAdapter.NavDrawerVH>(NavDrawerDiffUtil()) {

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

            if (item.hot_new?.equals(null) == true) {
                itemHotNew.hide()
            } else {
                itemHotNew.show()
                itemHotNew.setImageDrawable(item.hot_new)
            }
        }
        holder.binding.root.setOnClickListener {
            navDrawerAdapterClickListener?.onAdapterItemClick(item!!)
        }
    }

    interface NavDrawerAdapterClickListener {
        fun onAdapterItemClick(item: NavigationItem)
    }
}