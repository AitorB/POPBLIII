/** @file PanelDistancia.java
 *  @brief Clase para crear un panel que permite visualizar el panel de pruebas de distancia
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
package panelpruebas;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dialogos.DialogoOpcionesAlerta;
import panelconfiguracion.Coche;
import recursos.Fisica;
import xBee.DispositivoXBee;

/**
 * @brief Clase PanelDistancia
 */
public class PanelDistancia extends Pruebas {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private static final String ARIAL = "Arial";
	private static final String ERROR = "ERROR";
	private JFrame ventana;
	private Coche coche;
	private JRadioButton adelante;
	private JTextField distancia;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal
	 * @param titulo Título del panel
	 */
	public PanelDistancia(JFrame ventana, String titulo) {
		super(ventana, titulo);

		this.ventana = ventana;
	}

	/**
	 * @brief Método del panel datos: contiene los paneles para introducir datos
	 * @return Component
	 */
	protected Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));

		panel.add(crearPanelRadioButton());
		panel.add(crearPanelDistancia());

		return panel;
	}

	/**
	 * @brief Método del panel radio button: contiene los radio buttons
	 * @return Component
	 */
	private Component crearPanelRadioButton() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
		JRadioButton atras;
		ButtonGroup grupo = new ButtonGroup();

		adelante = new JRadioButton("  ADELANTE");
		adelante.setFont(new Font(ARIAL, Font.BOLD, 12));
		adelante.setVerticalAlignment(JButton.CENTER);
		adelante.setSelected(true);
		adelante.addActionListener(this);

		atras = new JRadioButton("  ATRÁS");
		atras.setVerticalAlignment(JButton.CENTER);
		atras.setFont(new Font(ARIAL, Font.BOLD, 12));
		atras.addActionListener(this);

		grupo.add(adelante);
		grupo.add(atras);

		panel.add(adelante);
		panel.add(atras);

		return panel;
	}

	/**
	 * @brief Método del panel distancia: pedir distancia a recorrer por el coche
	 * @return Component
	 */
	private Component crearPanelDistancia() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		JLabel texto = new JLabel("DISTANCIA (m):");
		texto.setFont(new Font(ARIAL, Font.BOLD, 12));

		distancia = new JTextField();
		distancia.setFont(new Font(ARIAL, Font.BOLD, 17));
		distancia.setHorizontalAlignment(JTextField.CENTER);
		distancia.setBorder(null);

		panel.add(texto);
		panel.add(distancia);

		return panel;
	}

	/**
	 * @brief Método para comprobar si los campos están vacíos
	 * @return boolean
	 */
	private boolean camposVacios() {
		boolean camposVacios = false;

		if (distancia.getText().isEmpty()) {
			camposVacios = true;
		}

		return camposVacios;
	}

	/**
	 * @brief Método para comprobar si los datos introducidos son correctos
	 * @return boolean
	 */
	private boolean comprobarDatos() {
		boolean datosValidos = true;

		try {
			if (Double.parseDouble(distancia.getText()) < 1 || Double.parseDouble(distancia.getText()) > 30) {
				datosValidos = false;
				new DialogoOpcionesAlerta(ventana, "¡Datos incorrectos! Distancia: mayor a 1 metro y menor a 30 metros", ERROR);
				distancia.setText(null);
				distancia.requestFocusInWindow();
			}
		} catch (NumberFormatException e) {
			datosValidos = false;
			new DialogoOpcionesAlerta(ventana, "¡Formato de datos no válido!", ERROR);
			distancia.setText(null);
			distancia.requestFocusInWindow();
		}

		return datosValidos;
	}

	/**
	 * @brief Método para tratar las acciones realizadas por el usuario
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("iniciar")) {
			if (!camposVacios()) {
				if (comprobarDatos()) {
					comprobarXBee();
				}
			} else {
				new DialogoOpcionesAlerta(ventana, "Introduce un valor", ERROR);
				distancia.requestFocusInWindow();
			}
		} else if (e.getActionCommand().equals("detener")) {
			detenerAction();
		}
	}

	public void comprobarXBee() {
		if (dispositivoXBee != null) {
			if(!dispositivoXBee.comprobarEstado()) {
				iniciarAction();
			} else {
				new DialogoOpcionesAlerta(ventana, "¡Ya hay una prueba en curso!", ERROR);
			}
		} else {
			new DialogoOpcionesAlerta(ventana, "¡Dispositivo XBee no conectado!", ERROR);
		}
	}

	public void iniciarAction() {
		iniciar.setEnabled(false);
		detener.setEnabled(true);

		dispositivoXBee.iniciarXBee();
		dispositivoXBee.enviarDatosPrueba(DispositivoXBee.DISTANCIA,
				(adelante.isSelected() ? DispositivoXBee.ADELANTE : DispositivoXBee.ATRAS),
				DispositivoXBee.INICIAR, Fisica.calcularVueltasTramo(Integer.valueOf(distancia.getText()), coche));
	}

	public void detenerAction() {
		iniciar.setEnabled(true);
		detener.setEnabled(false);

		dispositivoXBee.enviarDatosPrueba(DispositivoXBee.DISTANCIA, DispositivoXBee.DETENER,
				DispositivoXBee.DETENER, DispositivoXBee.DETENER);
		dispositivoXBee.detenerXBee();
	}

	/**
	 * @brief Método para determinar el valor del objeto coche
	 * @param coche Datos del coche a utilizar por el usuario
	 * @return void
	 */
	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	
}