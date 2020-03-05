package com.example.test.mapid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.test.mapid.R
import com.example.test.mapid.model.BlockData
import com.example.test.mapid.model.BlogData
import com.example.test.mapid.model.EntityData
import kotlinx.android.synthetic.main.content_text.view.*
import kotlinx.android.synthetic.main.entity_layout.view.*
import kotlinx.android.synthetic.main.head_content.view.*
import java.text.SimpleDateFormat
import java.util.*

class ContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val HEAD = 0
    private val TEXT = 1
    private val ENTITY = 2
    var listContent = mutableListOf<BlockData>()
    var blogData: BlogData? = null

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return listContent.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContentVH) {
            holder.onBind(listContent[position - 1])
        }

        if (holder is EntityVH) {
            holder.onBind(listContent[position -1].entities)
        }

        if (holder is HeadContentVH) {
            holder.onBind(blogData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TEXT -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.content_text, parent, false)
                return ContentVH(view)
            }
            HEAD -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.head_content, parent, false)
                return HeadContentVH(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.entity_layout, parent, false)
                return EntityVH(view)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return HEAD
        } else {
            if (listContent[position - 1].entities.isNullOrEmpty()) {
                return TEXT
            } else
                return ENTITY
        }
    }

    inner class HeadContentVH(private var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(blogData: BlogData?) {
            blogData?.let {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                val outputFormat = SimpleDateFormat("MMM dd yyyy",Locale.getDefault())
                val date = inputFormat.parse(it.date)
                val strDate =outputFormat.format(date)
                view.titleBlog.text = it.title
                Glide.with(view).load(it.author?.profile_picture?.url).transform(CircleCrop()).into(view.ivAuthor)
                view.tvAuthorName.text = it.author?.full_name
                view.tvDateBlog.text = strDate
            }

        }
    }

    inner class ContentVH(private var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(content: BlockData) {
            if (!content.text.isNullOrEmpty()) {
                view.tvContent.text = content.text
            }
        }
    }

    inner class EntityVH(private var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(entity: List<EntityData>?) {
            entity?.let {
                val adapter = EntitiesAdapter()
                view.rvEntity.setHasFixedSize(true)
                view.rvEntity.adapter = adapter
                adapter.listContent.addAll(entity)
                adapter.notifyDataSetChanged()

            }
        }
    }

}