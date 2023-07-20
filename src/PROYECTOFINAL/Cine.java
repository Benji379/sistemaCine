/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROYECTOFINAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

final class Cine<T extends Usuario, U extends Pelicula, V extends Boleto> {

    private final Map<String, T> usuarios;
    private final List<U> peliculas;
    private final List<V> boletos;
    
    public Cine() {
        usuarios = new HashMap<>();
        peliculas = new ArrayList<>();
        boletos = new ArrayList<>();

        usuarios.put("JohanAdmin", (T) new Administrador("JohanAdmin", "Admin1"));
        usuarios.put("Brandon", (T) new Comprador("Brandon", "123456"));
        usuarios.put("Ronal", (T) new Comprador("Ronal", "654321"));

        peliculas.add((U) new Pelicula("Tor", 120, "Accion", 10.0));
        peliculas.add((U) new Pelicula("Chavito", 90, "Comedia", 8.5));
        peliculas.add((U) new Pelicula("Sultan", 150, "Drama", 9.0));
        ordenarPeliculasPorDuracion(); // Llamado al método de ordenamiento
        U peliculaEncontrada = buscarPeliculaPorGenero("Comedia"); // Llamado al método de búsqueda
        if (peliculaEncontrada != null) {
            System.out.println("Pelicula encontrada: " + peliculaEncontrada);
        } else {
            System.out.println("No se encontró una película con ese género.");
        }
    }

    // Resto de los métodos existentes en la clase
    public void ordenarPeliculasPorDuracion() {
        Collections.sort(peliculas, Comparator.comparingInt(U::getDuracion));

        System.out.println("Películas ordenadas por duración:");
        peliculas.forEach((pelicula) -> {
            System.out.println(pelicula);
        });
    }

    public U buscarPeliculaPorGenero(String genero) {
        Optional<U> peliculaEncontrada = peliculas.stream()
                .filter(pelicula -> pelicula.getGenero().equalsIgnoreCase(genero))
                .findFirst();
        return peliculaEncontrada.orElse(null);
    }

    // Resto de los métodos existentes en la clase
    private Usuario validarCredenciales(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario != null && usuario.autenticar(nombreUsuario, contrasena)) {
            return usuario;
        }
        return null;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("================================================");
            System.out.println("Admin Usuario = JohanAdmin  Password = Admin1");
            System.out.println("Comprador Usuario = Brandon  Password = 123456");
            System.out.println("Comprador Usuario = Ronal  Password = 654321");
            System.out.println("================================================");
            System.out.println("¡Bienvenido al cine!");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.println("Ingrese su opción: ");

