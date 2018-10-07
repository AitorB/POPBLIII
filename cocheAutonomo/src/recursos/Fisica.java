/** @file Fisica.java
 *  @brief Clase para realizar cálculos físicos
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete recursos
 */
package recursos;

import panelconfiguracion.Circuito;
import panelconfiguracion.Coche;

/**
 * @brief Clase Fisica
 */
public class Fisica {
	/**
	 * @brief Atributos
	 */
	private final static double GRAVEDAD = 9.8;

	/**
	 * @brief Constructor
	 */
	public Fisica() {}

	/**
	 * @brief Método estático de clase para la distancia total del recorrido
	 * @param circuito Circuito seleccionado por el usuario
	 * @return void
	 */
	public static void calcularDistancia(Circuito circuito) {
		circuito.setDistancia((2 * circuito.getRecta()) + (2 * Math.PI * circuito.getRadio()));
	}
	
	/**
	 * @brief Método estático de clase para calcular la velocidad del coche en km/h
	 * @param circuito Circuito seleccionado por el usuario
	 * @return void
	 */
	public static void calcularVelocidadMaxima(Circuito circuito) {
		circuito.setVelocidadMaxima(Math.sqrt(circuito.getRadio() * circuito.getFriccion() * GRAVEDAD) * 3.6);
	}

	/**
	 * @brief Método estático de clase para calcular el número de vueltas de la rueda para completar la recta
	 * @param circuito Circuito seleccionado por el usuario
	 * @param coche Coche seleccionado por el usuario
	 * @return void
	 */
	public static void calcularVueltasRecta(Circuito circuito, Coche coche) {
		coche.setNumeroVueltasRecta((int) Math.round(circuito.getRecta() / (coche.getDiametroRueda() * 0.01 * Math.PI)));
	}
	
	/**
	 * @brief Método estático de clase para calcular el número de vueltas de la rueda para completar la curva
	 * @param circuito Circuito seleccionado por el usuario
	 * @param coche Coche seleccionado por el usuario
	 * @return void
	 */
	public static void calcularVueltasCurva(Circuito circuito, Coche coche) {
		coche.setNumeroVueltasCurva((int) Math.round((Math.PI * circuito.getRadio()) / (coche.getDiametroRueda() * 0.01 * Math.PI)));
	}

	/**
	 * @brief Método estático de clase para calcular el número de vueltas de la rueda para recorrer todo el circuito
	 * @param circuito Circuito seleccionado por el usuario
	 * @param coche Coche seleccionado por el usuario
	 * @return int
	 */
	public static int calcularVueltasTotal(Circuito circuito, Coche coche) {
		return ((coche.getNumeroVueltasRecta() * 2) + (coche.getNumeroVueltasCurva() * 2));
	}
	
	/**
	 * @brief Método estático de clase para calcular el número de vueltas de un tramo indicado
	 * @param distancia Indica la distancia en metros del tramo
	 * @param coche Coche seleccionado por el usuario
	 * @return int
	 */
	public static int calcularVueltasTramo(int distancia, Coche coche) {
		return ((int) Math.round((Math.PI * distancia) / (coche.getDiametroRueda() * 0.01 * Math.PI)));
	}
	
	/**
	 * @brief Método estático de clase para calcular el tiempo en dar una vuelta al circuito
	 * @param circuito Circuito seleccionado por el usuario
	 * @return void
	 */
	public static void calcularTiempo(Circuito circuito) {
		circuito.setTiempoRecorrido(
				((2 * circuito.getRecta()) + (2 * Math.PI * circuito.getRadio())) / (circuito.getVelocidadMaxima() / 3.6));
	}
	
	/**
	 * @brief Método estático de clase para calcular la velocidad media del coche
	 * @param distancia Distancia recorrida en metros
	 * @param tiempo Tiempo transcurrido en segundos
	 * @return double
	 */
	public static double calcularVelocidadMedia(double distancia, double tiempo) {
		return ((distancia / tiempo) * 3.6);
	}
	
	/**
	 * @brief Método estático de clase para calcular el movimiento del servo según el ángulo de giro de las ruedas en la curva
	 * @param circuito Circuito seleccionado por el usuario
	 * @param coche Coche seleccionado por el usuario
	 * @return int
	 */
	public static int calcularAnguloGiro(Circuito circuito, Coche coche) {
		double anguloGiro = (Math.asin((coche.getDistanciaEjes() * 0.01) / circuito.getRadio())) * (180 / Math.PI);

		return (int) Math.round((anguloGiro * 100 ) / 45);
	}
	
}