package com.example.productok.pantallas

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    onNavigateToDetalle: (Int) -> Unit
) {
    val productos = viewModel.productos // Lista reactiva

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Bienvenido, $nombreOperario", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn es vital para listas en Compose
        LazyColumn {
            items(productos) { prod ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    onClick = { onNavigateToDetalle(prod.id) }
                ) {
                    Text("${prod.nombre} - Stock: ${prod.stockActual}", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}