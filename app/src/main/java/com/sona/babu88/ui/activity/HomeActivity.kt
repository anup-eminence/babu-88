package com.sona.babu88.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.data.HomeViewModel
import com.sona.babu88.databinding.ActivityHomeBinding
import com.sona.babu88.ui.account.AccountFragment
import com.sona.babu88.ui.bank.BankDetailsFragment
import com.sona.babu88.ui.bettingpass.BettingPassFragment
import com.sona.babu88.ui.changepassword.ChangePasswordFragment
import com.sona.babu88.ui.deposit_withdrawal.DepositWithdrawalFragment
import com.sona.babu88.ui.details.DetailsFragment
import com.sona.babu88.ui.drawer.NavUtils
import com.sona.babu88.ui.drawer.NavigationDrawerAdapter
import com.sona.babu88.ui.drawer.NavigationItem
import com.sona.babu88.ui.faq.FAQFragment
import com.sona.babu88.ui.history.HistoryFragment
import com.sona.babu88.ui.home.HomeFragment
import com.sona.babu88.ui.luckyspin.LuckySpinFragment
import com.sona.babu88.ui.profile.MyProfileFragment
import com.sona.babu88.ui.promotion.PromotionFragment
import com.sona.babu88.ui.refer_earn.ReferEarnFragment
import com.sona.babu88.ui.rewards.ClaimVoucherFragment
import com.sona.babu88.ui.rewards.RewardsFragment
import com.sona.babu88.util.CurrLangDialogFragment
import com.sona.babu88.util.OnAccountListener

class HomeActivity : BaseActivity(), CurrLangDialogFragment.OnItemClick, NavigationDrawerAdapter.NavDrawerAdapterClickListener, OnAccountListener {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var langDialog: CurrLangDialogFragment

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HomeFragment(),binding.container.id)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        langDialog = CurrLangDialogFragment()
        langDialog.clickListener(this@HomeActivity)
        langDialog.isCancelable = false
        binding.layoutToolBar.clCountry.setOnClickListener {
            langDialog.show(supportFragmentManager, "language")
        }
        showSideDrawer()
        binding.bottomNav.selectedItemId = R.id.nav_home
        setBottomNav()
        binding.layoutToolBar.toolbarImage.setOnClickListener { setFragment(HomeFragment(),binding.container.id) } //temp... added

        observer()

    }

    private fun observer() {
       /* homeViewModel.getGameList(
            provider = "ALL",
            category = "SLOT",
            page = 1
        )*/

        homeViewModel.getSpecialGameList()

        homeViewModel.gameList.observe(this){
            println(">>>>>gameList ${it.data}")

        }

        homeViewModel.specialGameList.observe(this){
            println(">>>>>>specialGameList ${it.data}")
        }
    }

    override fun onCloseCLick() {
        langDialog.dismiss()
    }

    override fun onSelectItem(image: Int) {
        Glide.with(this).load(image).into(binding.layoutToolBar.countryImage)
        langDialog.dismiss()
    }

    private fun showSideDrawer() {
        val inflater = LayoutInflater.from(this)
        val customDrawerView =
            inflater.inflate(R.layout.drawer_layout, binding.sideNavigation, false)
        val rv = customDrawerView.findViewById<RecyclerView>(R.id.rv_menu)
        val rootLayout = customDrawerView.findViewById<ConstraintLayout>(R.id.root)
        val navAdapter = NavigationDrawerAdapter()
        rv.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = navAdapter
            navAdapter.submitList(NavUtils.provideNavigationList(this@HomeActivity))
            navAdapter.setItemAdapterListener(this@HomeActivity)
        }
        binding.sideNavigation.addView(customDrawerView)
        binding.layoutToolBar.menu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun setBottomNav() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_referral -> { setFragment(ReferEarnFragment(), binding.container.id) }
                R.id.nav_promotion -> { setFragment(PromotionFragment(), binding.container.id) }
                R.id.nav_home -> { setFragment(HomeFragment(), binding.container.id) }
                R.id.nav_deposit -> { setFragment(DepositWithdrawalFragment(), binding.container.id) }
                R.id.nav_account -> { setFragment(AccountFragment(), binding.container.id) }
            }
            true
        }
    }

    override fun onAdapterItemClick(item: NavigationItem) {
        binding.drawerLayout.closeDrawers()
        when (item.name) {
            "Cricket", "Live Casino", "Slot Games", "Table Games", "Sportsbook", "Fishing", "Crash" -> {
                val bundle = Bundle()
                val df = DetailsFragment()
                bundle.putString("title", item.name)
                df.arguments = bundle
                setFragment(df, binding.container.id)
            }
            "Promotion" -> { setFragment(PromotionFragment(), binding.container.id)
                binding.bottomNav.selectedItemId = R.id.nav_promotion
            }
            "Refer and Earn" -> { setFragment(ReferEarnFragment(), binding.container.id)
                binding.bottomNav.selectedItemId = R.id.nav_referral
            }
            "Betting Pass" -> { setFragment(BettingPassFragment(), binding.container.id) }
            "Agent Affiliate" -> { startActivity(Intent(this, AgentAffiliateActivity::class.java)) }
            "Language" -> { langDialog.show(supportFragmentManager, "language") }
            "FAQ" -> { setFragment(FAQFragment(), binding.container.id) }
            "Logout" -> { startActivity(Intent(this, NewActivity::class.java)) }
        }
    }

    override fun onAccountClick(title: String) {
        when(title) {
            "Bet History" -> { setFragment(HistoryFragment(), binding.container.id) }
            "Turnover History" -> { setFragment(HistoryFragment(), binding.container.id) }
            "Wallet History" -> { setFragment(HistoryFragment(), binding.container.id) }
            "Claim Voucher" -> { setFragment(ClaimVoucherFragment(), binding.container.id) }
            "Lucky Spin" -> { setFragment(LuckySpinFragment(), binding.container.id) }
            "Daily Check In" -> { setFragment(RewardsFragment(), binding.container.id) }
            "Coin Grab" -> {}
            "LIVE CHAT" -> {}
            "Facebook" -> {}
            "Instagram" -> {}
            "Telegram" -> {}
            "Twitter" -> {}
            "YouTube" -> {}
            "Refer and Earn", "Referral" -> { setFragment(ReferEarnFragment(), binding.container.id) }
            "Betting Pass" -> { setFragment(BettingPassFragment(), binding.container.id) }
            "Agent Affiliate" -> { startActivity(Intent(this, AgentAffiliateActivity::class.java)) }
            "Bank Details" -> { setFragment(BankDetailsFragment(), binding.container.id) }
            "Profile" -> { setFragment(MyProfileFragment(), binding.container.id) }
            "Change password" -> { setFragment(ChangePasswordFragment(), binding.container.id) }
            "Deposit" -> { setFragment(DepositWithdrawalFragment(), binding.container.id) }
            "Withdrawal" -> { setFragment(DepositWithdrawalFragment(), binding.container.id) }
            "Rewards" -> { setFragment(RewardsFragment(), binding.container.id) }
        }
    }
}