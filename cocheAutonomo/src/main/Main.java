/**
 * @file Main.java
 * @brief Clase principal del programa para gestionar el control remoto de un vehículo autónomo
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete main
 */

/** @brief Paquete main
 */
package main;

/** @brief Librerías
 */

import principal.VentanaPrincipal;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief Clase Main
 */
public class Main {
    /**
     * @brief Atributos
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static final String FICHERO_ORIGINAL = "archivos\\cocheAutonomo.dat";
    public static final String FICHERO_COPIA = "archivos\\cocheAutonomoBackUp";
    public static final int ANCHO_VENTANA = 1280;
    public static final int ALTO_VENTANA = 720;

    /**
     * @brief Función principal del programa que ejecuta el código
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        new VentanaPrincipal(true);
    }

}