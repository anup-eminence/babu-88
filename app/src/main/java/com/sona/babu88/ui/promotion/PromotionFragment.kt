package com.sona.babu88.ui.promotion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentPromotionBinding
import com.sona.babu88.ui.promotiondetails.PromotionDetailsFragment
import com.sona.babu88.util.OnAccountListener

class PromotionFragment : Fragment(), PromotionAdapter.OnItemClickListener {
    private lateinit var binding: FragmentPromotionBinding
    private lateinit var promotionAdapter: PromotionAdapter
    private var promotionList = arrayListOf<PromotionList>()
    private lateinit var promotionTabAdapter: PromotionTabAdapter
    private var promotionTabList = arrayListOf<PromotionTabList>()
    private var listener: OnAccountListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromotionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPromotionAdapter()
        setPromotionData()
        promotionAdapter.setPromotionData(promotionList)
        setTabAdapter()
        setTabData()
    }

    private fun setPromotionAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        promotionAdapter = PromotionAdapter()
        promotionAdapter.setOnItemClickListener(this@PromotionFragment)
        binding.recyclerView.adapter = promotionAdapter
    }

    private fun setPromotionData() {
        promotionList.add(PromotionList(R.drawable.img_promotion, "Get Bonus", "Get Bonus"))
        promotionList.add(PromotionList(R.drawable.img_promotion, "Joining bonanza","Joining bonanza"))
        promotionList.add(PromotionList(R.drawable.img_promotion, "Get Bonus", "Get Bonus"))
        promotionList.add(PromotionList(R.drawable.img_promotion, "Joining bonanza","Joining bonanza"))
    }

    override fun onBtnDetailsClickListener() {
        val dialog = PromotionDetailsFragment()
        dialog.isCancelable = false
        dialog.show(childFragmentManager, "promotion")
    }

    override fun onBtnApplyNowClickListener() { listener?.onAccountClick("Deposit") }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        promotionTabAdapter = PromotionTabAdapter()
//        promotionTabAdapter.setOnTabListener(this@PromotionFragment)
        binding.recyclerViewTab.adapter = promotionTabAdapter
    }

    private fun setTabData() {
        promotionTabList.add(PromotionTabList("All"))
        promotionTabList.add(PromotionTabList("Welcome Offer"))
        promotionTabList.add(PromotionTabList("SLOT"))
        promotionTabList.add(PromotionTabList("FH"))
        promotionTabList.add(PromotionTabList("EGAME"))
        promotionTabList.add(PromotionTabList("GAMESHOW"))
        promotionTabList.add(PromotionTabList("T10"))
        promotionTabList.add(PromotionTabList("PremiumMatch"))
        promotionTabList.add(PromotionTabList("Live"))
        promotionTabList.add(PromotionTabList("TABLE"))
        promotionTabList.add(PromotionTabList("VIRTUAL"))
        promotionTabList.add(PromotionTabList("LOTTO"))
        promotionTabList.add(PromotionTabList("BINGO"))
        promotionTabList.add(PromotionTabList("CRASH"))
        promotionTabList.add(PromotionTabList("P2P"))
        promotionTabList.add(PromotionTabList("ESPORTS"))
        promotionTabList.add(PromotionTabList("Sports"))
        promotionTabList.add(PromotionTabList("PremiumFancy"))
        promotionTabAdapter.setTabData(promotionTabList)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAccountListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnAccountListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}