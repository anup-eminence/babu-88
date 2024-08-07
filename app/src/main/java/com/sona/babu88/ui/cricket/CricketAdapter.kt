package com.sona.babu88.ui.cricket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.databinding.ItemCricketBinding
import com.sona.babu88.model.FishingList

class CricketAdapter : RecyclerView.Adapter<CricketAdapter.ViewHolder>() {
    var list = emptyList<FishingList?>()

    private var onItemClickListener: OnItemClickListener? = null

    fun setCricketData(itemList: List<FishingList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemCricketBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCricketBinding.inflate(
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
                Glide.with(root.context).load(item.image).into(image)
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