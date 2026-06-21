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
import com.example.productok.viewmodel.StockViewModel
import com.example.productok.ui.theme.ProductokTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductokTheme {
                val viewModel: StockViewModel = viewModel()
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "pantalla1") {

                    // Pantalla 1: Ingreso
                    composable("pantalla1") {
                        PantallaIngreso(onNavigateToCatalogo = { nombre ->
                            navController.navigate("pantalla2/$nombre")
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
                            onNavigateToDetalle = { id: Int -> navController.navigate("pantalla3/$id") },
                            onNavigateToReporte = { navController.navigate("pantalla4") }
                        )
                    }
                }
            }
        }
    }
}