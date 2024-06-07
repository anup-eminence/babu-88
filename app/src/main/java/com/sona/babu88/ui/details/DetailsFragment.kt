package com.sona.babu88.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.GameListResponse
import com.sona.babu88.data.HomeViewModel
import com.sona.babu88.databinding.FragmentDetailsBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.util.hide
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.show
import com.sona.babu88.util.showProgress
import com.sona.babu88.util.showToast

class DetailsFragment : Fragment(), DetailsTabAdapter.OnTabItemClickListener,
    DetailsAdapter.OnItemClickListener {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var detailsTabAdapter: DetailsTabAdapter
    private lateinit var detailsAdapter: DetailsAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private var title = ""
    private var category = ""
    private var providers = ""
    private val titleList =
        listOf("Cricket", "Casino", "Slot", "Table Games", "Sports", "Fishing", "Crash")
    private val categoryList = listOf("", "LIVE", "SLOT", "TABLE", "", "FH", "")
    private var currentIndex = 0
    private var currentPage = 1
    private var shouldLoadData = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getStringArrayList("params")?.let { params ->
                title = params[0]
                category = params[1]
                providers = params[2]
                currentIndex = params[3].toInt()
            }
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
        observer(providers, category)
        selectedTabPosition(category, providers)
    }

    private fun setOnClickListener() {
        binding.apply {
            leftArrow.setOnClickListener {
                currentPage = 1
                shouldLoadData = true
                detailsAdapter.clearData()
                if (currentIndex > 0) {
                    currentIndex--
                    updateData()
                }
            }
            rightArrow.setOnClickListener {
                currentPage = 1
                shouldLoadData = true
                detailsAdapter.clearData()
                if (currentIndex < titleList.size - 1) {
                    currentIndex++
                    updateData()
                }
            }
        }
    }

    private fun observer(providers: String, category: String) {
        homeViewModel.getGameList(provider = providers, category = category, page = currentPage)

        homeViewModel.gameList.observe(requireActivity()) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    if (it.data?.allImages?.isEmpty() == true) {
                        shouldLoadData = false
                    } else {
                        if (currentPage == 1) detailsAdapter.clearData()
                        detailsAdapter.setDetailsData(it.data?.allImages)
                    }
                    if (currentPage == 1) {
                        shouldLoadData = true
                        checkVisibleOrNot(it.data)
                        detailsTabAdapter.setTabData(getProviderList(it.data?.providers))
                    }
                    detailsTabAdapter.selectedPosition = selectedTabPosition(category, providers)
                    useTabPosition(providers)
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                    requireContext().showToast(it.message.toString())
                }
            }
        }

        binding.nestedScroll.setOnScrollChangeListener(object :
            NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                // on scroll change we are checking when users scroll as bottom.
                println(">>>>shouldLoadData $shouldLoadData")
                if ((scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) && shouldLoadData) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    currentPage++;
                    homeViewModel.getGameList(
                        provider = providers,
                        category = category,
                        page = currentPage
                    )
                }
            }


        })
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
        return when (type) {
            "PP" -> R.drawable.pp
            "PT" -> R.drawable.pt
            "JILI" -> R.drawable.jili
            "JDB" -> R.drawable.jdb
            "FC" -> R.drawable.fc
            "RT" -> R.drawable.rt
            "EVOPLAY" -> R.drawable.evoplay
            "SEXY" -> R.drawable.sexy_
            "EVO" -> R.drawable.evo
            "BETGAMES" -> R.drawable.betgames
            "ROYALGAMING" -> R.drawable.royalgaming_
            "EZUGI" -> R.drawable.ezugi
            "SPRIBE" -> R.drawable.crash_spribe
            "KM" -> R.drawable.km
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
        currentPage = 1
        detailsAdapter.clearData()
        shouldLoadData = true
        println(">>>>clicked $shouldLoadData")
        if (item?.text.isNullOrEmpty().not()) item?.text?.let { observer(it, category) }
        else observer("ALL", category)
    }

    private fun setDetailsAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        detailsAdapter = DetailsAdapter()
        detailsAdapter.setOnItemClickListener(this@DetailsFragment)
        binding.recyclerView.adapter = detailsAdapter
    }

    override fun onItemClickListener() {

    }

    private fun selectedTabPosition(category: String, provider: String): Int {
        return when (category) {
            "SLOT" -> when (provider) {
                "" -> 0
                "PP" -> 1
                "PT" -> 2
                "JILI" -> 3
                "JDB" -> 4
                "FC" -> 5
                "RT" -> 6
                "EVOPLAY" -> 7
                else -> 0
            }

            "LIVE" -> when (provider) {
                "" -> 0
                "SEXY" -> 1
                "EVO" -> 2
                "PP" -> 3
                "PT" -> 4
                "BETGAMES" -> 5
                "ROYALGAMING" -> 6
                "EZUGI" -> 7
                "EVOPLAY" -> 8
                else -> 0
            }

            "FH" -> when (provider) {
                "" -> 0
                "JDB" -> 1
                "FC" -> 2
                "JILI" -> 3
                else -> 0
            }

            "TABLE" -> when (provider) {
                "" -> 0
                "JILI" -> 1
                "FC" -> 2
                "SPRIBE" -> 3
                "KM" -> 4
                "RT" -> 5
                else -> 0
            }

            else -> -1
        }
    }

    private fun useTabPosition(providers: String) {
        binding.recyclerViewTab.scrollToPosition(selectedTabPosition(category, providers))
    }

    private fun updateData() {
        binding.tvTitle.text = titleList[currentIndex]
        category = categoryList[currentIndex]
        observer("ALL", categoryList[currentIndex])
    }

    private fun checkVisibleOrNot(data: GameListResponse?) {
        binding.apply {
            if (data?.providers.isNullOrEmpty()) recyclerViewTab.hide()
            else recyclerViewTab.show()
            if (data?.allImages.isNullOrEmpty()) {
                tvNoData.show()
                recyclerView.hide()
            } else {
                tvNoData.hide()
                recyclerView.show()
            }
        }
    }
}