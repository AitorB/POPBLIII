/**
 * @file Hora.java
 * @brief Clase que permite crear un JPanel que muestra la hora actual
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 01/01/2017
 * @brief Paquete recursos
 */

/** @brief Paquete recursos
 */
package recursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

/**
 * @brief Librerias
 */

/**
 * @brief Clase Hora
 */
public class Hora extends JPanel implements ActionListener {
    /** @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /** @brief Atributos
     */
    private JLabel textoCronometro;

    /**
     * @brief Constructor de la clase
     * @param colorLetra Color del texto que se muestra
     * @param letra Fuente del texto que se muestra
     * @param colorFondo Color del fondo del panel
     */
    public Hora(Color colorLetra, Font letra, Color colorFondo) {
        this.setLayout(new BorderLayout());
        this.setBackground(colorFondo);

        textoCronometro = new JLabel();
        textoCronometro.setForeground(colorLetra);
        textoCronometro.setFont(letra);
        textoCronometro.setHorizontalAlignment(JLabel.CENTER);

        this.add(textoCronometro, BorderLayout.CENTER);

        iniciar();
    }

    /** @brief Metodo para iniciarBtn la actualizacion de la hora
     *  @return void
     */
    private void iniciar() {
        Timer cronometro = new Timer(1000, this);

        cronometro.setInitialDelay(0);
        cronometro.start();
    }

    /** @brief Metodo para tratar las acciones a realizar cuando se activa el timer
     *  @param e Evento de accion
     *  @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        LocalTime horaActual = LocalTime.now();

        int hora = horaActual.getHour();
        int minuto = horaActual.getMinute();
        int segundo = horaActual.getSecond();

        textoCronometro.setText(String.format("%02d : %02d : %02d", hora, minuto, segundo));
    }

}