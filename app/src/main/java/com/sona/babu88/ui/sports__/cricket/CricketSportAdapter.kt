package com.sona.babu88.ui.sports__.cricket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemCricketSportBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class CricketSportAdapter : RecyclerView.Adapter<CricketSportAdapter.ViewHolder>() {
    var list = emptyList<CricketSportList?>()

    private var onCricketSportClickListener: OnCricketSportClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setCricketSportData(itemList: List<CricketSportList?>?) {
        if (itemList != null) {
            list = itemList
        }
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            if (item != null) {
                if (item.inPlay) {
                    clTrue.show()
                    clFalse.hide()
                    tvText.text = item.text
                } else {
                    clTrue.hide()
                    clFalse.show()
                    tvTextFalse.text = item.text
                }
            }
        }

        holder.binding.apply {
            root.setOnClickListener {
                onCricketSportClickListener?.onCricketSportClickListener()
            }
            btnPin.setOnClickListener {
                btnPin.setImageResource(R.drawable.ic_pin_active)
            }
            btnPinFalse.setOnClickListener {
                btnPinFalse.setImageResource(R.drawable.ic_pin_active)
            }
        }
    }

    interface OnCricketSportClickListener {
        fun onCricketSportClickListener()
    }
}

data class CricketSportList(
    val text: String,
    val inPlay: Boolean
)