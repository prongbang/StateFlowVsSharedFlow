# StateFlow Vs SharedFlow

https://developer.android.com/kotlin/flow/stateflow-and-sharedflow

### Example

#### StateFlow

```kotlin
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
```

#### SharedFlow

```kotlin
@HiltViewModel
class SharedFlowViewModel @Inject constructor() : ViewModel() {

    private val _states = MutableSharedFlow<FlowState>()
    val states: SharedFlow<FlowState> = _states

    fun test() = viewModelScope.launch(Dispatchers.IO) {
        _states.emit(FlowState.ShowLoading)
        _states.emit(FlowState.Data(1))
        _states.emit(FlowState.Data(2))
        _states.emit(FlowState.Data(3))
        _states.emit(FlowState.Data(4))
        _states.emit(FlowState.HideLoading)
    }
}
```

### Result

```shell
2022-05-23 22:56:15.839 I/FirstFragment: StateFlow - HideLoading
2022-05-23 22:56:15.840 I/FirstFragment: SharedFlow - ShowLoading
2022-05-23 22:56:16.111 I/FirstFragment: SharedFlow - Data: 1
2022-05-23 22:56:16.200 I/FirstFragment: SharedFlow - Data: 2
2022-05-23 22:56:16.203 I/FirstFragment: SharedFlow - Data: 3
2022-05-23 22:56:16.204 I/FirstFragment: SharedFlow - Data: 4
2022-05-23 22:56:16.205 I/FirstFragment: SharedFlow - HideLoading
```