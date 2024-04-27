package com.sona.babu88.ui.fragment

import androidx.recyclerview.widget.DiffUtil

class NavDrawerDiffUtil : DiffUtil.ItemCallback<NavigationItem>() {
    override fun areItemsTheSame(oldItem: NavigationItem, newItem: NavigationItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: NavigationItem, newItem: NavigationItem): Boolean {
       return oldItem == newItem
    }
}