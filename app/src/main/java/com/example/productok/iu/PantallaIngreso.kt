package com.example.productok.iu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaIngreso(onNavigateToCatalogo: (String) -> Unit) {
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenido a StockPro", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del Operario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { onNavigateToCatalogo(nombre) },
            enabled = nombre.length >= 3 // Validación exigida
        ) {
            Text("Ingresar al Sistema")
        }
    }
}