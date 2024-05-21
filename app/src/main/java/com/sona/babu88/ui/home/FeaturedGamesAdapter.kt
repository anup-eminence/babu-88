package com.sona.babu88.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.databinding.ItemHomeFeaturedGamesBinding
import com.sona.babu88.model.FishingList

class FeaturedGamesAdapter : RecyclerView.Adapter<FeaturedGamesAdapter.ViewHolder>() {
    var list = emptyList<FeaturedGameList?>()

    private var onFeaturedItemClickListener: OnFeaturedItemClickListener? = null

    fun setFeaturedGamesData(itemList: List<FeaturedGameList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnFeaturedItemClickListener(onFeaturedItemClickListener: OnFeaturedItemClickListener) {
        this.onFeaturedItemClickListener = onFeaturedItemClickListener
    }

    class ViewHolder(val binding: ItemHomeFeaturedGamesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeFeaturedGamesBinding.inflate(
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
            onFeaturedItemClickListener?.onFeaturedItemClickListener()
        }
    }

    interface OnFeaturedItemClickListener {
        fun onFeaturedItemClickListener()
    }
}


data class FeaturedGameList(
    val image: Int
)