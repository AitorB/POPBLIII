/**
 * @file DialogoOpcionesConfirmar.java
 * @brief Clase que permite crear un dialogo de confirmacion personalizado
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
 * @brief Clase DialogoOpcionesConfirmar
 */
public class DialogoOpcionesConfirmar extends DialogoOpciones {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private boolean aceptar;

    /**
     * @brief Constructor para crear un dialogo desde un JFrame
     * @param ventana Referencia a la ventana de tipo JFrame
     * @param texto Mensaje a mostrar en el dialogo
     * @param imagen Nombre de la imagen a mostrar en el dialogo
     */
    public DialogoOpcionesConfirmar(JFrame ventana, String texto, String imagen) {
        super(ventana, texto, imagen);
        super.mostrarVentana();
    }

    /**
     * @brief Constructor para crear un dialogo desde un JDialog
     * @param ventana Referencia a la ventana de tipo JDialog
     * @param texto Mensaje a mostrar en el dialogo
     * @param imagen Tipo de imagen a mostrar en el dialogo
     */
    public DialogoOpcionesConfirmar(JDialog ventana, String texto, String imagen) {
        super(ventana, texto, imagen);
        super.mostrarVentana();
    }

    /**
     * @brief Metodo del panel botones: contiene los botones aceptar y cancelar
     * @return Component
     */
    @Override
    protected Component crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton aceptarBtn = new JButton("Aceptar");
        aceptarBtn.setMnemonic(KeyEvent.VK_A);
        aceptarBtn.setActionCommand("aceptar");
        aceptarBtn.addActionListener(this);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setMnemonic(KeyEvent.VK_C);
        cancelar.setActionCommand("cancelar");
        cancelar.addActionListener(this);

        panel.add(aceptarBtn);
        panel.add(Box.createRigidArea(new Dimension(15, 0)));
        panel.add(cancelar);

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
            aceptar = true;
            this.dispose();
        }
        if (e.getActionCommand().equals("cancelar")) {
            aceptar = false;
            this.cerrarVentana();
        }
    }

    /**
     * @brief Metodo para obtener el valor de la variable aceptar
     * @return boolean
     */
    public boolean getAceptar() {
        return aceptar;
    }

}