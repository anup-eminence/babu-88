package com.sona.babu88.ui.fishing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.HomeViewModel
import com.sona.babu88.databinding.FragmentFishingBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.OnSelectedFragmentListener
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class FishingFragment : Fragment(), FishingAdapter.OnItemClickListener {
    private lateinit var binding: FragmentFishingBinding
    private lateinit var fishingAdapter: FishingAdapter
    private val homeViewModel : HomeViewModel by viewModels()
    private var listener: OnSelectedFragmentListener?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFishingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFishingAdapter()
        getData()
    }

    private fun setFishingAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fishingAdapter = FishingAdapter()
        fishingAdapter.setOnItemClickListener(this@FishingFragment)
        binding.recyclerView.adapter = fishingAdapter
    }

    override fun onItemClickListener(item: FishingList?) {
        item?.title?.let { listener?.onFragmentClickListener(arrayListOf("Fishing", "FH", it, "5")) }
    }

    private fun getData() {
        homeViewModel.getGameList(
            provider = "ALL" ,
            category = "FH",
            page = 1
        )
        homeViewModel.gameList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    fishingAdapter.setFishingData(it.data?.providers?.let { it1 -> getAdapterList(it1) })
                }

                is ApiResult.Error -> {

                }
            }
        }
    }

    private fun getAdapterList(provider : List<String>?) : List<FishingList> {
        val list = arrayListOf<FishingList>()
        provider?.forEach {
            list.add(FishingList(findImage(it),it))
        }
        return list
    }

    private fun findImage(type: String): Int {
        return when (type) {
            "JDB" -> R.drawable.fishing_jdb_banner
            "FC" -> R.drawable.fishing_fc_banner
            "JILI" -> R.drawable.fishing_jili_banner
            else -> -1
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSelectedFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSelectedFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}