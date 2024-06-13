package com.sona.babu88.ui.inplay.inplay

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.databinding.ItemCricketSportBinding

class InPlayAdapter : RecyclerView.Adapter<InPlayAdapter.ViewHolder>() {
    var list = mutableListOf<ResultItem?>()
    var selectedPos = -1

    private var onInPlayClickListener: OnInPlayClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setInPlayData(itemList: List<ResultItem?>?) {
        if (itemList != null) {
            list = itemList.toMutableList()
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onInPlayClickListener: OnInPlayClickListener) {
        this.onInPlayClickListener = onInPlayClickListener
    }

    class ViewHolder(val binding: ItemCricketSportBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCricketSportBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun notifyItemUpdated(pos: Int, resultItem: ResultItem) {
        list[pos] = resultItem
        notifyItemChanged(pos, resultItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            if (item != null) {
                tvText.text = item.matchName
            }

            if (item?.isPinned == true){
                btnPin.setImageResource(R.drawable.ic_pin_active)
            } else {
                btnPin.setImageResource(R.drawable.ic_pin_inactive)
            }
        }

        holder.binding.apply {
            root.setOnClickListener {
                onInPlayClickListener?.onInPlayClickListener(item)
            }
            btnPin.setOnClickListener {
                selectedPos = holder.adapterPosition
                onInPlayClickListener?.pinMatchClickListener(item, holder.adapterPosition)
            }
        }
    }

    interface OnInPlayClickListener {
        fun onInPlayClickListener(item: ResultItem?)
        fun pinMatchClickListener(item: ResultItem?, adapterPosition: Int)
    }
}