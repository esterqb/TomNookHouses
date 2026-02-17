package com.example.tomnookhouses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tomnookhouses.data.RepositorioCasas
import com.example.tomnookhouses.ui.pantallas.* import com.example.tomnookhouses.ui.theme.TomNookHousesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tomnookhouses.ui.CasaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TomNookHousesTheme {
                val navController = rememberNavController()
                // Instanciamos el ViewModel aquí una sola vez
                val casaViewModel: CasaViewModel = viewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "inicio") {
                        composable("inicio") { PantallaInicio(navController) }
                        composable("formulario") { PantallaFormulario(navController, casaViewModel) }
                        composable("galeria") { PantallaGaleria(navController, casaViewModel) }
                        composable(
                            route = "detalle/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id") ?: 0
                            PantallaDetalle(navController, id, casaViewModel)
                        }
                    }
                }
            }
        }
    }
}