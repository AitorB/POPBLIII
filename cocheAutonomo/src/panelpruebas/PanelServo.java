/**
 * @file PanelServo.java
 * @brief Clase para crear un panel que permite visualizar el panel de pruebas del servo
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete panelPruebas
 */

/** @brief Paquete panelPruebas
 */
package panelpruebas;

import dialogos.DialogoOpcionesAlerta;
import xbee.DispositivoXBee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @brief Clase PanelServo
 */
public class PanelServo extends Pruebas {
    /**
     * @brief Número de versión de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private static final String ARIAL = "Arial";
    private static final String ERROR = "ERROR";
    private JTextField porcentajeGiro;

    /**
     * @brief Constructor
     * @param ventana Referencia a la ventana principal
     * @param titulo Título del panel
     */
    public PanelServo(JFrame ventana, String titulo) {
        super(ventana, titulo);
        this.tipoPrueba = "Servo";
    }

    /**
     * @brief Método del panel datos: contiene los paneles para introducir datos
     * @return Component
     */
    protected Component crearPanelDatos() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));

        panel.add(crearPanelRadioButton());
        panel.add(crearPanelAngulo());

        return panel;
    }

    /**
     * @brief Método del panel radio button: contiene los radio buttons
     * @return Component
     */
    private Component crearPanelRadioButton() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        JRadioButton izquierda;
        JRadioButton derecha;
        ButtonGroup grupo = new ButtonGroup();

        izquierda = new JRadioButton("  IZQUIERDA");
        izquierda.setFont(new Font(ARIAL, Font.BOLD, 12));
        izquierda.setVerticalAlignment(JButton.CENTER);
        izquierda.setSelected(true);
        izquierda.addActionListener(this);

        derecha = new JRadioButton("  DERECHA");
        derecha.setVerticalAlignment(JButton.CENTER);
        derecha.setFont(new Font(ARIAL, Font.BOLD, 12));
        derecha.addActionListener(this);

        grupo.add(izquierda);
        grupo.add(derecha);

        panel.add(izquierda);
        panel.add(derecha);

        return panel;
    }

    /**
     * @brief Método del panel angulo: pedir angulo de giro
     * @return Component
     */
    private Component crearPanelAngulo() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel texto = new JLabel("GIRO (%):");
        texto.setFont(new Font(ARIAL, Font.BOLD, 12));

        porcentajeGiro = new JTextField();
        porcentajeGiro.setFont(new Font(ARIAL, Font.BOLD, 17));
        porcentajeGiro.setHorizontalAlignment(JTextField.CENTER);
        porcentajeGiro.setBorder(null);

        panel.add(texto);
        panel.add(porcentajeGiro);

        return panel;
    }

    /**
     * @brief Método para comprobar si los campos están vacíos
     * @return boolean
     */
    private boolean camposVacios() {
        boolean camposVacios = false;

        if (porcentajeGiro.getText().isEmpty()) {
            camposVacios = true;
        }

        return camposVacios;
    }

    /**
     * @brief Método para comprobar si los datos introducidos son correctos
     * @return boolean
     */
    private boolean comprobarDatos() {
        boolean datosValidos = true;

        try {
            if (Double.parseDouble(porcentajeGiro.getText()) < 0
                    || Double.parseDouble(porcentajeGiro.getText()) > 100) {
                datosValidos = false;
                new DialogoOpcionesAlerta(ventana, "¡Datos incorrectos! Valor servo: entre 0% y 100%", ERROR);
                porcentajeGiro.setText(null);
                porcentajeGiro.requestFocusInWindow();
            }
        } catch (NumberFormatException e) {
            datosValidos = false;
            new DialogoOpcionesAlerta(ventana, "¡Formato de datos no válido!", ERROR);
            porcentajeGiro.setText(null);
            porcentajeGiro.requestFocusInWindow();
        }

        return datosValidos;
    }

    /**
     * @brief Método para tratar las acciones realizadas por el usuario
     * @param e Acción realizada por el usuario
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("iniciarBtn")) {
            if (!camposVacios()) {
                if (comprobarDatos()) {
                    comprobarXBee();
                }
            } else {
                new DialogoOpcionesAlerta(ventana, "Introduce un valor", ERROR);
                porcentajeGiro.requestFocusInWindow();
            }
        } else if (e.getActionCommand().equals("detenerBtn")) {
            detenerAction();
        }
    }

    public void comprobarXBee() {
        if (dispositivoXBee != null) {
            if (!dispositivoXBee.comprobarEstado()) {
                iniciarAction();
            } else {
                new DialogoOpcionesAlerta(ventana, "¡Ya hay una prueba en curso!", ERROR);
            }
        } else {
            new DialogoOpcionesAlerta(ventana, "¡Dispositivo XBee no conectado!", ERROR);
        }
    }

    public void iniciarAction() {
        iniciarBtn.setEnabled(false);
        detenerBtn.setEnabled(true);

        dispositivoXBee.iniciarXBee();
        dispositivoXBee.enviarDatosPrueba(DispositivoXBee.FRENOS, DispositivoXBee.ADELANTE,
                DispositivoXBee.INICIAR, Integer.valueOf(porcentajeGiro.getText()));
    }

    public void detenerAction() {
        iniciarBtn.setEnabled(true);
        detenerBtn.setEnabled(false);

        dispositivoXBee.enviarDatosPrueba(DispositivoXBee.FRENOS, DispositivoXBee.DETENER, DispositivoXBee.DETENER,
                DispositivoXBee.DETENER);
        dispositivoXBee.detenerXBee();
    }
}