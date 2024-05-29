package com.sona.babu88.ui.activity

import MySharedPreferences
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.auth.AuthUtils
import com.sona.babu88.data.viewmodel.AuthViewModel
import com.sona.babu88.databinding.ActivitySplashBinding
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.showToast

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val responseDecrypt = AuthUtils.decryptData(
"DZZyZioH9X5uacvWSaEHleJNleZi8UE38bvRmDWDoAMw8x0E5lhSfQPLYvBXQl1VAam3S4A7Z55LGT5AbgGATczZ54O8m2S2/H8077vh7GWZPLM0fagVWv79bHEzG1ZEw6Re6UqOHLpEk3q9kqJXdwoExSzxkok1BnggMfG6iT8yhz9ICGQF8rV+Uw/YVtAi"
        )


        val decryptBody = AuthUtils.decryptData(
            "ZK1Rv1jlfvz4bs1SuDGUJEbZh7oHjE5ZrEBSVaHM5AFwCJq+r8ca+Ju1xXuuT2cUbpl3QN2Ce+KNZ7pZGyLAMXEQ/I6nCthQ5y7J5BbHxHbt2bL2Eo4NtWv7jlDNnRtyIHPHWSSNXdye3hqzeQnBMQ==",
            "UFJGSFguW1Q0MjdPWF0xfQ=="
        )
        println(">>>>>>>.responseDecrypt $responseDecrypt")
        println(">>>>>>>.encryptData $decryptBody")
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

       /* if (MySharedPreferences.readString(AppConstant.TOKEN,"").isNullOrEmpty()){
            authViewModel.authenticateUser(
                "ryzen2",
                "Abcd1234",
                "localhost",
                "180.151.28.84"
            )
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }*/

        authViewModel.authenticateUser.observe(this){
          when(it) {
              is ApiResult.Loading -> {
                  println(">>>>>>>loading")
              }
              is ApiResult.Success -> {
                  MySharedPreferences.writeString(AppConstant.TOKEN,it.data?.user?.token?:"")
                  Handler(Looper.getMainLooper()).postDelayed({
                      val intent = Intent(this, HomeActivity::class.java)
                      startActivity(intent)
                      finish()
                  }, 2000)
                  println(">>>>>>>success ${it.data}")
              }
              is ApiResult.Error -> {
                  println(">>>>>>erroro ${it.message}")
                  this.showToast(it.message.toString())
              }
          }
        }
    }
}