package com.example.productok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.material3.Text
import com.example.productok.iu.PantallaIngreso
import com.example.productok.ui.theme.ProductokTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductokTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "pantalla1") {

                    composable("pantalla1") {
                        PantallaIngreso(onNavigateToCatalogo = { nombre ->
                            navController.navigate("pantalla2/$nombre")
                        })
                    }

                    composable(
                        route = "pantalla2/{nombre}",
                        arguments = listOf(navArgument("nombre") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("nombre") ?: "Invitado"
                        // Próximamente aquí irá tu Pantalla2
                        Text(text = "Hola $nombre, bienvenido al catálogo")
                    }
                }
            }
        }
    }
}