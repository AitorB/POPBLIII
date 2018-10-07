/** @file Circuito.java
 *  @brief Clase que permite guardar informaci�n de un circuito
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete panelConfiguracion
 */
package panelconfiguracion;

/** @brief Librer�as
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import panelpractica.Practica;

/**
 * @brief Clase Circuito
 */
public class Circuito implements Serializable {
	/**
	 * @brief N�mero de versi�n de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private String nombre;
	private double recta;
	private double radio;
	private double anchoCarretera;
	private double friccion;
	private double distancia;
	private double velocidadMaxima;
	private double tiempoRecorrido;
	private boolean simulacionRealizada;
	private List<Practica> listaPracticas;

	/**
	 * @brief Constructor
	 * @param nombre Nombre del circuito
	 * @param recta Longitud de la recta
	 * @param radio Valor del radio
	 * @param anchoCarretera Ancho de la carretera
	 * @param friccion Valor de fricci�n de la superficie
	 */
	public Circuito(String nombre, double recta, double radio, double anchoCarretera, double friccion) {
		this.nombre = nombre;
		this.recta = recta;
		this.radio = radio;
		this.anchoCarretera = anchoCarretera;
		this.friccion = friccion;
		this.simulacionRealizada = false;	
		this.listaPracticas = new ArrayList<>();
	}

	/**
	 * @brief M�todo para obtener el valor de la variable nombre
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable nombre
	 * @param nombre Nombre del veh�culo
	 * @return void
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @brief M�todo para obtener el valor de la variable recta
	 * @return double
	 */
	public double getRecta() {
		return recta;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable recta
	 * @param recta Longitud de la recta
	 * @return void
	 */
	public void setRecta(double recta) {
		this.recta = recta;
	}

	/**
	 * @brief M�todo para obtener el valor de la variable radio
	 * @return double
	 */
	public double getRadio() {
		return radio;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable radio
	 * @param radio Radio de la curva
	 * @return void
	 */
	public void setRadio(double radio) {
		this.radio = radio;
	}

	/**
	 * @brief M�todo para obtener el valor de la variable anchoCarretera
	 * @return double
	 */
	public double getAnchoCarretera() {
		return anchoCarretera;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable anchoCarretera
	 * @param anchoCarretera Ancho de la carretera
	 * @return void
	 */
	public void setAnchoCarretera(double anchoCarretera) {
		this.anchoCarretera = anchoCarretera;
	}

	/**
	 * @brief M�todo para obtener el valor de la variable fricci�n
	 * @return double
	 */
	public double getFriccion() {
		return friccion;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable fricci�n
	 * @param friccion Fricci�n de la superficie
	 * @return void
	 */
	public void setFriccion(double friccion) {
		this.friccion = friccion;
	}

	/**
	 * @brief M�todo para obtener el valor de la variable distancia
	 * @return double
	 */
	public double getDistancia() {
		return distancia;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable distancia
	 * @param distancia Distancia del recorrido en metros
	 * @return void
	 */
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	/**
	 * @brief M�todo para obtener el valor de la variable velocidadMaxima
	 * @return double
	 */
	public double getVelocidadMaxima() {
		return velocidadMaxima;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable velocidadMaxima
	 * @param velocidadMaxima Velocidad m�xima en Km/h para realizar el circuito sin derrapar
	 * @return void
	 */
	public void setVelocidadMaxima(double velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	/**
	 * @brief M�todo para obtener el valor de la variable tiempoRecorrido
	 * @return double
	 */
	public double getTiempoRecorrido() {
		return tiempoRecorrido;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable tiempoRecorrido
	 * @param tiempoRecorrido Tiempo que tarda en dar una vuelta
	 * @return void
	 */
	public void setTiempoRecorrido(double tiempoRecorrido) {
		this.tiempoRecorrido = tiempoRecorrido;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable simulacionRealizada
	 * @return void
	 */
	public boolean isSimulacionRealizada() {
		return simulacionRealizada;
	}

	/**
	 * @brief M�todo para determinar el valor de la variable simulacionRealizada
	 * @param simulacionRealizada Determina si el circuito se ha simulado o no
	 * @return void
	 */
	public void setSimulacionRealizada(boolean simulacionRealizada) {
		this.simulacionRealizada = simulacionRealizada;
	}

	/**
	 * @brief M�todo para obtener el valor del ArrayList listaPracticas
	 * @return List<Practica>
	 */
	public List<Practica> getListaPracticas() {
		return listaPracticas;
	}

	/**
	 * @brief M�todo para determinar el valor del ArrayList listaPracticas
	 * @param listaPracticas Lista de practicas realizadas en el circuito
	 * @return void
	 */
	public void setListaPracticas(List<Practica> listaPracticas) {
		this.listaPracticas = listaPracticas;
	}

}
