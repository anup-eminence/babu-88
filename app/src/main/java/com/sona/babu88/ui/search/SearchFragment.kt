package com.sona.babu88.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.data.viewmodel.SportsViewModel
import com.sona.babu88.databinding.FragmentSearchBinding
import com.sona.babu88.util.OnSportsInteractionListener

class SearchFragment : DialogFragment(), SearchAdapter.OnSearchItemClickListener {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private var listener: OnSportsInteractionListener? = null
    private var allItems: List<ResultItem?>? = listOf()
    private val sportsViewModel: SportsViewModel by viewModels()
    private var position = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getString("position").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        observerGetUserSideBarMatches()
        setUpAdapter()
        textChangedListener()
    }

    private fun setOnClickListener() {
        binding.apply {
            ivBack.setOnClickListener {
                dialog?.dismiss()
                etText.text?.clear()
            }
            ivClear.setOnClickListener { etText.text?.clear() }
        }
    }

    private fun observerGetUserSideBarMatches() {
        val sportId = when (position) {
            "0" -> "4"
            "1" -> "1"
            "2" -> "2"
            else -> return
        }

        sportsViewModel.getUserSideBarMatches(sportId = sportId)
        sportsViewModel.userSideBarMatches.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {}

                is ApiResult.Success -> {
                    allItems = it.data?.results
                }

                is ApiResult.Error -> {}
            }
        }
    }

    private fun setUpAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchAdapter = SearchAdapter()
        searchAdapter.setOnItemClickListener(this@SearchFragment)
        binding.recyclerView.adapter = searchAdapter
    }

    override fun onSearchClickListener(item: ResultItem?) {
        dialog?.dismiss()
        listener?.onSportsClick(item?.id)
    }

    private fun textChangedListener() {
        binding.etText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim().lowercase()
                val filteredItems = if (searchText.isNotEmpty()) {
                    allItems?.filter { it?.matchName?.lowercase()?.contains(searchText) ?: false }
                        ?.toMutableList()
                } else {
                    mutableListOf()
                }
                searchAdapter.setSearchData(filteredItems)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSportsInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnSportsInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}