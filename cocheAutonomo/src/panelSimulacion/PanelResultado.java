/** @file PanelResultado.java
 *  @brief Clase que muestra un diálogo con el resultado de la simulación
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete panelSimulacion
 */
package panelSimulacion;

/** @brief Librerías
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import panelConfiguracion.Circuito;

/**
 * @brief Clase PanelResultado
 */
public class PanelResultado extends JDialog implements ActionListener {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private Circuito circuito;
	private JButton cerrar;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de la que se lanza el diálogo
	 * @param circuito Circuito seleccionado por el usuario
	 */
	public PanelResultado(JFrame ventana, Circuito circuito) {
		super(ventana, true);
		this.circuito = circuito;

		this.setContentPane(crearPanelPrincipal());
		this.setUndecorated(true);
		this.pack();
		
		int posicionX = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - 223);
		int posicionY = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - 66);
		this.setLocation(posicionX, posicionY);

		this.setVisible(true);
	}

	/**
	 * @brief Método del panel principal: contiene el resto de paneles
	 * @return Container
	 */
	private Container crearPanelPrincipal() {
		JPanel panel = new JPanel(new BorderLayout());

		panel.add(crearPanelCentro(), BorderLayout.CENTER);
		panel.add(crearPanelBoton(), BorderLayout.EAST);

		return panel;
	}
	
	/**
	 * @brief Método del panel centro: contiene el título y los datos a mostrar
	 * @return Component
	 */
	private Container crearPanelCentro() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new LineBorder(Color.WHITE, 5));

		panel.add(crearPanelTitulo(), BorderLayout.NORTH);
		panel.add(crearPanelDatos(), BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @brief Método del panel título: contiene el título de la ventana
	 * @return Component
	 */
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));

		JLabel texto = new JLabel("¡ SIMULACIÓN COMPLETADA !");
		texto.setFont(new Font("Arial", Font.BOLD, 15));

		panel.add(texto);

		return panel;
	}

	/**
	 * @brief Método del panel centro: contiene los paneles de datos
	 * @return Component
	 */
	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		panel.add(crearPanelResultado("DISTANCIA"));
		panel.add(crearPanelResultado("VELOCIDAD"));
		panel.add(crearPanelResultado("TIEMPO"));

		return panel;
	}

	/**
	 * @brief Método del panel resultado: muestra los resultados de la simulación
	 * @param dato Indica el tipo de datos a mostrar
	 * @return Component
	 */
	private Component crearPanelResultado(String dato) {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setPreferredSize(new Dimension(180, 60));

		JLabel texto = new JLabel(dato + ":");
		texto.setFont(new Font("Arial", Font.BOLD, 12));
		texto.setHorizontalAlignment(JLabel.CENTER);

		JTextField valor = new JTextField();
		valor.setFont(new Font("Arial", Font.BOLD, 15));
		valor.setHorizontalAlignment(JTextField.CENTER);
		valor.setBorder(null);
		valor.setEditable(false);
		valor.setBackground(Color.WHITE);

		switch (dato) {
		case "DISTANCIA":
			valor.setText(String.format("%.2f", circuito.getDistancia()) + " m");
			break;
		case "VELOCIDAD":
			double velocidadMetrosSegundo = circuito.getVelocidadMaxima();
			valor.setText(String.format("%.2f", velocidadMetrosSegundo) + " km/h");
			break;
		case "TIEMPO":
			valor.setText(String.format("%.2f", circuito.getTiempoRecorrido()) + " s");
			break;
		}

		panel.add(texto);
		panel.add(valor);

		return panel;
	}

	/**
	 * @brief Método del panel botón: contiene el botón cerrar
	 * @return Component
	 */
	private Component crearPanelBoton() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.WHITE));
		
		cerrar = new JButton("CERRAR");
		cerrar.setFont(new Font("Arial", Font.BOLD, 12));
		cerrar.setActionCommand("cerrar");
		cerrar.addActionListener(this);
		this.getRootPane().setDefaultButton(cerrar);
		cerrar.requestFocus();
        
		panel.add(cerrar, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @brief Método para tratar las acciones realizadas por el usuario
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("cerrar")) {
			this.dispose();
		}
	}

}