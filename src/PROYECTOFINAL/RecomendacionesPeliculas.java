/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROYECTOFINAL;

import java.util.LinkedList;

/**
 *
 * @author ASUS
 */
public class RecomendacionesPeliculas {
 
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
}