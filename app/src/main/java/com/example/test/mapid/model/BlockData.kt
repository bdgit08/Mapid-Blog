package com.example.test.mapid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BlockData {
    @SerializedName("key")
    @Expose
    var key: String? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("depth")
    @Expose
    var depth: Int = 0
    @SerializedName("inlineStyleRanges")
    @Expose
    var inlineStyleRanges: List<InlineStyle>? = mutableListOf()
    @SerializedName("entityRanges")
    @Expose
    var entityRanges: List<EntityRange> ?= mutableListOf()

    var entities: List<EntityData>? = mutableListOf()

    inner class InlineStyle {
        @SerializedName("offset")
        @Expose
        var offset: Int = 0
        @SerializedName("length")
        @Expose
        var length: Int = 0
        @SerializedName("style")
        @Expose
        var style: String? = null
    }

    inner class EntityRange {
        @SerializedName("offset")
        @Expose
        var offset: Int = 0
        @SerializedName("length")
        @Expose
        var length: Int = 0
        @SerializedName("key")
        @Expose
        var key: Int = 0
    }
}