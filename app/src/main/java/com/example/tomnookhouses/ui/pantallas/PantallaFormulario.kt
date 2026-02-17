package com.example.tomnookhouses.ui.pantallas

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.tomnookhouses.R
import com.example.tomnookhouses.data.Casa
import com.example.tomnookhouses.data.CasaDatabase
import com.example.tomnookhouses.ui.CasaViewModel
import kotlinx.coroutines.launch

@Composable
fun PantallaFormulario(navController: NavHostController, viewModel: CasaViewModel) {
    val context = LocalContext.current
    val rojoPersonalizado = Color(0xFFF20E0B)

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }
    var nombreError by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imagenUri = uri
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. FONDO
        AsyncImage(
            model = R.drawable.wallpaper,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().alpha(0.5f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            // Título
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 4.dp
            ) {
                Text(
                    text = "REGISTRAR CASA",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = rojoPersonalizado
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Card Formulario
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = {
                            nombre = it
                            nombreError = it.isBlank()
                        },
                        label = { Text("Nombre de la casa") },
                        isError = nombreError,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        placeholder = { Text("Mínimo 10 caracteres...") },
                        modifier = Modifier.fillMaxWidth().height(120.dp),
                        isError = descripcion.isNotEmpty() && descripcion.length < 10
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { launcher.launch("image/*") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Elegir Foto de Galería")
                    }

                    imagenUri?.let {
                        Spacer(modifier = Modifier.height(12.dp))
                        Image(
                            painter = rememberAsyncImagePainter(it),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- AQUÍ ESTÁ EL CAMBIO IMPORTANTE ---
            Button(
                onClick = {
                    // La lógica de guardado debe ir DENTRO del onClick
                    if (nombre.isNotBlank() && descripcion.length >= 10) {

                        // Permiso persistente
                        imagenUri?.let { uri ->
                            try {
                                context.contentResolver.takePersistableUriPermission(
                                    uri,
                                    android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
                                )
                            } catch (e: Exception) { e.printStackTrace() }
                        }

                        val nuevaCasa = Casa(
                            nombre = nombre,
                            imagenID = R.drawable.casa_default,
                            descripcion = descripcion,
                            imagenUri = imagenUri?.toString()
                        )

                        viewModel.insertarCasa(nuevaCasa)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = rojoPersonalizado),
                // Deshabilitamos el botón si no cumple los requisitos
                enabled = nombre.isNotBlank() && descripcion.length >= 10
            ) {
                Text("GUARDAR EN EL CATÁLOGO", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        // Banner fijo
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