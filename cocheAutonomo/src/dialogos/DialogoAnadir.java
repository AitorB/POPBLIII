/** @file DialogoAnadir.java
 *  @brief Clase abstracta para implementar di�logos de a�adir personalizados
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @brief Clase DialogoAnadir
 */
public abstract class DialogoAnadir extends JDialog implements ActionListener {
	/**
	 * @brief N�mero de versi�n de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Atributos
	 */
	private String titulo;
	protected JButton aceptar;
	
	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de tipo JFrame
	 * @param anchura Anchura del di�logo
	 * @param altura Altura del di�logo
	 * @param titulo T�tulo del di�logo
	 */
	public DialogoAnadir(JFrame ventana, int anchura, int altura, String titulo) {
		super(ventana);
		
		this.titulo = titulo;
		this.setContentPane(crearPanelPrincipal());
		this.setPreferredSize(new Dimension(anchura, altura));
		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(ventana);
	}
	
	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de tipo JDialog
	 * @param anchura Anchura del di�logo
	 * @param altura Altura del di�logo
	 * @param titulo T�tulo del di�logo
	 */
	public DialogoAnadir(JDialog ventana, int anchura, int altura, String titulo) {
		super(ventana);
		
		this.titulo = titulo;
		this.setContentPane(crearPanelPrincipal());
		this.setPreferredSize(new Dimension(anchura, altura));
		this.setUndecorated(true);
		this.pack();
		this.setLocationRelativeTo(ventana);
	}
	
	/**
	 * @brief M�todo para mostrar el di�logo
	 * @return void
	 */
	protected void mostrarVentana() {
		this.setModal(true);
		this.setVisible(true);
	}
	
	/**
	 * @brief M�todo del panel principal: contiene el resto de paneles
	 * @return Container
	 */
	protected Container crearPanelPrincipal() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new LineBorder(Color.BLACK, 5));

		panel.add(crearPanelTitulo(), BorderLayout.NORTH);
		panel.add(crearPanelCentro(), BorderLayout.CENTER);

		return panel;
	}
	
	/**
	 * @brief M�todo del panel t�tulo: contiene el t�tulo de la ventana
	 * @return Component
	 */
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.BLACK));

		JLabel texto = new JLabel(titulo);
		texto.setFont(new Font("Arial", Font.BOLD, 15));

		panel.add(texto);

		return panel;
	}
	
	/**
	 * @brief M�todo del panel centro: contiene el panel de datos y de botones
	 * @return Component
	 */
	private Component crearPanelCentro() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panel.add(crearPanelDatos(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);

		return panel;
	}
	
	/**
	 * @brief M�todo del panel datos: contiene los datos que el usuario debe introducir
	 * @return Component
	 */
	protected abstract Component crearPanelDatos();
	
	/**
	 * @brief M�todo del panel botones: contiene los botones aceptar y cancelar
	 * @return Component
	 */
	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		aceptar = new JButton("Aceptar");
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
	 * @brief M�todo para comprobar si los campos est�n vac�os
	 * @return boolean
	 */
	protected abstract boolean camposVacios();
	
}
