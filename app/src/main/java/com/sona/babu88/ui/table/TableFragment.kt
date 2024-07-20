package com.sona.babu88.ui.table

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
import com.sona.babu88.databinding.FragmentTableBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.util.OnSelectedFragmentListener
import com.sona.babu88.util.hideProgress
import com.sona.babu88.util.showProgress

class TableFragment : Fragment(), TableAdapter.OnItemClickListener {
    private lateinit var binding: FragmentTableBinding
    private lateinit var tableAdapter: TableAdapter
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
        binding = FragmentTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFishingAdapter()
        getData()
    }

    private fun setFishingAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        tableAdapter = TableAdapter()
        tableAdapter.setOnItemClickListener(this@TableFragment)
        binding.recyclerView.adapter = tableAdapter
    }

    override fun onItemClickListener(item: FishingList?) {
        item?.title?.let { listener?.onFragmentClickListener(arrayListOf("Table Games", "TABLE", it, "3")) }
    }

    private fun getData() {
        homeViewModel.getGameList(
            provider = "ALL" ,
            category = "TABLE",
            page = 1
        )
        homeViewModel.gameList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {
                    this.showProgress()
                }

                is ApiResult.Success -> {
                    this.hideProgress()
                    tableAdapter.setTableData(it.data?.providers?.let { it1 -> getAdapterList(it1) })
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
            "JILI" -> R.drawable.jili_banner
            "KINGMAKER" -> R.drawable.kingmaker_banner
            "LUDO" -> R.drawable.ludo_banner
            "EVOLUTION" -> R.drawable.evolution_banner
            "REDTIGER" -> R.drawable.redtiger_banner
            "JDB" -> R.drawable.jdb_banner
            "PGSOFT" -> R.drawable.pgsoft_banner
            "EZUGI" -> R.drawable.ezugi_banner
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