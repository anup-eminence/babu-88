package com.sona.babu88.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var list = emptyList<ResultItem?>()
    private var onSearchItemClickListener: OnSearchItemClickListener? = null

    fun setOnItemClickListener(onSearchItemClickListener: OnSearchItemClickListener) {
        this.onSearchItemClickListener = onSearchItemClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSearchData(itemList: List<ResultItem?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            tvTitle.text = item?.matchName
            tvTitle.setOnClickListener {
                onSearchItemClickListener?.onSearchClickListener(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnSearchItemClickListener {
        fun onSearchClickListener(item: ResultItem?)
    }
}