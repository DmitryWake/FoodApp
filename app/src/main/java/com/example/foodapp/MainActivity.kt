package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.foodapp.database.AUTH
import com.example.foodapp.database.initFirebase
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.screens.auth.EnterPhoneFragment
import com.example.foodapp.utilities.APP_ACTIVITY
import com.example.foodapp.utilities.replaceFragment
import com.example.foodapp.utilities.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        setSupportActionBar(toolbar)
        replaceFragment(EnterPhoneFragment(), false)
    }

    private fun initFields() {
        toolbar = binding.mainToolbar
        APP_ACTIVITY = this
    }



}