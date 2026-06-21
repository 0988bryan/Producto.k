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
    val productos = viewModel.productos

    // Obtenemos los valores calculados
    val totalInventario = viewModel.calcularValorTotalInventario()
    val productosRiesgo = viewModel.obtenerProductosEnRiesgo()
    val stockCero = viewModel.obtenerCantidadStockCero()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Bienvenido, $nombreOperario", style = MaterialTheme.typography.headlineSmall)

        // Tarjeta de Resumen (Filtros/Datos)
        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Total Inventario: $${"%.2f".format(totalInventario)}")
                Text("Productos en riesgo (<5): ${productosRiesgo.size}")
                Text("Productos agotados: $stockCero")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

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