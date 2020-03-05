package com.example.test.mapid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MenuData : Serializable {
    @SerializedName("_id")
    @Expose
    var _id: String ? = null
    @SerializedName("title")
    @Expose
    var title: String ? = null
    @SerializedName("sub_type")
    @Expose
    var sub_type: String ? = null
    @SerializedName("link")
    @Expose
    var link: String ? = null
}