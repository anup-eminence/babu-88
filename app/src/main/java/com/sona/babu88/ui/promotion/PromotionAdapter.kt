package com.sona.babu88.ui.promotion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.databinding.ItemPromotionBinding

class PromotionAdapter : RecyclerView.Adapter<PromotionAdapter.ViewHolder>() {
    var list = emptyList<PromotionList?>()

    private var onItemClickListener: OnItemClickListener? = null

    fun setPromotionData(itemList: List<PromotionList?>?) {
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
            Glide.with(root.context).load(item?.image).into(image)
            tvText1.text = item?.text1
            tvText2.text = item?.text2

            btnDetails.setOnClickListener {
                onItemClickListener?.onBtnDetailsClickListener()
            }
            btnApplyNow.setOnClickListener {
                onItemClickListener?.onBtnApplyNowClickListener()
            }
        }
    }

    interface OnItemClickListener {
        fun onBtnDetailsClickListener()
        fun onBtnApplyNowClickListener()
    }
}

data class PromotionList(
    val image: Int, val text1: String, val text2: String
)