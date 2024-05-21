package com.sona.babu88.ui.promotion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentPromotionBinding

class PromotionFragment : Fragment(), PromotionAdapter.OnItemClickListener {
    private lateinit var binding: FragmentPromotionBinding
    private lateinit var promotionAdapter: PromotionAdapter
    private var promotionList = arrayListOf<PromotionList>()

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
        setUpSpinner()
        promotionAdapter.setPromotionData(promotionList)
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
//        PromotionDetailsFragment()
    }

    override fun onBtnApplyNowClickListener() {}

    private fun setUpSpinner() {
        val list = ArrayList<String>()
        provideSpinnerList().forEach { list.add(it.title!!) }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, list)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun provideSpinnerList(): List<SpinnerListPromotion> {
        return listOf(
            SpinnerListPromotion(title = "All"),
            SpinnerListPromotion(title = "All"),
            SpinnerListPromotion(title = "All")
        )
    }
}

data class SpinnerListPromotion(
    val title: String?
)