package com.example.foodapp.screens.MainMenu.Sales

import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.models.MenuItemModel

class SaleItemView(private val item: MenuItemModel) {
    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val saleItemHolder = viewHolder as SaleItemHolder
        saleItemHolder.mItemText.text = item.id
    }
}