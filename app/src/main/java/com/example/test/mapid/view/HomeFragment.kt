package com.example.test.mapid.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.test.mapid.adapter.ContentAdapter
import com.example.test.mapid.R
import com.example.test.mapid.model.BlockData
import com.example.test.mapid.model.EntityData
import com.example.test.mapid.viewmodel.HomeViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var contentAdapter = ContentAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvContent.adapter = contentAdapter
        rvContent.setHasFixedSize(true)
        homeViewModel.blogDataMutable.observe(this, Observer {
            contentAdapter.blogData = it
            it.editorState?.let { jsonString ->
                convertStringtoObj(jsonString)
            }
        })
        homeViewModel.stateMutable.observe(this, Observer {
            when (it) {
                HomeViewModel.State.LOADING -> {
                    setLoading(View.VISIBLE)
                    rvContent.visibility = View.GONE
                }
                HomeViewModel.State.SUCCES -> {
                    setLoading(View.GONE)
                    rvContent.visibility = View.VISIBLE
                }
                else -> {

                }
            }
        })
    }

    private fun setLoading(loading: Int) {
        ivLogoLoading.visibility = loading
        progress_circular.visibility = loading
    }

    private fun convertStringtoObj(stringJson: String) {
        try {
            val jsonObject = JSONObject(stringJson)
            val jsonBlocks = jsonObject.getJSONArray("blocks")
            val jsonEntity = jsonObject.getJSONObject("entityMap")
            val length = jsonBlocks.length() - 1
            val blocks = mutableListOf<BlockData>()
            for (i in 0..length) {
                val model = Gson().fromJson(jsonBlocks.get(i).toString(), BlockData::class.java)
                val entityRange = model.entityRanges
                val entities = mutableListOf<EntityData>()
                if (!entityRange.isNullOrEmpty()) {
                    for (e in entityRange) {
                        val entity = jsonEntity.getJSONObject(e.key.toString())
                        entities.add(Gson().fromJson(entity.toString(), EntityData::class.java))
                    }
                }
                model.entities = entities
                blocks.add(model)
            }
            contentAdapter.listContent = blocks
            contentAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            print("e.message")
        }
    }

    fun fetch(link: String) {
        homeViewModel.fetchBlog(link)
    }
}