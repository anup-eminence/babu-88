package com.sona.babu88.ui.history

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentTransactionRecordsBinding
import com.sona.babu88.model.HomeTab
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TransactionRecordsFragment : Fragment() {
    private lateinit var binding: FragmentTransactionRecordsBinding
    private lateinit var historyTabAdapter: HistoryTabAdapter
    private var homeTabList = arrayListOf<HomeTab>()
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionRecordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        setTabAdapter()
        setTabData()
        setSpinner()
        val currentDate =
            SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(cal.timeInMillis)
        binding.layoutDateSpinner.apply {
            tvStartDate.text = currentDate
            tvEndDate.text = currentDate
        }
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
        historyTabAdapter = HistoryTabAdapter()
//        homeTabAdapter.setOnTabListener(this@WalletHistoryFragment)
        binding.recyclerViewTab.adapter = historyTabAdapter
    }

    private fun setTabData() {
        homeTabList.add(HomeTab(R.drawable.ic_deposit, "Deposit"))
        homeTabList.add(HomeTab(R.drawable.ic_withdrawal, "Withdrawal"))
        homeTabList.add(HomeTab(R.drawable.ic_transfer_history, "Adjustment"))
        historyTabAdapter.setTabData(homeTabList)
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

    private fun setSpinner() {
        val list = ArrayList<String>()
        list.add("All")
        list.add("Approved")
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