package com.sona.babu88.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.ActivitySettingBinding
import com.sona.babu88.ui.deposit_withdrawal.DepositAmountAdapter
import com.sona.babu88.ui.deposit_withdrawal.DepositAmountList

class SettingActivity : AppCompatActivity(), DepositAmountAdapter.OnAmountClickListener {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var depositAmountAdapter: DepositAmountAdapter
    private var depositAmountList = arrayListOf<DepositAmountList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        setDepositAmountAdapter()
        setDepositAmountData()
    }

    private fun setOnClickListener() {
        binding.apply {
            ivClose.setOnClickListener { finish() }
            btnCancel.setOnClickListener { finish() }
            btnSave.setOnClickListener { finish() }
        }
    }

    private fun setDepositAmountAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        depositAmountAdapter = DepositAmountAdapter()
        depositAmountAdapter.setAmountClickListener(this@SettingActivity)
        binding.recyclerView.adapter = depositAmountAdapter
    }

    private fun setDepositAmountData() {
        depositAmountList.add(DepositAmountList("1000"))
        depositAmountList.add(DepositAmountList("1500"))
        depositAmountList.add(DepositAmountList("2000"))
        depositAmountList.add(DepositAmountList("30000"))
        depositAmountList.add(DepositAmountList("50000"))
        depositAmountList.add(DepositAmountList("100"))
        depositAmountList.add(DepositAmountList("200"))
        depositAmountList.add(DepositAmountList("500"))

        depositAmountAdapter.setDepositAmountData(depositAmountList)
    }

    override fun onAmountClickListener(item: DepositAmountList?) {}
}