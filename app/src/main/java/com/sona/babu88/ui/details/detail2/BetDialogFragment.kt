package com.sona.babu88.ui.details.detail2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentBetDialogBinding
import com.sona.babu88.util.hide

class BetDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentBetDialogBinding
    private var pointClicked = false
    private val handler = Handler(Looper.getMainLooper())
    private val hideRunnable = Runnable {
        dialog?.dismiss()
    }
    private var color: Int? = null
    private var odds: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
        arguments?.let {
            color = it.getInt("color")
            odds = it.getString("odds").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        pointClicked = false
        binding.apply {
            clMain.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    color ?: R.color.white
                )
            )
            layoutBet.tvPrice.text = odds
        }
    }

    private fun setOnClickListener() {
        setNumberClick()
        binding.layoutBet.btnCancel.setOnClickListener { dialog?.dismiss() }
        binding.layoutBet.btnPlaceBet.setOnClickListener { dialog?.dismiss() }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setNumberClick() {
        binding.layoutBet.apply {
            tvNum1.setOnClickListener {
                tvText.text = ""
                append(tvNum1.text.toString())
            }
            tvNum2.setOnClickListener {
                tvText.text = ""
                append(tvNum2.text.toString())
            }
            tvNum3.setOnClickListener {
                tvText.text = ""
                append(tvNum3.text.toString())
            }
            tvNum4.setOnClickListener {
                tvText.text = ""
                append(tvNum4.text.toString())
            }
            tvNum5.setOnClickListener {
                tvText.text = ""
                append(tvNum5.text.toString())
            }
            tvNum6.setOnClickListener {
                tvText.text = ""
                append(tvNum6.text.toString())
            }

            tv0.setOnClickListener { append("0") }
            tv1.setOnClickListener { append("1") }
            tv2.setOnClickListener { append("2") }
            tv3.setOnClickListener { append("3") }
            tv4.setOnClickListener { append("4") }
            tv5.setOnClickListener { append("5") }
            tv6.setOnClickListener { append("6") }
            tv7.setOnClickListener { append("7") }
            tv8.setOnClickListener { append("8") }
            tv9.setOnClickListener { append("9") }
            tv00.setOnClickListener { append("00") }
            tvPoint.setOnClickListener {
                if (!pointClicked) {
                    append(".")
                    pointClicked = true
                }
            }
            tvBackSpace.setOnClickListener {
                val text = binding.layoutBet.tvText.text
                if (!text.isNullOrEmpty()) {
                    if (text.last() == '.') pointClicked = false
                    binding.layoutBet.tvText.text = text.subSequence(0, text.length - 1)
                }
            }
            btnMinus.setOnClickListener {
                if (tvText.text.toString()
                        .isNotEmpty() && tvText.text.toString() != "0"
                ) updateText(-1)
            }
            btnPlus.setOnClickListener { updateText(1) }
            btnCancel.setOnClickListener { root.hide() }
            btnPlaceBet.setOnClickListener { root.hide() }
        }
    }

    private fun append(num: String) {
        val currentText = binding.layoutBet.tvText.text.toString()
        binding.layoutBet.tvText.text = StringBuilder(currentText).append(num)
        handler.removeCallbacks(hideRunnable)
        handler.postDelayed(hideRunnable, 10000)
    }

    private fun updateText(value: Int) {
        val currentText = binding.layoutBet.tvText.text.toString()
        if (currentText.isNullOrEmpty().not()) {
            try {
                binding.layoutBet.tvText.text = (currentText.toInt() + value).toString()
            } catch (e: NumberFormatException) {
                binding.layoutBet.tvText.text = (currentText.toDouble() + value).toString()
            }
        } else if (currentText.isNullOrEmpty() && value > 0) {
            binding.layoutBet.tvText.text = value.toString()
        }
    }
}