package com.sona.babu88.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.color
import androidx.fragment.app.Fragment
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.data.viewmodel.HomeViewModel
import com.sona.babu88.databinding.ActivityNewBinding
import com.sona.babu88.ui.account.Account2Fragment
import com.sona.babu88.ui.bets.BetsActivity
import com.sona.babu88.ui.details.detail2.Details2Fragment
import com.sona.babu88.ui.inplay.InPlayFragment
import com.sona.babu88.ui.multimarket.MultiMarketsFragment
import com.sona.babu88.ui.setting.SettingActivity
import com.sona.babu88.ui.sports__.Sports2Fragment
import com.sona.babu88.util.OnSportsInteractionListener
import com.sona.babu88.util.hide
import com.sona.babu88.util.show

class NewActivity : BaseActivity(), OnSportsInteractionListener {
    private lateinit var binding: ActivityNewBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        initView()
        setOnClickListener()
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.fragments.isEmpty()){
            finish()
        }
    }

    private fun initView() {
        observerMessageWebsite()
        binding.apply {
            setFragment(Sports2Fragment(), binding.container.id)
            tvMain.text = SpannableStringBuilder().color(ContextCompat.getColor(this@NewActivity, R.color.yellow)){ append("Main") }.bold { append(" PTH 0") }
            tvExposure.text = SpannableStringBuilder().color(ContextCompat.getColor(this@NewActivity, R.color.yellow)){ append("Exposure") }.bold { append(" 0") }
        }
        setBottomNav()
    }

    private fun setOnClickListener() {
        binding.apply {
            floatingButton.setOnClickListener { finish() }
            imgSetting.setOnClickListener { startActivity(Intent(this@NewActivity, SettingActivity::class.java)) }
            clStart.setOnClickListener { startActivity(Intent(this@NewActivity, BetsActivity::class.java)) }
        }
    }

    private fun setBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_sports -> { setFragment(Sports2Fragment(), binding.container.id) }
                R.id.nav_in_play -> { setFragment(InPlayFragment(), binding.container.id)}
                R.id.empty -> { finish() }
                R.id.nav_multi_markets -> { setFragment(MultiMarketsFragment(), binding.container.id)}
                R.id.nav_account -> { setFragment(Account2Fragment(), binding.container.id) }
            }
            true
        }
    }

    override fun onSportsClick(id: String?) {
        setFragment(setFragmentArguments(Details2Fragment(), id ?: ""), binding.container.id)
    }

    private fun setFragmentArguments(fragment: Fragment, id: String): Fragment {
        val args = Bundle()
        args.putString("id", id)
        fragment.arguments = args
        return fragment
    }

    fun hideProgress() {
        binding.progressBar.hide()
    }

    fun showProgress() {
        binding.progressBar.show()
    }

    private fun observerMessageWebsite() {
        binding.tvMarquee.isSelected = true
        homeViewModel.getMessageWebsite()

        homeViewModel.messageWebsite.observe(this) {
            when (it) {
                is ApiResult.Loading -> {}

                is ApiResult.Success -> {
                    val text = StringBuilder()
                    if (it.data?.data?.isNullOrEmpty() == true) {
                        text.append("--            --             --             --                --             --             --             --")
                    }
                    else {
                        it.data?.data?.forEachIndexed { _, item ->
                            text.append("${item?.day} ${item?.month} ${item?.year} ${item?.title}: ${item?.message}       ")
                        }
                    }
                    binding.tvMarquee.text = text
                }

                is ApiResult.Error -> {}
                else -> {}
            }
        }
    }
}