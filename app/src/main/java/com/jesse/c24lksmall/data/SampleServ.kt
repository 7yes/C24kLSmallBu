package com.jesse.c24lksmall.data

import com.jesse.c24lksmall.core.Resource
import com.jesse.c24lksmall.data.model.ResponseSample
import com.jesse.c24lksmall.domain.model.SampleModel
import com.jesse.c24lksmall.domain.model.toDomain
import javax.inject.Inject

class SampleServ @Inject constructor(private val sampleApiServ: SampleApiServ) {

    suspend fun getData(): Resource<List<SampleModel>> {
        val call  = sampleApiServ.getData()

        return if (call.isSuccessful){
            Resource.Sucess(data = call.body()?.map { it.toDomain() } ?: emptyList())
        }else{
            Resource.Error(call.errorBody().toString())
        }
    }
}