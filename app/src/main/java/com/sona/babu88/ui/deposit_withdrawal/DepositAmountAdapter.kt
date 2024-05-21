package com.sona.babu88.ui.deposit_withdrawal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.databinding.ItemDeositAmountBinding

class DepositAmountAdapter : RecyclerView.Adapter<DepositAmountAdapter.ViewHolder>() {
    var list = emptyList<DepositAmountList?>()

    private var onAmountClickListener: OnAmountClickListener? = null

    fun setDepositAmountData(itemList: List<DepositAmountList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setAmountClickListener(onAmountClickListener: OnAmountClickListener) {
        this.onAmountClickListener = onAmountClickListener
    }

    class ViewHolder(val binding: ItemDeositAmountBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDeositAmountBinding.inflate(
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
                tvAmount.text = item.amount
            }
        }

        holder.binding.root.setOnClickListener {
            onAmountClickListener?.onAmountClickListener(item)
        }
    }

    interface OnAmountClickListener {
        fun onAmountClickListener(item: DepositAmountList?)
    }
}

data class DepositAmountList(
    val amount: String
)