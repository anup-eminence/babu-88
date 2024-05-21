package com.sona.babu88.ui.multimarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.databinding.ItemMultiMarketBinding

class MultiMarketAdapter : RecyclerView.Adapter<MultiMarketAdapter.ViewHolder>() {
    var list = emptyList<MultiMarketList?>()

    private var onItemClickListener: OnItemClickListener? = null

    fun setData(itemList: List<MultiMarketList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemMultiMarketBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMultiMarketBinding.inflate(
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
                tvMatch.text = item.title
                tvMatchTitle.text = item.matchTitle1
                tvMatchTitle2.text = item.matchTitle2
            }
        }

        holder.binding.root.setOnClickListener {
            onItemClickListener?.onItemClickListener()
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener()
    }
}

data class MultiMarketList(val title: String, val matchTitle1: String, val matchTitle2: String)