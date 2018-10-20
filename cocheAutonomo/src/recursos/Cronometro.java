/**
 * @file Cronometro.java
 * @brief Clase que permite crear un JPanel que muestra un cronometro
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @brief Clase Cronometro
 */
public class Cronometro extends JPanel implements ActionListener {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private JLabel textoCronometro;
    private Timer timer;
    private int minuto;
    private int segundo;
    private int milesima;

    /**
     * @brief Constructor de la clase
     * @param colorLetra Color del texto que se muestra
     * @param letra Fuente del texto que se muestra
     * @param colorFondo Color del fondo del panel
     */
    public Cronometro(Color colorLetra, Font letra, Color colorFondo) {
        this.setLayout(new BorderLayout());
        this.setBackground(colorFondo);

        textoCronometro = new JLabel("00 : 00 : 00");
        textoCronometro.setForeground(colorLetra);
        textoCronometro.setFont(letra);
        textoCronometro.setHorizontalAlignment(JLabel.CENTER);

        timer = new Timer(10, this);

        this.add(textoCronometro, BorderLayout.CENTER);
    }

    /**
     * @brief Metodo para actualizar la hora
     * @return void
     */
    public void iniciar() {
        minuto = 0;
        segundo = 0;
        milesima = 0;

        timer.setInitialDelay(0);
        timer.start();
    }

    /**
     * @brief Metodo para detenerBtn el cronometro
     * @return void
     */
    public void detener() {
        timer.stop();
    }

    /**
     * @brief Metodo para tratar las acciones a realizar cuando se activa el cronometro
     * @param e Evento de accion
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        textoCronometro.setText(String.format("%02d : %02d : %02d", minuto, segundo, milesima));

        milesima++;
        if (milesima == 100) {
            milesima = 0;
            segundo++;
        }
        if (segundo == 60) {
            segundo = 0;
            minuto++;
        }
    }

    /**
     * @brief Metodo para obtener el valor de la variable minuto
     * @return int
     */
    public int getMinuto() {
        return minuto;
    }

    /**
     * @brief Metodo para determinar el valor de la variable minuto
     * @param minuto Minuto actual
     * @return void
     */
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    /**
     * @brief Metodo para obtener el valor de la variable segundo
     * @return int
     */
    public int getSegundo() {
        return segundo;
    }

    /**
     * @brief Metodo para determinar el valor de la variable segundo
     * @param segundo Segundo actual
     * @return void
     */
    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    /**
     * @brief Metodo para obtener el valor de la variable milesima
     * @return int
     */
    public int getMilesima() {
        return milesima;
    }

    /**
     * @brief Metodo para determinar el valor de la variable milesima
     * @param milesima Milesima actual
     * @return void
     */
    public void setMilesimas(int milesima) {
        this.milesima = milesima;
    }

}