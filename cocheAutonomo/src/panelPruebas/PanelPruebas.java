/** @file PanelPruebas.java
 *  @brief Clase para crear un panel que permite realizar diferentes pruebas con el coche
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete panelPruebas
 */
package panelPruebas;

/** @brief Librerías
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import panelConfiguracion.Coche;
import xBee.DispositivoXBee;

/**
 * @brief Clase PanelPruebas
 */
public class PanelPruebas extends JPanel {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private JFrame ventana;
	private PanelServo panelServo;
	private PanelMotor panelMotor;
	private PanelFreno panelFreno;
	private PanelDistancia panelDistancia;
	private PanelDerrape panelDerrape;
	private PanelMovimiento panelMovimiento;
	
	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal
	 */
	public PanelPruebas(JFrame ventana) {
		super(new BorderLayout());

		this.ventana = ventana;
		
		this.add(crearPanelTitulo(), BorderLayout.NORTH);
		this.add(crearPanelPruebas(), BorderLayout.CENTER);
	}

	/**
	 * @brief Método del panel título: contiene un título
	 * @return Component
	 */
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createEtchedBorder());

		JLabel titulo = new JLabel("Panel de pruebas: solo desarrolladores");
		titulo.setFont(new Font("Arial", Font.BOLD, 20));

		panel.add(titulo);

		return panel;
	}

	/**
	 * @brief Método del panel pruebas: contiene los paneles para mostras la diferentes pruebas
	 * @return Component
	 */
	private Component crearPanelPruebas() {
		JPanel panel = new JPanel(new GridLayout(6, 1, 0, 10));

		panelServo = new PanelServo(ventana, "PRUEBA DE SERVO");
		panelMotor = new PanelMotor(ventana, "PRUEBA DE MOTOR");
		panelFreno = new PanelFreno(ventana, "PRUEBA DE FRENADO");
		panelDistancia = new PanelDistancia(ventana, "PRUEBA DE DISTANCIA");
		panelDerrape = new PanelDerrape(ventana, "PRUEBA DE DERRAPE");
		panelMovimiento = new PanelMovimiento("PRUEBA DE MOVIMIENTO");
		
		panel.add(panelServo);
		panel.add(panelMotor);
		panel.add(panelFreno);
		panel.add(panelDistancia);
		panel.add(panelDerrape);
		panel.add(panelMovimiento);

		return panel;
	}

	/**
	 * @brief Método para determinar el valor del objeto coche
	 * @param coche Coche seleccionado por el usuario
	 * @return void
	 */
	public void setCoche(Coche coche) {
		panelDistancia.setCoche(coche);
	}
	
	/**
	 * @brief Método para determinar el valor de la variable dispositivoXBee
	 * @param dispositivoXBee Dispositivo XBee utilizando para realizar la comunicación con el coche
	 * @return void
	 */
	public void setDispositivoXBee(DispositivoXBee dispositivoXBee) {
		panelServo.setDispositivoXBee(dispositivoXBee);
		panelMotor.setDispositivoXBee(dispositivoXBee);
		panelFreno.setDispositivoXBee(dispositivoXBee);
		panelDistancia.setDispositivoXBee(dispositivoXBee);
		panelDerrape.setDispositivoXBee(dispositivoXBee);
		panelMovimiento.setDispositivoXBee(dispositivoXBee);
	}
	
}