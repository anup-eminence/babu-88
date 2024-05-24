package com.sona.babu88.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.HomeViewModel
import com.sona.babu88.databinding.FragmentDetailsBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class DetailsFragment : Fragment(), DetailsTabAdapter.OnTabItemClickListener,
    DetailsAdapter.OnItemClickListener {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var detailsTabAdapter: DetailsTabAdapter
    private lateinit var detailsAdapter: DetailsAdapter
    private val homeViewModel : HomeViewModel by viewModels()
    private var title = ""
    private var params = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title").toString()
            params = it.getString("params").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.tvTitle.text = title
        setTabAdapter()
        setDetailsAdapter()
        observer(params)
        selectedTabPosition(params)
    }

    private fun setOnClickListener() {
        binding.apply {
            leftArrow.setOnClickListener {  }
            rightArrow.setOnClickListener {  }
        }
    }

    private fun observer(params: String) {
        homeViewModel.getGameList(provider = params, category = "SLOT", page = 1)

        homeViewModel.gameList.observe(requireActivity()){
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    detailsTabAdapter.setTabData(getProviderList(it.data?.providers))
                    detailsAdapter.setDetailsData(it.data?.allImages)
                    detailsTabAdapter.selectedPosition = selectedTabPosition(params)
                    useTabPosition(params)
                }

                is ApiResult.Error -> {

                }
            }
        }
    }

    private fun getProviderList(providers: List<String>?): List<HomeTab> {
        val tabList = arrayListOf<HomeTab>()
        tabList.add(0, HomeTab(0, ""))
        providers?.forEach {
            tabList.add(HomeTab(findImage(it), it))
        }
        return tabList
    }

    private fun findImage(type: String): Int {
        return when (type.lowercase()) {
            "pp" -> R.drawable.pp
            "pt" -> R.drawable.pt
            "jili" -> R.drawable.jili
            "jdb" -> R.drawable.jdb
            "fc" -> R.drawable.fc
            "rt" -> R.drawable.rt
            "evoplay" -> R.drawable.evoplay
            else -> -1
        }
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        detailsTabAdapter = DetailsTabAdapter()
        detailsTabAdapter.setOnTabListener(this@DetailsFragment)
        binding.recyclerViewTab.adapter = detailsTabAdapter
    }

    override fun onTabItemClickListener(item: HomeTab?) {
        if (item?.text.isNullOrEmpty().not()) item?.text?.let { observer(it) }
        else observer("ALL")
    }

    private fun setDetailsAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        detailsAdapter = DetailsAdapter()
        detailsAdapter.setOnItemClickListener(this@DetailsFragment)
        binding.recyclerView.adapter = detailsAdapter
    }

    override fun onItemClickListener() {

    }

//    private fun selectedTabPosition(params: String) {
//        when (params.lowercase()) {
//            "pp" -> binding.recyclerViewTab.scrollToPosition(0)
//            "pt" -> binding.recyclerViewTab.scrollToPosition(1)
//            "jili" -> binding.recyclerViewTab.scrollToPosition(2)
//            "jdb" -> binding.recyclerViewTab.scrollToPosition(3)
//            "fc" -> binding.recyclerViewTab.scrollToPosition(4)
//            "rt" -> binding.recyclerViewTab.scrollToPosition(5)
//            "evoplay" -> binding.recyclerViewTab.scrollToPosition(6)
//        }
//    }

    private fun selectedTabPosition(params: String): Int {
        return when (params.lowercase()) {
            "" -> 0
            "pp" -> 1
            "pt" -> 2
            "jili" -> 3
            "jdb" -> 4
            "fc" -> 5
            "rt" -> 6
            "evoplay" -> 7
            else -> 0
        }
    }

    private fun useTabPosition(params: String) {
        binding.recyclerViewTab.scrollToPosition(selectedTabPosition(params))
    }
}