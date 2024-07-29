package com.sona.babu88.ui.deposit_withdrawal.deposit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemPaymentMethodsBinding
import com.sona.babu88.model.HomeTab

class PaymentMethodsAdapter : RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder>() {
    var selectedPosition = -1
    private var lastSelectedPosition = -1
    var list = emptyList<HomeTab?>()
    private var onTabItemClickListener: OnTabItemClickListener? = null

    fun setPaymentMethodsData(itemList: List<HomeTab?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnTabListener(onTabItemClickListener: OnTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener
    }

    class ViewHolder(val binding: ItemPaymentMethodsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPaymentMethodsBinding.inflate(
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
            Glide.with(root.context).load(item?.image).into(image)
        }

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            onTabItemClickListener?.onTabItemClickListener(item)
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == holder.adapterPosition) {
            holder.binding.clImage.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    holder.binding.root.context, R.drawable.bg_20_rounded_yellow
                )
            )
        } else {
            holder.binding.clImage.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    holder.binding.root.context, R.drawable.bg_20_rounded_grey
                )
            )
        }
    }

    interface OnTabItemClickListener {
        fun onTabItemClickListener(item: HomeTab?)
    }
}