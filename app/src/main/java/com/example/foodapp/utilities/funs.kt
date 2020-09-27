package com.example.foodapp.utilities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.example.foodapp.MainActivity
import com.example.foodapp.R
import com.example.foodapp.database.APP_VERSION
import com.example.foodapp.screens.DatePickerFragment
import com.example.foodapp.screens.auth.RegisterFragment
import java.util.*

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    if (addStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.data_container, fragment)
            .commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(R.id.data_container, fragment)
            .commit()
    }
}

fun restartActivity() {
    val intent = Intent(APP_ACTIVITY, MainActivity::class.java)
    APP_ACTIVITY.startActivity(intent)
    APP_ACTIVITY.finish()
}

fun String.formatPhoneNumber(): String {
    return if (this.first() == '8')
        "+7" + this.substring(1)
    else
        this
}

fun updateVersion() {
    val builder = AlertDialog.Builder(APP_ACTIVITY)
    builder.setTitle("Доступно обновление!")
        .setMessage("Ваша версия приложения $APP_VERSION устарела. Для корректной работы обновитесь до новой версии")
        .setNegativeButton("Обновить", DialogInterface.OnClickListener { dialog, which ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://yadi.sk/d/_CT5HwiC7AMerg"))
            startActivity(APP_ACTIVITY, browserIntent, null)
        })
    val alertDialog = builder.create()
    alertDialog.show()
}