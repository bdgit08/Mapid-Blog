package com.example.test.mapid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BlogData : Serializable {
    @SerializedName("_id")
    @Expose
    var _id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("sub_type")
    @Expose
    var sub_type: String? = null
    @SerializedName("date")
    @Expose
    var date: String? = null
    @SerializedName("author")
    @Expose
    var author: Author? = null
    @SerializedName("editorState")
    @Expose
    var editorState : String?= null

    inner class Author : Serializable {
        @SerializedName("profile_picture")
        @Expose
        var profile_picture: ProfilePicture? = null
        @SerializedName("full_name")
        @Expose
        var full_name: String? = null
    }

    inner class ProfilePicture {
        @SerializedName("url")
        @Expose
        var url: String? = null
    }
}