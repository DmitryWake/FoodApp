package com.example.foodapp.screens.auth

import com.example.foodapp.R
import com.example.foodapp.database.*
import com.example.foodapp.screens.base.BaseFragment
import com.example.foodapp.utilities.*
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone.*
import java.util.concurrent.TimeUnit

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
        phoneNumber = enter_phone_edit_text.text.toString().formatPhoneNumber()
        if (phoneNumber.isEmpty()) {
            showToast("Введите номер")
        } else {
            authUser()
        }
    }

    private fun authUser() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            APP_ACTIVITY,
            callback
        )
    }

    private fun initCallback() {
        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnSuccessListener {
                    signIn(phoneNumber)
                }.addOnFailureListener { showToast(it.message.toString()) }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                showToast(e.message.toString())
            }

            override fun onCodeSent(id: String, p1: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(phoneNumber, id))
            }

        }
    }
}