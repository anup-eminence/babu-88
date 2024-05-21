package com.sona.babu88.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.databinding.ItemAccountChildBinding

class AccountChildAdapter : RecyclerView.Adapter<AccountChildAdapter.ViewHolder>() {
    var list = emptyList<ChildList?>()

    private var onChildItemClickListener: OnChildItemClickListener? = null

    fun setCAData(itemList: List<ChildList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onChildItemClickListener: OnChildItemClickListener) {
        this.onChildItemClickListener = onChildItemClickListener
    }

    class ViewHolder(val binding: ItemAccountChildBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAccountChildBinding.inflate(
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
            ivArrow.rotation = 270F
            title.text = item?.title

            root.setOnClickListener {
                onChildItemClickListener?.onChildItemClickListener(item)
            }
        }
    }

    interface OnChildItemClickListener {
        fun onChildItemClickListener(item: ChildList?)
    }
}