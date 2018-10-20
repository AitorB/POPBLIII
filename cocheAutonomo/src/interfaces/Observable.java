/**
 * @file Observable.java
 * @brief Interfaz para implementar un objeto observable que informa de sus cambios a un observador
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete interfaces
 */

/** @brief Paquete interfaces
 */
package interfaces;

/**
 * @brief Clase Observable
 */
public interface Observable {
    /**
     * @brief Metodo que permite anadir un observador
     * @param observer Clase que observa si hay cambios
     * @return void
     */
    void addObserver(Observer observer);

    /**
     * @brief Metodo que permite borrar un observador
     * @param observer Clase que observa si hay cambios
     * @return void
     */
    void removeObserver(Observer observer);

    /**
     * @brief Metodo que permite notificar cambios a los observadores
     * @return void
     */
    void notifyObservers();
}