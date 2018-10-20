/**
 * @file FondoJPanel.java
 * @brief Clase que permite crear un JPanel con una imagen de fondo personalizada
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete recursos
 */

/** @brief Paquete recursos
 */
package recursos;

/** @brief Librerias
 */

import javax.swing.*;
import java.awt.*;

/** @brief Clase FondoJPanel
 */
public class FondoJPanel extends JPanel {
    /** @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /** @brief Atributos
     */
    private transient Image imagen;

    /** @brief Constructor de la clase
     *  @param imagen Imagen que se muestra en el fondo
     */
    public FondoJPanel(Image imagen) {
        if (imagen != null) {
            this.imagen = imagen;
        }
    }

    /** @brief Metodo para pintar el panel
     *  @param g Objeto de la clase graphics que permite dibujar en pantalla
     *  @return void
     */
    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        } else {
            setOpaque(true);
        }
        super.paint(g);
    }

    /** @brief Metodo para determinar el valor de la variable imagen
     *  @param imagen Imagen que se quiere poner de fondo
     *  @return void
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
        repaint();
    }

}