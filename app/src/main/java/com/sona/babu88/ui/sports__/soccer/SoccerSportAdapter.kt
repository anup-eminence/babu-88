package com.sona.babu88.ui.sports__.soccer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemSoccerSportBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class SoccerSportAdapter : RecyclerView.Adapter<SoccerSportAdapter.ViewHolder>() {
    var list = emptyList<SoccerList?>()

    private var onSoccerClickListener: OnSoccerClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setSoccerData(itemList: List<SoccerList?>?) {
        if (itemList != null) {
            list = itemList
        }
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
                onSoccerClickListener?.onSoccerClickListener()
            }
            btnPin.setOnClickListener {
                btnPin.setImageResource(R.drawable.ic_pin_active)
            }
            btnPinFalse.setOnClickListener {
                btnPinFalse.setImageResource(R.drawable.ic_pin_active)
            }
        }
    }

    interface OnSoccerClickListener {
        fun onSoccerClickListener()
    }
}

data class SoccerList(
    val text: String, val inPlay: Boolean
)