package com.sona.babu88.ui.agentaffiliatte

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentAgentAffiliateBinding

class AgentAffiliateFragment : Fragment(), AgentAffiliateAdapter.OnAgentItemClickListener {
    private lateinit var binding: FragmentAgentAffiliateBinding
    private lateinit var agentAffiliateAdapter: AgentAffiliateAdapter
    private var agentAffiliateList = arrayListOf<AgentAffiliateList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgentAffiliateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setAgentAffiliateAdapter()
        setAgentAffiliateData()
    }

    private fun setAgentAffiliateAdapter() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        agentAffiliateAdapter = AgentAffiliateAdapter()
        agentAffiliateAdapter.setOnAgentItemClickListener(this@AgentAffiliateFragment)
        binding.recyclerView.adapter = agentAffiliateAdapter
    }

    private fun setAgentAffiliateData() {
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_trusted_brand,
                "Trusted Brand",
                "BABU88 is a premium cricket exchange and India’s largest online casino provider with over 100 live casinos, slots and virtual games!"
            )
        )
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_max_agent_commission,
                "Maximum Agent Commission",
                "BABU88 Agent offers the highest commission in the market! Always be a winner with us!"
            )
        )
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_agent_manager,
                "Agent Community Manager",
                "There are dedicated Agent Community Managers for any kind of support"
            )
        )
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_premium_platform,
                "Premium Platform",
                "BABU88 is a premium platform that is easy to use for all members with a simple user interface"
            )
        )
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_transparency,
                "Transparency",
                "Easily accessible backend software for agents to track daily data and commissions"
            )
        )
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_free_account,
                "Free Account",
                "No investment required! Anyone can register and join for free!"
            )
        )
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_promotional_material,
                "Promotional Materials",
                "All BABU88 agents will be provided with materials and latest marketing promotions to help agents promote"
            )
        )
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_fast_withdrawal,
                "Fast Withdrawal Guaranteed",
                "Your members will be constantly happy with our fast and 100% guaranteed withdrawals"
            )
        )
        agentAffiliateList.add(
            AgentAffiliateList(
                R.drawable.ic_language_support,
                "Hindi and English Language Support",
                "Talk to our customer service in Hindi or English, feel comfortable with our professional customer service"
            )
        )

        agentAffiliateAdapter.setAgentAffiliateData(agentAffiliateList)
    }

    override fun onAgentItemClickListener() {}
}