package com.example.foodapp.screens.objects

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.example.foodapp.R
import com.example.foodapp.database.USER
import com.example.foodapp.screens.settings.SettingsFragment
import com.example.foodapp.utilities.APP_ACTIVITY
import com.example.foodapp.utilities.CURRENT_PROFILE_IDENTIFIER
import com.example.foodapp.utilities.replaceFragment
import com.example.foodapp.utilities.showToast
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class AppDrawer {

    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mCurrentProfile: ProfileDrawerItem

    fun create() {
        createHeader()
        createDrawer()
        mDrawerLayout = mDrawer.drawerLayout
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder().withActivity(APP_ACTIVITY).withToolbar(APP_ACTIVITY.toolbar)
            .withActionBarDrawerToggleAnimated(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withName(APP_ACTIVITY.getString(R.string.app_drawer_shopper))
                    .withSelectable(false),
                PrimaryDrawerItem().withIdentifier(101)
                    .withName(APP_ACTIVITY.getString(R.string.app_drawer_order_history))
                    .withSelectable(false),
                PrimaryDrawerItem().withIdentifier(102)
                    .withName(APP_ACTIVITY.getString(R.string.app_drawer_cafe))
                    .withSelectable(false),
                DividerDrawerItem(),
                PrimaryDrawerItem().withIdentifier(103)
                    .withName(APP_ACTIVITY.getString(R.string.app_drawer_settings))
                    .withSelectable(false),
                PrimaryDrawerItem().withIdentifier(104)
                    .withName(APP_ACTIVITY.getString(R.string.app_drawer_help))
                    .withSelectable(false)
            )
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    choseItem(position)
                    return false
                }

            })
            .build()
    }

    private fun choseItem(position: Int) {
        when (position) {
            1 -> showToast("Корзина")
            2 -> showToast("История заказов")
            3 -> showToast("Рестораны")
            5 -> replaceFragment(SettingsFragment())
            6 -> showToast("Помощь")
        }
    }

    private fun createHeader() {
        mCurrentProfile =
            ProfileDrawerItem().withName(USER.fullname).withEmail(USER.phone).withIdentifier(
                CURRENT_PROFILE_IDENTIFIER
            )
        mHeader = AccountHeaderBuilder().withActivity(APP_ACTIVITY)
            .withHeaderBackground(R.drawable.header).addProfiles(mCurrentProfile).build()
    }

    fun disableDrawer() {
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        APP_ACTIVITY.toolbar.setNavigationOnClickListener {
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer() {
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        APP_ACTIVITY.toolbar.setNavigationOnClickListener {
            mDrawer.openDrawer()
        }
    }
}