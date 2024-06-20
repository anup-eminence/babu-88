package com.sona.babu88.ui.details.detail2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemDetail2Binding

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {
    var selectedPosition = 0
    private var lastSelectedPosition = -1
    var list = emptyList<DetailsList?>()

    private var onClickListener: OnClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(itemList: List<DetailsList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    class ViewHolder(val binding: ItemDetail2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDetail2Binding.inflate(
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
                text.text = item.text
            }
        }

        holder.binding.apply {
            root.setOnClickListener {
                lastSelectedPosition = selectedPosition
                selectedPosition = holder.adapterPosition
                onClickListener?.onClickListener()
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }

            if (selectedPosition == holder.adapterPosition) {
                text.setBackgroundDrawable(ContextCompat.getDrawable(root.context, R.drawable.bg_4_solid_white))
                text.setTextColor(ContextCompat.getColor(root.context, R.color.black))
            } else {
                text.setBackgroundDrawable(ContextCompat.getDrawable(root.context, R.drawable.bg_4_corner_border))
                text.setTextColor(ContextCompat.getColor(root.context, R.color.white))
            }
        }
    }

    interface OnClickListener {
        fun onClickListener()
    }
}

data class DetailsList(
    val text: String
)



//06 May 2024 test: testing data           06 May 2024 test2: testing 2             06 May 2024 welcome to kingbd: We Welcome you all to KingBd.com