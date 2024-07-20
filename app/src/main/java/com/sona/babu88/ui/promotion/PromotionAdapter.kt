package com.sona.babu88.ui.promotion

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.api.model.response.Promotion
import com.sona.babu88.databinding.ItemPromotionBinding

class PromotionAdapter : RecyclerView.Adapter<PromotionAdapter.ViewHolder>() {
    var list = emptyList<Promotion?>()

    private var onItemClickListener: OnItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setPromotionData(itemList: List<Promotion?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemPromotionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPromotionBinding.inflate(
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
            Glide.with(root.context).load(item?.poster).into(image)
            tvTitle.text = item?.title
            tvName.text = item?.name
            tvDate.text =
                StringBuilder().append(item?.pstartDate).append(" ~ ").append(item?.pdrawDate)

            btnDetails.setOnClickListener {
                onItemClickListener?.onBtnDetailsClickListener(position)
            }
            btnApplyNow.setOnClickListener {
                onItemClickListener?.onBtnApplyNowClickListener()
            }
        }
    }

    interface OnItemClickListener {
        fun onBtnDetailsClickListener(position: Int)
        fun onBtnApplyNowClickListener()
    }
}