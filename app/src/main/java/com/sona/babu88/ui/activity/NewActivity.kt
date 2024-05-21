package com.sona.babu88.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import com.sona.babu88.R
import com.sona.babu88.databinding.ActivityNewBinding
import com.sona.babu88.ui.account.Account2Fragment
import com.sona.babu88.ui.bets.BetsActivity
import com.sona.babu88.ui.details.detail2.Details2Fragment
import com.sona.babu88.ui.inplay.InPlayFragment
import com.sona.babu88.ui.multimarket.MultiMarketsFragment
import com.sona.babu88.ui.setting.SettingActivity
import com.sona.babu88.ui.sports__.Sports2Fragment
import com.sona.babu88.util.OnSportsInteractionListener

class NewActivity : BaseActivity(), OnSportsInteractionListener {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.apply {
            tvMarquee.isSelected = true
            setFragment(Sports2Fragment(), binding.container.id)
            tvMain.text = SpannableStringBuilder().color(ContextCompat.getColor(this@NewActivity, R.color.yellow)){ append("Main") }.bold { append(" PTH 0") }
            tvExposure.text = SpannableStringBuilder().color(ContextCompat.getColor(this@NewActivity, R.color.yellow)){ append("Exposure") }.bold { append(" 0") }
        }
        setBottomNav()
    }

    private fun setOnClickListener() {
        binding.apply {
            floatingButton.setOnClickListener { startActivity(Intent(this@NewActivity, HomeActivity::class.java)) }
            imgSetting.setOnClickListener { startActivity(Intent(this@NewActivity, SettingActivity::class.java)) }
            clStart.setOnClickListener { startActivity(Intent(this@NewActivity, BetsActivity::class.java)) }
        }
    }

    private fun setBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_sports -> { setFragment(Sports2Fragment(), binding.container.id) }
                R.id.nav_in_play -> { setFragment(InPlayFragment(), binding.container.id)}
                R.id.empty -> { startActivity(Intent(this, HomeActivity::class.java)) }
                R.id.nav_multi_markets -> { setFragment(MultiMarketsFragment(), binding.container.id)}
                R.id.nav_account -> { setFragment(Account2Fragment(), binding.container.id) }
            }
            true
        }
    }

    override fun onSportsClick() {
        setFragment(Details2Fragment(), binding.container.id)
    }
}