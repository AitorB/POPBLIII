/**
 * @file DialogoOpciones.java
 * @brief Clase abstracta para implementar dialogos de opciones personalizados
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

/** @brief Librerías
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief Clase DialogoOpciones
 */
public abstract class DialogoOpciones extends JDialog implements ActionListener {
    /**
     * @brief Número de versión de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private static final Logger LOGGER = Logger.getLogger(DialogoOpciones.class.getName());
    private String imagen;
    private String texto;

    /**
     * @brief Constructor para crear un diálogo desde un JFrame
     * @param ventana Referencia a la ventana de tipo JFrame
     * @param texto Mensaje a mostrar en el diálogo
     * @param imagen Nombre de la imagen a mostrar en el diálogo
     */
    public DialogoOpciones(JFrame ventana, String texto, String imagen) {
        super(ventana);
        this.texto = texto;
        this.imagen = imagen.toLowerCase();
        this.setContentPane(crearPanelPrincipal());
        this.setUndecorated(true);
        this.pack();
        this.setLocationRelativeTo(ventana);
    }

    /**
     * @brief Constructor para crear un diálogo desde un JDialog
     * @param ventana Referencia a la ventana de tipo JDialog
     * @param texto Mensaje a mostrar en el diálogo
     * @param imagen Nombre de la imagen a mostrar en el diálogo
     */
    public DialogoOpciones(JDialog ventana, String texto, String imagen) {
        super(ventana);

        this.texto = texto;
        this.imagen = imagen.toLowerCase();
        this.setContentPane(crearPanelPrincipal());
        this.setUndecorated(true);
        this.pack();
        this.setLocationRelativeTo(ventana);
    }

    /**
     * @brief Método para mostrar el diálogo
     * @return void
     */
    protected void mostrarVentana() {
        this.setModal(true);
        this.setVisible(true);
    }

    /**
     * @brief Método del panel principal: contiene el resto de paneles
     * @return Container
     */
    private Container crearPanelPrincipal() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 5),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));

        panel.add(crearPanelMensaje(), BorderLayout.CENTER);
        panel.add(crearPanelBotones(), BorderLayout.SOUTH);

        return panel;
    }

    /**
     * @brief Método del panel mensaje: contiene una imagen y el mensaje a mostrar
     * @return Component
     */
    private Component crearPanelMensaje() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel icono = new JLabel(new ImageIcon("iconos\\" + imagen + ".png"));
        JLabel mensage = new JLabel(texto);

        panel.add(icono);
        panel.add(Box.createRigidArea(new Dimension(8, 0)));
        panel.add(mensage);

        return panel;
    }

    /**
     * @brief Método del panel botones: contiene los botones del diálogo
     * @return Component
     */
    protected abstract Component crearPanelBotones();

    /**
     * @brief Método para cambiar la opacidad de la ventana al cerrarla
     * @return void
     */
    protected void cerrarVentana() {
        float valorOpacidad = 1;
        try {
            do {
                this.setOpacity(valorOpacidad);
                Thread.sleep(20);
                valorOpacidad -= 0.1;
            } while (valorOpacidad > 0);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            this.dispose();
        }
    }

}