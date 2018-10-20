/**
 * @file PanelConfiguracion.java
 * @brief Clase para crear un panel que permite introducir circuitos y configurar los parametros del coche
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete panelConfiguracion
 */

/** @brief Paquete panelConfiguracion
 */
package panelconfiguracion;

/** @brief Librerias
 */

import iniciosesion.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * @brief Clase PanelConfiguracion
 */
public class PanelConfiguracion extends JPanel {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private PanelCircuitos panelCircuitos;
    private PanelCoche panelCoche;

    /**
     * @brief Constructor
     * @param ventana Referencia a la ventana de la que se lanza el dialogo
     * @param usuario Usuario con el que se ha iniciado sesion
     */
    public PanelConfiguracion(JFrame ventana, Usuario usuario) {
        this.setLayout(new GridLayout(1, 2, 20, 0));

        panelCircuitos = new PanelCircuitos(ventana, usuario);
        panelCoche = new PanelCoche(ventana, usuario);

        this.add(panelCircuitos);
        this.add(panelCoche);
    }

    /**
     * @brief Metodo para obtener el valor del panel circuitos
     * @return PanelCircuitos
     */
    public PanelCircuitos getPanelCircuitos() {
        return panelCircuitos;
    }

    /**
     * @brief Metodo para obtener el valor del panel coche
     * @return PanelCoche
     */
    public PanelCoche getPanelCoche() {
        return panelCoche;
    }

}