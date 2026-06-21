package com.example.productok.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.productok.viewmodel.StockViewModel

@Composable
fun PantallaDetalle(
    productoId: Int,
    viewModel: StockViewModel,
    onNavigateBack: () -> Unit
) {
    // Buscamos el producto en el ViewModel usando el ID recibido
    val producto = viewModel.productos.find { it.id == productoId }
    var nuevoStock by remember { mutableStateOf(producto?.cantidad?.toString() ?: "0") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (producto != null) {
            Text("Editando: ${producto.nombre}", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nuevoStock,
                onValueChange = { nuevoStock = it },
                label = { Text("Nuevo Stock") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // Actualizamos el dato en el ViewModel
                viewModel.actualizarStock(producto.id, nuevoStock.toIntOrNull() ?: 0)
                onNavigateBack() // Regresamos al catálogo
            }) {
                Text("Guardar Cambios")
            }
        } else {
            Text("Producto no encontrado")
        }
    }
}