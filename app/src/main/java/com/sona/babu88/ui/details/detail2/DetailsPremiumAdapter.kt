package com.sona.babu88.ui.details.detail2

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.R
import com.sona.babu88.api.model.response.SportsBookMarketItem
import com.sona.babu88.api.model.response.SportsBookSelectionItem
import com.sona.babu88.databinding.ItemDetails2PremiumBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsPremiumAdapter : RecyclerView.Adapter<DetailsPremiumAdapter.ViewHolder>() {
    var list = mutableListOf<SportsBookMarketItem?>()

    private var onItemClickListener: OnItemClickListener? = null

    /*@SuppressLint("NotifyDataSetChanged")
    fun setDetailsPremiumData(itemList: List<SportsBookMarketItem?>?) {
        if (itemList != null) {
            list.addAll(itemList)
        }
        notifyDataSetChanged()
    }*/

    fun setDetailsPremiumData(newList: List<SportsBookMarketItem?>) {
        CoroutineScope(Dispatchers.Main).launch {
            val diffCallback = DetailsPremiumDiffCallback(list, newList)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            list.clear()
            list.addAll(newList)
            diffResult.dispatchUpdatesTo(this@DetailsPremiumAdapter)

        }
    }

    // DiffUtil Callback for calculating differences
    private class DetailsPremiumDiffCallback(
        private val oldList: List<SportsBookMarketItem?>,
        private val newList: List<SportsBookMarketItem?>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            try {
                return oldList[oldItemPosition]?.apisite == newList[newItemPosition]?.apisite || oldList[oldItemPosition]?.sportsBookSelection == newList[newItemPosition]?.sportsBookSelection
            }catch (e : Exception){
                e.printStackTrace()
                return true
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            try {
                val list1 = oldList[oldItemPosition]?.sportsBookSelection
                val list2 = oldList[newItemPosition]?.sportsBookSelection

                if (list2?.size == list1?.size) {
                    var shouldUpdate = false
                    list2?.forEachIndexed { index, sportsBookSelectionItem ->
                        shouldUpdate = sportsBookSelectionItem?.odds == list1?.get(index)?.odds
                    }
                  // if (list1.isNullOrEmpty()) shouldUpdate = true
                    return shouldUpdate
                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                return true
            }

        }
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
        item?.forEach { it1 ->
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_deails_premium, null)
            val matchName = view.findViewById<AppCompatTextView>(R.id.tv_match_name)
            val matchOdds = view.findViewById<AppCompatTextView>(R.id.tv_odds_num)

            matchName.text = it1?.selectionName?.replaceFirstChar { it.uppercase() }
            matchOdds.text = it1?.odds.toString()

            matchOdds.setOnClickListener { onItemClickListener?.onItemClickListener( it1?.odds?.toString() ?: "0.0") }

            llLayout.addView(view)
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(odds: String)
    }
}