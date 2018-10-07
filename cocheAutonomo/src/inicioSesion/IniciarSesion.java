/** @file IniciarSesion.java
 *  @brief Clase que muestra el dialogo de inicio de sesión y permite identificar al usuario
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete iniciarSesion
 */
package inicioSesion;

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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dialogos.DialogoAnadirUsuario;
import dialogos.DialogoOpcionesConfirmar;
import dialogos.DialogoOpcionesAlerta;
import main.Main;
import recursos.FondoJPanel;
import recursos.Hora;
import recursos.Reproductor;

/**
 * @brief Clase IniciarSesion
 */
public class IniciarSesion extends JDialog implements ActionListener {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private static final String FONDO_PANTALLA = "imagenes\\iniciar_sesion.jpg";
	private static final String SONIDO = "sonidos\\bienvenida.mp3";

	private JTextField nombre;
	private JPasswordField contrasena;
	private JButton iniciar;
	private Map<String, Usuario> mapaUsuarios;
	private Usuario usuario;
	private Reproductor reproductor;
	private boolean usuarioValido = false;

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal de la que se lanza el diálogo
	 */
	public IniciarSesion(JFrame ventana) {
		super(ventana, true);
		cargarListaUsuarios();
		reproducirMusica();

		this.setContentPane(crearPanelPrincipal());
		this.setSize(new Dimension(Main.ANCHO_VENTANA, Main.ALTO_VENTANA));
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	/**
	 * @brief Método del panel principal: contiene el resto de paneles
	 * @return Container
	 */
	private Container crearPanelPrincipal() {
		Image fondoPantalla;
		fondoPantalla = Toolkit.getDefaultToolkit().createImage(FONDO_PANTALLA);

		JPanel panel = new FondoJPanel(fondoPantalla);
		panel.setLayout(new BorderLayout(0, 10));
		panel.add(crearPanelNorte(), BorderLayout.NORTH);
		panel.add(crearPanelCentro(), BorderLayout.CENTER);
		panel.add(crearPanelSur(), BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * @brief Método del panel norte: contiene un texto indicando la hora actual
	 * @return Component
	 */
	private Component crearPanelNorte() {
		Hora reloj = new Hora(Color.WHITE, new Font("Arial", Font.BOLD, 15), Color.WHITE);

		reloj.setLayout(new FlowLayout(FlowLayout.RIGHT));
		reloj.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 20));
		reloj.setOpaque(false);

		return reloj;
	}

	/**
	 * @brief Método del panel centro: contiene los paneles de identificación de usuario y registro
	 * @return Component
	 */
	private Component crearPanelCentro() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 0, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(250, 500, 150, 500));

		panel.add(crearPanelUsuario());
		panel.add(crearPanelContrasena());
		panel.add(crearPanelRegistro());

		panel.setOpaque(false);

