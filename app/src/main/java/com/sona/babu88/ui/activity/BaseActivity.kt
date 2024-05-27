package com.sona.babu88.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

open class BaseActivity : AppCompatActivity() {
    
    protected fun setFragment(fragment: Fragment,container : Int) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(container, fragment)
        fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }
}