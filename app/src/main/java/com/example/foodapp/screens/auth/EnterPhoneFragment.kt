package com.example.foodapp.screens.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodapp.R
import com.example.foodapp.database.*
import com.example.foodapp.screens.MainMenuFragment
import com.example.foodapp.screens.base.BaseFragment
import com.example.foodapp.utilities.AppValueEventListener
import com.example.foodapp.utilities.replaceFragment
import com.example.foodapp.utilities.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone.*

class EnterPhoneFragment : BaseFragment(R.layout.fragment_enter_phone) {

    private lateinit var phoneNumber: String
    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        initCallback()
        initFields()
    }

    private fun initFields() {
        enter_phone_edit_text.requestFocus()
        btn_next.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        phoneNumber = enter_phone_edit_text.text.toString()
        if (phoneNumber.isEmpty()) {
            showToast("Введите номер")
        } else {
            showToast("Вы авторизованы с номером $phoneNumber")
            replaceFragment(MainMenuFragment())
        }
    }

    private fun initCallback() {
        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnSuccessListener {
                    val uid = AUTH.currentUser?.uid.toString()
                    val dataMap = mutableMapOf<String, Any>()

                    dataMap[CHILD_ID] = uid
                    dataMap[CHILD_PHONE] = phoneNumber

                    REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
                        .addListenerForSingleValueEvent(AppValueEventListener {
                            if (!it.hasChild(CHILD_FULLNAME)) {
                                showToast("Работает")
                            }
                        })
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                showToast(e.message.toString())
            }

        }
    }
}