package com.sona.babu88.ui.history

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.model.response.Provider
import com.sona.babu88.data.viewmodel.TransactionViewModel
import com.sona.babu88.databinding.FragmentBettingRecordsBinding
import com.sona.babu88.model.FishingList
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BettingRecordsFragment : Fragment(), BettingRecordsAdapter.OnTabItemClickListener {
    private lateinit var binding: FragmentBettingRecordsBinding
    private lateinit var bettingRecordsAdapter: BettingRecordsAdapter
    private val transactionViewModel: TransactionViewModel by viewModels()
    private val cal = Calendar.getInstance()
    private var selectedYear: Int = 0
    private var selectedMonth: Int = 0
    private var selectedDay: Int = 0
    private var selYear: Int = 0
    private var selMonth: Int = 0
    private var selDay: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBettingRecordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        val currentDate =
            SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(cal.timeInMillis)
        binding.layoutDateSpinner.apply {
            tvStartDate.text = currentDate
            tvEndDate.text = currentDate
        }
        setTabAdapter()
        observerPlatFormList()
        observerUserSCPack()
        selectedYear = cal.get(Calendar.YEAR)
        selectedMonth = cal.get(Calendar.MONTH)
        selectedDay = cal.get(Calendar.DAY_OF_MONTH)
        selYear = cal.get(Calendar.YEAR)
        selMonth = cal.get(Calendar.MONTH)
        selDay = cal.get(Calendar.DAY_OF_MONTH)
    }

    private fun setOnClickListener() {
        binding.layoutDateSpinner.apply {
            llStart.setOnClickListener { showDatePickerDialogStart() }
            llEnd.setOnClickListener { showDatePickerDialogEnd() }
            spinnerLayout.setOnClickListener { spinner.performClick() }
        }
    }

    private fun setTabAdapter() {
        binding.recyclerViewTab.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bettingRecordsAdapter = BettingRecordsAdapter()
        bettingRecordsAdapter.setOnTabListener(this@BettingRecordsFragment)
        binding.recyclerViewTab.adapter = bettingRecordsAdapter
    }

    override fun onTabItemClickListener(item: FishingList?) {}

    private fun observerPlatFormList() {
        transactionViewModel.getPlatFormList()

        transactionViewModel.platFormList.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {}
                is ApiResult.Success -> {
                    bettingRecordsAdapter.setTabData(it.data?.providers?.let { it1 ->
                        getAdapterList(
                            it1
                        )
                    })
                }

                is ApiResult.Error -> {}
            }
        }
    }

    private fun getAdapterList(providerList: List<Provider>): List<FishingList> {
        val list = arrayListOf<FishingList>()
        list.add(0, FishingList(0, ""))
        providerList.forEach {
            list.add(FishingList(findImage(it.providerName ?: ""), it.providerName))
        }
        return list
    }

    private fun findImage(type: String): Int {
        return when (type) {
            "JILI" -> R.drawable.jili
            "KINGMAKER" -> R.drawable.kingmaker
            "LUDO" -> R.drawable.ludo
            "EVOLUTION" -> R.drawable.evolution
            "BETGAMES" -> R.drawable.betgames
            "REDTIGER" -> R.drawable.redtiger
            "SPADEGAMING" -> R.drawable.spadegaming
            "FC" -> R.drawable.fc
            "JDB" -> R.drawable.jdb
            "YL" -> R.drawable.yl
            "DRAGONSOFT" -> R.drawable.dragonsoft
            "PRAGMATICPLAY" -> R.drawable.pragmaticplay
            "PLAYTECH" -> R.drawable.playtech
            "YESBINGO" -> R.drawable.yesbingo
            "SEXYBCRT" -> R.drawable.sexybcrt
            "FASTSPIN" -> R.drawable.fastspin
            "E1SPORT" -> R.drawable.e1sport
            "BIGGAMING" -> R.drawable.biggaming
            "PGSOFT" -> R.drawable.pgsoft
            "SV388" -> R.drawable.sv388
            "SABAVIRTUAL" -> R.drawable.sabavirtual
            "EZUGI" -> R.drawable.ezugi
            "SUPERSPADE" -> R.drawable.superspade
            "EVOPLAY" -> R.drawable.evoplay
            "ONETOUCH" -> R.drawable.onetouch
            "SPRIBE" -> R.drawable.spribe
            "BOMBAYLIVE" -> R.drawable.bombaylive
            "ROYALGAMING" -> R.drawable.royalgaming
            "EXCHANGE" -> R.drawable.exchange
            else -> -1
        }
    }

    private fun observerUserSCPack() {
        transactionViewModel.getUserSCPack()
        transactionViewModel.userSCPack.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Loading -> {}
                is ApiResult.Success -> {
                    val list = ArrayList<String>()
                    list.add("ALL")
                    it.data?.userSCPackResponse?.forEach { it1 ->
                        list.add(it1?.productName ?: "")
                        val adapter = ArrayAdapter(
                            requireContext(), android.R.layout.simple_list_item_1, list
                        )
                        binding.layoutDateSpinner.apply {
                            spinner.adapter = adapter
                            spinner.onItemSelectedListener =
                                object : AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
                                    ) {
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {
                                        spinner.setSelection(0)
                                    }
                                }
                        }
                    }
                }

                is ApiResult.Error -> {
                    val list = ArrayList<String>()
                    list.add("ALL")
                    val adapter = ArrayAdapter(
                        requireContext(), android.R.layout.simple_spinner_dropdown_item, list
                    )
                    binding.layoutDateSpinner.spinner.adapter = adapter
                }
            }
        }
    }

    private fun showDatePickerDialogStart() {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year, monthOfYear, dayOfMonth ->
                selectedYear = year
                selectedMonth = monthOfYear
                selectedDay = dayOfMonth
                binding.layoutDateSpinner.tvStartDate.text =
                    formatDateString((monthOfYear), dayOfMonth, year)
            }, selectedYear, selectedMonth, selectedDay
        )
        datePickerDialog.datePicker.maxDate = cal.timeInMillis
        datePickerDialog.show()
    }

    private fun showDatePickerDialogEnd() {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year, monthOfYear, dayOfMonth ->
                selYear = year
                selMonth = monthOfYear
                selDay = dayOfMonth
                binding.layoutDateSpinner.tvEndDate.text =
                    formatDateString(monthOfYear, dayOfMonth, year)
            }, selYear, selMonth, selDay
        )
        datePickerDialog.datePicker.maxDate = cal.timeInMillis
        datePickerDialog.show()
    }

    private fun formatDateString(month: Int, day: Int, year: Int): String {
        val calendar = Calendar.getInstance().apply {
            set(year, month, day)
        }
        val date = calendar.time
        return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date)
    }
}