package com.example.foodapp.screens.settings

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.foodapp.R
import com.example.foodapp.database.USER
import com.example.foodapp.database.signOutAndRestart
import com.example.foodapp.database.updateUserToDatabase
import com.example.foodapp.screens.DatePickerFragment
import com.example.foodapp.screens.base.BaseFragment
import com.example.foodapp.utilities.*
import kotlinx.android.synthetic.main.fragment_settings.*
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
    private lateinit var mEmailEditText: TextView
    private lateinit var mCheckMailer: CheckBox
    private lateinit var mEditEmailButton: ImageView
    private lateinit var mEditEmailDone: ImageView

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
        mCheckMailer.isChecked = USER.mailing
    }

    private fun initFunc() {
        mEditUserButton.setOnClickListener {
            editUser()
        }
        mEditEmailButton.setOnClickListener {
            editEmail()
        }
        settings_button_exit.setOnClickListener {
            signOutAndRestart()
        }
    }

    private fun editEmail() {
        mEmailText.visibility = View.INVISIBLE
        mEmailEditText.visibility = View.VISIBLE
        mEmailEditText.text = mEmailText.text

        mEditEmailButton.visibility = View.INVISIBLE
        mEditEmailDone.visibility = View.VISIBLE

        mCheckMailer.isClickable = true

        mEditEmailDone.setOnClickListener {
            editEmailDone()
        }
    }

    private fun editEmailDone() {
        mEmailText.text = mEmailEditText.text.toString()
        USER.email = mEmailText.text.toString()
        mEmailText.visibility = View.VISIBLE

        mEmailEditText.visibility = View.GONE
        mCheckMailer.isClickable = false
        USER.mailing = mCheckMailer.isChecked

        mEditEmailButton.visibility = View.VISIBLE
        mEditEmailDone.visibility = View.GONE

        updateUserToDatabase()
    }

    private fun editUser() {
        mFullnameEditText.text = mFullnameText.text
        mFullnameText.visibility = View.INVISIBLE
        mFullnameEditText.visibility = View.VISIBLE
        mEditUserButton.visibility = View.INVISIBLE
        mEditUserDone.visibility = View.VISIBLE

        mDateText.setOnClickListener {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val date = dateFormat.parse(mDateText.text.toString())
            showDatePickerDialog(this, date)
        }

        mEditUserDone.setOnClickListener {
            if (mFullnameEditText.text.toString().isNotEmpty())
                editUserDone()
        }

    }

    private fun editUserDone() {
        mFullnameText.text = mFullnameEditText.text.toString().replace(' ', '\n')
        USER.fullname = mFullnameText.text.toString()
        mFullnameText.visibility = View.VISIBLE

        mFullnameEditText.visibility = View.GONE

        mEditUserButton.visibility = View.VISIBLE
        mEditUserDone.visibility = View.GONE

        mDateText.setOnClickListener(null)
        USER.date = mDateText.text.toString()

        updateUserToDatabase()
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
        mEmailEditText = settings_email_edit_text
        mCheckMailer = settings_check_mailing_box
        mCheckMailer.isClickable = false
        mEditEmailButton = settings_email_edit
        mEditEmailDone = settings_email_done
        mEditEmailDone.visibility = View.GONE

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