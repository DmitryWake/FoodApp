package com.example.foodapp.screens.base

import androidx.fragment.app.Fragment
import com.example.foodapp.utilities.APP_ACTIVITY

open class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }
}