package com.sona.babu88.ui.activity

import android.os.Bundle
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.sona.babu88.R
import com.sona.babu88.databinding.ActivityAgentAffiliateBinding
import com.sona.babu88.ui.agentaffiliatte.AgentAffiliateFragment
import com.sona.babu88.util.showToast

class AgentAffiliateActivity : BaseActivity() {
    private lateinit var binding: ActivityAgentAffiliateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgentAffiliateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        setFragment(AgentAffiliateFragment(), binding.container.id)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.ivMenu.setOnClickListener {
            val popupMenu = PopupMenu(this, binding.ivMenu)
            popupMenu.menuInflater.inflate(R.menu.agent_popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.home -> { showToast(item.title.toString()) }
                    R.id.campaigns_promotions -> { showToast(item.title.toString()) }
                    R.id.join_us -> { showToast(item.title.toString()) }
                    R.id.faq -> { showToast(item.title.toString()) }
                    R.id.login -> { showToast(item.title.toString()) }
                }
                true
            })
            popupMenu.show()
        }
    }
}