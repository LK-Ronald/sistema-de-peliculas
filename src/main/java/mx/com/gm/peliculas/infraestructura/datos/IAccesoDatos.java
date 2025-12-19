package mx.com.gm.peliculas.infraestructura.datos;

import java.util.List;

import mx.com.gm.peliculas.dominio.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.dominio.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.dominio.excepciones.LecturaDatosEx;
import mx.com.gm.peliculas.dominio.modelo.Pelicula;

public interface IAccesoDatos {

    public abstract boolean existe(String nombreArchivo) throws AccesoDatosEx;

    public abstract List<Pelicula> listar(String nombre) throws LecturaDatosEx;

    public abstract void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx;

    public abstract String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx;

    public abstract void crear(String nombreArchivo) throws AccesoDatosEx;

    public abstract void borrar(String nombreArchivo) throws AccesoDatosEx;

}
