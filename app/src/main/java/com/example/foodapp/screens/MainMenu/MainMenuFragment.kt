package com.example.foodapp.screens.MainMenu


import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.database.NODE_CATEGORY
import com.example.foodapp.database.PERMISSION_ADMIN
import com.example.foodapp.database.REF_DATABASE_ROOT
import com.example.foodapp.database.USER
import com.example.foodapp.models.MenuItemModel
import com.example.foodapp.screens.MainMenu.Views.MenuItemView
import com.example.foodapp.screens.MainMenu.Views.SalesItemView
import com.example.foodapp.screens.MainMenu.Views.ViewTypes
import com.example.foodapp.screens.edit_content.AddMenuCategoryFragment
import com.example.foodapp.utilities.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {

    private lateinit var addCategoryButton: FloatingActionButton

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainMenuAdapter


    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        APP_ACTIVITY.title = getString(R.string.menu_category_text)
        initRecyclerView()
        initAddButton()
    }

    private fun initAddButton() {
        addCategoryButton = btn_add_category
        when (USER.permission) {
            PERMISSION_ADMIN -> {
                addCategoryButton.visibility = View.VISIBLE
                addCategoryButton.setOnClickListener {
                    replaceFragment(AddMenuCategoryFragment())
                }
            }
            else -> addCategoryButton.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        mRecyclerView = main_menu_recycler_view

        val dataList = mutableListOf<ViewTypes>()
        dataList.add(SalesItemView())

        mAdapter = MainMenuAdapter(dataList)

        REF_DATABASE_ROOT.child(NODE_CATEGORY).addChildEventListener(AppChildEventListener {
            it.getValue(MenuItemModel::class.java)?.let { it1 ->
                mAdapter.addCategory(MenuItemView(it1))
            }
        })

        mRecyclerView.layoutManager = LinearLayoutManager(this.context)
        mRecyclerView.adapter = mAdapter
    }
}
