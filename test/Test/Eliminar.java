package Test;

import dao.Crud;
import java.util.Scanner;

/**
 *
 * @author Benji
 */
public class Eliminar {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String mostrarDatos[] = {"id", "nombre", "duracion", "precio", "genero"};

        System.out.println("\nPELICULAS\n");
        Crud.consulta("peliculas", mostrarDatos);
        System.out.print("Fila a eliminar: ");
        int fila = teclado.nextInt();
        Crud.eliminarDatos("peliculas", fila);
        
        
        
        System.out.println("\nPELICULAS\n");
        Crud.consulta("peliculas", mostrarDatos);
    }
}
