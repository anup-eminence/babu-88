package com.sona.babu88.ui.activity

import MySharedPreferences
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sona.babu88.R
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.auth.AuthUtils
import com.sona.babu88.data.socket.SocketHandler
import com.sona.babu88.data.viewmodel.AuthViewModel
import com.sona.babu88.databinding.ActivitySplashBinding
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.showToast
import org.json.JSONObject

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        val responseDecrypt = AuthUtils.decryptData(
"z9E4rc+6 FZ/0 kChY0yKV77KfwVJgkG2/WEP/O6KSeaVwFhRpiFg7mPMht1c8qpw4y7BbulYYgtNTL4fUjcBwiDMVm71Gz+woroOCRv19nnBNDLFz8r4LlKoBY7yp7iCajPDU/nvnW+/xypt4aC7fLWlZEQt7N6U6Onxjh+FFuZgmlTJzRmcdBfPkWHsCIfnC2If2VLAiEub+YN1pc4QlyAt662lLYD2Oo46E5T1eepXRe7IOEJHQjAPMhsAA6kqDxApXFGBkNC//PzAwoGlC+s7BldriiUzDg8hG7F4C0nZm5Ovak/49 WWzWo0qwjytv8sANLQwWLDPUbqIjVVebN1zlzBIpG0HQpmSO4voMwmAnGzuyi+X/b59ZIvMsruCj75ORosPE/Ht0INxVkt523BDyLoVFZ5WL2Mx+jmnvRQgWUvMiY4zQppDZO6oJb/FXtD8o/Vjc/gdUPJbVxbEduWFMv/q5ImPGg0S93IqXAK+T2El+zX4Sg3LkH2Jrjr5PBJwJ6INCaPgrj73eCQayuzgzqyjYjq+KhtCjXZZN6EOsvuERlxE+EV9EjRqVEAAqRqOZi5M5wIAPbxkiCyT9yllCTv+INx8EZFktSuI8uYVsEuQZxct59EdYLYJsZofeYiZj9/TAnKzGvvDAvj6RcmYu7OKaywdkqVGhxjf1jAXewCQFmFMw3wNLguPrW+759 C79sga+/x4CqxpxCpkLPfmPcXrCCxxChaTSE8PJzcLuxIWsoM8m9FpA5Vg7Pr41dOg0gvYVkgVU91zbwJ4mhcG9NQ529h1co5j8weaTTr57aZ5pVPqTw6+XClwGDvDXvzgRvpRAJl2iTND/dmSWtBj+p1o5VkOYxf7r/es5IwAhD00g5eD2DrGe/kjAXzTDZZidBxiNAXTbYrQRad1S7Bc+EgA8W8FyqCOlGM0wzTUUx6DPV0E3SvA1MN0lwGIJ6X/fU0Cevjn9maMCSzkFpFxy5/B6uapBMTU4x87ya4j8zwn+J3/5 z4gvDK6iNzyqm/y6CtzFG36dnzjyqFWPP8lToLqGPNtrAzEF1aUTBHp6ANX30yPnXmnzW6wUuhJvXT33TyC/lV0ZNeb44Q2+KPGp8V+GudYeESTWBD1glypAP6YgOusYimP5TdFLVDLHJSGOHuvBcMPhff/HcPd/DDp+MpoM2tT/lhBxoRH0YxiLBcL1YxsQBOpmHVNQt7DClZDrejaKzJOq1uUPp403GfeU4xxFFXcabbV0AXGAkswSNTZmBAykz9hW0vdXLxdjYowNUTPFRXh5CoHZPc8bYXVTMj5B6GDydcbvLP+Srg5Gc55DLheiywTJDb5TfaP2FK0aHGRdLqEQCd8YyF6xNJ6FJJpOT9+loLSY+EZdOD41BKZ0aTU+iIGsk8d8mGRC0nghQ85mh64zwHNlUL4R7h8oaNlz7XGs8otfdTFYFj2uFi/sOjmAfEfrD45UDT5ws5hfM0YQyrCuZDYgPmF4pgiDzqMlfU5Pbc3Qy25n9MUSvnlOIN9Qqpm4NJmfdyel4srIz2F8N9oTAINrx0txKu2xxrp/TL+wCvMgIhlGkNAoKbb35wzU8/8 erVxF8UTVwsGypAwJqDCHP3XY+7 u7vNL4wl+WqKwDZSBb5/Sz5yZsjIjDVSr46EqR65Q985oQWcJHPT6iPsjubqSV0gU8Ig=="
        )


        val decryptBody = AuthUtils.decryptData(
            "hGw6ux+fpKY12wz+VbXB2kjUDIyGUQr8qvjlKJsi6EnnSXp9qPWkZA2Q03fVpqSfBoQacQRx0n/SDH1F5dJgEQHdrm+KBgw1cHlLD3xKl/s=",
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