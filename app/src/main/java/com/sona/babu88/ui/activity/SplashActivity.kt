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
import com.sona.babu88.data.HomeViewModel
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
"kLbkI7vVE8G8kmmVUf98YWbTaluNtC+/eymNuexlC7DTNy5CmHvUC/rMJ7tvicmX/2 s5xpMNI0F4wYFyuPmAG2D8Twy15g7NkUY+vCYchP4="
        )


        val decryptBody = AuthUtils.decryptData(
            "PGl77fs/9Ao2VDX2URcFOLuDjOlHfc2+MOtUBJQ2EvYKH/KCeZRYyqrm4AanNejqy6ZJf7EL0Jyu9+x2wrFoL816zuRs9zOqjAfaIHdd/38=",
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