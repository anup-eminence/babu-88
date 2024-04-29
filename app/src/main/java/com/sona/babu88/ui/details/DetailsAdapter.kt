package com.sona.babu88.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.databinding.ItemDetailsRvBinding
import com.sona.babu88.model.DetailsList
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    var list = emptyList<DetailsList?>()

    private var onItemClickListener: OnItemClickListener? = null

    fun setDetailsData(itemList: List<DetailsList?>?) {
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
                tvName.text = item.text
                if (item.hot) {
                    imageHot.show()
                    tvNew.hide()
                } else if (item.new) {
                    imageHot.hide()
                    tvNew.show()
                }
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