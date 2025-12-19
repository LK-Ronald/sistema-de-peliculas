package mx.com.gm.peliculas.infraestructura.datos.archivo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import mx.com.gm.peliculas.dominio.excepciones.*;
import mx.com.gm.peliculas.dominio.modelo.Pelicula;
import mx.com.gm.peliculas.infraestructura.datos.IAccesoDatos;

public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombre) throws LecturaDatosEx {
        File archivo = new File(nombre);
        List<Pelicula> peliculas = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = br.readLine();
            while (linea != null) {
                peliculas.add(new Pelicula(linea));
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al listar peliculas: " + ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(archivo, anexar));
            pw.println(pelicula.getNombre());
            pw.close();
            System.out.println("Se ha escrito la pelicula: " + pelicula.getNombre());
        } catch (IOException e) {
            e.printStackTrace(System.out);
            throw new EscrituraDatosEx("Excepcion al escribir peliculas: " + e.getMessage());
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        String resultado = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = br.readLine();
            int indice = 1;
            while (linea != null) {
                if (linea != null && linea.equalsIgnoreCase(buscar)) {
                    resultado = "Pelicula " + linea + " encontrada en el indice " + indice;
                    break;
                }
                linea = br.readLine();
                indice++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al buscar peliculas: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al buscar peliculas: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(archivo));
            pw.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException e) {
            e.printStackTrace(System.out);
            throw new AccesoDatosEx("Excepcion al crear archivo: " + e.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Se ha borrado el archivo");
            } else {
                throw new AccesoDatosEx("No se pudo borrar el archivo");
            }
        }
    }
}
