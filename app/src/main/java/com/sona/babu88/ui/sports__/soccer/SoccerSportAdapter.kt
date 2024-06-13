package com.sona.babu88.ui.sports__.soccer

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.databinding.ItemSoccerSportBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class SoccerSportAdapter : RecyclerView.Adapter<SoccerSportAdapter.ViewHolder>() {
    var list = mutableListOf<ResultItem?>()
    var selectedPos = -1

    private var onSoccerClickListener: OnSoccerClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setSoccerData(itemList: List<ResultItem?>?) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        itemList?.sortedBy {
            it?.day?.let { day -> dateFormat.parse(day) }
        }?.let { list = it.toMutableList() }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onSoccerClickListener: OnSoccerClickListener) {
        this.onSoccerClickListener = onSoccerClickListener
    }

    class ViewHolder(val binding: ItemSoccerSportBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSoccerSportBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun notifyItemUpdated(pos: Int, resultItem: ResultItem) {
        list[pos] = resultItem
        notifyItemChanged(pos, resultItem)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            if (item != null) {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val date = LocalDateTime.parse(item.day, formatter)
                val currentDate = LocalDateTime.now()

                if (date.isBefore(currentDate) || date.equals(currentDate)) {
                    clInPlay.show()
                    clInPlayNot.hide()
                    tvText.text = item.matchName
                    imgRound.setImageResource(R.drawable.circle_shape)
                } else if (date.isAfter(currentDate)) {
                    clInPlayNot.show()
                    clInPlay.hide()
                    tvText.text = item.matchName
                    tvTime.text = item.day
                    imgRound.setImageResource(R.drawable.circle_shape_grey)
                }
            }

            if (item?.isPinned == true){
                btnPin.setImageResource(R.drawable.ic_pin_active)
            } else {
                btnPin.setImageResource(R.drawable.ic_pin_inactive)
            }
        }

        holder.binding.apply {
            root.setOnClickListener {
                onSoccerClickListener?.onSoccerClickListener(item)
            }
            btnPin.setOnClickListener {
                selectedPos = holder.adapterPosition
                onSoccerClickListener?.pinMatchClickListener(item, holder, holder.adapterPosition)
            }
        }
    }

    interface OnSoccerClickListener {
        fun onSoccerClickListener(item: ResultItem?)
        fun pinMatchClickListener(item: ResultItem?, holder: ViewHolder, adapterPosition: Int)
    }
}