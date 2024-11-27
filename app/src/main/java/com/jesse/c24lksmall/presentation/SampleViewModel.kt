package com.jesse.c24lksmall.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.c24lksmall.domain.GetDataUC
import com.jesse.c24lksmall.domain.model.SampleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val getDataUC: GetDataUC) : ViewModel() {

    private val _myData = MutableLiveData<List<SampleModel>>()
    val myData: LiveData<List<SampleModel>> = _myData

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getDataUC()
            _myData.postValue(response)
            Log.d("TAG", "getData: on VM $response ")
        }
    }
}