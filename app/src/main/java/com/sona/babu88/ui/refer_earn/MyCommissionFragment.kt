package com.sona.babu88.ui.refer_earn

import MySharedPreferences
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.sona.babu88.R
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.databinding.FragmentMyCommissionBinding
import com.sona.babu88.util.AppConstant

class MyCommissionFragment : Fragment() {
    private lateinit var binding: FragmentMyCommissionBinding
    private var userData: UserData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyCommissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        userData = MySharedPreferences.getSavedObjectFromPreference(
            requireContext(),
            AppConstant.USER_DATA
        )
        binding.tvId.text = stringBuilder(getString(R.string.id), userData?.user?.userName ?: "")
        binding.tvCode.text = stringBuilder(getString(R.string.referral_code), userData?.user?.refCode ?: "")
        binding.imgQr.setImageBitmap(generateQRCode("https://olddata.in/Dregister/reg?refCode=${userData?.user?.refCode ?: ""}"))
     }

    private fun setOnClickListener() {
        binding.btnShare.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Refer a friend now and earn commission olddata.in")
                intent.putExtra(Intent.EXTRA_TEXT, "https://olddata.in/Dregister/reg?refCode=${userData?.user?.refCode ?: ""}")
                startActivity(Intent.createChooser(intent, "Share Via"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun stringBuilder(title: String, value: String): String {
        return StringBuilder().append(title).append(" ").append(value).toString()
    }

    private fun generateQRCode(text: String): Bitmap? {
        return try {
            val multiFormatWriter = MultiFormatWriter()
            val bitMatrix: BitMatrix =
                multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300)
            val barcodeEncoder = BarcodeEncoder()
            barcodeEncoder.createBitmap(bitMatrix)
        } catch (e: WriterException) {
            e.printStackTrace()
            null
        }
    }
}