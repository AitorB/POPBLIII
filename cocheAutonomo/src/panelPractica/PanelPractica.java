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
package panelPractica;

/** @brief Librerías
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import panelConfiguracion.Circuito;
import panelConfiguracion.Coche;
import xBee.DispositivoXBee;

/**
 * @brief Clase PanelPractica
 */
public class PanelPractica extends JPanel {
	/**
	 * @brief Número de versión de la clase
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
	 * @param ventana Referencia a la ventana de la que se lanza el diálogo
	 */
	public PanelPractica(JFrame ventana) {
		super(new BorderLayout(0, 10));
		this.ventana = ventana;
		
		this.add(crearPanelTitulo(), BorderLayout.NORTH);
		this.add(crearPanelDatos(), BorderLayout.CENTER);
	}

	/**
	 * @brief Método del panel título: contiene un título
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
	 * @brief Método del panel datos: muestra una tabla con información de los circuitos
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
	 * @brief Método para determinar el valor del objeto coche
	 * @param coche Datos del coche a utilizar por el usuario
	 * @return void
	 */
	public void setCoche(Coche coche) {
		panelDatosSimulacion.setCoche(coche);
		panelDatosTiempoReal.setCoche(coche);
	}

	/**
	 * @brief Método para determinar el valor del objeto circuito
	 * @param circuito Datos del circuito seleccionado
	 * @return void
	 */
	public void setCircuito(Circuito circuito) {
		panelDatosSimulacion.setCircuito(circuito);
		panelDatosTabla.setListaModelo(circuito.getListaPracticas());
		panelDatosTiempoReal.setCircuito(circuito);
		
		nombreCircuito.setText("Práctica real del circuito: " + circuito.getNombre());
	}

	/**
	 * @brief Método para obtener el valor del panelDatosSimulacion
	 * @return PanelDatosSimulacion
	 */
	public PanelDatosSimulacion getPanelDatosSimulacion() {
		return panelDatosSimulacion;
	}
	
	/**
	 * @brief Método para determinar el valor de la variable dispositivoXBee
	 * @param dispositivoXBee Dispositivo XBee utilizando para realizar la comunicación con el coche
	 * @return void
	 */
	public void setDispositivoXBee(DispositivoXBee dispositivoXBee) {
		panelDatosTiempoReal.setDispositivoXBee(dispositivoXBee);
	}

}