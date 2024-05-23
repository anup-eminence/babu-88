package com.sona.babu88.util

import java.util.Date

object AppConstant {

    const val TOKEN = "token"
    const val TIMESTAMP = "timeStamp"
    const val SECRET_KEY = "secretKey"

    fun getTimeStamp(): Map<String, String> {
        val resp = mapOf(
            TIMESTAMP to Date().time.toString(),
            SECRET_KEY to randomString()
        )
        return resp
    }

    private fun randomString(): String {
        val p = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (0 until 10).fold("") { acc, _ -> acc + p[(Math.random() * p.length).toInt()] }
    }
}