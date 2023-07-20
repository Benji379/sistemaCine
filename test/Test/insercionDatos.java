package Test;

import static dao.Crud.insertarDatos;
import java.util.Scanner;

/**
 *
 * @author Benji
 */
public class insercionDatos {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String columnaInsertar[] = {"nombre", "duracion", "precio", "genero"};
        System.out.println("\n          AGREGAR PELICULA\n");
        System.out.println("Datos: ");
        System.out.print("Nombre: ");
        String nombre = teclado.next();
        System.out.print("Duracion: ");
        String duracion = teclado.next();
        System.out.print("Precio:");
        double precio = teclado.nextDouble();
        System.out.print("Genero:");
        String genero = teclado.next();
        String datosInsertar[] = {nombre, duracion, String.valueOf(precio), genero};

        insertarDatos("peliculas", columnaInsertar, datosInsertar);

    }
}
