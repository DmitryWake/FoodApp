package com.example.foodapp.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.foodapp.R
import com.example.foodapp.utilities.APP_ACTIVITY
import kotlinx.android.synthetic.main.date_dialog.view.*
import java.util.*

class DatePickerFragment : DialogFragment() {

    private lateinit var mDatePicker: DatePicker

    companion object {
        const val EXTRA_DATE = "com.example.foodapp.date"
        private const val ARG_DATE = "date"
        fun newInstance(date: Date): DatePickerFragment {
            val arguments = Bundle()
            arguments.putSerializable(ARG_DATE, date)

            val fragment = DatePickerFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as Date

        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val view = LayoutInflater.from(APP_ACTIVITY).inflate(R.layout.date_dialog, null)

        mDatePicker = view.date_picker
        mDatePicker.init(year, month, day, null)

        return AlertDialog.Builder(APP_ACTIVITY)
            .setView(view)
            .setPositiveButton(getString(R.string.OK)) { dialogInterface, i ->
                val toSendDate = GregorianCalendar(
                    mDatePicker.year,
                    mDatePicker.month,
                    mDatePicker.dayOfMonth
                ).time
                sendResult(Activity.RESULT_OK, toSendDate)
            }
            .create()
    }

    private fun sendResult(resultCode: Int, date: Date) {
        if (targetFragment != null) {
            val intent = Intent().putExtra(EXTRA_DATE, date)
            targetFragment!!.onActivityResult(targetRequestCode, resultCode, intent)
        }
    }
}