/** @file VentanaPrincipal.java
 *  @brief Clase que muestra la ventana principal del programa
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete principal
 */
package principal;

/** @brief Librerías
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import bienvenida.Bienvenida;
import dialogos.DialogoOpcionesAlerta;
import dialogos.DialogoOpcionesConfirmar;
import inicioSesion.IniciarSesion;
import inicioSesion.Usuario;
import main.Main;
import panelConfiguracion.PanelConfiguracion;
import panelPractica.PanelPractica;
import panelPruebas.PanelPruebas;
import panelSimulacion.PanelSimulacion;
import recursos.Fecha;
import recursos.Hora;
import xBee.DispositivoXBee;

/**
 * @brief Clase VentanaPrincipal
 */
public class VentanaPrincipal extends JFrame implements ActionListener {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private static final String PANEL_UNO = "panel configuración";
	private static final String PANEL_DOS = "panel simulación";
	private static final String PANEL_TRES = "panel estadísticas";
	private static final String PANEL_CUATRO = "panel pruebas";
	
	private DispositivoXBee dispositivoXBee;
	
	private JPanel panelCentro;
	private PanelConfiguracion panelConfiguracion;
	private PanelSimulacion panelSimulacion;
	private PanelPractica panelPractica;
	private PanelPruebas panelPruebas;
	private Usuario usuario;
	private JButton simulacion, practica, pruebas, activarXBee;

	/**
	 * @brief Constructor
	 */
	public VentanaPrincipal(boolean bienvenida) {
		if (bienvenida) {
			new Bienvenida(this);
		}

		if (iniciarSesion()) {
			iniciarPrograma();
		} else {
			System.exit(0);
		}
	}

	/**
	 * @brief Método para lanzar el inicio de sesión
	 * @return boolean
	 */
	private boolean iniciarSesion() {
		boolean iniciarSesion = false;

		IniciarSesion ventanaInicioSesion = new IniciarSesion(this);

		if (ventanaInicioSesion.getUsuarioValido()) {
			usuario = ventanaInicioSesion.getUsuario();
			iniciarSesion = true;
		}

		return iniciarSesion;
	}

