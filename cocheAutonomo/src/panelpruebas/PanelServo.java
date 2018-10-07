/** @file PanelServo.java
 *  @brief Clase para crear un panel que permite visualizar el panel de pruebas del servo
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
import xBee.DispositivoXBee;

/**
 * @brief Clase PanelServo
 */
public class PanelServo extends Pruebas {
	/**
	 * @brief N�mero de versi�n de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private JFrame ventana;
	private JRadioButton izquierda, derecha;
	private JTextField porcentajeGiro;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal
	 * @param titulo T�tulo del panel
	 */
	public PanelServo(JFrame ventana, String titulo) {
		super(ventana, titulo);

		this.ventana = ventana;
		this.tipoPrueba = "Servo";
	}

	/**
	 * @brief M�todo del panel datos: contiene los paneles para introducir datos
	 * @return Component
	 */
	protected Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));

		panel.add(crearPanelRadioButton());
		panel.add(crearPanelAngulo());

		return panel;
	}

	/**
	 * @brief M�todo del panel radio button: contiene los radio buttons
	 * @return Component
	 */
	private Component crearPanelRadioButton() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));

		ButtonGroup grupo = new ButtonGroup();

		izquierda = new JRadioButton("  IZQUIERDA");
		izquierda.setFont(new Font("Arial", Font.BOLD, 12));
		izquierda.setVerticalAlignment(JButton.CENTER);
		izquierda.setSelected(true);
		izquierda.addActionListener(this);

		derecha = new JRadioButton("  DERECHA");
		derecha.setVerticalAlignment(JButton.CENTER);
		derecha.setFont(new Font("Arial", Font.BOLD, 12));
		derecha.addActionListener(this);

		grupo.add(izquierda);
		grupo.add(derecha);

		panel.add(izquierda);
		panel.add(derecha);

		return panel;
	}

	/**
	 * @brief M�todo del panel angulo: pedir angulo de giro
	 * @return Component
	 */
	private Component crearPanelAngulo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		JLabel texto = new JLabel("GIRO (%):");
		texto.setFont(new Font("Arial", Font.BOLD, 12));

		porcentajeGiro = new JTextField();
		porcentajeGiro.setFont(new Font("Arial", Font.BOLD, 17));
		porcentajeGiro.setHorizontalAlignment(JTextField.CENTER);
		porcentajeGiro.setBorder(null);

		panel.add(texto);
		panel.add(porcentajeGiro);

		return panel;
	}

	/**
	 * @brief M�todo para comprobar si los campos est�n vac�os
	 * @return boolean
	 */
	private boolean camposVacios() {
		boolean camposVacios = false;

		if (porcentajeGiro.getText().isEmpty()) {
			camposVacios = true;
		}

		return camposVacios;
	}

	/**
	 * @brief M�todo para comprobar si los datos introducidos son correctos
	 * @return boolean
	 */
	private boolean comprobarDatos() {
		boolean datosValidos = true;

		try {
			if (Double.parseDouble(porcentajeGiro.getText()) < 0
					|| Double.parseDouble(porcentajeGiro.getText()) > 100) {
				datosValidos = false;
				new DialogoOpcionesAlerta(ventana, "�Datos incorrectos! Valor servo: entre 0% y 100%", "ERROR");
				porcentajeGiro.setText(null);
				porcentajeGiro.requestFocusInWindow();
			}
		} catch (NumberFormatException e) {
			datosValidos = false;
			new DialogoOpcionesAlerta(ventana, "�Formato de datos no v�lido!", "ERROR");
			porcentajeGiro.setText(null);
			porcentajeGiro.requestFocusInWindow();
		}

		return datosValidos;
	}

	/**
	 * @brief M�todo para tratar las acciones realizadas por el usuario
	 * @param e Acci�n realizada por el usuario
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
							dispositivoXBee.enviarDatosPrueba(DispositivoXBee.SERVO,
									(derecha.isSelected() ? DispositivoXBee.DERECHA : DispositivoXBee.IZQUIERDA),
									DispositivoXBee.INICIAR, Integer.valueOf(porcentajeGiro.getText()));
						} else {
							new DialogoOpcionesAlerta(ventana, "�Ya hay una prueba en curso!", "ERROR");
						}
					} else {
						new DialogoOpcionesAlerta(ventana, "�Dispositivo XBee no conectado!", "ERROR");
					}	
				}
			} else {
				new DialogoOpcionesAlerta(ventana, "Introduce un valor", "ERROR");
				porcentajeGiro.requestFocusInWindow();
			}
		} else if (e.getActionCommand().equals("detener")) {
			iniciar.setEnabled(true);
			detener.setEnabled(false);

			dispositivoXBee.enviarDatosPrueba(DispositivoXBee.SERVO, DispositivoXBee.DETENER, DispositivoXBee.DETENER,
					DispositivoXBee.DETENER);
			dispositivoXBee.detenerXBee();
		}
	}

}