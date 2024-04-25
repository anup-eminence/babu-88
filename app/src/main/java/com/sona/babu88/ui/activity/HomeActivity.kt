package com.sona.babu88.ui.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.sona.babu88.databinding.ActivityHomeBinding
import com.sona.babu88.ui.fragment.HomeFragment
import com.sona.babu88.util.CurrLangDialogFragment

class HomeActivity : BaseActivity(), CurrLangDialogFragment.OnItemClick {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var langDialog: CurrLangDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HomeFragment(),binding.container.id)

        langDialog = CurrLangDialogFragment()
        langDialog.clickListener(this@HomeActivity)
        langDialog.isCancelable = false
        binding.layoutToolBar.clCountry.setOnClickListener {
            langDialog.show(supportFragmentManager, "")
        }
    }

    override fun onCloseCLick() {
        langDialog.dismiss()
    }

    override fun onSelectItem(image: Int) {
        Glide.with(this).load(image).into(binding.layoutToolBar.countryImage)
        langDialog.dismiss()
    }
}