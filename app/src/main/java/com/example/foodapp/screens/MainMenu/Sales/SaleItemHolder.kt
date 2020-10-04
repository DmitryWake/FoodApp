package com.example.foodapp.screens.MainMenu.Sales

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sale_item.view.*

class SaleItemHolder(view: View): RecyclerView.ViewHolder(view) {

    val mItemText: TextView = view.sale_text
    val mImage: ImageView = view.sale_image
}