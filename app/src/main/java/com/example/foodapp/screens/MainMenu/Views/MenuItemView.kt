package com.example.foodapp.screens.MainMenu.Views

import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.models.MenuItemModel
import com.example.foodapp.screens.MainMenu.ViewHolders.MenuItemHolder

class MenuItemView(private val item: MenuItemModel) : ViewTypes {

    override fun getViewType(): Int = ViewTypes.MENU_CATEGORY_ITEM
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val menuItemHolder = viewHolder as MenuItemHolder
        menuItemHolder.mCategoryText.text = item.id
    }
}