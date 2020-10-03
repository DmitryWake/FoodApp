package com.example.foodapp.screens.MainMenu.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.menu_item.view.*

class MenuItemHolder(view: View): RecyclerView.ViewHolder(view) {

    val mCategoryText: TextView = view.menu_text
}