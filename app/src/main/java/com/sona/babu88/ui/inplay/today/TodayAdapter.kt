package com.sona.babu88.ui.inplay.today

import android.annotation.SuppressLint
import android.os.Build
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.databinding.ItemInPlayBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TodayAdapter : RecyclerView.Adapter<TodayAdapter.ViewHolder>() {
    var list = mutableListOf<ResultItem?>()
    var selectedPos = -1

    private var onTodayClickListener: OnTodayClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setTodayData(itemList: List<ResultItem?>?) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        itemList?.sortedBy {
            it?.day?.let { day -> dateFormat.parse(day) }
        }?.let { list = it.toMutableList() }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onTodayClickListener: OnTodayClickListener) {
        this.onTodayClickListener = onTodayClickListener
    }

    class ViewHolder(val binding: ItemInPlayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInPlayBinding.inflate(
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
                tvText.text = item.matchName

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val date = LocalDateTime.parse(item.day, formatter)
                val currentDate = LocalDateTime.now()

                if (date.toLocalDate().isEqual(currentDate.toLocalDate())) {
                    tvTime.text = item.time?.substringBeforeLast(":")
                } else if (date.toLocalDate().isEqual(currentDate.plusDays(1).toLocalDate())) {
                    val spannableString = SpannableStringBuilder().append("Tomorrow").append(" ")
                        .append(item.time?.substringBeforeLast(":"))
                    tvTime.text = spannableString
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
                onTodayClickListener?.onTodayClickListener(item)
            }
            btnPin.setOnClickListener {
                selectedPos = holder.adapterPosition
                onTodayClickListener?.pinMatchClickListener(item, holder.adapterPosition)
            }
        }
    }

    interface OnTodayClickListener {
        fun onTodayClickListener(item: ResultItem?)
        fun pinMatchClickListener(item: ResultItem?, adapterPosition: Int)
    }
}