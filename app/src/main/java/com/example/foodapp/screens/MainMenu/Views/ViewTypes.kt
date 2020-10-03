package com.example.foodapp.screens.MainMenu.Views

import androidx.recyclerview.widget.RecyclerView

interface ViewTypes {
    companion object {
        val MENU_CATEGORY_ITEM: Int
            get() = 0
        val MENU_SALES_RECYCLER_VIEW: Int
            get() = 1
    }

    fun getViewType(): Int

    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder)
}