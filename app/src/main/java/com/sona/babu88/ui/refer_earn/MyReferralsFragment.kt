package com.sona.babu88.ui.refer_earn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sona.babu88.databinding.FragmentMyReferralsBinding

class MyReferralsFragment : Fragment() {
    private lateinit var binding: FragmentMyReferralsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyReferralsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnShare.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Refer a friend now and earn commission olddata.in")
                intent.putExtra(Intent.EXTRA_TEXT, "https://olddata.in/Dregister/reg?refCode=XlRTufNrma5YKjtB3bADOA==")
                startActivity(Intent.createChooser(intent, "Share Via"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}