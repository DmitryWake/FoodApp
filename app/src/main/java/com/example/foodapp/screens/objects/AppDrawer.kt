package com.example.foodapp.screens.objects

import androidx.drawerlayout.widget.DrawerLayout
import com.example.foodapp.database.USER
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.model.ProfileDrawerItem

class AppDrawer {

    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mCurrentProfile: ProfileDrawerItem

    fun create() {
        createHeader()
        createDrawer()
    }

    private fun createDrawer() {
        TODO("Not yet implemented")
    }

    private fun createHeader() {
        mCurrentProfile = ProfileDrawerItem().withName(USER.fullname)
    }
}