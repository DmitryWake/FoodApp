package com.example.foodapp.screens.MainMenu.Sales

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R

class SalesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<SaleItemView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sale_item, parent, false)
        return SaleItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataList[position].onBindViewHolder(holder)
    }

    override fun getItemCount(): Int = dataList.size

    fun addItem(item: SaleItemView) {
        dataList.add(item)
        notifyItemInserted(dataList.size)
    }
}