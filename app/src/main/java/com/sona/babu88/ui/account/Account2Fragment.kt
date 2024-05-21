package com.sona.babu88.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sona.babu88.databinding.FragmentAccount2Binding
import com.sona.babu88.util.showToast

class Account2Fragment : Fragment(), Account2Adapter.OnAccountItemClickListener {
    private lateinit var binding: FragmentAccount2Binding
    private lateinit var account2Adapter: Account2Adapter
    private var accountList = arrayListOf<AccountList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccount2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setAccountAdapter()
        setAccountData()
    }

    private fun setAccountAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        account2Adapter = Account2Adapter()
        account2Adapter.setOnItemClickListener(this@Account2Fragment)
        binding.recyclerView.adapter = account2Adapter
    }

    private fun setAccountData() {
        accountList.add(AccountList("My Profile"))
        accountList.add(AccountList("Balance Overview"))
        accountList.add(AccountList("My Bets"))
        accountList.add(AccountList("Bets History"))
        accountList.add(AccountList("Profit & Loss"))
        accountList.add(AccountList("Results"))
        accountList.add(AccountList("Activity Log"))
        account2Adapter.setAccountAdapterData(accountList)
    }

    override fun onAccountItemClickListener(item: AccountList?) {
        requireContext().showToast("${item?.title} Clicked...")
    }
}