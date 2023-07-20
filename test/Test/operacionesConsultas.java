package Test;

import dao.Crud;
import java.util.Scanner;

/**
 *
 * @author Benji
 */
public class operacionesConsultas {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String peliculas[] = {"id", "nombre", "duracion", "precio", "genero"};
        boolean salir = false;
        double acumulador = 0;
        while (salir == false) {
            System.out.println("\n      PELICULAS\n");
            Crud.consulta("peliculas", peliculas);
            
            System.out.print("Selecciona la pelicula: ");
            int op = teclado.nextInt();
            System.out.println("Precio: " + Crud.getConsulta("peliculas", "precio", op));
            acumulador += Double.parseDouble(Crud.getConsulta("peliculas", "precio", op));
            System.out.println("[1] Agregar");
            System.out.println("[2] Salir");
            int opcion = teclado.nextInt();
            salir = opcion == 2;
        }
        System.out.println("El total es: " + acumulador);
    }
}
