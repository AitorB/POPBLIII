/** @file Pruebas.java
 *  @brief Clase abstracta para implementar paneles de prueba personalizados
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
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xBee.DispositivoXBee;

/**
 * @brief Clase Pruebas
 */
public abstract class Pruebas extends JPanel implements ActionListener {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	protected DispositivoXBee dispositivoXBee;
	
	private static final int ANCHO_TITULO = 350;
	protected static final int ANCHO_BOTON = 180;
	
	protected JFrame ventana;
	protected JButton iniciar, detener;
	private String titulo;
	protected String tipoPrueba;
	
	/**
	 * @brief Constructor con ventana
	 * @param ventana Referencia a la ventana del panel de pruebas 
	 * @param titulo Título del panel
	 */
	public Pruebas(JFrame ventana, String titulo) {
		this.setLayout(new BorderLayout(20, 0));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));

		this.ventana = ventana;
		this.titulo = titulo;
		
		this.add(crearPanelTitulo(), BorderLayout.WEST);
		this.add(crearPanelDatos(), BorderLayout.CENTER);
		this.add(crearPanelBotones(), BorderLayout.EAST);
	}
	
	/**
	 * @brief Constructor sin ventana
	 * @param titulo Título del panel
	 */
	public Pruebas(String titulo) {
		this.setLayout(new BorderLayout(20, 0));

		this.titulo = titulo;

		this.add(crearPanelTitulo(), BorderLayout.WEST);
		this.add(crearPanelDatos(), BorderLayout.CENTER);
		this.add(crearPanelBotones(), BorderLayout.EAST);
	}

	/**
	 * @brief Método del panel título: contiene un título
	 * @return Component
	 */
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0),
				BorderFactory.createEtchedBorder()));

		JLabel texto = new JLabel(titulo);
		texto.setFont(new Font("Arial", Font.BOLD, 20));
		texto.setHorizontalAlignment(JLabel.CENTER);
		texto.setPreferredSize(new Dimension(ANCHO_TITULO, 0));

		panel.add(texto, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @brief Método del panel datos: contiene los paneles para introducir datos
	 * @return Component
	 */
	protected abstract Component crearPanelDatos();

	/**
	 * @brief Método del panel botones: contiene los botones
	 * @return Component
	 */
	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		iniciar = new JButton("  INICIAR", new ImageIcon("iconos\\iniciar.png"));
		iniciar.setFont(new Font("Arial", Font.BOLD, 20));
		iniciar.setMnemonic(KeyEvent.VK_I);
		iniciar.setPreferredSize(new Dimension(180, 0));
		iniciar.setActionCommand("iniciar");
		iniciar.addActionListener(this);

		detener = new JButton("  DETENER", new ImageIcon("iconos\\detener.png"));
		detener.setFont(new Font("Arial", Font.BOLD, 20));
		detener.setMnemonic(KeyEvent.VK_D);
		detener.setPreferredSize(new Dimension(180, 0));
		detener.setActionCommand("detener");
		detener.addActionListener(this);
		detener.setEnabled(false);
		
		panel.add(iniciar);
		panel.add(detener);

		return panel;
	}
	
	/**
	 * @brief Método para determinar el valor de la variable dispositivoXBee
	 * @param dispositivoXBee Dispositivo XBee utilizando para realizar la comunicación con el coche
	 * @return void
	 */
	public void setDispositivoXBee(DispositivoXBee dispositivoXBee) {
		this.dispositivoXBee = dispositivoXBee;
	}

}