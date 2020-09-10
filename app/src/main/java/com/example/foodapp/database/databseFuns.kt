package com.example.foodapp.database

import com.example.foodapp.models.UserModel
import com.example.foodapp.utilities.AppValueEventListener
import com.example.foodapp.utilities.restartActivity
import com.example.foodapp.utilities.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = UserModel()
}

fun signIn(phoneNumber: String) {
    val uid = AUTH.currentUser?.uid.toString()
    val dataMap = mutableMapOf<String, Any>()
    dataMap[CHILD_ID] = uid
    dataMap[CHILD_PHONE] = phoneNumber

    REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
        .addListenerForSingleValueEvent(AppValueEventListener {
            if (it.hasChild(CHILD_FULLNAME)) {
                showToast("Перейти на окно регистрации")
                restartActivity()
            } else {
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dataMap)
                    .addOnSuccessListener {
                        showToast("Добро пожаловать!")
                        restartActivity()
                    }
                    .addOnFailureListener { showToast(it.message.toString()) }
            }
        })
}