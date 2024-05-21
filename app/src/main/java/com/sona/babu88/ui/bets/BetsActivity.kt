package com.sona.babu88.ui.bets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sona.babu88.R
import com.sona.babu88.databinding.ActivityBetsBinding

class BetsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        initView()
        setOnClickListener()
    }

    private fun initView() {}

    private fun setOnClickListener() {
        binding.apply {
            ivClose.setOnClickListener { finish() }
        }
    }
}