package com.example.bicyclecrud.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bicyclecrud.data.IBicycleRepository
import com.example.bicyclecrud.model.Bicycle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BicycleViewModel @Inject constructor(
    private val repository: IBicycleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BicycleUiState())
    val state: StateFlow<BicycleUiState> = _state.asStateFlow()

    init {
        loadBicycles()
    }

    fun loadBicycles() {
        viewModelScope.launch {
            repository.getAllBicycles()
                .onStart { _state.update { it.copy(isLoading = true) } }
                .catch { e -> _state.update { it.copy(isLoading = false, error = e.message) } }
                .collect { bicycleList ->
                    _state.update { it.copy(bicycles = bicycleList, isLoading = false, error = null) }
                }
        }
    }

    fun insertBicycle(bicycle: Bicycle) {
        viewModelScope.launch {
            try {
                repository.insertBicycle(bicycle)
                loadBicycles()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun updateBicycle(bicycle: Bicycle) {
        viewModelScope.launch {
            try {
                repository.updateBicycle(bicycle)
                loadBicycles()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun deleteBicycle(id: Int) {
        viewModelScope.launch {
            try {
                repository.softDeleteBicycle(id)
                loadBicycles()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    fun clearError() {
        _state.update { it.copy(error = null) }
    }
}
