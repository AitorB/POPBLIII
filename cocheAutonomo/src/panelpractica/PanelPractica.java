/** @file PanelPractica.java
 *  @brief Clase para crear un panel que permite realizar una prueba real del circuito seleccionado
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete PanelPractica
 */
package panelpractica;

/** @brief Librer�as
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import panelconfiguracion.Circuito;
import panelconfiguracion.Coche;
import xbee.DispositivoXBee;

/**
 * @brief Clase PanelPractica
 */
public class PanelPractica extends JPanel {
	/**
	 * @brief N�mero de versi�n de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private JFrame ventana;
	private PanelDatosSimulacion panelDatosSimulacion;
	private PanelDatosTabla panelDatosTabla;
	private PanelDatosTiempoReal panelDatosTiempoReal;
	private JLabel nombreCircuito;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de la que se lanza el di�logo
	 */
	public PanelPractica(JFrame ventana) {
		super(new BorderLayout(0, 10));
		this.ventana = ventana;
		
		this.add(crearPanelTitulo(), BorderLayout.NORTH);
		this.add(crearPanelDatos(), BorderLayout.CENTER);
	}

	/**
	 * @brief M�todo del panel t�tulo: contiene un t�tulo
	 * @return Component
	 */
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		nombreCircuito = new JLabel();
		nombreCircuito.setFont(new Font("Arial", Font.BOLD, 20));

		panel.add(nombreCircuito);

		return panel;
	}

	/**
	 * @brief M�todo del panel datos: muestra una tabla con informaci�n de los circuitos
	 * @return Component
	 */
	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new BorderLayout(0, 10));

		panelDatosSimulacion = new PanelDatosSimulacion();
		panelDatosTabla = new PanelDatosTabla(ventana);
		panelDatosTiempoReal = new PanelDatosTiempoReal(ventana, panelDatosTabla);

		panel.add(panelDatosSimulacion, BorderLayout.NORTH);
		panel.add(panelDatosTabla, BorderLayout.CENTER);
		panel.add(panelDatosTiempoReal, BorderLayout.SOUTH);
		
		return panel;
	}

	/**
	 * @brief M�todo para determinar el valor del objeto coche
	 * @param coche Datos del coche a utilizar por el usuario
	 * @return void
	 */
	public void setCoche(Coche coche) {
		panelDatosSimulacion.setCoche(coche);
		panelDatosTiempoReal.setCoche(coche);
	}

	/**
	 * @brief M�todo para determinar el valor del objeto circuito
	 * @param circuito Datos del circuito seleccionado
	 * @return void
	 */
	public void setCircuito(Circuito circuito) {
		panelDatosSimulacion.setCircuito(circuito);
		panelDatosTabla.setListaModelo(circuito.getListaPracticas());
		panelDatosTiempoReal.setCircuito(circuito);
		
		nombreCircuito.setText("Pr�ctica real del circuito: " + circuito.getNombre());
	}

	/**
	 * @brief M�todo para obtener el valor del panelDatosSimulacion
	 * @return PanelDatosSimulacion
	 */
	public PanelDatosSimulacion getPanelDatosSimulacion() {
		return panelDatosSimulacion;
	}
	
	/**
	 * @brief M�todo para determinar el valor de la variable dispositivoXBee
	 * @param dispositivoXBee Dispositivo XBee utilizando para realizar la comunicaci�n con el coche
	 * @return void
	 */
	public void setDispositivoXBee(DispositivoXBee dispositivoXBee) {
		panelDatosTiempoReal.setDispositivoXBee(dispositivoXBee);
	}

}