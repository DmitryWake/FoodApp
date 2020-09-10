package com.example.foodapp.screens.auth

import android.widget.EditText
import com.example.foodapp.R
import com.example.foodapp.database.*
import com.example.foodapp.screens.MainMenuFragment
import com.example.foodapp.screens.base.BaseFragment
import com.example.foodapp.utilities.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(val phoneNumber: String, val id: String) :
    BaseFragment(R.layout.fragment_enter_code) {

    var code = "      "

    override fun onStart() {
        super.onStart()
        initFields()
    }

    private fun initFields() {
        APP_ACTIVITY.title = phoneNumber
        code_0.requestFocus()
        code_0.addTextChangedListener(AppTextWatcher {
            code = code_0.text.toString() + code.substring(1, 6)
            code_1.requestFocus()
            code_1.addTextChangedListener(AppTextWatcher {
                code = code.first() + code_1.text.toString() + code.substring(2, 6)
                code_2.requestFocus()
                code_2.addTextChangedListener(AppTextWatcher {
                    code = code.substring(0, 2) + code_2.text.toString() + code.substring(3, 6)
                    code_3.requestFocus()
                    code_3.addTextChangedListener(AppTextWatcher {
                        code = code.substring(0, 3) + code_3.text.toString() + code.substring(4, 6)
                        code_4.requestFocus()
                        code_4.addTextChangedListener(AppTextWatcher {
                            code = code.substring(0, 4) + code_4.text.toString() + code.last()
                            code_5.requestFocus()
                            code_5.addTextChangedListener(AppTextWatcher {
                                code = code.substring(0, 5) + code_5.text.toString()
                                enterCode()
                            })
                        })
                    })
                })
            })
        })
    }

    private fun enterCode() {
        showToast(code)
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnSuccessListener {
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
    }
}
