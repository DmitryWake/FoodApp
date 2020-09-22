package com.example.foodapp.screens.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R
import com.example.foodapp.utilities.APP_ACTIVITY

open class BaseFragment(layout: Int) : Fragment(layout) {

    private lateinit var rootView: View

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }
}