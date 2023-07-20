/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROYECTOFINAL;
class Boleto {
    private Pelicula pelicula;
    private int cantidad;

    public Boleto(Pelicula pelicula, int cantidad) {
        this.pelicula = pelicula;
        this.cantidad = cantidad;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Pelicula: " + pelicula.getNombre() + ", Cantidad: " + cantidad + ", Precio total: $" + (cantidad * pelicula.getPrecio());
    }
}
