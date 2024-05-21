package com.sona.babu88.ui.details.detail2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.databinding.ItemDetail2Binding

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    var list = emptyList<DetailsList?>()

    private var onClickListener: OnClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(itemList: List<DetailsList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    class ViewHolder(val binding: ItemDetail2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDetail2Binding.inflate(
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
                text.text = item.text
            }
        }

        holder.binding.apply {
            root.setOnClickListener {
                onClickListener?.onClickListener()
            }
        }
    }

    interface OnClickListener {
        fun onClickListener()
    }
}

data class DetailsList(
    val text: String
)