package com.sona.babu88.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.databinding.ItemAccountParentBinding
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class AccountParentAdapter : RecyclerView.Adapter<AccountParentAdapter.ViewHolder>(),
    AccountChildAdapter.OnChildItemClickListener {
    var list = emptyList<ParentList?>()
    private var toggle = false

    private var onItemClickListener: OnItemClickListener? = null

    fun setPAData(itemList: List<ParentList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemAccountParentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAccountParentBinding.inflate(
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
            Glide.with(root.context).load(item?.img).into(ivImg)
            title.text = item?.title

            root.setOnClickListener {
                toggle = !toggle
                if (toggle) {
                    childRecyclerView.show()
                    ivArrow.rotation = 180F
                } else {
                    childRecyclerView.hide()
                    ivArrow.rotation = 360F
                }
            }
        }

        val childAdapter = AccountChildAdapter()
        childAdapter.setOnItemClickListener(this)
        val childRecyclerView: RecyclerView = holder.itemView.findViewById(R.id.child_RecyclerView)
        childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        childRecyclerView.adapter = AccountChildAdapter()
        childRecyclerView.adapter = childAdapter
        childAdapter.setCAData(item?.childItems)
    }

    interface OnItemClickListener {
        fun onItemClickListener(item: ChildList?)
    }

    override fun onChildItemClickListener(item: ChildList?) {
        onItemClickListener?.onItemClickListener(item)
    }
}

data class ParentList(val title: String, val img: Int, val childItems: List<ChildList>)
data class ChildList(val title: String)