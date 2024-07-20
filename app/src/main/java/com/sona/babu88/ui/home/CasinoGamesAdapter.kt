package com.sona.babu88.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.api.model.response.CasinoImage
import com.sona.babu88.databinding.ItemHotGamesBinding

class CasinoGamesAdapter : RecyclerView.Adapter<CasinoGamesAdapter.ViewHolder>() {
    var list = emptyList<CasinoImage?>()

    private var onCasinoItemClickListener: OnCasinoItemClickListener? = null

    fun setCasinoGamesData(itemList: List<CasinoImage?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnCasinoItemClickListener(onCasinoItemClickListener: OnCasinoItemClickListener) {
        this.onCasinoItemClickListener = onCasinoItemClickListener
    }

    class ViewHolder(val binding: ItemHotGamesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHotGamesBinding.inflate(
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
            onCasinoItemClickListener?.onCasinoItemClickListener()
        }
    }

    interface OnCasinoItemClickListener {
        fun onCasinoItemClickListener()
    }
}