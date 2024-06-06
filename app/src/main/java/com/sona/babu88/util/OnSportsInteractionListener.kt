package com.sona.babu88.util

interface OnSportsInteractionListener {
    fun onSportsClick(id: String?)
}

interface OnAccountListener {
    fun onAccountClick(title: String)
}

interface OnSelectedFragmentListener{
    fun onFragmentClickListener(params: ArrayList<String>)
}