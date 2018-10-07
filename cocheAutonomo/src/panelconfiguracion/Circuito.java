/** @file Circuito.java
 *  @brief Clase que permite guardar información de un circuito
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

/** @brief Librerías
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
	 * @brief Número de versión de la clase
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
	 * @param friccion Valor de fricción de la superficie
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
	 * @brief Método para obtener el valor de la variable nombre
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @brief Método para determinar el valor de la variable nombre
	 * @param nombre Nombre del vehículo
	 * @return void
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @brief Método para obtener el valor de la variable recta
	 * @return double
	 */
	public double getRecta() {
		return recta;
	}

	/**
	 * @brief Método para determinar el valor de la variable recta
	 * @param recta Longitud de la recta
	 * @return void
	 */
	public void setRecta(double recta) {
		this.recta = recta;
	}

	/**
	 * @brief Método para obtener el valor de la variable radio
	 * @return double
	 */
	public double getRadio() {
		return radio;
	}

	/**
	 * @brief Método para determinar el valor de la variable radio
	 * @param radio Radio de la curva
	 * @return void
	 */
	public void setRadio(double radio) {
		this.radio = radio;
	}

	/**
	 * @brief Método para obtener el valor de la variable anchoCarretera
	 * @return double
	 */
	public double getAnchoCarretera() {
		return anchoCarretera;
	}

	/**
	 * @brief Método para determinar el valor de la variable anchoCarretera
	 * @param anchoCarretera Ancho de la carretera
	 * @return void
	 */
	public void setAnchoCarretera(double anchoCarretera) {
		this.anchoCarretera = anchoCarretera;
	}

	/**
	 * @brief Método para obtener el valor de la variable fricción
	 * @return double
	 */
	public double getFriccion() {
		return friccion;
	}

	/**
	 * @brief Método para determinar el valor de la variable fricción
	 * @param friccion Fricción de la superficie
	 * @return void
	 */
	public void setFriccion(double friccion) {
		this.friccion = friccion;
	}

	/**
	 * @brief Método para obtener el valor de la variable distancia
	 * @return double
	 */
	public double getDistancia() {
		return distancia;
	}

	/**
	 * @brief Método para determinar el valor de la variable distancia
	 * @param distancia Distancia del recorrido en metros
	 * @return void
	 */
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	/**
	 * @brief Método para obtener el valor de la variable velocidadMaxima
	 * @return double
	 */
	public double getVelocidadMaxima() {
		return velocidadMaxima;
	}

	/**
	 * @brief Método para determinar el valor de la variable velocidadMaxima
	 * @param velocidadMaxima Velocidad máxima en Km/h para realizar el circuito sin derrapar
	 * @return void
	 */
	public void setVelocidadMaxima(double velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	/**
	 * @brief Método para obtener el valor de la variable tiempoRecorrido
	 * @return double
	 */
	public double getTiempoRecorrido() {
		return tiempoRecorrido;
	}

	/**
	 * @brief Método para determinar el valor de la variable tiempoRecorrido
	 * @param tiempoRecorrido Tiempo que tarda en dar una vuelta
	 * @return void
	 */
	public void setTiempoRecorrido(double tiempoRecorrido) {
		this.tiempoRecorrido = tiempoRecorrido;
	}

	/**
	 * @brief Método para determinar el valor de la variable simulacionRealizada
	 * @return void
	 */
	public boolean isSimulacionRealizada() {
		return simulacionRealizada;
	}

	/**
	 * @brief Método para determinar el valor de la variable simulacionRealizada
	 * @param simulacionRealizada Determina si el circuito se ha simulado o no
	 * @return void
	 */
	public void setSimulacionRealizada(boolean simulacionRealizada) {
		this.simulacionRealizada = simulacionRealizada;
	}

	/**
	 * @brief Método para obtener el valor del ArrayList listaPracticas
	 * @return List<Practica>
	 */
	public List<Practica> getListaPracticas() {
		return listaPracticas;
	}

	/**
	 * @brief Método para determinar el valor del ArrayList listaPracticas
	 * @param listaPracticas Lista de practicas realizadas en el circuito
	 * @return void
	 */
	public void setListaPracticas(List<Practica> listaPracticas) {
		this.listaPracticas = listaPracticas;
	}

}
