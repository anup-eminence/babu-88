package com.sona.babu88.ui.details.detail2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.SportsBookMarketItem
import com.sona.babu88.api.model.response.SportsBookSelectionItem
import com.sona.babu88.databinding.ItemDetails2PremiumBinding

class DetailsPremiumAdapter : RecyclerView.Adapter<DetailsPremiumAdapter.ViewHolder>() {
    var list = mutableListOf<SportsBookMarketItem?>()

    private var onItemClickListener: OnItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setDetailsPremiumData(itemList: List<SportsBookMarketItem?>?) {
        if (itemList != null) {
            list.addAll(itemList)
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemDetails2PremiumBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemDetails2PremiumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            tvTitle.text = item?.marketName
            addItems(llLayout, item?.sportsBookSelection, root.context)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("MissingInflatedId")
    private fun addItems(
        llLayout: LinearLayoutCompat, item: List<SportsBookSelectionItem?>?, context: Context
    ) {
        llLayout.removeAllViews()
        item?.forEach {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_deails_premium, null)
            val matchName = view.findViewById<AppCompatTextView>(R.id.tv_match_name)
            val matchOdds = view.findViewById<AppCompatTextView>(R.id.tv_odds_num)

            matchName.text = it?.selectionName
            matchOdds.text = it?.odds.toString()

            llLayout.addView(view)
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener()
    }
}