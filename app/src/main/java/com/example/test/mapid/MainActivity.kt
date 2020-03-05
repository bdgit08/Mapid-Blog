package com.example.test.mapid

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.test.mapid.adapter.MenusAdapter
import com.example.test.mapid.view.HomeFragment
import com.example.test.mapid.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, SubmenuInterface {
    private lateinit var menuViewModel : MenuViewModel
    private val menuAdapter = MenusAdapter(this)
    private lateinit var fragment: HomeFragment
    private var viewTemp: View ?= null
    private var linkTemp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        fragment = supportFragmentManager.findFragmentById(R.id.fragmentHome) as HomeFragment
        rvMenu.adapter = menuAdapter
        menuViewModel.fetchYear()
        menuViewModel.mutable.observe(this, Observer { map ->
            menuAdapter.maps = map
            menuAdapter.notifyDataSetChanged()
        })
        fragment.fetch("mapid_at_i4startup_competition_15_big")
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.ivButtonDraw) {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    override fun onclickSubMenu(link: String?, view: View?) {
        if (linkTemp != link) {
            link?.let {
                viewTemp?.let {tv->
                    tv.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    (tv as TextView).setTextColor(Color.parseColor("#757575"))
                }
                fragment.fetch(it)
                drawer_layout.closeDrawers()
                linkTemp = it
            }
            view?.let {
                it.setBackgroundColor(Color.parseColor("#448AFF"))
                (it as TextView).setTextColor(Color.parseColor("#FFFFFF"))
                viewTemp = it
            }
        }


    }


}
