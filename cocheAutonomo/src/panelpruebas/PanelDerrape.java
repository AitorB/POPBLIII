/** @file PanelDerrape.java
 *  @brief Clase para crear un panel que permite visualizar el panel de pruebas de derrape
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
import xbee.DispositivoXBee;

/**
 * @brief Clase PanelDerrape
 */
public class PanelDerrape extends Pruebas {
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
	private JRadioButton derecha;
	private JTextField revoluciones;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal
	 * @param titulo Título del panel
	 */
	public PanelDerrape(JFrame ventana, String titulo) {
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
		panel.add(crearPanelDerrape());

		return panel;
	}

	/**
	 * @brief Método del panel radio button: contiene los radio buttons
	 * @return Component
	 */
	private Component crearPanelRadioButton() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
		JRadioButton izquierda;
		ButtonGroup grupo = new ButtonGroup();

		izquierda = new JRadioButton("  IZQUIERDA");
		izquierda.setFont(new Font(ARIAL, Font.BOLD, 12));
		izquierda.setVerticalAlignment(JButton.CENTER);
		izquierda.setSelected(true);
		izquierda.addActionListener(this);

		derecha = new JRadioButton("  DERECHA");
		derecha.setVerticalAlignment(JButton.CENTER);
		derecha.setFont(new Font(ARIAL, Font.BOLD, 12));
		derecha.addActionListener(this);

		grupo.add(izquierda);
		grupo.add(derecha);

		panel.add(izquierda);
		panel.add(derecha);

		return panel;
	}

	/**
	 * @brief Método del panel datos: pedir revoluciones del motor
	 * @return Component
	 */
	protected Component crearPanelDerrape() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		JLabel texto = new JLabel("RPM (%):");
		texto.setFont(new Font(ARIAL, Font.BOLD, 12));

		revoluciones = new JTextField();
		revoluciones.setFont(new Font(ARIAL, Font.BOLD, 17));
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
				new DialogoOpcionesAlerta(ventana, "¡Datos incorrectos! Revoluciones del motor: entre 15% y 100%", ERROR);
				revoluciones.setText(null);
				revoluciones.requestFocusInWindow();
			}
		} catch (NumberFormatException e) {
			datosValidos = false;
			new DialogoOpcionesAlerta(ventana, "¡Formato de datos no válido!", ERROR);
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
					comprobarXBee();
				}
			} else {
				new DialogoOpcionesAlerta(ventana, "Introduce un valor", ERROR);
				revoluciones.requestFocusInWindow();
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
		dispositivoXBee.enviarDatosPrueba(DispositivoXBee.DERRAPE,
				(derecha.isSelected() ? DispositivoXBee.DERECHA : DispositivoXBee.IZQUIERDA),
				DispositivoXBee.INICIAR, Integer.valueOf(revoluciones.getText()));
	}

	public void detenerAction() {
		iniciar.setEnabled(true);
		detener.setEnabled(false);

		dispositivoXBee.enviarDatosPrueba(DispositivoXBee.DERRAPE, DispositivoXBee.DETENER, DispositivoXBee.DETENER,
				DispositivoXBee.DETENER);
		dispositivoXBee.detenerXBee();
	}
}