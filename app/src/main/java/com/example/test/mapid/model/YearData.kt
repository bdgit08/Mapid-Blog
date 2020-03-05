package com.example.test.mapid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class YearData : Serializable {
    @SerializedName("_id")
    @Expose
    var _id: String ? = null
    @SerializedName("sub_type")
    @Expose
    var sub_type: String ? = null
    @SerializedName("id")
    @Expose
    var id: String ? = null
}