		return panel;
	}

	/**
	 * @brief Método del panel usuario: permite introducir un usuario
	 * @return Component
	 */
	private Component crearPanelUsuario() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		nombre = new JTextField();
		nombre.setFont(new Font("Arial", Font.BOLD, 15));
		nombre.setPreferredSize(new Dimension(195, 20));

		nombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contrasena.requestFocusInWindow();
			}
		});

		nombre.setOpaque(false);
		nombre.setBorder(null);

		panel.add(nombre);
		
		panel.setOpaque(false);

		return panel;
	}

	/**
	 * @brief Método del panel contraseña: permite introducir una contaseña
	 * @return Component
	 */
	private Component crearPanelContrasena() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

		contrasena = new JPasswordField();
		contrasena.setFont(new Font("Arial", Font.BOLD, 15));
		contrasena.setPreferredSize(new Dimension(195, 20));

		contrasena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciar.requestFocusInWindow();
			}
		});

		contrasena.setOpaque(false);
		contrasena.setBorder(null);

		panel.add(contrasena);

		panel.setOpaque(false);

		return panel;
	}

	/**
	 * @brief Método del panel de registro: contiene un enlace para crear un nuevo usuario
	 * @return Component
	 */
	private Component crearPanelRegistro() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

		JLabel texto = new JLabel("¿Todavía no tienes cuenta?");
		texto.setFont(new Font("Arial", Font.BOLD, 12));

		JButton registro = new JButton("registrate");
		registro.setFont(new Font("Arial", Font.BOLD, 13));
		registro.setMnemonic(KeyEvent.VK_R);
		registro.setActionCommand("registro");
		registro.addActionListener(this);
		registro.setContentAreaFilled(false);

		panel.add(texto);
		panel.add(registro);

		panel.setOpaque(false);

		return panel;
	}

	/**
	 * @brief Método del panel sur: contiene los botones iniciar y apagar
	 * @return Component
	 */
	private Component crearPanelSur() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));

		iniciar = new JButton(new ImageIcon("iconos\\iniciar_sesion.png"));
		iniciar.setActionCommand("iniciar");
		iniciar.addActionListener(this);
		iniciar.setContentAreaFilled(false);

		JButton apagar = new JButton(new ImageIcon("iconos\\apagar.png"));
		apagar.setActionCommand("apagar");
		apagar.addActionListener(this);
		apagar.setContentAreaFilled(false);

		panel.add(Box.createHorizontalGlue());
		panel.add(iniciar);
		panel.add(Box.createRigidArea(new Dimension(40, 0)));
		panel.add(apagar);
		panel.add(Box.createHorizontalGlue());

		panel.setOpaque(false);

		return panel;
	}

	/**
	 * @brief Método para cargar todos los usuario del fichero
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void cargarListaUsuarios() {
		mapaUsuarios = new HashMap<>();

		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(Main.FICHERO_ORIGINAL))) {
			mapaUsuarios = (Map<String, Usuario>) reader.readObject();
		} catch (FileNotFoundException e) {
			File archivo = new File(Main.FICHERO_ORIGINAL);
			try {
				archivo.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brief Método para crear un diálogo que permite añadir usuarios
	 * @return void
	 */
	private void dialogoAnadirUsuario() {
		DialogoAnadirUsuario dialogo = new DialogoAnadirUsuario(this, 300, 200, "NUEVO USUARIO", mapaUsuarios);

		Usuario usuario = dialogo.getNuevoUsuario();

		if (usuario != null) {
			mapaUsuarios.put(usuario.getNombre().toLowerCase(), usuario);
		}
	}

	/**
	 * @brief Método para reproducir música
	 * @return void
	 */
	private void reproducirMusica() {
		try {
			reproductor = new Reproductor();
			reproductor.AbrirFichero(SONIDO);
			reproductor.Reproducir();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * @brief Método para detener la música
	 * @return void
	 */
	private void pararMusica() {
		try {
			reproductor.Parar();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * @brief Método para comprobar si el usario introducido es correcto
	 * @param nombre Nombre del usuario
	 * @param contraseña Contraseña del usuario
	 * @return boolean
	 */
	private boolean comprobarUsuario(String nombre, char[] contrasena) {
		boolean usuarioValido = false;

		Iterator<String> iterator = mapaUsuarios.keySet().iterator();

		while (iterator.hasNext() && usuarioValido == false) {
			String clave = iterator.next();
			Usuario valor = mapaUsuarios.get(clave);
			if (clave.equals(nombre.toLowerCase()) && Arrays.equals(valor.getContrasena(), contrasena)) {
				usuario = valor;
				usuarioValido = true;
			}
		}

		return usuarioValido;
	}

	/**
	 * @brief Método para comprobar si los campos están vacíos
	 * @return boolean
	 */
	private boolean camposVacios() {
		boolean camposVacios = false;

		if (nombre.getText().isEmpty() || contrasena.getPassword().length == 0) {
			camposVacios = true;
		}

		return camposVacios;
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
				if (comprobarUsuario(nombre.getText(), contrasena.getPassword())) {
					usuarioValido = true;
					usuario.guardarListaUsuarios(mapaUsuarios);
					pararMusica();
					this.dispose();
				} else {
					new DialogoOpcionesAlerta(this, "Usuario y contraseña incorrectos", "ERROR");
					nombre.setText(null);
					contrasena.setText(null);
					nombre.requestFocusInWindow();
				}
			} else {
				new DialogoOpcionesAlerta(this, "Introduzca usuario y contraseña", "ERROR");
				nombre.requestFocusInWindow();
			}

		} else if (e.getActionCommand().equals("apagar")) {
			DialogoOpcionesConfirmar dialogo = new DialogoOpcionesConfirmar(this, "¿Desea cerrar la aplicación?", "PREGUNTA");
			if (dialogo.getAceptar()) {
				usuario.guardarListaUsuarios(mapaUsuarios);
				pararMusica();
				this.dispose();
			} else {
				nombre.requestFocusInWindow();
				nombre.selectAll();
			}

		} else if (e.getActionCommand().equals("registro")) {
			nombre.setText(null);
			contrasena.setText(null);
			nombre.requestFocusInWindow();
			dialogoAnadirUsuario();	
		}
	}

	/**
	 * @brief Método para obtener el valor de la variable usarioValido
	 * @return boolean
	 */
	public boolean getUsuarioValido() {
		return usuarioValido;
	}

	/**
	 * @brief Método para obtener el valor de la variable usuario
	 * @return Usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	
}