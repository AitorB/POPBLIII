/**
 * @file PanelDatosSimulacion.java
 * @brief Clase para crear un panel que muestra los datos de la simulacion realizada
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
import recursos.Fisica;

import javax.swing.*;
import java.awt.*;

/**
 * @brief Clase PanelDatosSimulacion
 */
public class PanelDatosSimulacion extends JPanel {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private Circuito circuito;
    private Coche coche;

    private JTextField distancia;
    private JTextField tiempo;
    private JTextField velocidad;
    private JTextField vueltasRueda;

    private static final String ARIAL = "Arial";

    /**
     * @brief Constructor
     */
    public PanelDatosSimulacion() {
        super(new BorderLayout(10, 0));
        this.setPreferredSize(new Dimension(0, 90));

        this.add(crearPanelTitulo(), BorderLayout.WEST);
        this.add(crearPanelDatos(), BorderLayout.CENTER);
    }

    /**
     * @brief Metodo del panel titulo: contiene un titulo
     * @return Component
     */
    private Component crearPanelTitulo() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 5));
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.setPreferredSize(new Dimension(180, 0));

        JLabel textoSuperior = new JLabel("DATOS");
        textoSuperior.setFont(new Font(ARIAL, Font.BOLD, 20));
        textoSuperior.setHorizontalAlignment(JLabel.CENTER);
        textoSuperior.setVerticalAlignment(JLabel.BOTTOM);

        JLabel textoInferior = new JLabel("SIMULACION");
        textoInferior.setFont(new Font(ARIAL, Font.BOLD, 20));
        textoInferior.setHorizontalAlignment(JLabel.CENTER);
        textoInferior.setVerticalAlignment(JLabel.NORTH);

        panel.add(textoSuperior);
        panel.add(textoInferior);

        return panel;
    }

    /**
     * @brief Metodo del panel datos: contiene los datos de la simulacion
     * @return Component
     */
    private Component crearPanelDatos() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 0, 5));
        panel.setBorder(BorderFactory.createEtchedBorder());

        panel.add(crearPanelDistancia());
        panel.add(crearPanelVelocidad());
        panel.add(crearPanelTiempo());
        panel.add(crearPanelVueltasRueda());

        return panel;
    }

    /**
     * @brief Metodo del panel distancia: muestra la distancia total a recorrer en
     *        el circuito
     * @return Component
     */
    private Component crearPanelDistancia() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel textoDistancia = new JLabel("DISTANCIA:");
        textoDistancia.setFont(new Font(ARIAL, Font.BOLD, 12));
        textoDistancia.setHorizontalAlignment(JTextField.CENTER);

        distancia = new JTextField("-");
        distancia.setFont(new Font(ARIAL, Font.BOLD, 17));
        distancia.setHorizontalAlignment(JTextField.CENTER);
        distancia.setBorder(null);
        distancia.setEditable(false);
        distancia.setBackground(Color.WHITE);

        panel.add(textoDistancia);
        panel.add(distancia);

        return panel;
    }

    /**
     * @brief Metodo del panel tiempo: muestra el tiempo necesario para completar el
     *        circuito
     * @return Component
     */
    private Component crearPanelTiempo() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel textoTiempo = new JLabel("TIEMPO:");
        textoTiempo.setFont(new Font(ARIAL, Font.BOLD, 12));
        textoTiempo.setHorizontalAlignment(JTextField.CENTER);

        tiempo = new JTextField("-");
        tiempo.setFont(new Font(ARIAL, Font.BOLD, 17));
        tiempo.setHorizontalAlignment(JTextField.CENTER);
        tiempo.setBorder(null);
        tiempo.setEditable(false);
        tiempo.setBackground(Color.WHITE);

        panel.add(textoTiempo);
        panel.add(tiempo);

        return panel;
    }

    /**
     * @brief Metodo del panel velocidad: muestra a que velocidad se realiza el
     *        circuito
     * @return Component
     */
    private Component crearPanelVelocidad() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel textoVelocidad = new JLabel("VELOCIDAD:");
        textoVelocidad.setFont(new Font(ARIAL, Font.BOLD, 12));
        textoVelocidad.setHorizontalAlignment(JTextField.CENTER);

        velocidad = new JTextField("-");
        velocidad.setFont(new Font(ARIAL, Font.BOLD, 17));
        velocidad.setHorizontalAlignment(JTextField.CENTER);
        velocidad.setBorder(null);
        velocidad.setEditable(false);
        velocidad.setBackground(Color.WHITE);

        panel.add(textoVelocidad);
        panel.add(velocidad);

        return panel;
    }

    /**
     * @brief Metodo del panel vueltas rueda: muestra el numero de vueltas de la
     *        rueda para completar el circuito
     * @return Component
     */
    private Component crearPanelVueltasRueda() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 9));

        JLabel textoVueltasRueda = new JLabel("VUELTAS RUEDA:");
        textoVueltasRueda.setFont(new Font(ARIAL, Font.BOLD, 12));
        textoVueltasRueda.setHorizontalAlignment(JTextField.CENTER);

        vueltasRueda = new JTextField("-");
        vueltasRueda.setFont(new Font(ARIAL, Font.BOLD, 17));
        vueltasRueda.setHorizontalAlignment(JTextField.CENTER);
        vueltasRueda.setBorder(null);
        vueltasRueda.setEditable(false);
        vueltasRueda.setBackground(Color.WHITE);

        panel.add(textoVueltasRueda);
        panel.add(vueltasRueda);

        return panel;
    }

    /**
     * @brief Metodo para mostrar los datos de la simulacion si esta se ha realizado
     * @return void
     */
    public void setDatosSimulacion() {
        if (circuito.isSimulacionRealizada()) {
            distancia.setText(String.format("%.2f", circuito.getDistancia()) + " m");
            tiempo.setText(String.format("%.2f", circuito.getTiempoRecorrido()) + " s");
            velocidad.setText(String.format("%.2f", circuito.getVelocidadMaxima()) + " km/h");
            vueltasRueda.setText(String.valueOf(Fisica.calcularVueltasTotal(coche)));
        } else {
            distancia.setText("-");
            tiempo.setText("-");
            velocidad.setText("-");
            vueltasRueda.setText("-");
        }
    }

    /**
     * @brief Metodo para determinar el valor del objeto coche
     * @param coche Datos del coche a utilizar por el usuario
     * @return void
     */
    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    /**
     * @brief Metodo para determinar el valor del objeto circuito
     * @param circuito Datos del circuito seleccionado
     * @return void
     */
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

}