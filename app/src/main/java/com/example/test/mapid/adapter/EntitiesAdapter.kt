package com.example.test.mapid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.mapid.R
import com.example.test.mapid.model.EntityData
import kotlinx.android.synthetic.main.entity_item_layout.view.*

class EntitiesAdapter : RecyclerView.Adapter<EntitiesAdapter.EntitiesVH>() {
    var listContent = mutableListOf<EntityData>()
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: EntitiesVH, position: Int) {
        holder.onBind(listContent[position])
    }

    override fun getItemCount(): Int {
        return listContent.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntitiesVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.entity_item_layout, parent, false)
        return EntitiesVH(view)
    }

    inner class EntitiesVH(private var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(content : EntityData) {
            if (content.type.equals("image")){
                content.data?.src?.let {
                    Glide.with(view).load(it).into(view.ivEntityItem)
                }
            }
        }
    }

}