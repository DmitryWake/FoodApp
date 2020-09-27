package com.example.foodapp.database

import com.example.foodapp.models.UserModel
import com.example.foodapp.screens.auth.RegisterFragment
import com.example.foodapp.utilities.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = UserModel()
}

fun initUser(function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(UserModel::class.java) ?: UserModel()
            function()
        })
}

fun signIn(phoneNumber: String) {
    val uid = AUTH.currentUser?.uid.toString()

    REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
        .addListenerForSingleValueEvent(AppValueEventListener {
            if (!it.hasChild(CHILD_FULLNAME)) {
                replaceFragment(RegisterFragment(phoneNumber, uid))
            } else {
                showToast("Добро пожаловать!")
                restartActivity()
            }
        })
}

fun checkVersion() {
    REF_DATABASE_ROOT.child(CHILD_VERSION).addListenerForSingleValueEvent(AppValueEventListener {
        if (APP_VERSION.toDouble() > it.value.toString().toDouble())
            updateVersionToDatabase()
        else if (APP_VERSION.toDouble() != it.value.toString().toDouble())
            updateVersion()
    })
}

fun updateVersionToDatabase() {
    REF_DATABASE_ROOT.child(CHILD_VERSION).setValue(APP_VERSION).addOnFailureListener {
        showToast(it.message.toString())
    }
}

fun addCategory(mCategoryData: MutableMap<String, Any>, function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_CATEGORY).child(mCategoryData[CHILD_ID].toString())
        .updateChildren(mCategoryData).addOnSuccessListener {
            function()
        }.addOnFailureListener {
            showToast(it.message.toString())
        }
}

fun signOutAndRestart() {
    AUTH.signOut()
    restartActivity()
}
