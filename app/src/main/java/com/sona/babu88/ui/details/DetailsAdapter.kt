package com.sona.babu88.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.api.model.response.GameImage
import com.sona.babu88.databinding.ItemDetailsRvBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    var list = emptyList<GameImage?>()

    private var onItemClickListener: OnItemClickListener? = null

    fun setDetailsData(itemList: List<GameImage?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemDetailsRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDetailsRvBinding.inflate(
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
                tvName.text = item.name
                imageHot.show()
                tvNew.hide()
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