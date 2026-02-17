package com.example.tomnookhouses.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.tomnookhouses.R
import com.example.tomnookhouses.data.RepositorioCasas
import com.example.tomnookhouses.ui.CasaViewModel

@Composable
fun PantallaGaleria(navController: NavHostController, viewModel: CasaViewModel) {
    val listaDeCasas = viewModel.listaCasas

    Box(modifier = Modifier.fillMaxSize()) {

        // 1. IMAGEN DE FONDO
        AsyncImage(
            model = R.drawable.wallpaper,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.5f),
            contentScale = ContentScale.Crop
        )

        // 2. LISTA DE CASAS
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 95.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
        ) {
            items(listaDeCasas) { casa ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { navController.navigate("detalle/${casa.id}") },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {

                        val imagenAMostrar = if (!casa.imagenUri.isNullOrEmpty()) {
                            casa.imagenUri
                        } else {
                            casa.imagenID ?: R.drawable.casa_default
                        }

                        AsyncImage(
                            model = imagenAMostrar,
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = casa.nombre,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                    }
                }
            }
        }

        // 3. BANNER
        AsyncImage(
            model = R.drawable.banner,
            contentDescription = "Logo banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.FillWidth
        )
    }
}