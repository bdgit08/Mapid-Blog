package com.example.test.mapid.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BlockResult {
    @SerializedName("blocks")
    @Expose
    var blocks :List<BlockData> ?= mutableListOf()
}