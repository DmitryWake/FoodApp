package com.example.foodapp.screens.MainMenu.ViewHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.screens.MainMenu.Views.ViewTypes

class ViewHolderFactory {
    companion object {
        fun getHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                ViewTypes.MENU_CATEGORY_ITEM -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.menu_item, parent, false)
                    MenuItemHolder(view)
                }
                else -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.sales_list, parent, false)
                    SalesItemHolder(view)
                }
            }
        }
    }
}