package com.example.bicyclecrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.bicyclecrud.ui.screen.BicycleListScreen
import com.example.bicyclecrud.ui.theme.BicycleCRUDTheme
import com.example.bicyclecrud.viewModel.BicycleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: BicycleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BicycleCRUDTheme {
                BicycleListScreen(
                    viewModel = viewModel,
                    onAddClicked = {  },
                    onEditClicked = {  },
                    onDeleteClicked = { bicycle ->
                        viewModel.deleteBicycle(bicycle.id)
                    }
                )
            }
        }
    }
}
