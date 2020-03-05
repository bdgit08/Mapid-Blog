package com.example.test.mapid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.mapid.R
import com.example.test.mapid.SubmenuInterface
import com.example.test.mapid.model.MenuData
import kotlinx.android.synthetic.main.menu_layout.view.*

class MenusAdapter(var submenuInterface: SubmenuInterface) : RecyclerView.Adapter<MenusAdapter.TitleVH>() {
    var maps = mapOf<String, List<MenuData>>()

    override fun onBindViewHolder(holder: TitleVH, position: Int) {
        val title = maps.keys.elementAt(position)
        val list = maps.getValue(title)
        holder.onBind(title, list)
    }

    override fun getItemCount(): Int {
        return maps.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_layout, parent, false)
        return TitleVH(view)
    }

    inner class TitleVH(private var view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(key: String, list: List<MenuData>) {
            val adapter = SubMenusAdapter(submenuInterface)
            view.titleMenu.text = key
            view.rvSubmenu.adapter = adapter
            adapter.values = list
            adapter.notifyDataSetChanged()
        }
    }

}