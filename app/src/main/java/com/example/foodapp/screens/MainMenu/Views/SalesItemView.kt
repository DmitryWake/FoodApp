package com.example.foodapp.screens.MainMenu.Views

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.models.MenuItemModel
import com.example.foodapp.screens.MainMenu.Sales.SaleItemView
import com.example.foodapp.screens.MainMenu.Sales.SalesAdapter
import com.example.foodapp.screens.MainMenu.ViewHolders.SalesItemHolder
import com.example.foodapp.utilities.APP_ACTIVITY

class SalesItemView: ViewTypes {

    private lateinit var mAdapter: SalesAdapter

    override fun getViewType(): Int = ViewTypes.MENU_SALES_RECYCLER_VIEW
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val salesHolder = viewHolder as SalesItemHolder

        mAdapter = SalesAdapter()
        salesHolder.mRecyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY, LinearLayoutManager.HORIZONTAL, false)
        salesHolder.mRecyclerView.adapter = mAdapter

        mAdapter.addItem(SaleItemView(MenuItemModel("Акция")))
        mAdapter.addItem(SaleItemView(MenuItemModel("Акция")))
        mAdapter.addItem(SaleItemView(MenuItemModel("Акция")))
    }
}