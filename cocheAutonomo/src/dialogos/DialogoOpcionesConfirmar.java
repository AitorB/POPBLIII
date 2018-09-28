/** @file DialogoOpcionesConfirmar.java
 *  @brief Clase que permite crear un dialogo de confirmaci�n personalizado
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

/** @brief Librer�as
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @brief Clase DialogoOpcionesConfirmar
 */
public class DialogoOpcionesConfirmar extends DialogoOpciones {
	/**
	 * @brief N�mero de versi�n de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private boolean aceptar;

	/**
	 * @brief Constructor para crear un di�logo desde un JFrame
	 * @param ventana Referencia a la ventana de tipo JFrame
	 * @param texto Mensaje a mostrar en el di�logo
	 * @param imagen Nombre de la imagen a mostrar en el di�logo
	 */
	public DialogoOpcionesConfirmar(JFrame ventana, String texto, String imagen) {
		super(ventana, texto, imagen);
		super.mostrarVentana();
	}
	
	/**
	 * @brief Constructor para crear un di�logo desde un JDialog
	 * @param ventana Referencia a la ventana de tipo JDialog
	 * @param texto Mensaje a mostrar en el di�logo
	 * @param imagen Tipo de imagen a mostrar en el di�logo
	 */
	public DialogoOpcionesConfirmar(JDialog ventana, String texto, String imagen) {
		super(ventana, texto, imagen);
		super.mostrarVentana();
	}

	/**
	 * @brief M�todo del panel botones: contiene los botones aceptar y cancelar
	 * @return Component
	 */
	@Override
	protected Component crearPanelBotones() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JButton aceptar = new JButton("Aceptar");
		aceptar.setMnemonic(KeyEvent.VK_A);
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(this);

		JButton cancelar = new JButton("Cancelar");
		cancelar.setMnemonic(KeyEvent.VK_C);
		cancelar.setActionCommand("cancelar");
		cancelar.addActionListener(this);

		panel.add(aceptar);
		panel.add(Box.createRigidArea(new Dimension(15, 0)));
		panel.add(cancelar);

		return panel;
	}

	/**
	 * @brief M�todo para tratar las acciones realizadas por el usuario
	 * @param e Acci�n realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("aceptar")) {
			aceptar = true;
			this.dispose();
		}
		if (e.getActionCommand().equals("cancelar")) {
			aceptar = false;
			this.cerrarVentana();
		}
	}

	/**
	 * @brief M�todo para obtener el valor de la variable aceptar
	 * @return boolean
	 */
	public boolean getAceptar() {
		return aceptar;
	}

}