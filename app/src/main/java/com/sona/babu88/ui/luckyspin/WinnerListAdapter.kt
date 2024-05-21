package com.sona.babu88.ui.luckyspin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.databinding.ItemWinnerListBinding

class WinnerListAdapter : RecyclerView.Adapter<WinnerListAdapter.ViewHolder>() {
    var list = emptyList<WinnerList?>()

    private var onItemWinnerClickListener: OnItemWinnerClickListener? = null

    fun setData(itemList: List<WinnerList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemWinnerClickListener: OnItemWinnerClickListener) {
        this.onItemWinnerClickListener = onItemWinnerClickListener
    }

    class ViewHolder(val binding: ItemWinnerListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWinnerListBinding.inflate(
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
                tvDate.text = item.date
                tvName.text = item.username
                tvAmount.text = item.amount
            }
        }

        holder.binding.root.setOnClickListener {
            onItemWinnerClickListener?.onItemWinnerClickListener()
        }
    }

    interface OnItemWinnerClickListener {
        fun onItemWinnerClickListener()
    }
}

data class WinnerList(val date: String, val username: String, val amount: String)