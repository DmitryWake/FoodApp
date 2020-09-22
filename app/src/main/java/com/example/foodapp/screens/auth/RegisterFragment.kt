package com.example.foodapp.screens.auth

import androidx.fragment.app.Fragment
import com.example.foodapp.R
import com.example.foodapp.database.*
import com.example.foodapp.screens.base.BaseFragment
import com.example.foodapp.utilities.restartActivity
import com.example.foodapp.utilities.showToast
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment(val phoneNumber: String, val uid: String) :
    Fragment(R.layout.fragment_register) {

    private var userData = mutableMapOf<String, Any>()

    //Ошибка!!!
    override fun onStart() {
        super.onStart()
        userData[CHILD_ID] = uid
        userData[CHILD_PHONE] = phoneNumber
        userData[CHILD_PERMISSION] = PERMISSION_USER
        register_button.setOnClickListener {
            if (collectData()) {
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(userData)
                    .addOnSuccessListener {
                        restartActivity()
                    }.addOnFailureListener {
                    showToast(it.message.toString())
                }
            }
        }
    }

    private fun collectData(): Boolean {
        if (register_edit_text_first_name.text.toString().isEmpty() ||
            register_edit_text_last_name.text.toString().isEmpty()
        ) {
            showToast("Пожалуйста, введите Имя и Фамилию")
            return false
        } else {
            userData[CHILD_FULLNAME] =
                "${register_edit_text_first_name.text} ${register_edit_text_last_name.text}"
        }
        userData[CHILD_EMAIL] = register_edit_text_email.text.toString()
        userData[CHILD_DATE] = register_edit_text_date.text.toString()
        return true
    }
}