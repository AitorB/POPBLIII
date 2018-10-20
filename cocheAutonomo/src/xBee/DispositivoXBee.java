/** @file DispositivoXBee.java
 *  @brief Clase para gestionar el funcionamiento del módulo XBee
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

import javax.swing.JFrame;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeMessage;

import dialogos.DialogoOpcionesAlerta;
import interfaces.Observable;
import interfaces.Observer;

/**
 * @brief Clase DispositivoXBee
 */
public class DispositivoXBee implements Observable, Observer {
	/**
	 * @brief Atributos
	 */
	private static final String MAC_XBEE_REMOTA = "0013A20040004C0D";
	private static final int INDICADOR_TRAMA_UNO = 0;
	private static final int INDICADOR_TRAMA_DOS = 1;

	public static final int CIRCUITO = 0;
	public static final int RECTA = 64;
	public static final int CURVA = 128;
	public static final int PRUEBA = 192;
	public static final int SERVO = 8;
	public static final int MOTOR = 16;
	public static final int FRENOS = 24;
	public static final int DISTANCIA = 32;
	public static final int DERRAPE = 40;
	public static final int MOVIMIENTO = 48;
	public static final int ADELANTE = 4;
	public static final int ATRAS = 0;
	public static final int IZQUIERDA = 4;
	public static final int RECTO = 0;
	public static final int DERECHA = 0;
	public static final int INICIAR = 2;
	public static final int DETENER = 0;

	public static final int VUELTAS_RUEDA_PRACTICA = 64;
	public static final int OBSTACULO_PRACTICA = 32;
	public static final int FINALIZAR_PRACTICA = 16;
	
	private JFrame ventana;
	private XBeeDevice dispositivoLocal;
	private XDataListener listener;
	private RemoteXBeeDevice dispositivoRemoto;
	private XBeeMessage mensajeXBee;
	private boolean activado;
	private boolean errorEnvio;
	
	private Observer observer;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal
	 */
	public DispositivoXBee(JFrame ventana) {
		this.activado = true;
		this.ventana = ventana;
		
		establecerPuerto();
	}

	/**
	 * @brief Método para conectar la XBee al puerto COM asignado
	 * @return void
	 */
	public void establecerPuerto() {
		int numeroPuerto = 0;
		
		do {
			dispositivoLocal = new XBeeDevice("COM" + numeroPuerto, 9600);
			try {
				dispositivoLocal.open();
				anadirListener();
			} catch (XBeeException e) {
				numeroPuerto++;
				detenerXBee();
			}
		} while (!dispositivoLocal.isOpen() && numeroPuerto < 20);
		
		if (dispositivoLocal.isOpen()) {
			detenerXBee();
			dispositivoRemoto = new RemoteXBeeDevice(dispositivoLocal, new XBee64BitAddress(MAC_XBEE_REMOTA));
		} else {
			activado = false;
		}
	}

	/**
	 * @brief Método para añadir un listener al dispositivo XBee
	 * @return void
	 */
	private void anadirListener() {
		listener = new XDataListener();
		listener.addObserver(this);
		dispositivoLocal.addDataListener(listener);
	}
	
	/**
	 * @brief Método para iniciar la XBee
	 * @return void
	 */
	public void iniciarXBee() {
		if (dispositivoLocal.isOpen()) {
			detenerXBee();
		}
		
		try {
			dispositivoLocal.open();
			anadirListener();
		} catch (XBeeException e) {
			e.printStackTrace();
			detenerXBee();
		}
	}

	/**
	 * @brief Método para detener la XBee
	 * @return void
	 */
	public void detenerXBee() {
		if (dispositivoLocal.isOpen()) {
			dispositivoLocal.removeDataListener(listener);
			dispositivoLocal.close();
		}
	}

	/**
	 * @brief Método para transfomar un número decimal
	 * @param numero Número decimal (0-255) que se desea convertir a binario
	 * @return void
	 */
	public XBeeByte transformarNumero(int numero) {
		XBeeByte trama;	
		trama = new XBeeByte(numero);
		
		return trama;
	}

