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
    onNavigateToDetalle: (Int) -> Unit,
    onNavigateToReporte: () -> Unit // Agregamos este parámetro necesario
) {
    // 1. Estado de los filtros y datos
    var mostrarSoloCriticos by remember { mutableStateOf(false) }

    // 2. Filtramos la lista basándonos en el estado
    val listaFiltrada = if (mostrarSoloCriticos) {
        viewModel.obtenerProductosEnRiesgo()
    } else {
        viewModel.productos
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToReporte) {
                Text("Reporte")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Operario: $nombreOperario", style = MaterialTheme.typography.headlineSmall)

            // Filtros
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Button(onClick = { mostrarSoloCriticos = false }) { Text("Ver Todo") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { mostrarSoloCriticos = true }) { Text("Stock Crítico") }
            }

            // Lista (usando listaFiltrada)
            LazyColumn {
                items(listaFiltrada) { prod ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        onClick = { onNavigateToDetalle(prod.id) }
                    ) {
                        // Cambiamos el color a rojo si el stock es < 5
                        val colorTexto = if (prod.stockActual < 5) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface

                        Text(
                            text = "${prod.nombre} - Stock: ${prod.stockActual}",
                            modifier = Modifier.padding(16.dp),
                            color = colorTexto
                        )
                    }
                }
            }
        }
    }
}