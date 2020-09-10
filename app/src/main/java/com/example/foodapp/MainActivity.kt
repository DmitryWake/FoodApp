package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.foodapp.database.AUTH
import com.example.foodapp.database.checkVersion
import com.example.foodapp.database.initFirebase
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.screens.MainMenuFragment
import com.example.foodapp.screens.auth.EnterPhoneFragment
import com.example.foodapp.utilities.APP_ACTIVITY
import com.example.foodapp.utilities.replaceFragment

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

    override fun onStart() {
        super.onStart()
        checkVersion()
    }

    private fun initFunc() {
        setSupportActionBar(toolbar)
        if (AUTH.currentUser != null) {
            replaceFragment(MainMenuFragment())
        }
        else
            replaceFragment(EnterPhoneFragment(), false)
    }

    private fun initFields() {
        toolbar = binding.mainToolbar
        APP_ACTIVITY = this
    }


}