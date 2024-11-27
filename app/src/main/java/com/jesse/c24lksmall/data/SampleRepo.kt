package com.jesse.c24lksmall.data

import com.jesse.c24lksmall.domain.model.SampleModel
import com.jesse.c24lksmall.domain.model.toDomain
import javax.inject.Inject

class SampleRepo @Inject constructor(private val sampleApiServ: SampleApiServ) {

    suspend fun getData(): List<SampleModel> {
        val call = sampleApiServ.getData()
        val data: List<SampleModel> = call.body()?.map { it.toDomain() } ?: emptyList()
        return data
    }
}