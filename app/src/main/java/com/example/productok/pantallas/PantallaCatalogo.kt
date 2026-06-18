package com.example.productok.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.productok.viewmodel.StockViewModel

@Composable
fun PantallaCatalogo(
    nombreOperario: String,
    viewModel: StockViewModel,
    onNavigateToDetalle: (Int) -> Unit,
    onNavigateToReporte: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Bienvenido, $nombreOperario", style = MaterialTheme.typography.headlineSmall)
        // Aquí irá tu lista de productos luego
        Text("Esta es la pantalla del Catálogo")
    }
}