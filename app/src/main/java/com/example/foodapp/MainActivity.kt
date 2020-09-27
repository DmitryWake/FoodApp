package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.foodapp.database.*
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.screens.MainMenu.MainMenuFragment
import com.example.foodapp.screens.auth.EnterPhoneFragment
import com.example.foodapp.screens.objects.AppDrawer
import com.example.foodapp.utilities.APP_ACTIVITY
import com.example.foodapp.utilities.AppValueEventListener
import com.example.foodapp.utilities.replaceFragment
import com.example.foodapp.utilities.restartActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var toolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()
        initUser {
            initFields()
            initFunc()
        }
    }


    override fun onStart() {
        super.onStart()
        checkVersion()
    }

    private fun initFunc() {
        setSupportActionBar(toolbar)
        when {
            AUTH.currentUser != null -> {
                mAppDrawer.create()
                replaceFragment(MainMenuFragment(), false)
            }
            AUTH.currentUser == null -> {
                replaceFragment(EnterPhoneFragment(), false)
            }
            else -> {
                REF_DATABASE_ROOT.child(NODE_USERS).addListenerForSingleValueEvent(AppValueEventListener{
                    if (!it.hasChild(CURRENT_UID)) {
                        signOutAndRestart()
                    }
                })
            }
        }
    }

    private fun initFields() {
        toolbar = binding.mainToolbar
        mAppDrawer = AppDrawer()
        APP_ACTIVITY = this
    }


}