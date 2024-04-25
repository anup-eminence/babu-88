package com.sona.babu88.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sona.babu88.FishingAdapter
import com.sona.babu88.FishingList
import com.sona.babu88.R
import com.sona.babu88.databinding.ActivityMainBinding
import com.sona.babu88.utils.CurrLangDialogFragment

class MainActivity : AppCompatActivity(), CurrLangDialogFragment.OnItemClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var langDialog: CurrLangDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        langDialog = CurrLangDialogFragment()
        langDialog.clickListener(this@MainActivity)
        langDialog.isCancelable = false
        binding.layoutToolBar.clCountry.setOnClickListener {
            langDialog.show(supportFragmentManager, "")
        }
    }

    override fun onCloseCLick() {
        langDialog.dismiss()
    }

    override fun onSelectItem(image: Int) {
        Glide.with(this).load(image).into(binding.layoutToolBar.countryImage)
        langDialog.dismiss()
    }








    /*

    private lateinit var fishingAdapter: FishingAdapter
    private var fishingList = arrayListOf<FishingList>()


    setFishingAdapter()
    setFishingData()
    fishingAdapter.setFishingData(fishingList)

   private fun setFishingAdapter() {
        binding.layoutFishing.recyclerView.layoutManager = LinearLayoutManager(this)
        fishingAdapter = FishingAdapter()
        binding.layoutFishing.recyclerView.adapter = fishingAdapter
    }

    private fun setFishingData() {
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))
        fishingList.add(FishingList(R.drawable.img_fishing))






          <include
        android:id="@+id/layout_fishing"
        layout="@layout/layout_fishing"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_marginTop="15dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/layout_tool_bar" />

    <androidx.appcompat.widget.AppCompatImageView
        android:id="@+id/img_1"
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:src="@drawable/img1_1"
        app:layout_constraintTop_toBottomOf="@id/layout_fishing"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>
    }*/
}