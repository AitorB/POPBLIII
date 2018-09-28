/** @file XDataListener.java
 *  @brief Clase que permite detectar los mensajes recibidos en la XBee
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete xBee
 */
package xBee;

/** @brief Librerías
 */

import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBeeMessage;

import interfaces.Observable;
import interfaces.Observer;

/**
 * @brief Clase XDataListener
 */
public class XDataListener implements IDataReceiveListener, Observable {
	/**
	 * @brief Atributos
	 */
	private XBeeMessage mensajeXBee;
	
	private Observer observer;

	/**
	 * @brief Constructor
	 */
	public XDataListener() {
		this.mensajeXBee = null;
	}
	
	/**
	 * @brief Método para detectar el mensaje recibido por el coche
	 * @param mensajeXBee Mensaje XBee enviado por el coche
	 * @return void
	 */
	@Override
	public void dataReceived(XBeeMessage mensajeXBee) {
		this.mensajeXBee = mensajeXBee;
		this.notifyObservers();
	}

	/**
	 * @brief Método que permite añadir un observador
	 * @param observer Clase que observa si hay cambios
	 * @return void
	 */
	@Override
	public void addObserver(Observer observer) {
		this.observer = observer;
	}

	/**
	 * @brief Método que permite borrar un observador
	 * @param observer Clase que observa si hay cambios
	 * @return void
	 */
	@Override
	public void removeObserver(Observer observer) {
		this.observer = null;
	}

	/**
	 * @brief Método que permite notificar cambios a los observadores
	 * @return void
	 */
	@Override
	public void notifyObservers() {
		this.observer.update(this, mensajeXBee);
	}

}