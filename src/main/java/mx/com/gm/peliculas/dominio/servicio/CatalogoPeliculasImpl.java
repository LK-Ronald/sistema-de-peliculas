package mx.com.gm.peliculas.dominio.servicio;

import java.util.List;
import mx.com.gm.peliculas.dominio.excepciones.*;
import mx.com.gm.peliculas.dominio.modelo.Pelicula;
import mx.com.gm.peliculas.dominio.puerto.ICatalogoPeliculas;
import mx.com.gm.peliculas.infraestructura.datos.IAccesoDatos;
import mx.com.gm.peliculas.infraestructura.datos.archivo.AccesoDatosImpl;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {

    private IAccesoDatos datos;

    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = this.datos.existe(NOMBRE_ARCHIVO);
            this.datos.escribir(pelicula, NOMBRE_ARCHIVO, anexar);
        } catch (AccesoDatosEx e) {
            System.out.println("Error de acceso a datos");
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            List<Pelicula> peliculas = this.datos.listar(NOMBRE_ARCHIVO);
            for (Pelicula pelicula : peliculas) {
                System.out.println(pelicula.getNombre());
            }
        } catch (LecturaDatosEx e) {
            System.out.println("Error de lectura de datos");
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String encontrado = null;
        try {
            List<Pelicula> peliculas = this.datos.listar(NOMBRE_ARCHIVO);
            for (Pelicula p : peliculas) {
                if (p.getNombre().equalsIgnoreCase(buscar)) {
                    encontrado = p.getNombre();
                    break;
                }
            }
            System.out.println("Pelicula encontrada: " + encontrado);
        } catch (AccesoDatosEx e) {
            System.out.println("Error de acceso a datos");
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void iniciarCatalogoPelicula() {
        try {
            if (this.datos.existe(NOMBRE_ARCHIVO)) {
                datos.borrar(NOMBRE_ARCHIVO);
                datos.crear(NOMBRE_ARCHIVO);
            } else {
                datos.crear(NOMBRE_ARCHIVO);
            }
        } catch (AccesoDatosEx e) {
            System.out.println("Error al crear el catalogo de peliculas");
            e.printStackTrace(System.out);
        }
    }
}
