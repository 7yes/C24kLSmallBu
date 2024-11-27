package com.jesse.c24lksmall.data.model

import com.google.gson.annotations.SerializedName

data class ResponseSampleItem(
    @SerializedName("age") val age: String,
    @SerializedName("allOccupations") val allOccupations: List<String>,
    @SerializedName("firstEpisode") val firstEpisode: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("hair") val hair: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String,
    @SerializedName("occupation") val occupation: String,
    @SerializedName("relatives") val relatives: List<Relative>,
    @SerializedName("url") val url: String,
    @SerializedName("voicedBy") val voicedBy: String,
    @SerializedName("wikiUrl") val wikiUrl: String
)