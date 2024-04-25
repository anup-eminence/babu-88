package com.sona.babu88.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager.widget.PagerAdapter
import com.sona.babu88.R
import com.sona.babu88.util.ViewPagerItem

class ViewPagerAdapter(
    private val list: List<ViewPagerItem>
) : PagerAdapter() {


    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(
            R.layout.viewpager_item, container, false
        )
        with(view) {
            findViewById<ImageView>(R.id.img).setImageDrawable(
                AppCompatResources.getDrawable(
                    context, list[position].img
                )
            )
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}