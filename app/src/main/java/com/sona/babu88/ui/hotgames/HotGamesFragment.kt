package com.sona.babu88.ui.hotgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sona.babu88.R
import com.sona.babu88.databinding.FragmentHotGamesBinding
import com.sona.babu88.model.FishingList
import com.sona.babu88.ui.auth.LoginDialogFragment
import com.sona.babu88.ui.auth.register.RegisterDialogFragment

class HotGamesFragment : Fragment(), HotGamesAdapter.OnItemClickListener,
    LoginDialogFragment.OnItemClickListener, RegisterDialogFragment.RegisterDialogListener {
    private lateinit var binding: FragmentHotGamesBinding
    private lateinit var hotGamesAdapter: HotGamesAdapter
    private var fishingList = arrayListOf<FishingList>()
    private lateinit var loginSignupDialog: LoginDialogFragment
    private lateinit var registerDialogFragment: RegisterDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHotGamesAdapter()
        setHotGamesData()
        hotGamesAdapter.setHotGamesData(fishingList)
        loginSignupDialog = LoginDialogFragment()
        registerDialogFragment = RegisterDialogFragment(this@HotGamesFragment)
        loginSignupDialog.onItemClickListener(this@HotGamesFragment)
        loginSignupDialog.isCancelable = false
    }

    private fun setHotGamesAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        hotGamesAdapter = HotGamesAdapter()
        hotGamesAdapter.setOnItemClickListener(this@HotGamesFragment)
        binding.recyclerView.adapter = hotGamesAdapter
    }

    private fun setHotGamesData() {
        fishingList.add(FishingList(R.drawable.img_home_1))
        fishingList.add(FishingList(R.drawable.img_home_2))
        fishingList.add(FishingList(R.drawable.img_home_3))
        fishingList.add(FishingList(R.drawable.img_home_4))
        fishingList.add(FishingList(R.drawable.img_home_5))
        fishingList.add(FishingList(R.drawable.img_home_6))
        fishingList.add(FishingList(R.drawable.img_home_7))
        fishingList.add(FishingList(R.drawable.img_home_8))
        fishingList.add(FishingList(R.drawable.img_home_9))
        fishingList.add(FishingList(R.drawable.img_home_10))
        fishingList.add(FishingList(R.drawable.img_home_11))
        fishingList.add(FishingList(R.drawable.img_home_12))
    }

    override fun onItemClickListener() {
        if (loginSignupDialog != null && loginSignupDialog.isVisible.not()) {
            loginSignupDialog.show(requireActivity().supportFragmentManager, "")
        }
    }

    override fun dialogDismiss() {
        loginSignupDialog.dismiss()
    }

    override fun onLoginClick() {}

    override fun onSignUpClick() {
        loginSignupDialog.dismiss()
        registerDialogFragment.show(childFragmentManager, "register")
    }

    override fun onForgotPasswordClick() {}


    override fun moveToLogin() {
        if (loginSignupDialog != null && loginSignupDialog.isVisible.not()) {
            loginSignupDialog.show(
                requireActivity().supportFragmentManager,
                loginSignupDialog.javaClass.name
            )
        }
    }
}