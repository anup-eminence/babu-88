package com.sona.babu88.util
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams

@Suppress("unused")
class AppBarBehaviorFixed(context: Context?, attrs: AttributeSet?) :
    AppBarLayout.Behavior(context, attrs) {

    private var view: AppBarLayout? = null
    private var snapEnabled = false

    private var isUpdating = false
    private var isScrolling = false
    private var isTouching = false

    private var lastOffset = 0

    private val handler = Handler()

    private val snapAction = Runnable {
        val view = view ?: return@Runnable
        val offset = -lastOffset
        val height = view.run { height - paddingTop - paddingBottom - getChildAt(0).minimumHeight }

        if (offset > 1 && offset < height - 1) view.setExpanded(offset < height / 2)
    }

    private val updateFinishDetector = Runnable {
        isUpdating = false
        scheduleSnapping()
    }

    private fun initView(view: AppBarLayout) {
        if (this.view != null) return

        this.view = view

        // Checking "snap" flag existence (applied through child view) and removing it
        val child = view.getChildAt(0)
        val params = child.layoutParams as LayoutParams
        snapEnabled = params.scrollFlags hasFlag LayoutParams.SCROLL_FLAG_SNAP
        params.scrollFlags = params.scrollFlags removeFlag LayoutParams.SCROLL_FLAG_SNAP
        child.layoutParams = params

        // Listening for offset changes
        view.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, offset ->
            lastOffset = offset

            isUpdating = true
            scheduleSnapping()

            handler.removeCallbacks(updateFinishDetector)
            handler.postDelayed(updateFinishDetector, 50L)
        })
    }

    private fun scheduleSnapping() {
        handler.removeCallbacks(snapAction)
        if (snapEnabled && !isUpdating && !isScrolling && !isTouching) {
            handler.postDelayed(snapAction, 50L)
        }
    }

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        abl: AppBarLayout,
        layoutDirection: Int
    ): Boolean {
        initView(abl)
        return super.onLayoutChild(parent, abl, layoutDirection)
    }

    override fun onTouchEvent(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        ev: MotionEvent
    ): Boolean {
        isTouching =
            ev.actionMasked != MotionEvent.ACTION_UP && ev.actionMasked != MotionEvent.ACTION_CANCEL
        scheduleSnapping()
        return super.onTouchEvent(parent, child, ev)
    }

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        val started = super.onStartNestedScroll(
            parent, child, directTargetChild, target, nestedScrollAxes, type
        )

        if (started) {
            isScrolling = true
            scheduleSnapping()
        }

        return started
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        abl: AppBarLayout,
        target: View,
        type: Int
    ) {
        isScrolling = false
        scheduleSnapping()

        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
    }


    private infix fun Int.hasFlag(flag: Int) = flag and this == flag

    private infix fun Int.removeFlag(flag: Int) = this and flag.inv()

}