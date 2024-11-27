package com.jesse.c24lksmall.domain.model

import com.jesse.c24lksmall.data.model.ResponseSampleItem

data class SampleModel (
    val firstEpisode: String?,
    val image: String?,
    val name: String?,
    val id: Int?
)

fun ResponseSampleItem.toDomain() = SampleModel(
    firstEpisode = firstEpisode,
    image = image,
    name = name,
    id = id
)