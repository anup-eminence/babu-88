package com.sona.babu88.ui.agentaffiliatte

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.databinding.ItemAgentAffiliateBinding

class AgentAffiliateAdapter : RecyclerView.Adapter<AgentAffiliateAdapter.ViewHolder>() {
    var list = emptyList<AgentAffiliateList?>()

    private var onAgentItemClickListener: OnAgentItemClickListener? = null

    fun setAgentAffiliateData(itemList: List<AgentAffiliateList?>?) {
        if (itemList != null) {
            list = itemList
        }
        notifyDataSetChanged()
    }

    fun setOnAgentItemClickListener(onAgentItemClickListener: OnAgentItemClickListener) {
        this.onAgentItemClickListener = onAgentItemClickListener
    }

    class ViewHolder(val binding: ItemAgentAffiliateBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAgentAffiliateBinding.inflate(
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
                Glide.with(root.context).load(item.image).into(image)
                tvTitle.text = item.title
                tvText.text = item.text
            }
        }

        holder.binding.root.setOnClickListener {
            onAgentItemClickListener?.onAgentItemClickListener()
        }
    }

    interface OnAgentItemClickListener {
        fun onAgentItemClickListener()
    }
}


data class AgentAffiliateList(
    val image: Int,
    val title: String,
    val text: String
)