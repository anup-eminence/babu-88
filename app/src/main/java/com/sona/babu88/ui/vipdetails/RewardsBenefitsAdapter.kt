package com.sona.babu88.ui.vipdetails

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.api.model.response.RewardsBenefitsResponse
import com.sona.babu88.databinding.ItemRewardsBenefitsBinding

class RewardsBenefitsAdapter : RecyclerView.Adapter<RewardsBenefitsAdapter.ViewHolder>() {
    var list = emptyList<RewardsBenefitsResponse?>()
    private var keyList = arrayListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setRewardsBenefitsData(
        itemList: List<RewardsBenefitsResponse?>?,
        nameList: ArrayList<String>
    ) {
        if (itemList != null) {
            list = itemList
            keyList = nameList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemRewardsBenefitsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRewardsBenefitsBinding.inflate(
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
            tvLevel.text = item?.fullName ?: ""
//            setImageBasedOnCondition(root.context, item?.elite, R.drawable.true_tick, R.drawable.false_tick, ivImage1)
//            setImageBasedOnCondition(root.context, item?.master, R.drawable.true_tick, R.drawable.false_tick, ivImage2)
//            setImageBasedOnCondition(root.context, item?.grandMaster, R.drawable.true_tick, R.drawable.false_tick, ivImage3)
//            setImageBasedOnCondition(root.context, item?.legend, R.drawable.true_tick, R.drawable.false_tick, ivImage4)
//            setImageBasedOnCondition(root.context, item?.mythic, R.drawable.true_tick, R.drawable.false_tick, ivImage5)

            val keyToValue = mapOf(
                "elite" to item?.elite,
                "master" to item?.master,
                "grandmaster" to item?.grandMaster,
                "legend" to item?.legend,
                "mythic" to item?.mythic,
                "gold" to item?.gold,
                "platinum" to item?.platinum,
                "diamond" to item?.diamond,
                "crown" to item?.crown,
                "ace" to item?.ace,
                "conquerer" to item?.conquerer
            )
            keyList.forEachIndexed { index, key ->
                setImageBasedOnCondition(
                    root.context,
                    keyToValue[key],
                    R.drawable.true_tick,
                    R.drawable.false_tick,
                    getTextViewId(holder.binding, index)
                )
            }
        }
    }

    private fun setImageBasedOnCondition(
        context: Context,
        condition: Boolean?,
        trueResId: Int,
        falseResId: Int,
        imageView: AppCompatImageView?
    ) {
        val resId = if (condition == true) trueResId else falseResId
        Glide.with(context).load(resId).into(imageView!!)
    }

    private fun getTextViewId(
        binding: ItemRewardsBenefitsBinding,
        index: Int
    ): AppCompatImageView? {
        return when (index) {
            0 -> binding.ivImage1
            1 -> binding.ivImage2
            2 -> binding.ivImage3
            3 -> binding.ivImage4
            4 -> binding.ivImage5
            else -> null
        }
    }
}