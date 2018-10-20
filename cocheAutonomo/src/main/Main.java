/** @file Main.java
 *  @brief Clase principal del programa para gestionar el control remoto de un veh�culo aut�nomo
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete main
 */
package main;

/** @brief Librer�as
 */
import javax.swing.UIManager;

import principal.VentanaPrincipal;

/**
 * @brief Clase Main
 */
public class Main {
	/**
	 * @brief Atributos
	 */
	public static final String FICHERO_ORIGINAL = "archivos\\cocheAutonomo.dat";
	public static final String FICHERO_COPIA = "archivos\\cocheAutonomoBackUp";
	public static final int ANCHO_VENTANA = 1280;
	public static final int ALTO_VENTANA = 720;

	/**
	 * @brief Funci�n principal del programa que ejecuta el c�digo
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			LOGGER.log("context", e);
		}

		new VentanaPrincipal(true);
	}

}