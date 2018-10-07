/** @file PanelCircuitos.java
 *  @brief Clase que crea un panel que permite gestionar circuitos
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete panelConfiguracion
 */
package panelconfiguracion;

/** @brief Librerías
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dialogos.DialogoAnadirCircuito;
import dialogos.DialogoOpcionesConfirmar;
import inicioSesion.Usuario;
import principal.VentanaPrincipal;

/**
 * @brief Clase PanelCircuitos
 */
public class PanelCircuitos extends JPanel implements ActionListener, ListSelectionListener {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private JFrame ventana;
	private Usuario usuario;
	private JList<Circuito> listaCircuitos;
	private DefaultListModel<Circuito> modeloCircuitos;
	private Circuito circuito;
	private JTextField valorRecta;
	private JTextField valorRadio;
    private JTextField valorFriccion;
	private JButton anadir;
	private JButton borrar;
	private final String FONT ="Arial";
	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de la que se lanza el diálogo
	 * @param usuario Usuario con el que se ha iniciado sesión
	 */
	public PanelCircuitos(JFrame ventana, Usuario usuario) {
		super(new BorderLayout(0, 10));

		this.ventana = ventana;
		this.usuario = usuario;
		this.circuito = null;

		this.add(crearPanelTitulo(), BorderLayout.NORTH);
		this.add(crearPanelDatos(), BorderLayout.CENTER);
		this.add(crearPanelBotones(), BorderLayout.SOUTH);
	}

