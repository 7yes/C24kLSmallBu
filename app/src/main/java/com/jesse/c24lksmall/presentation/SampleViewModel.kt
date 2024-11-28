package com.jesse.c24lksmall.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesse.c24lksmall.domain.GetDataUC
import com.jesse.c24lksmall.presentation.uistate.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(private val getDataUC: GetDataUC) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            try {
                val result = getDataUC()
                _uiState.value = UIState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message.toString())
            }
        }
    }
}