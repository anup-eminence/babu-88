package com.sona.babu88.ui.vipdetails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.api.model.response.PointListResponse
import com.sona.babu88.databinding.ItemDialogPointsBinding

class DialogPointsAdapter : RecyclerView.Adapter<DialogPointsAdapter.ViewHolder>() {
    var list = emptyList<PointListResponse?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setPointsData(itemList: List<PointListResponse?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemDialogPointsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDialogPointsBinding.inflate(
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
            tvProductType.text = item?.fullName
            tvBetAmount.text = (item?.betAmount ?: 0.0).toInt().toString()
            tvVipPoints.text = (item?.vipPoint ?: 0).toInt().toString()
        }
    }
}