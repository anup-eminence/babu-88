package com.sona.babu88.ui.sports__.cricket

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.databinding.FragmentCricketSportBinding
import com.sona.babu88.util.OnSportsInteractionListener

class CricketSportFragment : Fragment(), CricketSportAdapter.OnCricketSportClickListener {
    private lateinit var binding: FragmentCricketSportBinding
    private lateinit var cricketSportAdapter: CricketSportAdapter
    private val cricketSportList = arrayListOf<CricketSportList>()
    private var listener: OnSportsInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCricketSportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setCricketSportAdapter()
        setCricketSportData()
    }

    private fun setCricketSportAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cricketSportAdapter = CricketSportAdapter()
        cricketSportAdapter.setOnItemClickListener(this@CricketSportFragment)
        binding.recyclerView.adapter = cricketSportAdapter
    }

    private fun setCricketSportData() {
        for (i in 1..30) { cricketSportList.add(CricketSportList("Islamabad United SRL T20 v Quetta Gladiator Quetta", true)) }
        for (i in 1..30) { cricketSportList.add(CricketSportList("Islamabad United SRL T20 v Quetta", false)) }
        cricketSportAdapter.setCricketSportData(cricketSportList)
    }

    override fun onCricketSportClickListener() {
        listener?.onSportsClick()
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