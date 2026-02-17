# Nook's Homes 🏠🍃

**Nook's Homes** es una aplicación de gestión inmobiliaria desarrollada para el módulo de **Programación Multimedia y Dispositivos Móviles (2º DAM)**. La aplicación permite a los usuarios explorar un catálogo de viviendas inspiradas en el universo de *Animal Crossing*, visualizar detalles específicos y registrar nuevas propiedades con soporte multimedia.

<img width="647" height="431" alt="house_logo" src="https://github.com/user-attachments/assets/433734e9-c837-4789-bb12-892a319367a7" />


## 🚀 Características

* **Arquitectura MVVM**: Separación clara entre la interfaz de usuario, la lógica de negocio y la persistencia de datos.
* **Persistencia con Room**: Los datos se almacenan en una base de datos local SQLite, garantizando que la información se mantenga tras cerrar o reiniciar la app.
* **Navegación Fluida**: Implementación de `NavHost` para un flujo de pantallas coherente y paso de argumentos dinámicos.
* **Gestión Multimedia**: Integración con la galería del dispositivo y carga asíncrona de imágenes mediante la librería **Coil**.
* **Interfaz Reactiva**: UI moderna construida íntegramente con **Jetpack Compose**.

## 🛠️ Tecnologías Utilizadas

* **Kotlin**: Lenguaje de programación principal.
* **Jetpack Compose**: Diseño de interfaz declarativa.
* **Room Persistence Library**: Manejo de la base de datos local.
* **ViewModel & State**: Gestión del ciclo de vida y estados reactivos.
* **Coil**: Carga optimizada de imágenes.

## 📂 Estructura del Proyecto

El código está organizado siguiendo las mejores prácticas y los requisitos del proyecto:

* **`data`**: Contiene las Entidades, DAOs, la Base de Datos de Room y el Repositorio (fuente de verdad).
* **`ui`**: Contiene el `CasaViewModel` y la configuración del tema visual.
* **`ui.pantallas`**: Componentes Composable que definen la interfaz:
    * `PantallaInicio`
    * `PantallaGaleria`
    * `PantallaFormulario`
    * `PantallaDetalle`

## 📖 Instalación y Uso

1.  **Clonar** este repositorio o descargar el código fuente.
2.  **Abrir** el proyecto en Android Studio (Ladybug o superior).
3.  **Sincronizar** el proyecto con los archivos Gradle.
4.  **Ejecutar** en un emulador o dispositivo físico con **Android 8.0 (API 26)** o superior.

> [!IMPORTANT]
> **Nota sobre permisos**: Al registrar una casa, la app solicita permisos de lectura para la galería. Se ha implementado **persistencia de permisos** para asegurar que las imágenes seleccionadas sigan siendo visibles incluso después de reiniciar el dispositivo.

---
Las imágenes del videojuego Animal Crossing pertenecen a Nintendo, NO son mi creación. Esta aplicación no es lucrativa, es para mi aprendizaje con la estética de este videojuego.
**Desarrollado por:** Ester Cubero - 2º DAM.
