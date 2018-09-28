/** @file Observable.java
 *  @brief Interfaz para implementar un objeto observable que informa de sus cambios a un observador
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete interfaces
 */
package interfaces;

/**
 * @brief Clase Observable
 */
public interface Observable {
	/**
	 * @brief Método que permite añadir un observador
	 * @param observer Clase que observa si hay cambios
	 * @return void
	 */
	public void addObserver(Observer observer);

	/**
	 * @brief Método que permite borrar un observador
	 * @param observer Clase que observa si hay cambios
	 * @return void
	 */
	public void removeObserver(Observer observer);

	/**
	 * @brief Método que permite notificar cambios a los observadores
	 * @return void
	 */
	public void notifyObservers();

}