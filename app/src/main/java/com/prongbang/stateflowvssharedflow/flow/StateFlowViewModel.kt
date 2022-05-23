package com.prongbang.stateflowvssharedflow.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prongbang.stateflowvssharedflow.flow.state.FlowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StateFlowViewModel @Inject constructor() : ViewModel() {

    private val _states = MutableStateFlow<FlowState>(FlowState.Initial)
    val states: StateFlow<FlowState> = _states

    fun test() = viewModelScope.launch(Dispatchers.IO) {
        _states.emit(FlowState.ShowLoading)
        _states.emit(FlowState.Data(1))
        _states.emit(FlowState.Data(2))
        _states.emit(FlowState.Data(3))
        _states.emit(FlowState.Data(4))
        _states.emit(FlowState.HideLoading)
    }
}