package com.example.foodapp.screens.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.foodapp.R
import com.example.foodapp.database.*
import com.example.foodapp.screens.DatePickerFragment
import com.example.foodapp.utilities.restartActivity
import com.example.foodapp.utilities.showToast
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class RegisterFragment(private val phoneNumber: String, private val uid: String) :
    Fragment() {

    private lateinit var mEditDateText: TextView

    companion object {
        private const val DIALOG_DATE = "DialogDate"
        private const val REQUEST_DATE = 0
    }

    private var userData = mutableMapOf<String, Any>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        initFields(view)
        initFunc(view)
        return view
    }

    private fun initFields(view: View?) {
        if (view != null) {
            mEditDateText = view.register_edit_text_date
        }
    }

    private fun initFunc(view: View) {
        mEditDateText.setOnClickListener {
            val dateDialog = DatePickerFragment.newInstance(Date(101, 5, 18))
            dateDialog.setTargetFragment(this, REQUEST_DATE)
            fragmentManager?.let { it1 ->
                dateDialog.show(it1, DIALOG_DATE) }
        }

        view.register_button.setOnClickListener {
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

    override fun onStart() {
        super.onStart()
        userData[CHILD_ID] = uid
        userData[CHILD_PHONE] = phoneNumber
        userData[CHILD_PERMISSION] = PERMISSION_USER
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK)
            return

        when (requestCode) {
            REQUEST_DATE -> {
                val date = data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                mEditDateText.text = dateFormat.format(date).toString()
            }
        }
    }
}