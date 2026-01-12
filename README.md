#  Sistema de Catálogo de Películas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Layered-blue?style=for-the-badge)

Un proyecto robusto y elegante desarrollado en **Java** para la gestión de un catálogo de películas persistente. Este sistema demuestra la aplicación práctica de principios de ingeniería de software modernos como **SOLID**, **Patrones de Diseño** y una **Arquitectura en Capas**.

---

##  Descripción del Proyecto

Este mini-proyecto es una aplicación de consola que permite gestionar una colección de películas. Los datos se persisten en un archivo de texto (`peliculas.txt`), asegurando que la información se mantenga entre ejecuciones. El objetivo principal es ilustrar cómo estructurar una aplicación escalable y mantenible utilizando buenas prácticas de POO (Programación Orientada a Objetos).

###  Funcionalidades Principales

*   **Iniciar Catálogo**: Crea o reinicia el archivo de almacenamiento.
*   **Agregar Película**: Guarda nuevas películas en el catálogo persistente.
*   **Listar Películas**: Muestra todas las películas almacenadas.
*   **Buscar Película**: Encuentra una película específica por nombre ignorando mayúsculas/minúsculas.

---

##  Arquitectura y Diseño Técnico

El proyecto sigue una arquitectura modular separada en capas, lo que garantiza el desacoplamiento y facilita el mantenimiento.

###  Capas del Sistema

1.  **Dominio (`mx.com.gm.peliculas.dominio`)**:
    *   Contiene la lógica de negocio y las "reglas del juego".
    *   **Modelo**: Entidades como `Pelicula`.
    *   **Puerto (Interfaces)**: `ICatalogoPeliculas` define las operaciones disponibles sin preocuparse de la implementación.
    *   **Servicio**: `CatalogoPeliculasImpl` orquesta las operaciones entre la capa de presentación y la capa de datos.

2.  **Infraestructura (`mx.com.gm.peliculas.infraestructura`)**:
    *   Maneja los detalles técnicos de bajo nivel (persistencia de archivos).
    *   Implementa las interfaces definidas por el dominio (`AccesoDatosImpl`).

3.  **App (`mx.com.gm.peliculas.app`)**:
    *   Punto de entrada (`Main`) que interactúa con el usuario y utiliza los servicios del dominio.

###  Conceptos Aplicados

#### 1. Principios SOLID 
*   **SRP (Single Responsibility Principle)**: Cada clase tiene una responsabilidad única. `Pelicula` solo guarda datos, `AccesoDatosImpl` solo gestiona el archivo, y `CatalogoPeliculasImpl` solo gestiona la lógica de negocio.
*   **DIP (Dependency Inversion Principle)**: El módulo de alto nivel (`CatalogoPeliculasImpl`) depende de abstracciones (`IAccesoDatos`) y no de implementaciones concretas, lo que facilita el cambio del mecanismo de persistencia en el futuro.

#### 2. Polimorfismo e Interfaces 
El uso de interfaces como `ICatalogoPeliculas` e `IAccesoDatos` permite que el sistema sea flexible. Podríamos cambiar la implementación de archivo de texto a una base de datos SQL simplemente creando una nueva clase que implemente `IAccesoDatos`, sin tocar nada de la lógica de negocio.

#### 3. Manejo de Excepciones Personalizadas 
Se ha implementado una jerarquía de excepciones propias para tener un control granular de los errores:
*   `AccesoDatosEx` (Padre)
    *   `LecturaDatosEx`
    *   `EscrituraDatosEx`

---

##  Estructura del Proyecto

```bash
mx.com.gm.peliculas
│
├── app
│   └── Main.java                  # Punto de entrada y menú de usuario
│
├── dominio
│   ├── excepciones                # Jerarquía de excepciones personalizadas
│   │   ├── AccesoDatosEx.java
│   │   ├── EscrituraDatosEx.java
│   │   └── LecturaDatosEx.java
│   │
│   ├── modelo
│   │   └── Pelicula.java          # POJO / Java Bean
│   │
│   ├── puerto
│   │   └── ICatalogoPeliculas.java # Interfaz de lógica de negocio
│   │
│   └── servicio
│       └── CatalogoPeliculasImpl.java # Implementación de negocio
│
└── infraestructura
    └── datos
        ├── IAccesoDatos.java      # Interfaz de acceso a datos
        └── archivo
            └── AccesoDatosImpl.java # Implementación con Archivos
```

---

##  Cómo Ejecutar

1.  Asegúrate de tener **Java** instalado.
2.  Compila el proyecto.
3.  Ejecuta la clase `Main`.

```console
Bienvenido al catalogo de Peliculas
1. Iniciar Catalogo peliculas
2. Agregar pelicula
3. Listar Peliculas
4. Buscar Pelicula
0. Salir
Elige una opcion:
```

¡Disfruta gestionando tu colección de películas con un código limpio y profesional!
