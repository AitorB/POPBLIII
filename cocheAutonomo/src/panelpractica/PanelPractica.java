/**
 * @file PanelPractica.java
 * @brief Clase para crear un panel que permite realizar una prueba real del circuito seleccionado
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete PanelPractica
 */

/** @brief Paquete PanelPractica
 */
package panelpractica;

/** @brief Librerias
 */

import panelconfiguracion.Circuito;
import panelconfiguracion.Coche;
import xbee.DispositivoXBee;

import javax.swing.*;
import java.awt.*;

/**
 * @brief Clase PanelPractica
 */
public class PanelPractica extends JPanel {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private JFrame ventana;
    private PanelDatosSimulacion panelDatosSimulacion;
    private PanelDatosTabla panelDatosTabla;
    private PanelDatosTiempoReal panelDatosTiempoReal;
    private JLabel nombreCircuito;

    /**
     * @brief Constructor
     * @param ventana Referencia a la ventana de la que se lanza el dialogo
     */
    public PanelPractica(JFrame ventana) {
        super(new BorderLayout(0, 10));
        this.ventana = ventana;

        this.add(crearPanelTitulo(), BorderLayout.NORTH);
        this.add(crearPanelDatos(), BorderLayout.CENTER);
    }

    /**
     * @brief Metodo del panel titulo: contiene un titulo
     * @return Component
     */
    private Component crearPanelTitulo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEtchedBorder());

        nombreCircuito = new JLabel();
        nombreCircuito.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(nombreCircuito);

        return panel;
    }

    /**
     * @brief Metodo del panel datos: muestra una tabla con informacion de los circuitos
     * @return Component
     */
    private Component crearPanelDatos() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));

        panelDatosSimulacion = new PanelDatosSimulacion();
        panelDatosTabla = new PanelDatosTabla(ventana);
        panelDatosTiempoReal = new PanelDatosTiempoReal(ventana, panelDatosTabla);

        panel.add(panelDatosSimulacion, BorderLayout.NORTH);
        panel.add(panelDatosTabla, BorderLayout.CENTER);
        panel.add(panelDatosTiempoReal, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * @brief Metodo para determinar el valor del objeto coche
     * @param coche Datos del coche a utilizar por el usuario
     * @return void
     */
    public void setCoche(Coche coche) {
        panelDatosSimulacion.setCoche(coche);
        panelDatosTiempoReal.setCoche(coche);
    }

    /**
     * @brief Metodo para determinar el valor del objeto circuito
     * @param circuito Datos del circuito seleccionado
     * @return void
     */
    public void setCircuito(Circuito circuito) {
        panelDatosSimulacion.setCircuito(circuito);
        panelDatosTabla.setListaModelo(circuito.getListaPracticas());
        panelDatosTiempoReal.setCircuito(circuito);

        nombreCircuito.setText("Practica real del circuito: " + circuito.getNombre());
    }

    /**
     * @brief Metodo para obtener el valor del panelDatosSimulacion
     * @return PanelDatosSimulacion
     */
    public PanelDatosSimulacion getPanelDatosSimulacion() {
        return panelDatosSimulacion;
    }

    /**
     * @brief Metodo para determinar el valor de la variable dispositivoXBee
     * @param dispositivoXBee Dispositivo XBee utilizando para realizar la comunicacion con el coche
     * @return void
     */
    public void setDispositivoXBee(DispositivoXBee dispositivoXBee) {
        panelDatosTiempoReal.setDispositivoXBee(dispositivoXBee);
    }

}