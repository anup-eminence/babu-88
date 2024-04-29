package com.sona.babu88.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sona.babu88.R
import com.sona.babu88.databinding.ActivityHomeBinding
import com.sona.babu88.ui.drawer.NavUtils
import com.sona.babu88.ui.drawer.NavigationDrawerAdapter
import com.sona.babu88.ui.drawer.NavigationItem
import com.sona.babu88.ui.fragment.HomeFragment
import com.sona.babu88.util.CurrLangDialogFragment

class HomeActivity : BaseActivity(), CurrLangDialogFragment.OnItemClick, NavigationDrawerAdapter.NavDrawerAdapterClickListener {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var langDialog: CurrLangDialogFragment

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
            langDialog.show(supportFragmentManager, "")
        }
        showSideDrawer()
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

    override fun onAdapterItemClick(item: NavigationItem) {
        binding.drawerLayout.closeDrawers()
    }
}