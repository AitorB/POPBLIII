/**
 * @file DialogoOpcionesAlerta.java
 * @brief Clase que permite crear un dialogo de alerta personalizado
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

/** @brief Librerias
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @brief Clase DialogoOpcionesAlerta
 */
public class DialogoOpcionesAlerta extends DialogoOpciones {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Constructor para crear un dialogo desde un JFrame
     * @param ventana Referencia a la ventana de tipo JFrame
     * @param texto Mensaje a mostrar en el dialogo
     * @param imagen Tipo de imagen a mostrar en el dialogo
     */
    public DialogoOpcionesAlerta(JFrame ventana, String texto, String imagen) {
        super(ventana, texto, imagen);
        super.mostrarVentana();
    }

    /**
     * @brief Constructor para crear un dialogo desde un JDialog
     * @param ventana Referencia a la ventana de tipo JDialog
     * @param texto Mensaje a mostrar en el dialogo
     * @param imagen Nombre de la imagen a mostrar en el dialogo
     */
    public DialogoOpcionesAlerta(JDialog ventana, String texto, String imagen) {
        super(ventana, texto, imagen);
        super.mostrarVentana();
    }

    /**
     * @brief Metodo del panel botones: contiene el boton aceptar
     * @return Component
     */
    @Override
    protected Component crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton aceptar = new JButton("Aceptar");
        aceptar.setMnemonic(KeyEvent.VK_A);
        aceptar.setActionCommand("aceptar");
        aceptar.addActionListener(this);

        panel.add(aceptar);

        return panel;
    }

    /**
     * @brief Metodo para tratar las acciones realizadas por el usuario
     * @param e Accion realizada por el usuario
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("aceptar")) {
            cerrarVentana();
        }
    }

}