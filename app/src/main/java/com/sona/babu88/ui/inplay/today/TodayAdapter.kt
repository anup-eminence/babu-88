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
    var list = emptyList<ResultItem?>()

    private var onTodayClickListener: OnTodayClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setTodayData(itemList: List<ResultItem?>?) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        itemList?.sortedBy {
            it?.day?.let { day -> dateFormat.parse(day) }
        }?.let { list = it }
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
        }

        holder.binding.apply {
            root.setOnClickListener {
                onTodayClickListener?.onTodayClickListener(item)
            }
            btnPin.setOnClickListener {
                btnPin.setImageResource(R.drawable.ic_pin_active)
            }
        }
    }

    interface OnTodayClickListener {
        fun onTodayClickListener(item: ResultItem?)
    }
}