package com.example.test.mapid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EntityData {
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("mutability")
    @Expose
    var mutability: String? = null
    @SerializedName("data")
    @Expose
    var data: ImageData? = null

    inner class ImageData {
        @SerializedName("src")
        @Expose
        var src: String? = null
    }
}