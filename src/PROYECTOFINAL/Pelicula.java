/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROYECTOFINAL;

class Pelicula {
    private String nombre;
    private int duracion;
    private String genero;
    private double precio;

    public Pelicula(String nombre, int duracion, String genero, double precio) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.genero = genero;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Duración: " + duracion + " minutos, Género: " + genero + ", Precio: $" + precio;
    }
}
