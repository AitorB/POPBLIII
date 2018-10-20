/**
 * @file Observer.java
 * @brief Interfaz para implementar un objeto observador que observa cambios en otra clase observable
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
 * @brief Clase Observer
 */
public interface Observer {
    /**
     * @brief Metodo que se ejecuta tras observar un cambio en una clase observable
     * @param observable Clase que es observada para saber cuando cambia
     * @param objeto Objeto que cambia
     * @return void
     */
    void update(Observable observable, Object objeto);
}