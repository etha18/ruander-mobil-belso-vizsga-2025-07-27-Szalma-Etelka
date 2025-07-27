package com.example.bicyclecrud.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bicyclecrud.model.Bicycle

@Composable
fun BicycleCard(
    bicycle: Bicycle,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Gyártó: ${bicycle.manufacturer}")
            Text(text = "Modell: ${bicycle.model}")
            Text(text = "Nettó ár: ${bicycle.netPrice} Ft")
            Text(text = "Aktív: ${if (bicycle.isActive) "Igen" else "Nem"}")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onEdit) {
                    Text("Szerkesztés")
                }
                TextButton(onClick = onDelete) {
                    Text("Törlés")
                }
            }
        }
    }
}
