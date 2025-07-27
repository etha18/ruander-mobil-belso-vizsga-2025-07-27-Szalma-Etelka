package com.example.bicyclecrud.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bicyclecrud.model.Bicycle
import com.example.bicyclecrud.viewModel.BicycleViewModel
import com.example.bicyclecrud.ui.components.DeleteConfirmDialog
import com.example.bicyclecrud.ui.components.BicycleCard
import com.example.bicyclecrud.ui.components.BicycleAddDialog

@Composable
fun BicycleListScreen(
    viewModel: BicycleViewModel,
    onAddClicked: () -> Unit,
    onEditClicked: (Bicycle) -> Unit,
    onDeleteClicked: (Bicycle) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val bicycleList = state.bicycles

    var bicycleToDelete by remember { mutableStateOf<Bicycle?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(bicycleList) { bicycle ->
                    BicycleCard(
                        bicycle = bicycle,
                        onEdit = { onEditClicked(bicycle) },
                        onDelete = {
                            bicycleToDelete = bicycle
                            showDialog = true
                        }
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("+")
        }
    }
    if (showDialog && bicycleToDelete != null) {
        DeleteConfirmDialog(
            itemName = bicycleToDelete!!.model,
            onConfirm = {
                onDeleteClicked(bicycleToDelete!!)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }

    if (showAddDialog) {
        BicycleAddDialog(
            onDismiss = { showAddDialog = false },
            onSave = { newBicycle ->
                viewModel.insertBicycle(newBicycle)
                showAddDialog = false
            }
        )
    }

}
