package com.sona.babu88.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.api.model.response.TransactionResultsItem
import com.sona.babu88.databinding.ItemBettingRecordsBinding

class BettingRecordDataAdapter : RecyclerView.Adapter<BettingRecordDataAdapter.ViewHolder>() {
    var list = emptyList<TransactionResultsItem?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTransactionData(itemList: List<TransactionResultsItem?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemBettingRecordsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBettingRecordsBinding.inflate(
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
            tvDate.text = item?.date
            tvSportName.text = item?.sportName
            tvSource.text = item?.sourceType
            tvWinLose.text = stringBuilder((item?.pnl ?: 0.0).toInt().toString())
            tvCommission.text = stringBuilder((item?.commM ?: 0.0).toInt().toString())
            tvTurnover.text = stringBuilder((item?.turnOver ?: 0.0).toInt().toString())
        }
    }

    private fun stringBuilder(data: String): String {
        return StringBuilder().append("₹").append(" ").append(data).toString()
    }
}