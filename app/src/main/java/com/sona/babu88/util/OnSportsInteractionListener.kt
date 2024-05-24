package com.sona.babu88.util

interface OnSportsInteractionListener {
    fun onSportsClick()
}

interface OnAccountListener {
    fun onAccountClick(title: String)
}

interface OnSelectedFragmentListener{
    fun onFragmentClickListener(title: String, params: String)
}