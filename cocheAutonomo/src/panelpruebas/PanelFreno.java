/** @file PanelFreno.java
 *  @brief Clase para crear un panel que permite visualizar el panel de pruebas de frenado
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dialogos.DialogoOpcionesAlerta;
import xBee.DispositivoXBee;

/**
 * @brief Clase PanelFreno
 */
public class PanelFreno extends Pruebas {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private JFrame ventana;
	private JTextField revoluciones;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal
	 * @param titulo Título del panel
	 */
	public PanelFreno(JFrame ventana, String titulo) {
		super(ventana, titulo);

		this.ventana = ventana;
	}

	/**
	 * @brief Método del panel datos: contiene los paneles para introducir datos
	 * @return Component
	 */
	protected Component crearPanelDatos() {
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
							dispositivoXBee.enviarDatosPrueba(DispositivoXBee.FRENOS, DispositivoXBee.ADELANTE,
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

			dispositivoXBee.enviarDatosPrueba(DispositivoXBee.FRENOS, DispositivoXBee.DETENER, DispositivoXBee.DETENER,
					DispositivoXBee.DETENER);
			dispositivoXBee.detenerXBee();
		}
	}

}