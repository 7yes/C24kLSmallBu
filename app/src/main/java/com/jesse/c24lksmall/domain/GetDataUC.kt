package com.jesse.c24lksmall.domain

import com.jesse.c24lksmall.core.Resource
import com.jesse.c24lksmall.data.SampleRepo
import com.jesse.c24lksmall.domain.model.SampleModel
import javax.inject.Inject

class GetDataUC @Inject constructor(private val sampleRepo: SampleRepo) {

    suspend operator fun invoke():Resource<List<SampleModel>> = sampleRepo.getData()

}