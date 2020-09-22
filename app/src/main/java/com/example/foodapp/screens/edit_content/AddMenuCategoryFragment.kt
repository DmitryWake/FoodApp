package com.example.foodapp.screens.edit_content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.foodapp.R
import com.example.foodapp.database.CHILD_ID
import com.example.foodapp.database.addCategory
import com.example.foodapp.screens.MainMenu.MainMenuFragment
import com.example.foodapp.screens.base.BaseFragment
import com.example.foodapp.utilities.AppTextWatcher
import com.example.foodapp.utilities.replaceFragment
import com.example.foodapp.utilities.showToast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_menu_category.*
import java.util.*


class AddMenuCategoryFragment : BaseFragment(R.layout.fragment_add_menu_category) {

    private var mCategoryData = mutableMapOf<String, Any>()

    private lateinit var mCategoryNameEditText: EditText
    private lateinit var mCategoryNamePreview: TextView
    private lateinit var mButtonCreate: FloatingActionButton

    override fun onResume() {
        super.onResume()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        mCategoryNameEditText.addTextChangedListener(AppTextWatcher {
            mCategoryNamePreview.text = mCategoryNameEditText.text
        })

        mButtonCreate.setOnClickListener {
            if (mCategoryNameEditText.text.toString().isNotEmpty()) {
                mCategoryData[CHILD_ID] = mCategoryNameEditText.text.toString()
                addCategory(mCategoryData) {
                    showToast("Успешно!")
                    replaceFragment(MainMenuFragment(), false)
                }
            }
        }
    }

    private fun initFields() {
        mCategoryNameEditText = add_category_name_edit_text
        mCategoryNamePreview = menu_text
        mButtonCreate = btn_add_category_done
    }

}