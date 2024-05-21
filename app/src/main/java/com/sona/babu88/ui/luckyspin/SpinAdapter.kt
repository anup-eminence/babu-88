package com.sona.babu88.ui.luckyspin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.databinding.ItemLuckySpinBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class SpinAdapter : RecyclerView.Adapter<SpinAdapter.ViewHolder>() {
    var list = emptyList<LuckySpinList?>()

    private var onItemClickListener: OnItemClickListener? = null

    fun setData(itemList: List<LuckySpinList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemLuckySpinBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLuckySpinBinding.inflate(
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
                if (position == 0) {
                    ivIphone.show()
                } else {
                    ivIphone.hide()
                    tvAmount.text = item.amount
                }
                tvNum.text = item.num.toString()
                tvAmount2.text = item.amount2
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

data class LuckySpinList(val num: Int, val amount: String, val amount2: String)