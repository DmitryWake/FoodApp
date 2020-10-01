package com.example.foodapp.screens.settings

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.foodapp.R
import com.example.foodapp.database.USER
import com.example.foodapp.database.signOutAndRestart
import com.example.foodapp.screens.DatePickerFragment
import com.example.foodapp.screens.base.BaseFragment
import com.example.foodapp.utilities.*
import kotlinx.android.synthetic.main.fragment_settings.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private lateinit var mFullnameText: TextView
    private lateinit var mFullnameEditText: TextView
    private lateinit var mDateText: TextView
    private lateinit var mEditUserButton: ImageView
    private lateinit var mEditUserDone: ImageView

    private lateinit var mPhoneNumberText: TextView
    private lateinit var mIdText: TextView

    private lateinit var mEmailText: TextView
    private lateinit var mCheckMailer: CheckBox
    private lateinit var mEditEmailButton: ImageView

    private lateinit var mBonusCount: TextView

    override fun onResume() {
        super.onResume()
        initFields()
        insertUserData()
        initFunc()
    }

    @SuppressLint("SetTextI18n")
    private fun insertUserData() {
        mFullnameText.text = USER.fullname.replace(' ', '\n')
        mPhoneNumberText.text = USER.phone
        mIdText.text = "ID: " + USER.id
        mEmailText.text = USER.email
        mDateText.text = USER.date
    }

    private fun initFunc() {
        mEditUserButton.setOnClickListener {
            editUser()
        }
        mEditEmailButton.setOnClickListener {
            showToast("Редактировать email")
        }
        settings_button_exit.setOnClickListener {
            signOutAndRestart()
        }
    }

    private fun editUser() {
        mFullnameEditText.text = mFullnameText.text
        mFullnameText.visibility = View.INVISIBLE
        mFullnameEditText.visibility = View.VISIBLE
        mEditUserButton.visibility = View.INVISIBLE
        mEditUserDone.visibility = View.VISIBLE

        mDateText.setOnClickListener {
            showDatePickerDialog(this)
        }

        mEditUserDone.setOnClickListener {
            if (mFullnameEditText.text.toString().isNotEmpty())
                editUserDone()
        }

    }

    private fun editUserDone() {
        mFullnameText.text = mFullnameEditText.text.toString().replace(' ', '\n')
        mFullnameText.visibility = View.VISIBLE

        mFullnameEditText.visibility = View.GONE

        mEditUserButton.visibility = View.VISIBLE
        mEditUserDone.visibility = View.GONE

        mDateText.setOnClickListener(null)
    }

    private fun initFields() {
        mFullnameText = settings_fullname_text
        mFullnameEditText = settings_fullname_edit_text
        mFullnameEditText.visibility = View.GONE
        mDateText = settings_date_text
        mEditUserButton = settings_user_edit
        mEditUserDone = settings_user_done
        mEditUserDone.visibility = View.GONE

        mPhoneNumberText = settings_phone_text
        mIdText = settings_id_text

        mEmailText = settings_email_text
        mEmailText.isFocusable = false
        mCheckMailer = settings_check_mailing_box
        mEditEmailButton = settings_email_edit

        mBonusCount = settings_bonus_count
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK)
            return

        when (requestCode) {
            REQUEST_DATE -> {
                val date = data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                mDateText.text = dateFormat.format(date).toString()
            }
        }
    }

}