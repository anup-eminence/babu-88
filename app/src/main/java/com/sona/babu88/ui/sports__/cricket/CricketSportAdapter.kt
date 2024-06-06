package com.sona.babu88.ui.sports__.cricket

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.databinding.ItemCricketSportBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class CricketSportAdapter : RecyclerView.Adapter<CricketSportAdapter.ViewHolder>() {
    var list = mutableListOf<ResultItem?>()

    private var onCricketSportClickListener: OnCricketSportClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setCricketSportData(itemList: List<ResultItem?>?) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        itemList?.sortedBy {
            it?.day?.let { day -> dateFormat.parse(day) }
        }?.let { list = it.toMutableList() }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onCricketSportClickListener: OnCricketSportClickListener) {
        this.onCricketSportClickListener = onCricketSportClickListener
    }

    class ViewHolder(val binding: ItemCricketSportBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCricketSportBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun notifyItemUpdated(pos : Int,resultItem: ResultItem){
        list[pos] = resultItem
        notifyItemChanged(pos,list)
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
        }
        if (item?.isPinned == true){
            holder.binding.btnPin.setImageResource(R.drawable.ic_pin_active)
        } else {
            holder.binding.btnPin.setImageResource(R.drawable.ic_pin_inactive)
        }

        holder.binding.apply {
            root.setOnClickListener {
                onCricketSportClickListener?.onCricketSportClickListener(item)
            }
            btnPin.setOnClickListener {
                onCricketSportClickListener?.pinMatch(item,holder,position)
            }
        }
    }

    interface OnCricketSportClickListener {
        fun onCricketSportClickListener(item: ResultItem?)
        fun pinMatch(item : ResultItem?,holder: ViewHolder,pos :Int)
    }
}