package com.sona.babu88.ui.drawer

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.sona.babu88.R

data class NavigationItem(
    val name: String?, val image: Drawable?, val header: String?, val hot: Boolean?, val new: Boolean?
)

object NavUtils {
    fun provideNavigationList(context: Context): List<NavigationItem> {
        return listOf(

            NavigationItem(
                name = ContextCompat.getString(context, R.string.promotion),
                image = ContextCompat.getDrawable(context, R.drawable.ic_promotion),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.refer_and_earn),
                image = ContextCompat.getDrawable(context, R.drawable.ic_refer_earn),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.betting_pass),
                image = ContextCompat.getDrawable(context, R.drawable.ic_betting_pass),
                header = "",
                hot = false,
                new = true
            ),
//            NavigationItem(
//                name = ContextCompat.getString(context, R.string.ipl_2024_),
//                image = ContextCompat.getDrawable(context, R.drawable.ic_ipl),
//                header = "",
//                hot_new = ContextCompat.getDrawable(context, R.drawable.ic_hot)
//            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.agent_affiliate),
                image = ContextCompat.getDrawable(context, R.drawable.ic_agent_affliate),
                header = ContextCompat.getString(context, R.string.games),
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.cricket),
                image = ContextCompat.getDrawable(context, R.drawable.ic_cricket),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.live_casino),
                image = ContextCompat.getDrawable(context, R.drawable.ic_casino),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.slot_games),
                image = ContextCompat.getDrawable(context, R.drawable.ic_slot),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.table_games),
                image = ContextCompat.getDrawable(context, R.drawable.ic_table),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.sports_book),
                image = ContextCompat.getDrawable(context, R.drawable.ic_sport_book),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.fishing),
                image = ContextCompat.getDrawable(context, R.drawable.ic_fishing),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.crash),
                image = ContextCompat.getDrawable(context, R.drawable.ic_crash),
                header = ContextCompat.getString(context, R.string.others),
                hot = false,
                new = true
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.language),
                image = ContextCompat.getDrawable(context, R.drawable.ic_language),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.faq),
                image = ContextCompat.getDrawable(context, R.drawable.ic_faq),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.live_chat),
                image = ContextCompat.getDrawable(context, R.drawable.ic_live_chat),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.download_app),
                image = ContextCompat.getDrawable(context, R.drawable.ic_download_app),
                header = "",
                hot = false,
                new = false
            ),
            NavigationItem(
                name = ContextCompat.getString(context, R.string.logout),
                image = ContextCompat.getDrawable(context, R.drawable.ic_logout),
                header = "",
                hot = false,
                new = false
            )
        )
    }
}