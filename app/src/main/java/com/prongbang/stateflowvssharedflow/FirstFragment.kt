package com.prongbang.stateflowvssharedflow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.prongbang.stateflowvssharedflow.databinding.FragmentFirstBinding
import com.prongbang.stateflowvssharedflow.flow.SharedFlowViewModel
import com.prongbang.stateflowvssharedflow.flow.StateFlowViewModel
import com.prongbang.stateflowvssharedflow.flow.state.FlowState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val sharedFlowViewModel: SharedFlowViewModel by viewModels()
    private val stateFlowViewModel: StateFlowViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        lifecycleScope.launch {
            sharedFlowViewModel.states.collect {
                when (it) {
                    is FlowState.Data -> {
                        Log.i(TAG, "SharedFlow - Data: ${it.data}")
                    }
                    FlowState.HideLoading -> {
                        Log.i(TAG, "SharedFlow - HideLoading")
                    }
                    FlowState.ShowLoading -> {
                        Log.i(TAG, "SharedFlow - ShowLoading")
                    }
                }
            }
        }

        lifecycleScope.launch {
            stateFlowViewModel.states.collect {
                when (it) {
                    is FlowState.Data -> {
                        Log.i(TAG, "StateFlow - Data: ${it.data}")
                    }
                    FlowState.HideLoading -> {
                        Log.i(TAG, "StateFlow - HideLoading")
                    }
                    FlowState.ShowLoading -> {
                        Log.i(TAG, "StateFlow - ShowLoading")
                    }
                }
            }
        }

        sharedFlowViewModel.test()
        stateFlowViewModel.test()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = FirstFragment::class.java.simpleName
    }
}