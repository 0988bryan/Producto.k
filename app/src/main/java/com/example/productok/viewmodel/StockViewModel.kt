package com.example.productok.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.productok.model.Producto

class StockViewModel : ViewModel() {

    // 1. Lista reactiva con 6 productos iniciales
    private val _productos = mutableStateListOf(
        Producto(1, "Bondex Preg Intaco Premium", "Adhesivo premium", 15.50, 10),
        Producto(2, "Bondex Preg Intaco Standard", "Adhesivo estándar", 12.00, 20),
        Producto(3, "Groutex Gris Cálido", "Boquilla de alta resistencia", 8.75, 4), // Riesgo (<5)
        Producto(4, "Maxiseal 3000", "Impermeabilizante", 45.00, 2), // Riesgo (<5)
        Producto(5, "Maxisellador", "Sellador de superficies", 22.00, 0), // Stock Cero
        Producto(6, "Malla Flex", "Malla de refuerzo", 30.00, 15)
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