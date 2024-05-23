package com.sona.babu88.ui.promotiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sona.babu88.databinding.FragmentPromotionDetailsBinding

class PromotionDetailsFragment : DialogFragment() {
    private lateinit var binding: FragmentPromotionDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromotionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setOnClickListener()
    }

    private fun initView() {
        binding.tvText.text = "₹10,000 RELOAD BONUS\n" + "\n" + "RESTRICTIONS:\n" + ".EACH MEMBER IS ONLY ENTITLED TO PARTICIPATE IN \n" + "THIS PROMOTION ONCE A WEEK.\n" + "\n" + "- ONLY THE NEXT QUALIFYING DEPOSIT MADE AFTER OPTING IN TO THE PROMOTION WILL QUALIFY,\n" + " \n" + "ANY SUBSEQUENT DEPOSITS WILL NOT BE \n" + "ELIGIBLE.THIS PROMOTION CANNOT BE USED IN\n" + "CONJUNCTION WITH ANY OTHER \n" + "PROMOTIONS ON THE WEBSITE.\n" + "\n" + "\n" + "OTHER TERMS:\n" + "- BAJI RESERVES THE RIGHT TO WITHDRAW THE MEMBER'S BONUS IF THE ACCOUNT IS SUSPECTED OF ABUSE AND/OR DOES NOT COMPLY WITH THE BENEFITS RECEIVED.\n" + "- BAJI RESERVES THE RIGHT TO EXCLUDE ANY MEMBER FROM THIS PROMOTION AT ANY TIME AND TO VARY, AMEND, CHANGE AND /OR WITHDRAW ANY BONUS OFFERS OR PROMOTIONS WITHOUT PROVIDING ANY EXPLANATION WHATSOEVER.\n" + "- BAJI’S GENERAL TERMS AND CONDITIONS APPLY.\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "GET READY FOR THE UPCOMING EXCITING MATCHES BY BOOSTING YOUR ACCOUNT BALANCE WITH A 5% SPORTS BONUS AND AN EXTRA 5% LIVE CASINO BONUS IN OUR 'BPL & PSL OFF-MATCH SPECIALS' OFFER! THIS OFFER IS PERFECTLY CRAFTED TO ENSURE YOU'RE WELL-PREPARED TO MAKE THE MOST OF EVERY PLAY! DON'T JUST BE A SPECTATOR - BE A PART OF THE WINNING EXCITEMENT!\n" + " \n" + " \n" + "THIS PROMOTION IS AVAILABLE TO ALL MEMBERS.\n" + "THIS PROMOTION STARTS FROM 21:30 (GMT+5.5) ON 18TH JANUARY 2024 UNTIL 21:29 (GMT+5.5) ON 18TH MARCH 2024.\n" + "*THIS PROMOTION IS ONLY AVAILABLE ON DAYS WHEN THERE ARE NO BPL OR PSL MATCHES.\n" + " \n" + " \n" + "WHAT SHOULD YOU DO?\n" + "- SELECT THIS OFFER ON THE DEPOSIT PAGE WHILE MAKING YOUR DEPOSIT.\n" + "- MAKE A MINIMUM DEPOSIT OF 500 INR.\n" + " \n" + " \n" + "WHAT DO YOU GET?\n" + "- YOU WILL IMMEDIATELY RECEIVE A 5% LIVE CASINO BONUS, FOLLOWED BY A 5% SPORTS BONUS THE NEXT DAY.\n" + "EXAMPLE: IF YOU DEPOSIT 1,000 INR, THEN THE LIVE CASINO BONUS YOU WILL RECEIVE IS 50 INR (1,000 X 5% = 50). THE NEXT DAY, YOU WILL RECEIVE A SPORT BONUS OF 50 INR (1,000 X 5% = 50). THE TOTAL AMOUNT YOU WILL RECEIVE IS 1,100 INR (1,000 + 50 + 50 = 1,100).\n" + " \n" + "- BAJI RESERVES THE RIGHT TO EXCLUDE ANY MEMBER FROM THIS PROMOTION AT ANY TIME AND TO VARY, AMEND, CHANGE AND /OR WITHDRAW ANY BONUS OFFERS OR PROMOTIONS WITHOUT PROVIDING ANY EXPLANATION WHATSOEVER.\n" + "- BAJIÂ€™S GENERAL TERMS AND CONDITIONS APPLY."
    }

    private fun setOnClickListener() {
        binding.ivClose.setOnClickListener { dialog?.dismiss() }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}