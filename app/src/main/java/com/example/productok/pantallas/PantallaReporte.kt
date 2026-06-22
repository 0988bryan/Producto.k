package com.example.productok.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.productok.viewmodel.StockViewModel

@Composable
fun PantallaReporte(viewModel: StockViewModel, onNavigateBack: () -> Unit) {
    val totalInventario = viewModel.calcularValorTotalInventario()
    val productosAgotados = viewModel.obtenerCantidadStockCero()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Reporte Financiero", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Capital Invertido Total:", style = MaterialTheme.typography.titleMedium)
                Text("$${"%.2f".format(totalInventario)}", style = MaterialTheme.typography.displaySmall)
            }
        }

        Text("Total de productos con stock en cero: $productosAgotados")

        Button(onClick = onNavigateBack, modifier = Modifier.padding(top = 16.dp)) {
            Text("Volver al Catálogo")
        }
    }
}