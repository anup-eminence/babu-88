package com.sona.babu88.util

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.sona.babu88.R

fun ViewPager.autoScroll(interval: Long) {

    val handler = Handler()
    var scrollPosition = 0

    val runnable = object : Runnable {

        override fun run() {

            /**
             * Calculate "scroll position" with
             * adapter pages count and current
             * value of scrollPosition.
             */
            val count = adapter?.count ?: 0
            setCurrentItem(scrollPosition++ % count, true)

            handler.postDelayed(this, interval)
        }
    }

    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            // Updating "scroll position" when user scrolls manually
            scrollPosition = position + 1
        }

        override fun onPageScrollStateChanged(state: Int) {
            // Not necessary
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            // Not necessary
        }
    })

    handler.post(runnable)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.replaceFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean = false, tag: String? = null) {
    childFragmentManager.beginTransaction().apply {
        replace(containerId, fragment, tag)
        if (addToBackStack) {
            addToBackStack(tag)
        }
        commit()
    }
}

fun provideViewPagerList(): List<ViewPagerItem> {
    return listOf(
        ViewPagerItem(
            id = 0,
            img = R.drawable.v_1
        ),
        ViewPagerItem(
            id = 1,
            img = R.drawable.v_2
        ),
        ViewPagerItem(
            id = 2,
            img = R.drawable.v_3
        ),
        ViewPagerItem(
            id = 3,
            img = R.drawable.v_4
        ),
        ViewPagerItem(
            id = 4,
            img = R.drawable.v_5
        ),
        ViewPagerItem(
            id = 5,
            img = R.drawable.v_6
        ),
        ViewPagerItem(
            id = 6,
            img = R.drawable.v_7
        ),
        ViewPagerItem(
            id = 7,
            img = R.drawable.v_8
        )
    )
}

data class ViewPagerItem(
    val id: Int,
    val img: Int
)