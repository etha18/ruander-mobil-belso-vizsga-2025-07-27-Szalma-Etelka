package com.example.bicyclecrud.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.bicyclecrud.model.Bicycle
@Composable
fun BicycleAddDialog(
    onDismiss: () -> Unit,
    onSave: (Bicycle) -> Unit
) {
    var manufacturer by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var netPriceInput by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(true) }
    var showError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val netPrice = netPriceInput.toIntOrNull()

                val isValid = manufacturer.length >= 2 &&
                        model.length >= 3 &&
                        netPrice != null &&
                        netPrice in 0..10_000_000

                if (isValid) {
                    val bicycle = Bicycle(
                        manufacturer = manufacturer.trim(),
                        model = model.trim(),
                        netPrice = netPrice!!,
                        isActive = isActive,
                        isDeleted = false
                    )
                    onSave(bicycle)
                } else {
                    showError = true
                }
            }) {
                Text("Mentés")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Mégse")
            }
        },
        title = { Text("Új kerékpár felvitele") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = manufacturer,
                    onValueChange = { manufacturer = it },
                    label = { Text("Gyártó") },
                    isError = showError && manufacturer.length < 2,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it },
                    label = { Text("Modell") },
                    isError = showError && model.length < 3,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = netPriceInput,
                    onValueChange = { netPriceInput = it },
                    label = { Text("Nettó ár (Ft)") },
                    isError = showError && (netPriceInput.toIntOrNull() == null || netPriceInput.toIntOrNull() !in 0..10_000_000),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(
                        checked = isActive,
                        onCheckedChange = { isActive = it }
                    )
                    Text("Aktív")
                }
                if (showError) {
                    Text(
                        text = "Hibás adatok! Ellenőrizd a mezőket.",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    )
}
