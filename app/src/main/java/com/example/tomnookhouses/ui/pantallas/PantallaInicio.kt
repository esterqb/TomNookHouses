package com.example.tomnookhouses.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage // Librería para carga optimizada
import com.example.tomnookhouses.R

@Composable
fun PantallaInicio(navController: NavHostController) {
    val rojo = Color(0xFFF20E0B)

    Box(modifier = Modifier.fillMaxSize()) {

        // 1. IMAGEN DE FONDO
        AsyncImage(
            model = R.drawable.wallpaper,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.5f),
            contentScale = ContentScale.Crop
        )

        // 2. BANNER
        AsyncImage(
            model = R.drawable.banner,
            contentDescription = "Logo banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.FillWidth
        )

        // 3. CONTENIDO CENTRAL
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            AsyncImage(
                model = R.drawable.house_logo,
                contentDescription = "Logo Tom Nook",
                modifier = Modifier
                    .size(350.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            Surface(
                color = Color.White,
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 4.dp
            ) {
                Text(
                    text = "Encuentra tu nuevo hogar en la isla",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = { navController.navigate("galeria") },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = rojo)
            ) {
                Text("Ver Catálogo de Casas", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { navController.navigate("formulario") },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = rojo
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text("Registrar Nueva Casa", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}