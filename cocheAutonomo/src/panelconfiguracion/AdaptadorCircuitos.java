/**
 * @file AdaptadorCircuitos.java
 * @brief Clase que permite personalizar el aspecto en que se muestra la informacion de la lista de circuitos
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 01/01/2017
 * @brief Paquete panelConfiguracion
 */

/** @brief Paquete panelConfiguracion
 */
package panelconfiguracion;

/** @brief Librerias
 */

import javax.swing.*;
import java.awt.*;

/**
 * @brief Clase AdaptadorCircuitos
 */
public class AdaptadorCircuitos implements ListCellRenderer<Circuito> {

    /**
     * @brief Metodo para gestionar la visualizacion de la lista
     * @param listaCircuitos La lista que estamos redibujando
     * @param circuito El circuito que se ha seleccionado
     * @param indice El indice de la seleccion realizada
     * @param seleccionado Devuelve true si se realiza una seleccion en la lista
     * @param foco Devuelve true si seleccion realizada tiene el foco
     * @return Component
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Circuito> listaCircuitos, Circuito circuito, int indice,
                                                  boolean seleccionado, boolean foco) {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel lTitulo = new JLabel(circuito.getNombre());
        lTitulo.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        lTitulo.setForeground(seleccionado ? Color.WHITE : Color.BLACK);

        panel.add(lTitulo, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(seleccionado ? new Color(255, 107, 107) : Color.WHITE);
        panel.setOpaque(true);

        return panel;
    }

}