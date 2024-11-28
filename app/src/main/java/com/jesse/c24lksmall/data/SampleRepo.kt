package com.jesse.c24lksmall.data

import com.jesse.c24lksmall.core.Resource
import com.jesse.c24lksmall.domain.model.SampleModel
import javax.inject.Inject

class SampleRepo @Inject constructor(private val sampleServ: SampleServ) {

    suspend fun getData(): Resource<List<SampleModel>> {
        val response: Resource<List<SampleModel>> = sampleServ.getData()
        return response
    }
}