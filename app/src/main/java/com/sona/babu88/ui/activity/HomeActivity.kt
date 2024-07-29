package com.sona.babu88.ui.activity

import MySharedPreferences
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.auth.CheckUserLogin
import com.sona.babu88.data.viewmodel.HomeViewModel
import com.sona.babu88.data.viewmodel.AuthViewModel
import com.sona.babu88.databinding.ActivityHomeBinding
import com.sona.babu88.ui.account.AccountFragment
import com.sona.babu88.ui.auth.LoginDialogFragment
import com.sona.babu88.ui.auth.register.RegisterDialogFragment
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
import com.sona.babu88.ui.vip.VipFragment
import com.sona.babu88.ui.vipdetails.VipDetailsFragment
import com.sona.babu88.util.AppConstant.TOKEN
import com.sona.babu88.util.AppConstant.USER_DATA
import com.sona.babu88.util.CurrLangDialogFragment
import com.sona.babu88.util.OnAccountListener
import com.sona.babu88.util.OnSelectedFragmentListener
import com.sona.babu88.util.hide
import com.sona.babu88.util.show
import com.sona.babu88.util.showToast

class HomeActivity : BaseActivity(), CurrLangDialogFragment.OnItemClick, NavigationDrawerAdapter.NavDrawerAdapterClickListener, OnAccountListener, OnSelectedFragmentListener,
    LoginDialogFragment.OnItemClickListener, RegisterDialogFragment.RegisterDialogListener {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var langDialog: CurrLangDialogFragment

    private val homeViewModel : HomeViewModel by viewModels()
    private val authViewModel : AuthViewModel by viewModels()
    private lateinit var loginSignupDialog: LoginDialogFragment
    private lateinit var registerDialogFragment: RegisterDialogFragment

    companion object {
        var userLogedIn = MutableLiveData<Boolean>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginSignupDialog = LoginDialogFragment()
        registerDialogFragment = RegisterDialogFragment(this@HomeActivity)
        loginSignupDialog.onItemClickListener(this@HomeActivity)
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
        observer()
        loginUser()
        binding.layoutToolBar.toolbarImage.setOnClickListener { setFragment(HomeFragment(),binding.container.id) } //temp... added
    }


    private fun loginUser() {
        if (MySharedPreferences.readString(TOKEN, "").isNullOrEmpty()) {
            binding.login.root.show()
            binding.bottomNav.hide()
            binding.layoutToolBar.clCountry.show()
            binding.layoutToolBar.ivMessage.hide()
        } else {
            binding.login.root.hide()
            binding.bottomNav.show()
            binding.layoutToolBar.clCountry.hide()
            binding.layoutToolBar.ivMessage.show()
        }
        binding.login.signUp.setOnClickListener {
            if (registerDialogFragment != null && !registerDialogFragment.isVisible){
                registerDialogFragment.show(supportFragmentManager,"login_signup")
            }
        }
        binding.login.login.setOnClickListener {
            if (loginSignupDialog != null && !loginSignupDialog.isVisible){
                loginSignupDialog.show(supportFragmentManager,"login_signup")
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun observer() {
       /* homeViewModel.getGameList(
            provider = "ALL",
            category = "SLOT",
            page = 1
        )*/

        CheckUserLogin.checkUserLogin.observe(this){
            println(">>>>it user $it")
        }

        userLogedIn.observe(this) {
            if (it) {
              //  CheckUserLogin.startLoginCheck()
            } else {
                CheckUserLogin.stopLoginCheck()
            }
        }


//        homeViewModel.getSpecialGameList()

//        homeViewModel.gameList.observe(this){
//            println(">>>>>gameList ${it.data}")
//
//        }

//        homeViewModel.specialGameList.observe(this){
//            println(">>>>>>specialGameList ${it.data}")
//        }
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
            "Cricket" -> setFragment(setArguments(DetailsFragment(), params = arrayListOf("Cricket", "", "ALL", "0")), binding.container.id)
            "Live Casino" -> setFragment(setArguments(DetailsFragment(), params = arrayListOf("Casino", "LIVE", "ALL", "1")), binding.container.id)
            "Slot Games" -> setFragment(setArguments(DetailsFragment(), params = arrayListOf("SLOT", "SLOT", "ALL", "2")), binding.container.id)
            "Table Games" -> setFragment(setArguments(DetailsFragment(), params = arrayListOf("Table Games", "TABLE", "ALL", "3")), binding.container.id)
            "Sportsbook" -> setFragment(setArguments(DetailsFragment(), params = arrayListOf("Sports", "", "ALL", "4")), binding.container.id)
            "Fishing" -> setFragment(setArguments(DetailsFragment(), params = arrayListOf("Fishing", "FH", "ALL", "5")), binding.container.id)
            "Crash" -> setFragment(setArguments(DetailsFragment(), params = arrayListOf("Crash", "", "ALL", "6")), binding.container.id)

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
            "Forum" -> {}
            "Download App" -> {}
            "Logout" -> { logout() }
        }
    }

    override fun onAccountClick(title: String) {
        when(title) {
            "My VIP" -> { setFragment(VipFragment(), binding.container.id) }
            "VipDetails" -> { setFragment(VipDetailsFragment(), binding.container.id) }
            "Betting Records", "Bet History" -> { setFragment(setFragmentArguments(HistoryFragment(), "tab", "0"), binding.container.id) }
            "Turnover" -> { setFragment(setFragmentArguments(HistoryFragment(), "tab", "1"), binding.container.id) }
            "Transaction Records" -> { setFragment(setFragmentArguments(HistoryFragment(), "tab", "2"), binding.container.id) }
            "Profit Loss" -> { setFragment(setFragmentArguments(HistoryFragment(), "tab", "3"), binding.container.id) }
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
            "Withdrawal" -> { setFragment(setFragmentArguments(DepositWithdrawalFragment(), "tab", "1"), binding.container.id) }
            "Rewards" -> { setFragment(RewardsFragment(), binding.container.id) }
            "LOG_OUT" -> { logout() }
        }
    }

    private fun setFragmentArguments(fragment: Fragment, key: String, value: String): Fragment {
        val args = Bundle()
        args.putString(key, value)
        fragment.arguments = args
        return fragment
    }

    private fun setArguments(fragment: Fragment, params: ArrayList<String>): Fragment {
        val args = Bundle()
        args.putStringArrayList("params", params)
        fragment.arguments = args
        return fragment
    }

    override fun onFragmentClickListener(params: ArrayList<String>) {
        setFragment(setArguments(DetailsFragment(), params), binding.container.id)
    }

    fun hideProgress() {
        binding.progressBar.hide()
    }

    fun showProgress() {
        binding.progressBar.show()
    }

    override fun dialogDismiss() {
        loginSignupDialog?.dismiss()
    }

    override fun onLoginClick(usernName: String, passWord: String) {
        authViewModel.authenticateUser(
            userId = usernName,
            passWord = passWord
        )
        authViewModel.authenticateUser.observe(this){
            when(it) {
                is ApiResult.Loading -> {
                    println(">>>>>>>loading")
                }
                is ApiResult.Success -> {
                    if (it.data?.login == true) {
                        MySharedPreferences.writeString(TOKEN, it.data?.user?.token ?: "")
                        MySharedPreferences.saveObjectToSharedPreference(this@HomeActivity,USER_DATA,it.data)
                        loginSignupDialog.dismiss()
                        binding.bottomNav.show()
                        binding.login.root.hide()
                        binding.layoutToolBar.clCountry.hide()
                        binding.layoutToolBar.ivMessage.show()
                        userLogedIn.postValue(true)
                        recreate()
                    } else {
                        this.showToast("Login Failed")
                    }
                    println(">>>>>>>success ${it.data}")
                }
                is ApiResult.Error -> {
                    println(">>>>>>erroro ${it.message}")
                    this.showToast(it.message.toString())
                }
            }
        }
    }

    override fun onSignUpClick() {
        registerDialogFragment.show(supportFragmentManager,"register")
    }

    override fun onForgotPasswordClick() {

    }

    override fun moveToLogin() {
        loginSignupDialog.show(supportFragmentManager,"login")
    }

    private fun logout() {
        MySharedPreferences.writeString(TOKEN,"")
        MySharedPreferences.clear()
        binding.bottomNav.hide()
        binding.bottomNav.selectedItemId = R.id.nav_home
        binding.login.root.show()
        binding.layoutToolBar.clCountry.show()
        binding.layoutToolBar.ivMessage.hide()
        setFragment(HomeFragment(),binding.container.id )
        println(">>>>>>>success logout")
    }
}