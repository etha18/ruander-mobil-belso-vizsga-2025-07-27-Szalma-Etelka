package com.example.bicyclecrud.viewModel

import com.example.bicyclecrud.model.Bicycle

data class BicycleUiState(
    val bicycles: List<Bicycle> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
