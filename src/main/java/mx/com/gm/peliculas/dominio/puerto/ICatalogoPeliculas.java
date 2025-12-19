package mx.com.gm.peliculas.dominio.puerto;

public interface ICatalogoPeliculas {
    
    String NOMBRE_ARCHIVO = "peliculas.txt";

    public abstract void agregarPelicula(String nombrePelicula);

    public abstract void listarPeliculas();

    public abstract void buscarPelicula(String buscar);

    public abstract void iniciarCatalogoPelicula();
}