	/**
	 * @brief Método del panel título: contiene un título
	 * @return Component
	 */
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createEtchedBorder());

		JLabel titulo = new JLabel("Configuración de circuitos");
		titulo.setFont(new Font(FONT, Font.BOLD, 20));

		panel.add(titulo);

		return panel;
	}

	/**
	 * @brief Método del panel datos: contiene los paneles para mostrar los circuitos y su información
	 * @return Component
	 */
	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 0, 10));

		panel.add(crearPanelListaCircuitos());
		panel.add(crearPanelInfoCircuitos());

		return panel;
	}

	/**
	 * @brief Método del panel lista circuitos: muestra la lista de circuitos del usuario
	 * @return Component
	 */
	private Component crearPanelListaCircuitos() {
		JScrollPane panelScroll = new JScrollPane();

		panelScroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		panelScroll.setBackground(new Color(255, 155, 109));
		panelScroll.setOpaque(true);

		listaCircuitos = new JList<>();
		listaCircuitos.addListSelectionListener(this);

		modeloCircuitos = new DefaultListModel<>();

		cargarCircuitosUsuario();

		listaCircuitos.setModel(modeloCircuitos);

		listaCircuitos.setCellRenderer(new AdaptadorCircuitos());

		panelScroll.setViewportView(listaCircuitos);

		return panelScroll;
	}

	/**
	 * @brief Método del panel información circuitos: muestra información del circuito seleccionado en la lista
	 * @return Component
	 */
	private Component crearPanelInfoCircuitos() {
		JPanel panel = new JPanel((new GridLayout(3, 2, 10, 10)));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 5),
				BorderFactory.createEmptyBorder(8, 8, 8, 8)));

		JLabel textoRecta = new JLabel("Longitud de la recta:");
		textoRecta.setFont(new Font(FONT, Font.BOLD, 15));

		valorRecta = new JTextField();
		valorRecta.setFont(new Font(FONT, Font.BOLD, 17));
		valorRecta.setHorizontalAlignment(JTextField.CENTER);
		valorRecta.setBorder(null);
		valorRecta.setEditable(false);
		valorRecta.setBackground(Color.WHITE);

		JLabel textoRadio = new JLabel("Radio de la curva:");
		textoRadio.setFont(new Font(FONT, Font.BOLD, 15));

		valorRadio = new JTextField();
		valorRadio.setFont(new Font(FONT, Font.BOLD, 17));
		valorRadio.setHorizontalAlignment(JTextField.CENTER);
		valorRadio.setBorder(null);
		valorRadio.setEditable(false);
		valorRadio.setBackground(Color.WHITE);

		JLabel textoFriccion = new JLabel("Coeficiente de fricción:");
		textoFriccion.setFont(new Font(FONT, Font.BOLD, 15));

		valorFriccion = new JTextField();
		valorFriccion.setFont(new Font(FONT, Font.BOLD, 17));
		valorFriccion.setHorizontalAlignment(JTextField.CENTER);
		valorFriccion.setBorder(null);
		valorFriccion.setEditable(false);
		valorFriccion.setBackground(Color.WHITE);

		panel.add(textoRecta);
		panel.add(valorRecta);
		panel.add(textoRadio);
		panel.add(valorRadio);
		panel.add(textoFriccion);
		panel.add(valorFriccion);

		listaCircuitos.setSelectedIndex(modeloCircuitos.size() - 1);
		
		return panel;
	}

	/**
	 * @brief Método del panel botones: contiene los botones añadir y borrar circuito
	 * @return Component
	 */
	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));

		anadir = new JButton("  AÑADIR", new ImageIcon("iconos\\anadir.png"));
		anadir.setFont(new Font(FONT, Font.BOLD, 20));
		anadir.setMnemonic(KeyEvent.VK_A);
		anadir.setPreferredSize(new Dimension(0, 50));
		anadir.setActionCommand("anadir");
		anadir.addActionListener(this);
		
		borrar = new JButton("  BORRAR", new ImageIcon("iconos\\borrar.png"));
		borrar.setFont(new Font(FONT, Font.BOLD, 20));
		borrar.setMnemonic(KeyEvent.VK_B);
		borrar.setPreferredSize(new Dimension(0, 50));
		borrar.setActionCommand("borrar");
		borrar.addActionListener(this);
		
		if(usuario.getCoche() == null) {
			anadir.setEnabled(false);
			borrar.setEnabled(false);
		}

		panel.add(anadir);
		panel.add(borrar);

		return panel;
	}

	/**
	 * @brief Método para crear un diálogo que permite añadir un nuevo circuito
	 * @return void
	 */
	private void anadirCircuito() {
		DialogoAnadirCircuito dialogo = new DialogoAnadirCircuito(ventana, 300, 310, "NUEVO CIRCUITO", modeloCircuitos);

		Circuito nuevoCircuito = dialogo.getNuevoCircuito();

		if (nuevoCircuito != null) {
			modeloCircuitos.addElement(nuevoCircuito);
			if (!borrar.isEnabled()) {
				borrar.setEnabled(true);
			}
		}
	}

	/**
	 * @brief Método para borrar un circuito
	 * @return void
	 */
	private void borrarCircuito() {
		DialogoOpcionesConfirmar dialogo = new DialogoOpcionesConfirmar(ventana, "¿Desea borrar el circuito seleccionado?",
				"PREGUNTA");
		if (dialogo.getAceptar()) {
			modeloCircuitos.removeElement(listaCircuitos.getSelectedValue());
		}
		if (modeloCircuitos.isEmpty()) {
			borrar.setEnabled(false);
		}
	}

	/**
	 * @brief Método para cargar la lista de circuitos del usuario y añadirlos al modelo
	 * @return void
	 */
	private void cargarCircuitosUsuario() {
		for (Circuito circuit : usuario.getListaCircuitos()) {
			modeloCircuitos.addElement(circuit);
		}
	}

	/**
	 * @brief Método para detectar cambios en la selección de la lista de circuitos
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			JList<?> lista = (JList<?>) e.getSource();
			if (!lista.isSelectionEmpty()) {
				circuito = (Circuito) lista.getSelectedValue();
				valorRecta.setText(String.valueOf(circuito.getRecta()) + " m");
				valorRadio.setText(String.valueOf(circuito.getRadio()) + " m");
				valorFriccion.setText(String.valueOf(circuito.getFriccion()) + " m");
				((VentanaPrincipal) ventana).getBotonSimulacion().setEnabled(true);
				((VentanaPrincipal) ventana).getBotonPractica().setEnabled(true);
			} else {
				((VentanaPrincipal) ventana).getBotonSimulacion().setEnabled(false);
				((VentanaPrincipal) ventana).getBotonPractica().setEnabled(false);
			}
		}
	}

	/**
	 * @brief Método para tratar las acciones realizadas por el usuario
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("anadir")) {
			anadirCircuito();
		} else if (e.getActionCommand().equals("borrar")) {
			borrarCircuito();
		}
		
		listaCircuitos.setSelectedIndex(modeloCircuitos.size() - 1);
	}

	/**
	 * @brief Método para obtener el valor de la variable listaCircuitos
	 * @return List<Circuito>
	 */
	public List<Circuito> getListaCircuitos() {
		List<Circuito> ciruitList = new ArrayList<>();

		for (int i = 0; i < modeloCircuitos.size(); i++) {
			ciruitList.add(modeloCircuitos.getElementAt(i));
		}

		return ciruitList;
	}

	/**
	 * @brief Método para obtener el valor de la variable circuito
	 * @return Circuito
	 */
	public Circuito getCircuito() {
		return circuito;
	}
	
	/**
	 * @brief Método para determinar el estado del botón añadir
	 * @return void
	 */
	public void setBotonAnadir(boolean estado) {
		anadir.setEnabled(estado);
	}

}