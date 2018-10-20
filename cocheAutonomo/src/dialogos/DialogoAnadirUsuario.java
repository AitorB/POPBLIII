/**
 * @file DialogoAnadirUsuario.java
 * @brief Clase para crear un dialogo que permite introducir un nuevo usuario
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete dialogos
 */

/** @brief Paquete dialogos
 */
package dialogos;

import iniciosesion.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @brief Clase DialogoAnadirUsuario
 */
public class DialogoAnadirUsuario extends DialogoAnadir {
    /**
     * @brief Nemero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private Usuario nuevoUsuario;
    private JTextField nombre;
    private JPasswordField contrasena;
    private JPasswordField confirmarContrasena;
    private Map<String, Usuario> mapaUsuarios;

    private static final String DIALOGO_ERROR = "ERROR";

    /**
     * @brief Constructor
     * @param ventana Referencia a la ventana de tipo JDialog
     * @param anchura Anchura del dialogo
     * @param altura Altura del dialogo
     * @param titulo Titulo del dialogo
     * @param mapaUsuarios Mapa que contiene todos los usuarios
     */
    public DialogoAnadirUsuario(JDialog ventana, int anchura, int altura, String titulo, Map<String, Usuario> mapaUsuarios) {
        super(ventana, anchura, altura, titulo);
        this.mapaUsuarios = mapaUsuarios;
        super.mostrarVentana();
    }

    /**
     * @brief Metodo del panel datos: contiene los datos que el usuario debe introducir
     * @return Component
     */
    protected Component crearPanelDatos() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        nombre = new JTextField();
        nombre.addActionListener(event -> contrasena.requestFocusInWindow());

        contrasena = new JPasswordField();
        contrasena.addActionListener(event -> confirmarContrasena.requestFocusInWindow());

        confirmarContrasena = new JPasswordField();
        confirmarContrasena.addActionListener(event -> aceptar.requestFocusInWindow());

        panel.add(new JLabel("Nombre:"));
        panel.add(nombre);
        panel.add(new JLabel("Contrasena:"));
        panel.add(contrasena);
        panel.add(new JLabel("Confirmar contrasena:"));
        panel.add(confirmarContrasena);

        return panel;
    }

    /**
     * @brief Metodo para comprobar si los campos estan vacios
     * @return boolean
     */
    protected boolean camposVacios() {
        boolean camposVacios = false;

        if ((nombre.getText().isEmpty() || contrasena.getPassword().length == 0
                || confirmarContrasena.getPassword().length == 0)) {
            camposVacios = true;
        }

        return camposVacios;
    }

    /**
     * @brief Metodo para verificar si el usuario ya existe
     * @return boolean
     */
    private boolean comprobarUsuario() {
        boolean usuarioValido = true;

        Iterator<String> iterator = mapaUsuarios.keySet().iterator();

        while (iterator.hasNext() && usuarioValido) {
            String clave = iterator.next();
            if (clave.equalsIgnoreCase(nombre.getText())) {
                usuarioValido = false;
            }
        }

        return usuarioValido;
    }

    /**
     * @brief Metodo para comprobar si las contrasenas son iguales
     * @return boolean
     */
    private boolean contrasenasIguales() {
        boolean camposIguales = false;

        if (Arrays.equals(contrasena.getPassword(), confirmarContrasena.getPassword())) {
            camposIguales = true;
        }

        return camposIguales;
    }

    /**
     * @brief Metodo para crear un nuevo usuario
     * @return void
     */
    private void crearUsuario() {
        nuevoUsuario = new Usuario(nombre.getText(), contrasena.getPassword());
    }

    /**
     * @brief Metodo para tratar las acciones realizadas por el usuario
     * @param e Accion realizada por el usuario
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("aceptar")) {
            if (!camposVacios()) {
                if (contrasenasIguales()) {
                    if (comprobarUsuario()) {
                        this.crearUsuario();
                        this.dispose();
                    } else {
                        new DialogoOpcionesAlerta(this, "¡El usuario ya existe!", DIALOGO_ERROR);
                        nombre.setText(null);
                        nombre.requestFocusInWindow();
                    }
                } else {
                    new DialogoOpcionesAlerta(this, "Las contrasenas no coinciden", DIALOGO_ERROR);
                    contrasena.requestFocusInWindow();
                    contrasena.setText(null);
                    confirmarContrasena.setText(null);
                }
            } else {
                new DialogoOpcionesAlerta(this, "Rellena todos los campos", DIALOGO_ERROR);
                nombre.selectAll();
                nombre.requestFocusInWindow();
            }
        }

        if (e.getActionCommand().equals("cancelar")) {
            this.dispose();
        }
    }

    /**
     * @brief Metodo para obtener el valor de la variable nuevoUsuario
     * @return Usuario
     */
    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

}