	/**
	 * @brief Método para para construir la ventana principal
	 * @return void
	 */
	private void iniciarPrograma() {
		this.setTitle("Coche autónomo");
		this.setIconImage(new ImageIcon ("iconos\\mu.png").getImage());
		this.setContentPane(crearPanelPrincipal());
		this.setPreferredSize(new Dimension(Main.ANCHO_VENTANA, Main.ALTO_VENTANA));
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarAplicacion();
			}
		});
		this.setVisible(true);
	}

	/**
	 * @brief Método del panel principal: contiene el resto de paneles
	 * @return Container
	 */
	private Container crearPanelPrincipal() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

		panel.add(crearPanelMenu(), BorderLayout.NORTH);
		panel.add(crearPanelDatos(), BorderLayout.CENTER);
		panel.add(crearPanelEstado(), BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * @brief Método del panel menú: contiene la barra de menú del programa
	 * @return Component
	 */
	private Component crearPanelMenu() {
		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
		toolBar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8),
				BorderFactory.createRaisedBevelBorder()));
		toolBar.setFloatable(false);

		JButton configuracion = new JButton("Configuración", new ImageIcon("iconos\\configuracion.png"));
		configuracion.setMnemonic(KeyEvent.VK_C);
		configuracion.setActionCommand("configuracion");
		configuracion.addActionListener(this);

		simulacion = new JButton("Simulación", new ImageIcon("iconos\\simulacion.png"));
		simulacion.setMnemonic(KeyEvent.VK_S);
		simulacion.setActionCommand("simulacion");
		simulacion.addActionListener(this);
		simulacion.setEnabled(false);

		practica = new JButton("Práctica", new ImageIcon("iconos\\practica.png"));
		practica.setMnemonic(KeyEvent.VK_E);
		practica.setActionCommand("practica");
		practica.addActionListener(this);
		practica.setEnabled(false);
		
		pruebas = new JButton("Pruebas", new ImageIcon("iconos\\pruebas.png"));
		pruebas.setMnemonic(KeyEvent.VK_P);
		pruebas.setActionCommand("pruebas");
		pruebas.addActionListener(this);
		pruebas.setEnabled((usuario.getNombre().equalsIgnoreCase("PRUEBAS")) ? true : false);
	
		JButton cerrarSesion = new JButton("Cerrar Sesión", new ImageIcon("iconos\\cerrar_sesion.png"));
		cerrarSesion.setMnemonic(KeyEvent.VK_R);
		cerrarSesion.setActionCommand("cerrarSesion");
		cerrarSesion.addActionListener(this);

		JButton salir = new JButton("Salir", new ImageIcon("iconos\\salir.png"));
		salir.setMnemonic(KeyEvent.VK_L);
		salir.setActionCommand("salir");
		salir.addActionListener(this);

		toolBar.add(configuracion);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(simulacion);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(practica);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(pruebas);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(cerrarSesion);
		toolBar.addSeparator(new Dimension(20, 0));
		toolBar.add(salir);

		return toolBar;
	}

	/**
	 * @brief Método del panel datos: contiene todos los paneles de datos del programa
	 * @return Component
	 */
	private Component crearPanelDatos() {
		panelCentro = new JPanel(new CardLayout());
		panelCentro.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		panelConfiguracion = new PanelConfiguracion(this, usuario);
		panelSimulacion = new PanelSimulacion(this);
		panelPractica = new PanelPractica(this);
		panelPruebas = new PanelPruebas(this);

		panelCentro.add(panelConfiguracion, PANEL_UNO);
		panelCentro.add(panelSimulacion, PANEL_DOS);
		panelCentro.add(panelPractica, PANEL_TRES);
		panelCentro.add(panelPruebas, PANEL_CUATRO);

		return panelCentro;
	}

	/**
	 * @brief Método del panel estado: contiene el panel de información y XBee
	 * @return Component
	 */
	private Component crearPanelEstado() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(crearPanelInfo(), BorderLayout.CENTER);
		panel.add(crearPanelXBee(), BorderLayout.EAST);
		
		return panel;
	}
	
	/**
	 * @brief Método del panel información: muestra una barra de estado con información de la aplicación
	 * @return Component
	 */
	private Component crearPanelInfo() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 0));
		panel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.lightGray));
		panel.setBackground(Color.LIGHT_GRAY);

		panel.add(crearPanelUsuario());
		panel.add(new Fecha(Color.BLACK, new Font("Arial", Font.BOLD, 13), new Color(248, 248, 248)));
		panel.add(new Hora(Color.BLACK, new Font("Arial", Font.BOLD, 13), new Color(248, 248, 248)));

		return panel;
	}

	/**
	 * @brief Método del panel usuario: muestra el nombre del usuario en la barra de estado
	 * @return Component
	 */
	private Component crearPanelUsuario() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(new Color(248, 248, 248));
		panel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
		
		JLabel texto = new JLabel("Usuario: ");
		texto.setFont(new Font("Arial", Font.BOLD, 13));

		JLabel nombreUsuario = new JLabel(usuario.getNombre());
		nombreUsuario.setForeground(Color.RED);
		nombreUsuario.setFont(new Font("Arial", Font.BOLD, 13));

		panel.add(texto);
		panel.add(nombreUsuario);

		return panel;
	}
	/**
	 * @brief Método del panel información: muestra una barra de estado con información de la aplicación
	 * @return Component
	 */
	private Component crearPanelXBee() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.lightGray));
		
		activarXBee = new JButton("XBee");
		activarXBee.setFont(new Font("Arial", Font.BOLD, 18));
		activarXBee.setForeground(Color.WHITE);
		activarXBee.setBackground(new Color(255, 107, 107));
		activarXBee.setContentAreaFilled(false);
		activarXBee.setOpaque(true);
		activarXBee.setHorizontalAlignment(JButton.CENTER);
		activarXBee.setMnemonic(KeyEvent.VK_X);
		activarXBee.setActionCommand("xbee");
		activarXBee.addActionListener(this);
		
		panel.add(activarXBee, BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * @brief Método para iniciar el dispositivo XBee
	 * @return void
	 */
	private void iniciarXBee() {
		dispositivoXBee = new DispositivoXBee(this);
		
		if(dispositivoXBee.getActivado()) {
			new DialogoOpcionesAlerta(this, "Dispositivo XBee activado", "INFORMACION");
			activarXBee.setBackground(new Color(141, 200, 154));
			
			panelPruebas.setDispositivoXBee(dispositivoXBee);
			panelPractica.setDispositivoXBee(dispositivoXBee);
		} else {
			new DialogoOpcionesAlerta(this, "Dispositivo XBee no detectado", "ERROR");
		}
		
	}

	/**
	 * @brief Método que actualiza los datos del usuario en el fichero
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void actualizarUsuario() {
		Map<String, Usuario> mapaUsuarios = new HashMap<>();

		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(Main.FICHERO_ORIGINAL))) {
			mapaUsuarios = (Map<String, Usuario>) reader.readObject();

			usuario.setListaCircuitos(panelConfiguracion.getPanelCircuitos().getListaCircuitos());
			usuario.setCoche(panelConfiguracion.getPanelCoche().getCoche());
			mapaUsuarios.put(usuario.getNombre().toLowerCase(), usuario);

			try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(Main.FICHERO_ORIGINAL))) {
				writer.writeObject(mapaUsuarios);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brief Método para cerrar la aplicación con diálogo de confirmación
	 * @return void
	 */
	private void cerrarAplicacion() {
		DialogoOpcionesConfirmar dialogo = new DialogoOpcionesConfirmar(this, "¿Desea cerrar la aplicación?", "PREGUNTA");
		if (dialogo.getAceptar()) {
			actualizarUsuario();
			System.exit(0);
		}
	}

	/**
	 * @brief Método para tratar las acciones realizadas por el usuario
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout panel = (CardLayout) panelCentro.getLayout();
		
		if (e.getActionCommand().equals("configuracion")) {
			panel.show(panelCentro, PANEL_UNO);
			
		} else if (e.getActionCommand().equals("simulacion")) {
			panel.show(panelCentro, PANEL_DOS);
			panelSimulacion.setCircuito(panelConfiguracion.getPanelCircuitos().getCircuito());
			panelSimulacion.setCoche(panelConfiguracion.getPanelCoche().getCoche());
			
		} else if (e.getActionCommand().equals("practica")) {
			panel.show(panelCentro, PANEL_TRES);
			panelPractica.setCircuito(panelConfiguracion.getPanelCircuitos().getCircuito());
			panelPractica.setCoche(panelConfiguracion.getPanelCoche().getCoche());
			panelPractica.getPanelDatosSimulacion().setDatosSimulacion();
			
		} else if (e.getActionCommand().equals("pruebas")) {
			panel.show(panelCentro, PANEL_CUATRO);
			panelPruebas.setCoche(panelConfiguracion.getPanelCoche().getCoche());
		
		} else if (e.getActionCommand().equals("xbee")) {
			iniciarXBee();
			
		} else if (e.getActionCommand().equals("cerrarSesion")) {
			DialogoOpcionesConfirmar dialogo = new DialogoOpcionesConfirmar(this,
					"¿Desea cerrar la sesión de " + usuario.getNombre() + " ?", "PREGUNTA");
			if (dialogo.getAceptar()) {
				actualizarUsuario();
				this.dispose();
				new VentanaPrincipal(false);
			}
			
		} else if (e.getActionCommand().equals("salir")) {
			cerrarAplicacion();
		}
	}
		
	/**
	 * @brief Método para obtener el botón simulación
	 * @return JButton
	 */
	public JButton getBotonSimulacion() {
		return simulacion;
	}
	
	/**
	 * @brief Método para obtener el el botón practica
	 * @return JButton
	 */
	public JButton getBotonPractica() {
		return practica;
	}
	
	/**
	 * @brief Método para obtener el valor del panel configuración
	 * @return PanelConfiguracion
	 */
	public PanelConfiguracion getPanelConfiguracion() {
		return panelConfiguracion;
	}

}