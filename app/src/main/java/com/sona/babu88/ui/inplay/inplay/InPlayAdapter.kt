package com.sona.babu88.ui.inplay.inplay

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.databinding.ItemCricketSportBinding

class InPlayAdapter : RecyclerView.Adapter<InPlayAdapter.ViewHolder>() {
    var list = emptyList<ResultItem?>()

    private var onInPlayClickListener: OnInPlayClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setInPlayData(itemList: List<ResultItem?>?) {
        if (itemList != null) {
            list = itemList
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            if (item != null) {
                tvText.text = item.matchName
            }
        }

        holder.binding.apply {
            root.setOnClickListener {
                onInPlayClickListener?.onInPlayClickListener(item)
            }
            btnPin.setOnClickListener {
                btnPin.setImageResource(R.drawable.ic_pin_active)
            }
        }
    }

    interface OnInPlayClickListener {
        fun onInPlayClickListener(item: ResultItem?)
    }
}