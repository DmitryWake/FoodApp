package com.example.foodapp.screens.MainMenu

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.screens.MainMenu.ViewHolders.ViewHolderFactory
import com.example.foodapp.screens.MainMenu.Views.ViewTypes

class MainMenuAdapter(private val dataList: List<ViewTypes>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.getHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].getViewType()

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataList[position].onBindViewHolder(holder)
    }

    override fun getItemCount(): Int = dataList.size

}