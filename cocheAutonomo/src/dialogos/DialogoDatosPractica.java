/** @file DialogoDatosPractica.java
 *  @brief Clase para crear un diálogo que permite introducir la dirección del circuito y la velocidad del coche para realizar la práctica
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

import java.awt.BorderLayout;
/** @brief Librerías
 */
import java.awt.Component;
import java.awt.Dimension;
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

/**
 * @brief Clase DialogoDatosPractica
 */
public class DialogoDatosPractica extends DialogoAnadir {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private static final String ERROR = "ERROR";
	private JTextField textoRevoluciones;
	private JRadioButton botonIzquierda;
	private boolean iniciarPractica;
	
	private static final String FONT_TYPE = "Arial";

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de tipo JFrame
	 * @param anchura Anchura del diálogo
	 * @param altura Altura del diálogo
	 * @param titulo Título del diálogo
	 */
	public DialogoDatosPractica(JFrame ventana, int anchura, int altura, String titulo) {
		super(ventana, anchura, altura, titulo);
		this.iniciarPractica = false;
		
		super.mostrarVentana();
	}

	/**
	 * @brief Método del panel datos: contiene los datos que el usuario debe introducir
	 * @return Component
	 */
	protected Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 0, 8));
		panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

		panel.add(crearPanelRadioButton());
		panel.add(crearPanelRevoluciones());
		
		return panel;
	}

	/**
	 * @brief Método del panel radio button: contiene los radio buttons
	 * @return Component
	 */
	private Component crearPanelRadioButton() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
		JRadioButton botonDerecha;
		JLabel texto = new JLabel("Sentido del circuito:");
		ButtonGroup grupo = new ButtonGroup();

		texto.setFont(new Font(FONT_TYPE, Font.BOLD, 13));
		texto.setHorizontalAlignment(JLabel.LEFT);
		
		botonIzquierda = new JRadioButton("  IZQUIERDA");
		botonIzquierda.setFont(new Font(FONT_TYPE, Font.BOLD, 12));
		botonIzquierda.setHorizontalAlignment(JButton.RIGHT);
		botonIzquierda.setSelected(true);
		botonIzquierda.addActionListener(event -> {textoRevoluciones.selectAll(); textoRevoluciones.requestFocusInWindow();});
		
		botonDerecha = new JRadioButton("  DERECHA");
		botonDerecha.setHorizontalAlignment(JButton.RIGHT);
		botonDerecha.setFont(new Font(FONT_TYPE, Font.BOLD, 12));
		botonDerecha.addActionListener(event -> {textoRevoluciones.selectAll(); textoRevoluciones.requestFocusInWindow();});

		grupo.add(botonIzquierda);
		grupo.add(botonDerecha);

		panel.add(texto);
		panel.add(botonIzquierda);
		panel.add(botonDerecha);
		
		return panel;
	}

	/**
	 * @brief Método del panel revoluciones: pedir revoluciones del coche
	 * @return Component
	 */
	private Component crearPanelRevoluciones() {
		JPanel panel = new JPanel(new BorderLayout());

		JLabel mensaje = new JLabel("Revoluciones (%):");
		mensaje.setFont(new Font(FONT_TYPE, Font.BOLD, 13));
		
		textoRevoluciones = new JTextField();
		textoRevoluciones.setFont(new Font(FONT_TYPE, Font.BOLD, 17));
		textoRevoluciones.setPreferredSize(new Dimension(120, 40));
		textoRevoluciones.setHorizontalAlignment(JTextField.CENTER);
		textoRevoluciones.setBorder(null);
		textoRevoluciones.addActionListener(e -> aceptar.requestFocusInWindow());
		
		panel.add(mensaje, BorderLayout.CENTER);
		panel.add(textoRevoluciones, BorderLayout.EAST);

		return panel;
	}

	/**
	 * @brief Método para comprobar si los campos están vacíos
	 * @return boolean
	 */
	protected boolean camposVacios() {
		boolean camposVacios = false;

		if (textoRevoluciones.getText().isEmpty()) {
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
			if (Double.parseDouble(textoRevoluciones.getText()) < 15 || Double.parseDouble(textoRevoluciones.getText()) > 100) {
				datosValidos = false;
				new DialogoOpcionesAlerta(this, "¡Datos incorrectos! Revoluciones del motor: entre 15% y 100%", ERROR);
				textoRevoluciones.setText(null);
				textoRevoluciones.requestFocusInWindow();
			}
		} catch (NumberFormatException e) {
			datosValidos = false;
			new DialogoOpcionesAlerta(this, "¡Formato de datos no válido!", ERROR);
			textoRevoluciones.setText(null);
			textoRevoluciones.requestFocusInWindow();
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
		if (e.getActionCommand().equals("aceptar")) {
			if (!camposVacios()) {
				if (comprobarDatos()) {
					iniciarPractica = true;
					this.dispose();
				}
			} else {
				new DialogoOpcionesAlerta(this, "Rellena todos los campos", ERROR);
				textoRevoluciones.selectAll();
				textoRevoluciones.requestFocusInWindow();
			}
		}
		if (e.getActionCommand().equals("cancelar")) {
			this.dispose();
		}
	}

	/**
	 * @brief Método para obtener el valor de la variable iniciarPractica
	 * @return boolean
	 */
	public boolean getIniciarPractica() {
		return iniciarPractica;
	}

	/**
	 * @brief Método para obtener el valor del componente botonIzquierda
	 * @return JRadioButton
	 */
	public JRadioButton getBotonIzquierda() {
		return botonIzquierda;
	}

	/**
	 * @brief Método para obtener el valor de la variable revoluciones
	 * @return int
	 */
	public int getRevoluciones() {
		return Integer.valueOf(textoRevoluciones.getText());
	}

}