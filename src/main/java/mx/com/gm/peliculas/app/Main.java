package mx.com.gm.peliculas.app;

import java.util.Scanner;
import mx.com.gm.peliculas.dominio.servicio.CatalogoPeliculasImpl;

/**
 * Clase principal que ejecuta el catalogo de peliculas
 * @version 1.0
 * @author LK Ronald
 * @see visita https://github.com/LK-Ronald
 */
public class Main {
    public static void main(String[] args) {
        CatalogoPeliculasImpl catalogo = new CatalogoPeliculasImpl();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.print("Bienvenido al catalogo de Peliculas"
                    + "\n1. Iniciar Catalogo peliculas"
                    + "\n2. Agregar pelicula"
                    + "\n3. Listar Peliculas"
                    + "\n4. Buscar Pelicula"
                    + "\n0. Salir"
                    + "\nElige una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                    catalogo.iniciarCatalogoPelicula();
                    System.out.println("Calatolo de peliculas iniciado");
                    break;

                case 2:
                    System.out.print("Nombre de la pelicula: ");
                    String nombrePelicula = scanner.nextLine();
                    catalogo.agregarPelicula(nombrePelicula);
                    System.out.println("Pelicula agregada!");
                    break;

                case 3:
                    catalogo.listarPeliculas();
                    break;

                case 4:
                    System.out.print("Ingrese el nomre de la pelicula: ");
                    String nombrePeliculaBuscar = scanner.nextLine();
                    catalogo.buscarPelicula(nombrePeliculaBuscar);
                    break;

                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
