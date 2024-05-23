package com.sona.babu88.ui.deposit_withdrawal

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.CustomDepositDialogBinding
import com.sona.babu88.databinding.FragmentDepositBinding

class DepositFragment : Fragment(), DepositAmountAdapter.OnAmountClickListener {
    private lateinit var binding: FragmentDepositBinding
    private lateinit var depositAmountAdapter: DepositAmountAdapter
    private var depositAmountList = arrayListOf<DepositAmountList>()
    private var totalAmount = 0
    private var selectedTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDepositBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            tvPaymentMethods.text = getFormattedText(getString(R.string.payment_methods))
            tvDepositChannel.text = getFormattedText(getString(R.string.deposit_channel))
            tvDepositAmount.text = getFormattedText(getString(R.string.deposit_amount))
            tvDepositBonus.text = getFormattedText(getString(R.string.deposit_bonus))
        }
        setDepositAmountAdapter()
        setDepositAmountData()
    }

    private fun setOnClickListener() {
        binding.apply {
            imgClear.setOnClickListener {
                etDepositAmount.text?.clear()
                totalAmount = 0
            }
            btnCashout.setOnClickListener { selectTextView(btnCashout) }
            btnSendMoney.setOnClickListener { selectTextView(btnSendMoney) }
            btnDeposit.setOnClickListener { showCustomDialog() }
        }
    }

    private fun selectTextView(textView: TextView) {
        selectedTextView?.setBackgroundResource(R.drawable.bg_8_black_corner_border)
        if (selectedTextView == textView) {
            selectedTextView = null
        } else {
            textView.setBackgroundResource(R.drawable.bg_8_yellow_border)
            selectedTextView = textView
        }
    }

    private fun setDepositAmountAdapter() {
        binding.amountRecyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        depositAmountAdapter = DepositAmountAdapter()
        depositAmountAdapter.setAmountClickListener(this@DepositFragment)
        binding.amountRecyclerView.adapter = depositAmountAdapter
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

    override fun onAmountClickListener(item: DepositAmountList?) {
        totalAmount += item?.amount?.toInt()!!
        binding.etDepositAmount.text =
            Editable.Factory.getInstance().newEditable(totalAmount.toString())
    }

    private fun getFormattedText(text: String): SpannableStringBuilder {
        return SpannableStringBuilder().append(text)
            .color(ContextCompat.getColor(requireContext(), R.color.light_red)) { append(" *") }
    }

    private fun showCustomDialog() {
        CustomDepositDialogBinding.inflate(LayoutInflater.from(requireContext())).apply {
            val depositFloat = binding.etDepositAmount.text.toString().toFloatOrNull() ?: 0.0f
            val bonusFloat = binding.txtDepositBonus.text.toString().toFloatOrNull() ?: 0.0f

            depositAmount.text = "₹ %.2f".format(depositFloat)
            bonusAmount.text = "₹ %.2f".format(bonusFloat)
            targetAmount.text = "₹ %.2f".format(depositFloat + bonusFloat)

            val builder = AlertDialog.Builder(requireContext())
                .setView(root)
                .setCancelable(false)
                .create()
            ivClose.setOnClickListener { builder.dismiss() }
            btnConfirm.setOnClickListener { builder.dismiss() }
            builder.show()
        }
    }
}