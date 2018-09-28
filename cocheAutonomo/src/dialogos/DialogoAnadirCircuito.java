/** @file DialogoAnadirCircuito.java
 *  @brief Clase para crear un diálogo que permite introducir un nuevo circuito
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete dialogos
 */
package dialogos;

/** @brief Librerías
 */
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import panelConfiguracion.Circuito;

/**
 * @brief Clase DialogoAnadirCircuito
 */
public class DialogoAnadirCircuito extends DialogoAnadir {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private Circuito nuevoCircuito;
	private JTextField nombre, recta, radio, anchoCarretera, friccion;
	private DefaultListModel<Circuito> modeloCircuitos;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de tipo JFrame
	 * @param anchura Anchura del diálogo
	 * @param altura Altura del diálogo
	 * @param titulo Título del diálogo
	 * @param modeloCircuitos Modelo de la lista de cicuitos del usuario
	 */
	public DialogoAnadirCircuito(JFrame ventana, int anchura, int altura, String titulo, DefaultListModel<Circuito> modeloCircuitos) {
		super(ventana, anchura, altura, titulo);
		this.modeloCircuitos = modeloCircuitos;
		super.mostrarVentana();
	}

	/**
	 * @brief Método del panel datos: contiene los datos que el usuario debe introducir
	 * @return Component
	 */
	protected Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(5, 2, 0, 8));
		panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

		nombre = new JTextField();
		nombre.setHorizontalAlignment(JTextField.RIGHT);
		nombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recta.requestFocusInWindow();
			}
		});

		recta = new JTextField();
		recta.setHorizontalAlignment(JTextField.RIGHT);
		recta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				radio.requestFocusInWindow();
			}
		});

		radio = new JTextField();
		radio.setHorizontalAlignment(JTextField.RIGHT);
		radio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anchoCarretera.requestFocusInWindow();
			}
		});

		anchoCarretera = new JTextField();
		anchoCarretera.setHorizontalAlignment(JTextField.RIGHT);
		anchoCarretera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				friccion.requestFocusInWindow();
			}
		});

		friccion = new JTextField();
		friccion.setHorizontalAlignment(JTextField.RIGHT);
		friccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptar.requestFocusInWindow();
			}
		});

		panel.add(new JLabel("Nombre:"));
		panel.add(nombre);
		panel.add(new JLabel("Longitud recta:"));
		panel.add(recta);
		panel.add(new JLabel("Radio:"));
		panel.add(radio);
		panel.add(new JLabel("Ancho de carretera:"));
		panel.add(anchoCarretera);
		panel.add(new JLabel("Coeficiente de fricción:"));
		panel.add(friccion);

		return panel;
	}

	/**
	 * @brief Método para comprobar si los campos están vacíos
	 * @return boolean
	 */
	protected boolean camposVacios() {
		boolean camposVacios = false;

		if ((nombre.getText().isEmpty() || recta.getText().isEmpty() || radio.getText().isEmpty()
				|| anchoCarretera.getText().isEmpty() || friccion.getText().isEmpty())) {
			camposVacios = true;
		}

		return camposVacios;
	}

	/**
	 * @brief Método para verificar si el circuito ya existe
	 * @return boolean
	 */
	private boolean comprobarCircuito() {
		boolean circuitoValido = true;

		for (int cont = 0; cont < modeloCircuitos.getSize(); cont++) {
			if (nombre.getText().equalsIgnoreCase(modeloCircuitos.getElementAt(cont).getNombre())) {
				circuitoValido = false;
			}
		}

		return circuitoValido;
	}

	/**
	 * @brief Método para comprobar si los datos introducidos son correctos
	 * @return boolean
	 */
	private boolean comprobarDatos() {
		boolean datosValidos = true;

		try {
			if (Double.parseDouble(recta.getText()) < 1.5 || Double.parseDouble(recta.getText()) > 60) {
				datosValidos = false;
				new DialogoOpcionesAlerta(this, "¡Datos incorrectos! Valor recta: mayor a 1.5 metros y menor a 60 metros",
						"ERROR");
				recta.setText(null);
				recta.requestFocusInWindow();

			} else if (Double.parseDouble(radio.getText()) < 1 || Double.parseDouble(radio.getText()) > 50) {
				datosValidos = false;
				new DialogoOpcionesAlerta(this, "¡Datos incorrectos! Valor radio: mayor a 1 metro y menor a 50 metros", "ERROR");
				radio.setText(null);
				radio.requestFocusInWindow();

			} else if (Double.parseDouble(anchoCarretera.getText()) < 1
					|| Double.parseDouble(anchoCarretera.getText()) > 5) {
				datosValidos = false;
				new DialogoOpcionesAlerta(this, "¡Datos incorrectos! Valor ancho carretera: mayor a 1 metro y menor a 5 metros",
						"ERROR");
				anchoCarretera.setText(null);
				anchoCarretera.requestFocusInWindow();
			} else if (Double.parseDouble(friccion.getText()) <= 0 || Double.parseDouble(friccion.getText()) >= 1) {
				datosValidos = false;
				new DialogoOpcionesAlerta(this, "¡Datos incorrectos! Valor coeficiente de fricción: mayor a 0 y menor a 1",
						"ERROR");
				friccion.setText(null);
				friccion.requestFocusInWindow();
			}
		} catch (NumberFormatException e) {
			datosValidos = false;
			new DialogoOpcionesAlerta(this, "¡Formato de datos no válido!", "ERROR");
			recta.setText(null);
			radio.setText(null);
			anchoCarretera.setText(null);
			friccion.setText(null);
			recta.requestFocusInWindow();
		}

		return datosValidos;
	}

	/**
	 * @brief Método para crear un nuevo circuito
	 * @return void
	 */
	private void crearCircuito() {
		nuevoCircuito = new Circuito(nombre.getText(), Double.valueOf(recta.getText()), Double.valueOf(radio.getText()),
				Double.valueOf(anchoCarretera.getText()), Double.valueOf(friccion.getText()));
	}

	/**
	 * @brief Método para tratar las acciones realizadas por el usuario
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("aceptar")) {
			if (!camposVacios()) {
				if (comprobarCircuito()) {
					if (comprobarDatos()) {
						this.crearCircuito();
						this.dispose();
					}
				} else {
					new DialogoOpcionesAlerta(this, "¡El circuito ya existe!", "ERROR");
					nombre.setText(null);
					nombre.requestFocusInWindow();
				}
			} else {
				new DialogoOpcionesAlerta(this, "Rellena todos los campos", "ERROR");
				nombre.selectAll();
				nombre.requestFocusInWindow();
			}
		}
		if (e.getActionCommand().equals("cancelar")) {
			this.dispose();
		}
	}
	
	/**
	 * @brief Método para obtener el valor de la variable nuevoCircuito
	 * @return Circuito
	 */
	public Circuito getNuevoCircuito() {
		return nuevoCircuito;
	}

}