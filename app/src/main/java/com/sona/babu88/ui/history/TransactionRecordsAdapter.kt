package com.sona.babu88.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.TransactionsDataItem
import com.sona.babu88.databinding.ItemTransactionRecordsBinding

class TransactionRecordsAdapter : RecyclerView.Adapter<TransactionRecordsAdapter.ViewHolder>() {
    var list = emptyList<TransactionsDataItem?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTransactionRecordsData(itemList: List<TransactionsDataItem?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemTransactionRecordsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTransactionRecordsBinding.inflate(
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
            if (item?.type == "Deposit") {
                method.text = root.context.getString(R.string.deposit_method)
                channel.text = root.context.getString(R.string.deposit_channel)
                id.text = root.context.getString(R.string.deposit_id)
                amount.text = root.context.getString(R.string.deposit_amount)
            } else {
                method.text = root.context.getString(R.string.withdraw_method)
                channel.text = root.context.getString(R.string.withdraw_channel)
                id.text = root.context.getString(R.string.withdraw_id)
                amount.text = root.context.getString(R.string.withdraw_amount)
            }

            tvDate.text = StringBuilder(item?.createdOnDate ?: "").append(" ")
                .append(item?.createdOnTime ?: "")
            tvMethod.text = item?.method ?: ""
            tvChannel.text = item?.channel ?: ""
            tvId.text = item?.userTransId ?: ""
            tvAmount.text = StringBuilder().append("₹").append(" ")
                .append((item?.amount ?: 0.0).toInt().toString())
            tvMobileNumber.text = item?.address ?: ""
        }
    }
}