	/**
	 * @brief Método para enviar una trama de datos binarios
	 * @param trama Trama de datos en binario
	 * @return void
	 */
	public void enviarTrama(XBeeByte trama) {
		try {
			dispositivoLocal.sendData(dispositivoRemoto, trama.getDatos());
		} catch (XBeeException e) {
			if (!errorEnvio) {
				new DialogoOpcionesAlerta(ventana, "¡ERROR: No ha sido posible conectar con el dispositivo XBee remoto!", "ERROR");
				errorEnvio = true;
			}		
		}
	}

	/**
	 * @brief Método para enviar al coche las dimensiones del circuito
	 * @param tipoRecorrido Indica si es una recta o una curva
	 * @param numeroVueltasRueda Número de vueltas que tiene que dar la rueda para recorrer la recta o la curva
	 * @return void
	 */
	public void enviarDimensionesCircuito(int tipoRecorrido, int numeroVueltasRueda) {
		int primeros5bits = 0;
		int ultimos7bits = 0;
		int potencia = (int) Math.pow(2, 7);

		primeros5bits = numeroVueltasRueda / potencia;
		ultimos7bits = numeroVueltasRueda - (primeros5bits * potencia);

		enviarTrama(transformarNumero(tipoRecorrido + (primeros5bits * 2) + INDICADOR_TRAMA_UNO));
		enviarTrama(transformarNumero((ultimos7bits * 2) + INDICADOR_TRAMA_DOS));
	}

	/**
	 * @brief Método para enviar al coche los datos para realizar el circuito
	 * @param tipoAccion Indica si mover el servo o el motor
	 * @param direccion Indica la dirección en la que se va a mover el servo o el motor
	 * @param estado Iniciar o detener el movimiento
	 * @param porcentaje Valor porcentual para mover el servo o el motor
	 * @return void
	 */
	public void enviarDatosCircuito(int tipoAccion, int direccion, int estado, int porcentaje) {
		enviarTrama(transformarNumero(DispositivoXBee.CIRCUITO + tipoAccion + direccion + estado + INDICADOR_TRAMA_UNO));
		enviarTrama(transformarNumero((porcentaje * 2) + INDICADOR_TRAMA_DOS));

		this.errorEnvio = false;
	}
	
	/**
	 * @brief Método para enviar al coche los datos para realizar las diferentes pruebas
	 * @param tipoPrueba Indica que tipo de prueba se va a realizar
	 * @param direccion Indica la dirección en la que se va a mover el servo o el motor
	 * @param estado Iniciar o detener el movimiento
	 * @param valor Valor que varía en función del tipo de prueba
	 * @return void
	 */
	public void enviarDatosPrueba( int tipoPrueba, int direccion, int estado, int valor) {
		enviarTrama(transformarNumero(DispositivoXBee.PRUEBA + tipoPrueba + direccion + estado + INDICADOR_TRAMA_UNO));
		enviarTrama(transformarNumero((valor * 2) + INDICADOR_TRAMA_DOS));
		
		this.errorEnvio = false;
	}

	/**
	 * @brief Método para comprobar si el dispositivo local está activo
	 * @return boolean
	 */
	public boolean comprobarEstado() {
		return dispositivoLocal.isOpen();
	}
	
	/**
	 * @brief Método para detectar los datos enviados por el coche
	 * @param observable Referencia a XDataListener del dispositivo XBee
	 * @param objeto Mensaje XBee recibido
	 * @return void
	 */
	@Override
	public void update(Observable observable, Object objeto) {
		this.mensajeXBee = (XBeeMessage) objeto;
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

	/**
	 * @brief Método para obtener el valor del objeto dispositivoLocal
	 * @return XBeeDevice
	 */
	public XBeeDevice getDispositivoLocal() {
		return dispositivoLocal;
	}

	/**
	 * @brief Método para obtener el valor de la variable activado
	 * @return boolean
	 */
	public boolean getActivado() {
		return activado;
	}
	
	/**
	 * @brief Método para establecer el valor de la variable errorEnvio
	 * @return void
	 */
	public void setErrorEnvio(boolean estado) {
		this.errorEnvio = estado;
	}
	
}