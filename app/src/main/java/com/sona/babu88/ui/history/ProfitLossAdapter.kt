package com.sona.babu88.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.api.model.response.AccountLogsDataItem
import com.sona.babu88.databinding.ItemProfitLossBinding

class ProfitLossAdapter : RecyclerView.Adapter<ProfitLossAdapter.ViewHolder>() {
    var list = emptyList<AccountLogsDataItem?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setProfitLossData(itemList: List<AccountLogsDataItem?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemProfitLossBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProfitLossBinding.inflate(
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
            tvDate.text = item?.createdOn ?: ""
            tvPlus.text =
                if (item?.isCredit == true) (item.amount ?: 0.0).toInt().toString() else "0"
            tvMinus.text =
                if (item?.isCredit == true) "0" else (item?.amount ?: 0.0).toInt().toString()
            tvBalance.text = (item?.balance ?: 0.0).toInt().toString()
            tvRemark.text = item?.narration ?: ""
        }
    }
}