            int opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    iniciarSesion(scanner);
                    break;
                case 2:
                    System.out.println("¡Gracias por visitarnos!");
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void iniciarSesion(Scanner scanner) {
        System.out.println("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.next();

        System.out.println("Ingrese su contraseña: ");
        String contrasena = scanner.next();

        Usuario usuario = validarCredenciales(nombreUsuario, contrasena);

        if (usuario != null) {
            if (usuario instanceof Administrador) {
                mostrarMenuAdministrador(scanner);
            } else if (usuario instanceof Comprador) {
                mostrarMenuComprador(scanner);
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    private void mostrarMenuAdministrador(Scanner scanner) {
        while (true) {
            System.out.println("Menú Administrador");
            System.out.println("1. Agregar película");
            System.out.println("2. Ver lista de películas");
            System.out.println("3. Volver al menú principal");
            System.out.println("Ingrese su opción: ");

            int opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    agregarPelicula(scanner);
                    break;
                case 2:
                    mostrarPeliculas();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void agregarPelicula(Scanner scanner) {
        System.out.println("Ingrese el nombre de la película: ");
        String nombre = scanner.next();

        System.out.println("Ingrese la duración en minutos: ");
        int duracion = obtenerEntero(scanner);

        System.out.println("Ingrese el género de la película: ");
        String genero = scanner.next();

        System.out.println("Ingrese el precio de la película: ");
        double precio = obtenerDouble(scanner);

        Pelicula pelicula = new Pelicula(nombre, duracion, genero, precio);
        peliculas.add((U) pelicula);

        System.out.println("La película ha sido agregada con éxito.");
    }

    private void mostrarPeliculas() {
        System.out.println("Lista de películas:");
        peliculas.forEach((pelicula) -> {
            System.out.println(pelicula);
        });
    }

    private void mostrarMenuComprador(Scanner scanner) {
        while (true) {
            System.out.println("Menú Comprador");
            System.out.println("1. Comprar boletos");
            System.out.println("2. Ver boletos comprados");
            System.out.println("3. Recomendaciones");
            System.out.println("4. Pagar boletos");
            System.out.println("5. Volver al menú principal");
            System.out.println("Ingrese su opción: ");

            int opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    comprarBoletos(scanner);
                    break;
                case 2:
                    verBoletosComprados();
                    break;
                case 3:
                    RecomendacionesPeliculas(scanner);
                    break;
                case 4:
                    pagarBoletos(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void comprarBoletos(Scanner scanner) {
        mostrarPeliculas();
        System.out.println("Ingrese el nombre de la película que desea ver: ");
        String nombrePelicula = scanner.next();

        Pelicula pelicula = buscarPelicula(nombrePelicula);

        if (pelicula != null) {
            System.out.println("Ingrese la cantidad de boletos que desea comprar: ");
            int cantidad = obtenerEntero(scanner);

            Boleto boleto = new Boleto(pelicula, cantidad);
            boletos.add((V) boleto);

            System.out.println("Boletos comprados exitosamente.");
        } else {
            System.out.println("La película no existe. Intente nuevamente.");
        }
    }

    private Pelicula buscarPelicula(String nombrePelicula) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getNombre().equalsIgnoreCase(nombrePelicula)) {
                return pelicula;
            }
        }
        return null;
    }

    private void verBoletosComprados() {
        System.out.println("Boletos comprados:");
        boletos.forEach((boleto) -> {
            System.out.println(boleto);
        });
    }

    private void pagarBoletos(Scanner scanner) {
        double total = calcularTotalBoletos();

        System.out.println("Total a pagar: $" + total);
        System.out.println("Ingrese su método de pago Visa o MasterCARD ");
        String metodoPago = scanner.next();
        
        System.out.println("Ingrese su número de tarjeta: ");
        String numeroTarjeta = scanner.next();

        System.out.println("Boletos pagados exitosamente.");
        imprimirBoleta();
    }

    private double calcularTotalBoletos() {
        double total = 0;
        for (Boleto boleto : boletos) {
            total += boleto.getCantidad() * boleto.getPelicula().getPrecio();
        }
        return total;
    }

    private void imprimirBoleta() {
        System.out.println("Boleta:");
        System.out.println("==========");
        boletos.forEach((boleto) -> {
            System.out.println(boleto);
        });
        System.out.println("==========");
    }

    private int obtenerEntero(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
                scanner.next(); // Limpiar el búfer del escáner
            }
        }
    }

    private double obtenerDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intente nuevamente.");
                scanner.next(); // Limpiar el búfer del escáner
            }
        }
    }

    private void RecomendacionesPeliculas(Scanner scanner) {

        String opcion;
        LinkedList<String> recomendaciones = new LinkedList<String>();

        do {
            System.out.println("------Menu------");
            System.out.println("1. Mostrar recomendaciones");
            System.out.println("2. Agregar recomendación");
            System.out.println("3. Eliminar recomendación");
            System.out.println("4. Salir");
            System.out.println("----------------");
            System.out.println("Ingrese opción: ");
            opcion = scanner.next();

            switch (opcion) {
                case "1":
                    mostrarRecomendaciones(recomendaciones);
                    break;
                case "2":
                    System.out.println("Ingrese la recomendación: ");
                    System.out.println("Que contenga el sgte formato: 'nombrepelicula':'valoración 1-10' ");
                    String recomendacion = scanner.next();
                    agregarRecomendacion(recomendaciones, recomendacion);
                    break;
                case "3":
                    System.out.println("Ingrese la recomendación a eliminar: ");
                    String recomendacionEliminar = scanner.next();
                    eliminarRecomendacion(recomendaciones, recomendacionEliminar);
                    break;
                case "4":
                    System.out.println("¡Gracias por utilizar el sistema de recomendaciones!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (!opcion.equals("4"));
    }

    public static void agregarRecomendacion(LinkedList<String> recomendaciones, String recomendacion) {
        recomendaciones.add(recomendacion);
        System.out.println("Recomendación agregada exitosamente.");
    }

    public static void mostrarRecomendaciones(LinkedList<String> recomendaciones) {
        if (recomendaciones.isEmpty()) {
            System.out.println("No hay recomendaciones disponibles.");
        } else {
            System.out.println("Recomendaciones:");
            for (String recomendacion : recomendaciones) {
                System.out.println(recomendacion);
            }
        }
    }

    public static void eliminarRecomendacion(LinkedList<String> recomendaciones, String recomendacion) {
        if (recomendaciones.remove(recomendacion)) {
            System.out.println("Recomendación eliminada exitosamente.");
        } else {
            System.out.println("No se encontró la recomendación especificada.");
        }
    }

    private static Cine instance;

    public static Cine getInstance() {
        if (instance == null) {
            instance = new Cine();
        }
        return instance;
    }

    public class UsuarioFactory {

        public Usuario crearUsuario(String tipo, String nombreUsuario, String contrasena) {
            if (tipo.equalsIgnoreCase("Administrador")) {
                return new Administrador(nombreUsuario, contrasena);
            } else if (tipo.equalsIgnoreCase("Comprador")) {
                return new Comprador(nombreUsuario, contrasena);
            } else {
                throw new IllegalArgumentException("Tipo de usuario inválido: " + tipo);
            }
        }
    }
}
