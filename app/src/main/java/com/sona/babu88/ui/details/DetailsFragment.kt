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
import com.sona.babu88.api.model.response.GameImage
import com.sona.babu88.data.HomeViewModel
import com.sona.babu88.databinding.FragmentDetailsBinding
import com.sona.babu88.model.HomeTab
import com.sona.babu88.util.hideProgress
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
    private var selectedTab = ""

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
        observeApi()
    }

    private fun initView() {
        binding.tvTitle.text = title
        setTabAdapter()
        setDetailsAdapter()
        observer(providers, category)
        selectedTab = providers
    }

    private fun setOnClickListener() {
        binding.apply {
            leftArrow.setOnClickListener {
                currentPage = 1
                shouldLoadData = true
                if (!category.isNullOrEmpty()) detailsAdapter.clearData()
                if (currentIndex > 0) {
                    currentIndex--
                    updateData()
                }
            }
            rightArrow.setOnClickListener {
                currentPage = 1
                shouldLoadData = true
                if (!category.isNullOrEmpty()) detailsAdapter.clearData()
                if (currentIndex < titleList.size - 1) {
                    currentIndex++
                    updateData()
                }
            }
        }
    }

    private fun observeApi() {
        homeViewModel.gameList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    if (category.isNullOrEmpty()) {
                        shouldLoadData = false
                        setStaticDetailData(titleList[currentIndex])
                    } else {
                        if (it.data?.allImages?.isEmpty() == true) {
                            shouldLoadData = false
                        } else {
                            if (currentPage == 1) detailsAdapter.clearData()
                            detailsAdapter.setDetailsData(it.data?.allImages)
                        }
                    }
                    if (currentPage == 1) {
                        shouldLoadData = true
                        detailsTabAdapter.setTabData(getProviderList(it.data?.providers))
                    }
                    detailsTabAdapter.selectedPosition = detailsTabAdapter.findPosition(selectedTab)
                    useTabPosition()
                }

                is ApiResult.Error -> {
                    this.hideProgress()
                    requireContext().showToast(it.message.toString())
                }
            }
        }

        binding.nestedScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            println(">>>>shouldLoadData $shouldLoadData")
            if ((scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) && shouldLoadData) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                currentPage++;
                homeViewModel.getGameList(
                    provider = selectedTab,
                    category = category,
                    page = currentPage
                )
            }
        })
    }
    private fun observer(providers: String, category: String) {
        selectedTab =  providers
        homeViewModel.getGameList(provider = providers, category = category, page = currentPage)
    }

    private fun getProviderList(providers: List<String>?): List<HomeTab> {
        val tabList = arrayListOf<HomeTab>()
        tabList.add(0, HomeTab(0, ""))
        if (providers.isNullOrEmpty()) {
            setStaticData(tabList, titleList[currentIndex])
        } else {
            providers?.forEach {
                tabList.add(HomeTab(findImage(it), it))
            }
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
        if (!category.isNullOrEmpty()) {
            currentPage = 1
            detailsAdapter.clearData()
            shouldLoadData = true
            selectedTab = item?.text.toString()
            println(">>>>clicked $shouldLoadData")
            if (item?.text.isNullOrEmpty().not()) item?.text?.let { observer(it, category) }
            else observer("ALL", category)
        }
    }

    private fun setDetailsAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        detailsAdapter = DetailsAdapter()
        detailsAdapter.setOnItemClickListener(this@DetailsFragment)
        binding.recyclerView.adapter = detailsAdapter
    }

    override fun onItemClickListener() {

    }

    private fun useTabPosition() {
        binding.recyclerViewTab.scrollToPosition(detailsTabAdapter.findPosition(selectedTab))
    }

    private fun updateData() {
        binding.tvTitle.text = titleList[currentIndex]
        category = categoryList[currentIndex]
        observer("ALL", categoryList[currentIndex])
    }

    private fun setStaticData(tabList: ArrayList<HomeTab>, title: String) {
        when (title) {
            "Cricket" -> {
                tabList.add(HomeTab(R.drawable._9wicket, "9Wicket"))
                tabList.add(HomeTab(R.drawable.betswiz, "BetSwiz"))
                tabList.add(HomeTab(R.drawable.dream_spots_, "Dream Exchange"))
            }

            "Sports" -> {
                tabList.add(HomeTab(R.drawable.aura, "SABA"))
            }

            "Crash" -> {
                tabList.add(HomeTab(R.drawable.aura, "Aura"))
                tabList.add(HomeTab(R.drawable.evo, "Evo"))
                tabList.add(HomeTab(R.drawable.pt, "PT"))
                tabList.add(HomeTab(R.drawable.pp, "Pragmatic"))
                tabList.add(HomeTab(R.drawable.royalgaming_, "Royal"))
                tabList.add(HomeTab(R.drawable.sexy_, "AE Casino"))
                tabList.add(HomeTab(R.drawable.ezugi, "Ezugi"))
            }
        }
    }

    private fun setStaticDetailData(title: String) {
        val list = arrayListOf<GameImage?>()
        list.clear()
        detailsAdapter.clearData()

        fun addGameImage(
            name: String,
            image: String,
            isStaticImage: Boolean = false,
            staticImage: Int? = null
        ) {
            list.add(
                GameImage(
                    id = "",
                    name = name,
                    gameCode = "",
                    imageFavGame = false,
                    imageUrl = "",
                    image = image,
                    providerId = "",
                    categoryId = "",
                    createdAt = "",
                    updatedAt = "",
                    version = 0.0,
                    isStaticImage = isStaticImage,
                    staticImage = staticImage
                )
            )
        }

        when (title) {
            "Cricket" -> {
                addGameImage(
                    name = "Exchange",
                    isStaticImage = true,
                    image = "",
                    staticImage = R.drawable.img_betswiz_
                )
                addGameImage(
                    name = "9Wickets",
                    isStaticImage = true,
                    image = "",
                    staticImage = R.drawable.img_9wickets
                )
                addGameImage(
                    name = "Dream Exchange",
                    isStaticImage = true,
                    image = "",
                    staticImage = R.drawable.img_dream_sports
                )
            }

            "Sports" -> {
                addGameImage(
                    name = "IBC Sports",
                    image = "https://akm-media.9terawolf.com/images/babu/game_icons/en/ibc/0_0.jpg"
                )
            }

            "Crash" -> {
                addGameImage(
                    name = "Aviator",
                    image = "https://luckmedia.link/spb_aviator/thumb.webp"
                )
                addGameImage(
                    name = "Go Rush",
                    image = "https://akm-media.9terawolf.com/cms/h8/image/646c363d76997.png"
                )
                addGameImage(
                    name = "NFT Aviatrix",
                    image = "https://luckmedia.link/avx_nft_aviatrix/thumb.webp"
                )
                addGameImage(
                    name = "PlinkoX",
                    image = "https://luckmedia.link/sms_plinkox/thumb.webp"
                )
                addGameImage(
                    name = "CricketX",
                    image = "https://luckmedia.link/sms_cricketx/thumb.webp"
                )
                addGameImage(
                    name = "Zeppelin",
                    image = "https://luckmedia.link/btsl_zeppelin/thumb.webp"
                )
                addGameImage(
                    name = "Baloon",
                    image = "https://luckmedia.link/sms_baloon/thumb.webp"
                )
                addGameImage(
                    name = "JetX",
                    image = "https://luckmedia.link/sms_jetx/thumb.webp"
                )
                addGameImage(
                    name = "Multi Hot 5",
                    image = "https://luckmedia.link/sms_multi_hot_5/thumb.webp"
                )
                addGameImage(
                    name = "JetX3",
                    image = "https://luckmedia.link/sms_jetx3/thumb.webp"
                )
                addGameImage(
                    name = "Cappadocia",
                    image = "https://luckmedia.link/sms_cappadocia/thumb.webp"
                )
                addGameImage(
                    name = "SpinX",
                    image = "https://luckmedia.link/sms_spinx/thumb.webp"
                )
                addGameImage(
                    name = "Foxy Hot 20",
                    image = "https://luckmedia.link/sms_foxy_hot_20/thumb.webp"
                )
                addGameImage(
                    name = "Smash X",
                    image = "https://luckmedia.link/sms_smash_x/thumb.webp"
                )
                addGameImage(
                    name = "Crash Duel X",
                    image = "https://luckmedia.link/sms_crash_duel_x/thumb.webp"
                )
            }
        }
        detailsAdapter.setDetailsData(list)
    }
}