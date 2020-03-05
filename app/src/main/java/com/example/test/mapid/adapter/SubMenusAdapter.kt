package com.example.test.mapid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.mapid.R
import com.example.test.mapid.SubmenuInterface
import com.example.test.mapid.model.MenuData
import kotlinx.android.synthetic.main.subtitle_layout.view.*

class SubMenusAdapter(var submenuInterface: SubmenuInterface) : RecyclerView.Adapter<SubMenusAdapter.SubTitleVH>() {
    var values = listOf<MenuData>()

    override fun onBindViewHolder(holder: SubTitleVH, position: Int) {
        val value = values[position]
        holder.onBind(value)
    }

    override fun getItemCount(): Int {
      return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTitleVH {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.subtitle_layout,parent,false)
        return SubTitleVH(view)
    }

    inner class SubTitleVH(private var view : View):RecyclerView.ViewHolder(view){
        fun onBind(subTitle : MenuData){
            view.tvSubtitle.text = subTitle.title
           view.setOnClickListener {
               submenuInterface.onclickSubMenu(subTitle.link,it)
           }
        }
    }
}