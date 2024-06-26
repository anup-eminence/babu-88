package com.sona.babu88.ui.crash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentCrashBinding
import com.sona.babu88.model.CrashList
import com.sona.babu88.util.OnSelectedFragmentListener

class CrashFragment : Fragment(), CrashAdapter.OnItemClickListener {
    private lateinit var binding: FragmentCrashBinding
    private lateinit var crashAdapter: CrashAdapter
    private var crashList = arrayListOf<CrashList>()
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
        binding = FragmentCrashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSlotAdapter()
        setSlotData()
        crashAdapter.setCrashData(crashList)
    }

    private fun setSlotAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        crashAdapter = CrashAdapter()
        crashAdapter.setOnItemClickListener(this@CrashFragment)
        binding.recyclerView.adapter = crashAdapter
    }

    private fun setSlotData() {
        crashList.add(CrashList(R.drawable.img_crash_1,  R.drawable.ic_hot))
    }

    override fun onItemClickListener(item: CrashList?) {
        item?.image?.let { listener?.onFragmentClickListener(arrayListOf("Crash", "", "ALL", "6")) }
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