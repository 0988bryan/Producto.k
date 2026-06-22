package com.example.productok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.productok.pantallas.PantallaIngreso
import com.example.productok.pantallas.PantallaCatalogo
import com.example.productok.pantallas.PantallaDetalle
import com.example.productok.viewmodel.StockViewModel
import com.example.productok.ui.theme.ProductokTheme
import com.example.productok.pantallas.PantallaReporte

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductokTheme {
                val viewModel: StockViewModel = viewModel()
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "pantalla1") {
                    composable("pantalla4") {
                        PantallaReporte(viewModel = viewModel, onNavigateBack = { navController.popBackStack() })
                    }
                    // Pantalla 1: Ingreso
                    composable("pantalla1") {
                        PantallaIngreso(onNavigateToCatalogo = { nombre ->
                            navController.navigate("pantalla2/$nombre")
                        })
                    }

                    // Pantalla 2: Catálogo
                    composable(
                        route = "pantalla2/{nombre}",
                        arguments = listOf(navArgument("nombre") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("nombre") ?: "Invitado"

                        PantallaCatalogo(
                            nombreOperario = nombre,
                            viewModel = viewModel,
                            onNavigateToDetalle = { id -> navController.navigate("pantalla3/$id") },
                            onNavigateToReporte = { navController.navigate("pantalla4") } // <--- ¡Y esta línea!
                        )
                    }

                    // Pantalla 3: Detalle (Ahora está fuera, donde corresponde)
                    composable(
                        route = "pantalla3/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("id") ?: 0
                        PantallaDetalle(
                            productoId = id,
                            viewModel = viewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}