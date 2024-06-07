package com.sona.babu88.ui.multimarket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.DataItem
import com.sona.babu88.databinding.ItemMultiMarketBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class MultiMarketAdapter : RecyclerView.Adapter<MultiMarketAdapter.ViewHolder>() {
    var list = emptyList<DataItem?>()

    private var onItemClickListener: OnItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(itemList: List<DataItem?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemMultiMarketBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMultiMarketBinding.inflate(
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
                tvMatch.text = item.matchName

                val parts = item.matchName?.split(" v ")
                if (parts?.size == 2) {
                    tvMatchTitle2.show()
                    cl3.show()
                    cl4.show()
                    view2.show()
                    tvMatchTitle.text = parts[0]
                    tvMatchTitle2.text = parts[1]
                } else {
                    tvMatchTitle2.hide()
                    cl3.hide()
                    cl4.hide()
                    view2.hide()
                    tvMatchTitle.text = item.matchName
                }

                when (item.sportId) {
                    4 -> tvSportName.text = root.context.getString(R.string.cricket)
                    1 -> tvSportName.text = root.context.getString(R.string.soccer)
                    2 -> tvSportName.text = root.context.getString(R.string.tennis)
                }
            }
        }

        holder.binding.apply {
            root.setOnClickListener {
                onItemClickListener?.onItemClickListener()
            }

            ivPin.setOnClickListener {
                ivPin.setImageResource(R.drawable.ic_pin_inactive)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener()
    }
}