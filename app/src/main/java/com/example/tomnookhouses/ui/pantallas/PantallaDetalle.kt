package com.example.tomnookhouses.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.tomnookhouses.R
import com.example.tomnookhouses.data.RepositorioCasas
import com.example.tomnookhouses.ui.CasaViewModel

@Composable
fun PantallaDetalle(navController: NavHostController, casaId: Int, viewModel: CasaViewModel) {
    val casa = viewModel.obtenerCasaPorId(casaId)
    val rojoPersonalizado = Color(0xFFF20E0B)

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. FONDO
        AsyncImage(
            model = R.drawable.wallpaper,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.5f),
            contentScale = ContentScale.Crop
        )

        if (casa != null) {
            // 2. CONTENIDO PRINCIPAL
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                // Imagen Principal
                Card(
                    modifier = Modifier.fillMaxWidth().height(280.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    val painter = if (!casa.imagenUri.isNullOrEmpty()) {
                        rememberAsyncImagePainter(casa.imagenUri)
                    } else {
                        painterResource(id = casa.imagenID ?: R.drawable.casa_default)
                    }
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Nombre
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    shadowElevation = 4.dp
                ) {
                    Text(
                        text = casa.nombre,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = rojoPersonalizado
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Descripción
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = casa.descripcion,
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Fotos casa dentro
                Text(
                    text = "Vistas del Interior",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start).padding(start = 8.dp, bottom = 8.dp)
                )

                // Fila 1: decor1 y decor2
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FotoDecor(casa.decor1, Modifier.weight(1f))
                    FotoDecor(casa.decor2, Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Fila 2: decor3 y decor4
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FotoDecor(casa.decor3, Modifier.weight(1f))
                    FotoDecor(casa.decor4, Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(32.dp))

                // BOTÓN VOLVER (Debajo de las fotos)
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth(0.7f).height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = rojoPersonalizado)
                ) {
                    Text("Volver al catálogo", color = Color.White)
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }

        // 3. BANNER
        AsyncImage(
            model = R.drawable.banner,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun FotoDecor(imagenID: Int?, modifier: Modifier) {
    Card(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        AsyncImage(
            model = imagenID ?: R.drawable.casa_default,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}