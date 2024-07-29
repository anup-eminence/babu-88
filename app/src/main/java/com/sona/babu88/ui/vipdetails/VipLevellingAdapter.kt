package com.sona.babu88.ui.vipdetails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.databinding.ItemVipLevellingBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class VipLevellingAdapter : RecyclerView.Adapter<VipLevellingAdapter.ViewHolder>() {
    var list = emptyList<VipLevels?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setVipLevelData(itemList: List<VipLevels?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemVipLevellingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVipLevellingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.root.layoutParams = RecyclerView.LayoutParams(0, 0)
            holder.binding.root.hide()
        } else {
            val item = list[position]
            holder.binding.apply {
                root.show()
                Glide.with(root.context).load(item?.image).into(image)
                tvName.text = item?.fullName
                tvPoints.text = item?.reqPoints.toString()
                tvName.setTextColor(ContextCompat.getColor(root.context, item?.color ?: 0))
            }
        }
    }
}

data class VipLevels(val image: Int, val fullName: String, val color: Int, val reqPoints: Int, val vipToCashRatio: Int)