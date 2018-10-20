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
package iniciosesion;

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
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

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
	private static final String CONTEXT = "context";
	private static final String ARIAL = "Arial";
	private static final String FONDO_PANTALLA = "imagenes\\iniciar_sesion.jpg";
	private static final String SONIDO = "sonidos\\bienvenida.mp3";

	private JTextField nombre;
	private JPasswordField contrasena;
	private JButton iniciar;
	private HashMap<String, Usuario> mapaUsuarios;
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
		Hora reloj = new Hora(Color.WHITE, new Font(ARIAL, Font.BOLD, 15), Color.WHITE);

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
		nombre.setFont(new Font(ARIAL, Font.BOLD, 15));
		nombre.setPreferredSize(new Dimension(195, 20));

		nombre.addActionListener(e -> contrasena.requestFocusInWindow());

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
		contrasena.setFont(new Font(ARIAL, Font.BOLD, 15));
		contrasena.setPreferredSize(new Dimension(195, 20));

		contrasena.addActionListener(e -> iniciar.requestFocusInWindow());

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
		texto.setFont(new Font(ARIAL, Font.BOLD, 12));

		JButton registro = new JButton("registrate");
		registro.setFont(new Font(ARIAL, Font.BOLD, 13));
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
		File archivo = new File(Main.FICHERO_ORIGINAL);

		if (!archivo.createNewFile()) {
			try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(archivo))) {
				mapaUsuarios = (HashMap<String, Usuario>) reader.readObject();
			}
			catch (Exception e) {
				LOGGER.log(CONTEXT, e);
			}
		}
	}

	/**
	 * @brief Método para crear un diálogo que permite añadir usuarios
	 * @return void
	 */
	private void dialogoAnadirUsuario() {
		DialogoAnadirUsuario dialogo = new DialogoAnadirUsuario(this, 300, 200, "NUEVO USUARIO", mapaUsuarios);

		Usuario nuevoUsuario = dialogo.getNuevoUsuario();

		if (nuevoUsuario != null) {
			mapaUsuarios.put(nuevoUsuario.getNombre().toLowerCase(), nuevoUsuario);
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
			LOGGER.log(CONTEXT, e);
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
			LOGGER.log(CONTEXT, e);
		}
	}

	/**
	 * @brief Método para comprobar si el usario introducido es correcto
	 * @param nombre Nombre del usuario
	 * @param contraseña Contraseña del usuario
	 * @return boolean
	 */
	private boolean comprobarUsuario(String nombre, char[] contrasena) {
		Iterator<String> iterator = mapaUsuarios.keySet().iterator();

		while (iterator.hasNext() && !usuarioValido) {
			String clave = iterator.next();
			Usuario valor = mapaUsuarios.get(clave);
			if (clave.equalsIgnoreCase(nombre) && Arrays.equals(valor.getContrasena(), contrasena)) {
				usuario = valor;
				usuarioValido = true;
			}
		}
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
	 * @brief Método para guardar usuarios
	 * @return void
	 */
	public void guardarListaUsuarios(Map<String, Usuario> mapaUsuarios) {
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(Main.FICHERO_ORIGINAL))) {
			writer.writeObject(mapaUsuarios);
		} catch (Exception e) {
			LOGGER.log(CONTEXT, e);
		}
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
				comprobarUsuario(nombre.getText(), contrasena.getPassword());
				if (usuarioValido) {
					guardarListaUsuarios(mapaUsuarios);
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
				guardarListaUsuarios(mapaUsuarios);
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