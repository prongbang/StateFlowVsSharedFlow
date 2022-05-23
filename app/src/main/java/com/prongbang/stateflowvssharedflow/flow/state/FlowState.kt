package com.prongbang.stateflowvssharedflow.flow.state

sealed class FlowState {
    object Initial : FlowState()
    object ShowLoading : FlowState()
    object HideLoading : FlowState()
    data class Data(val data: Int) : FlowState()
}
