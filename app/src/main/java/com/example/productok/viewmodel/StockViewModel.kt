package com.example.productok.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.productok.model.Producto

class StockViewModel : ViewModel() {

    // 1. Lista reactiva con 6 productos iniciales
    private val _productos = mutableStateListOf(
        Producto(1, "Taladro", "Industrial", 50.0, 10),
        Producto(2, "Martillo", "Manual", 15.0, 2),
        Producto(3, "Sierra", "Corte", 120.0, 8),
        Producto(4, "Destornillador", "Manual", 5.0, 3),
        Producto(5, "Nivel", "Medición", 25.0, 0),
        Producto(6, "Cinta Métrica", "Medición", 10.0, 12)
    )

    // Exponemos la lista para que las pantallas la lean
    val productos: List<Producto> get() = _productos

    // 2. Funciones de negocio exigidas
    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = _productos.indexOfFirst { it.id == id }
        if (index != -1) {
            val p = _productos[index]

            _productos[index] = p.copy(stockActual = nuevaCantidad)
        }
    }


    fun calcularValorTotalInventario(): Double {
        return _productos.sumOf { it.precio * it.stockActual }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return _productos.filter { it.stockActual < 5 }
    }

    fun obtenerCantidadStockCero(): Int {
        return _productos.count { it.stockActual == 0 }
    }

    // Función auxiliar para obtener un producto por ID
    fun obtenerProducto(id: Int): Producto? {
        return _productos.find { it.id == id }
    }
}