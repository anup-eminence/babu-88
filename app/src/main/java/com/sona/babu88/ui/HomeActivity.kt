package com.sona.babu88.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sona.babu88.R
import com.sona.babu88.databinding.ActivityHomeBinding
import com.sona.babu88.databinding.ActivityMainBinding
import com.sona.babu88.ui.activity.BaseActivity
import com.sona.babu88.ui.fragment.HomeFragment

class HomeActivity : BaseActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HomeFragment(),binding.container.id)
    }
}