package com.sona.babu88.ui.slot

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
import com.sona.babu88.databinding.FragmentSlotBinding
import com.sona.babu88.model.SlotList
import com.sona.babu88.util.OnSelectedFragmentListener
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class SlotFragment : Fragment(), SlotAdapter.OnItemClickListener {
    private lateinit var binding: FragmentSlotBinding
    private lateinit var slotAdapter: SlotAdapter
    private val homeViewModel : HomeViewModel by viewModels()
    private var listener: OnSelectedFragmentListener ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setSlotAdapter()
        observer()
    }

    private fun observer() {
        homeViewModel.getGameList(provider = "ALL", category = "SLOT", page = 1)

        homeViewModel.gameList.observe(viewLifecycleOwner){
            println(">>>>>gameList ${it.data}")
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    slotAdapter.setSlotData(getProviderList(it.data?.providers))
                }

                is ApiResult.Error -> {

                }
            }
        }
    }

    private fun getProviderList(providers: List<String>?): List<SlotList> {
        val slotList = arrayListOf<SlotList>()
        providers?.forEach {
            slotList.add(SlotList(findImage(it), it, R.drawable.ic_hot))
        }
        return slotList
    }

    private fun findImage(type: String): Int {
        return when (type) {
            "JILI" -> R.drawable.jili
            "KINGMAKER" -> R.drawable.kingmaker
            "EVOLUTION" -> R.drawable.evolution
            "REDTIGER" -> R.drawable.redtiger
            "SPADEGAMING" -> R.drawable.spadegaming
            "FC" -> R.drawable.fc
            "JDB" -> R.drawable.jdb
            "DRAGONSOFT" -> R.drawable.dragonsoft
            "PRAGMATICPLAY" -> R.drawable.pragmaticplay
            "PLAYTECH" -> R.drawable.playtech
            "YESBINGO" -> R.drawable.yesbingo
            "FASTSPIN" -> R.drawable.fastspin
            "PGSOFT" -> R.drawable.pgsoft
            "EVOPLAY" -> R.drawable.evoplay
            "ONETOUCH" -> R.drawable.onetouch
            else -> -1
        }
    }

    private fun setSlotAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        slotAdapter = SlotAdapter()
        slotAdapter.setOnItemClickListener(this@SlotFragment)
        binding.recyclerView.adapter = slotAdapter
    }

    override fun onItemClickListener(item: SlotList?) {
        item?.text?.let { listener?.onFragmentClickListener(params = arrayListOf("SLOT", "SLOT", it, "2")) }
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