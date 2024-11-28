package com.jesse.c24lksmall.presentation.uistate

import com.jesse.c24lksmall.domain.model.SampleModel


sealed class UIState {
    data object Loading:UIState()
    data class Error(val error:String):UIState()
    data class Success(val mySuccessList:List<SampleModel>):UIState()
}

