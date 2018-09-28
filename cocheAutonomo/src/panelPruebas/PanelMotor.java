/** @file PanelMotor.java
 *  @brief Clase para crear un panel que permite visualizar el panel de pruebas del motor
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
import xBee.DispositivoXBee;

/**
 * @brief Clase PanelMotor
 */
public class PanelMotor extends Pruebas {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private JFrame ventana;
	private JRadioButton adelante, atras;
	private JTextField revoluciones;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal
	 * @param titulo Título del panel
	 */
	public PanelMotor(JFrame ventana, String titulo) {
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
		panel.add(crearPanelRevoluciones());

		return panel;
	}

	/**
	 * @brief Método del panel radio button: contiene los radio buttons
	 * @return Component
	 */
	private Component crearPanelRadioButton() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));

		ButtonGroup grupo = new ButtonGroup();

		adelante = new JRadioButton("  ADELANTE");
		adelante.setFont(new Font("Arial", Font.BOLD, 12));
		adelante.setVerticalAlignment(JButton.CENTER);
		adelante.setSelected(true);
		adelante.addActionListener(this);

		atras = new JRadioButton("  ATRÁS");
		atras.setVerticalAlignment(JButton.CENTER);
		atras.setFont(new Font("Arial", Font.BOLD, 12));
		atras.addActionListener(this);

		grupo.add(adelante);
		grupo.add(atras);

		panel.add(adelante);
		panel.add(atras);

		return panel;
	}

	/**
	 * @brief Método del panel revoluciones: pedir revoluciones del motor
	 * @return Component
	 */
	private Component crearPanelRevoluciones() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		JLabel texto = new JLabel("RPM (%):");
		texto.setFont(new Font("Arial", Font.BOLD, 12));

		revoluciones = new JTextField();
		revoluciones.setFont(new Font("Arial", Font.BOLD, 17));
		revoluciones.setHorizontalAlignment(JTextField.CENTER);
		revoluciones.setBorder(null);

		panel.add(texto);
		panel.add(revoluciones);

		return panel;
	}

	/**
	 * @brief Método para comprobar si los campos están vacíos
	 * @return boolean
	 */
	private boolean camposVacios() {
		boolean camposVacios = false;

		if (revoluciones.getText().isEmpty()) {
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
			if (Double.parseDouble(revoluciones.getText()) < 15 || Double.parseDouble(revoluciones.getText()) > 100) {
				datosValidos = false;
				new DialogoOpcionesAlerta(ventana, "¡Datos incorrectos! Revoluciones del motor: entre 15% y 100%",
						"ERROR");
				revoluciones.setText(null);
				revoluciones.requestFocusInWindow();
			}
		} catch (NumberFormatException e) {
			datosValidos = false;
			new DialogoOpcionesAlerta(ventana, "¡Formato de datos no válido!", "ERROR");
			revoluciones.setText(null);
			revoluciones.requestFocusInWindow();
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
					if (dispositivoXBee != null) {
						if(!dispositivoXBee.comprobarEstado()) {
							iniciar.setEnabled(false);
							detener.setEnabled(true);
	
							dispositivoXBee.iniciarXBee();
							dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOTOR,
									(adelante.isSelected() ? DispositivoXBee.ADELANTE : DispositivoXBee.ATRAS),
									DispositivoXBee.INICIAR, Integer.valueOf(revoluciones.getText()));
						} else {
							new DialogoOpcionesAlerta(ventana, "¡Ya hay una prueba en curso!", "ERROR");
						}
					} else {
						new DialogoOpcionesAlerta(ventana, "¡Dispositivo XBee no conectado!", "ERROR");
					}
				}
			} else {
				new DialogoOpcionesAlerta(ventana, "Introduce un valor", "ERROR");
				revoluciones.requestFocusInWindow();
			}
		} else if (e.getActionCommand().equals("detener")) {
			iniciar.setEnabled(true);
			detener.setEnabled(false);

			dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOTOR, DispositivoXBee.DETENER, DispositivoXBee.DETENER,
					DispositivoXBee.DETENER);
			dispositivoXBee.detenerXBee();
		}
	}

}