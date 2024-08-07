package com.sona.babu88.ui.casino

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.viewmodel.HomeViewModel
import com.sona.babu88.databinding.FragmentCasinoBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.OnSelectedFragmentListener
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress


class CasinoFragment : Fragment(), CasinoAdapter.OnItemClickListener {
    private lateinit var binding: FragmentCasinoBinding
    private lateinit var casinoAdapter: CasinoAdapter
    private val homeViewModel : HomeViewModel by viewModels()
    private var listener: OnSelectedFragmentListener?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCasinoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCasinoAdapter()
        getData()
    }

    private fun getData() {
        homeViewModel.getGameList(
            provider = "ALL" ,
            category = "LIVE",
            page = 1
        )
        homeViewModel.gameList.observe(viewLifecycleOwner) {
            when(it){
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    casinoAdapter.setCasinoData(it.data?.providers?.let { it1 -> getAdapterList(it1) })
                }

                is ApiResult.Error -> {

                }
            }
        }
    }

    private fun setCasinoAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        casinoAdapter = CasinoAdapter()
        casinoAdapter.setOnItemClickListener(this@CasinoFragment)
        binding.recyclerView.adapter = casinoAdapter
    }

    override fun onItemClickListener(item: FishingList?) {
        item?.title?.let { listener?.onFragmentClickListener(arrayListOf("Casino", "LIVE", it, "1")) }
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
            "EVOLUTION" -> R.drawable.evolution_banner
            "BETGAMES" -> R.drawable.betgames_banner
            "PRAGMATICPLAY" -> R.drawable.pragmaticplay_banner
            "PLAYTECH" -> R.drawable.playtech_banner
            "SEXYBCRT" -> R.drawable.sexybcrt_banner
            "BIGGAMING" -> R.drawable.biggaming_banner
            "SV388" -> R.drawable.sv388_banner
            "EZUGI" -> R.drawable.ezugi_banner
            "SUPERSPADE" -> R.drawable.superspade_banner
            "ONETOUCH" -> R.drawable.onetouch_banner
            "SPRIBE" -> R.drawable.spribe_banner
            "BOMBAYLIVE" -> R.drawable.bombaylive_banner
            "ROYALGAMING" -> R.drawable.royalgaming_banner
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