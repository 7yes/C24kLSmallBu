package com.jesse.c24lksmall.data.model

import com.google.gson.annotations.SerializedName

data class Relative(
    @SerializedName("_id") val _id: String,
    @SerializedName("name") val name: String,
    @SerializedName("relationship") val relationship: String,
    @SerializedName("url") val url: String,
    @SerializedName("wikiUrl") val wikiUrl: String
)