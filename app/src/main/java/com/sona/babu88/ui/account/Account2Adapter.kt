package com.sona.babu88.ui.account

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sona.babu88.databinding.ItemAccount2Binding

class Account2Adapter : RecyclerView.Adapter<Account2Adapter.ViewHolder>() {
    var list = emptyList<AccountList?>()

    private var onAccountItemClickListener: OnAccountItemClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setAccountAdapterData(itemList: List<AccountList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onAccountItemClickListener: OnAccountItemClickListener) {
        this.onAccountItemClickListener = onAccountItemClickListener
    }

    class ViewHolder(val binding: ItemAccount2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAccount2Binding.inflate(
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
            tvTitle.text = item?.title
            ivArrow.rotation = 270F
        }
        holder.binding.root.setOnClickListener {
            onAccountItemClickListener?.onAccountItemClickListener(item)
        }
    }

    interface OnAccountItemClickListener {
        fun onAccountItemClickListener(item: AccountList?)
    }
}

data class AccountList(val title: String)