package com.sona.babu88.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.sona.babu88.R
import com.sona.babu88.ui.activity.HomeActivity
import com.sona.babu88.ui.activity.NewActivity
import java.text.DateFormat
import java.util.Calendar

fun DialogFragment.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}

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

fun Context.showExitAlert(positiveClick: () -> Unit) {
    val builder = AlertDialog.Builder(this)
    builder.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_logo))
    builder.setMessage("Are you sure you want to exit?")

    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
        positiveClick()
    }
    builder.setNegativeButton(android.R.string.no) { dialog, which ->
        dialog.dismiss()
    }
    builder.show()
}

fun View.onBackPress(doWork: () -> Unit) {
    this.isFocusableInTouchMode = true
    this.requestFocus()
    this.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            doWork()
            return@OnKeyListener true
        }
        false
    })
}

fun Fragment.showProgress() {
    (activity as HomeActivity).showProgress()
}

fun Fragment.hideProgress() {
    (activity as HomeActivity).hideProgress()
}

fun Fragment.hideKeyboard() {
    val view = activity?.currentFocus
    if (view != null) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun getSeventhDayDate(): String {
    val calendar = Calendar.getInstance()
    // Get the current date
    val currentDate = calendar.time
    calendar.time = currentDate
    calendar.add(Calendar.DAY_OF_YEAR, -7)
    val seventhDay = calendar.time
    return android.text.format.DateFormat.format("yyyy-MM-dd", seventhDay).toString()
}

fun getCurrrentDate() : String {
    val calendar = Calendar.getInstance()
    return android.text.format.DateFormat.format("yyy-MM-dd",calendar.time).toString()
}

fun Fragment.showProgress1() {
    (activity as NewActivity).showProgress()
}

fun Fragment.hideProgress1() {
    (activity as NewActivity).hideProgress()
}