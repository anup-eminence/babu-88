package com.sona.babu88.ui.sports__.tennis

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.databinding.FragmentTennisSportBinding
import com.sona.babu88.ui.sports__.soccer.SoccerList
import com.sona.babu88.ui.sports__.soccer.SoccerSportAdapter
import com.sona.babu88.util.OnSportsInteractionListener

class TennisSportFragment : Fragment(), SoccerSportAdapter.OnSoccerClickListener {
    private lateinit var binding: FragmentTennisSportBinding
    private lateinit var soccerSportAdapter: SoccerSportAdapter
    private var soccerList = arrayListOf<SoccerList>()
    private var listener: OnSportsInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTennisSportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setSoccerAdapter()
        setSoccerData()
    }

    private fun setSoccerAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        soccerSportAdapter = SoccerSportAdapter()
        soccerSportAdapter.setOnItemClickListener(this@TennisSportFragment)
        binding.recyclerView.adapter = soccerSportAdapter
    }

    private fun setSoccerData() {
        for (i in 1..10) {
            soccerList.add(SoccerList("Islamabad United SRL T20 v Quetta Gladiator Quetta", true))
        }
        for (i in 1..30) {
            soccerList.add(SoccerList("Islamabad United SRL T20 v Quetta", false))
        }
        soccerSportAdapter.setSoccerData(soccerList)
    }

    override fun onSoccerClickListener() {
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