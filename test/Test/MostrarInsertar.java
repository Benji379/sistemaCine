package Test;

import dao.Crud;
import java.util.Scanner;

/**
 *
 * @author Benji
 */
public class MostrarInsertar {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("\n PELICULAS\n");
        String datosMostrar[] = {"id", "nombre", "contraseña", "tipoUsuario"};
        String columModificar[] = {"nombre", "contraseña", "tipoUsuario"};
        Crud.consulta("usuarios", datosMostrar);
        
        
        System.out.print("Fila a modificar: ");
        int filaModificar = teclado.nextInt();
        System.out.println("");
        System.out.print("Nombre: ");
        String nombre = teclado.next();
        System.out.print("Contraseña: ");
        String contraseña = teclado.next();
        System.out.print("Tipo Usuario: ");
        String tipoUsuario = teclado.next();
        
        String datosModificar[] = {nombre, contraseña, tipoUsuario};
        
        Crud.modificarDatos("usuarios", columModificar, datosModificar, filaModificar);
    }
}
