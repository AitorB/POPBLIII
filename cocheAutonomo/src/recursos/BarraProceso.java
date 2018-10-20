/**
 * @file BarraProceso.java
 * @brief Clase que permite crear un JPanel parar mostrar una barra de proceso
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

import interfaces.Observable;
import interfaces.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @brief Librerías
 */

/**
 * @brief Clase BarraProceso
 */
public class BarraProceso extends JPanel implements ActionListener, Observable {
    /**
     * @brief Número de versión de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private JProgressBar progressBar;
    private Timer cronometro;
    private int porcentaje = 0;
    private boolean barraAleatoria;
    private Random random = new Random();

    private transient Observer observer;

    /**
     * @brief Constructor
     * @param anchura Anchura de la barra de proceso
     * @param altura Altura de la barra de proceso
     * @param orientacion Orientación de la barra de proceso
     * @param color Color de la barra de proceso
     * @param mostrarPorcentaje True para monstrar porcentaje en la barra de proceso
     * @param barraAleatoria True si la barra de debe completar de forma aleatoria
     */
    public BarraProceso(int anchura, int altura, String orientacion, Color color, boolean mostrarPorcentaje,
                        boolean barraAleatoria) {
        this.setLayout(new BorderLayout());
        progressBar = new JProgressBar();

        progressBar.setPreferredSize(new Dimension(anchura, altura));
        progressBar.setOrientation((orientacion.equals("HORIZONTAL")) ? JProgressBar.HORIZONTAL : JProgressBar.VERTICAL);

        this.barraAleatoria = barraAleatoria;

        progressBar.setStringPainted(mostrarPorcentaje);
        progressBar.setForeground(color);

        this.add(progressBar, BorderLayout.CENTER);
    }

    /**
     * @brief Método para iniciar el proceso
     * @param duracionSegundos
     * @return void
     */
    public void iniciar(double duracionSegundos) {
        this.porcentaje = 0;

        cronometro = new Timer((int) ((duracionSegundos * 1000) / 100), this);
        cronometro.setActionCommand("cronometro");

        cronometro.start();
    }

    /**
     * @brief Método para detener el proceso
     * @return void
     */
    public void detener() {
        cronometro.stop();
    }

    /**
     * @brief Método para reiniciar el proceso
     * @return void
     */
    public void reiniciar() {
        cronometro.stop();
        porcentaje = 0;
        progressBar.setValue(porcentaje);
        this.notifyObservers();
    }

    /**
     * @brief Método para tratar las acciones a realizar cuando se activa el cronómetro
     * @param e Evento de acción
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        progressBar.setValue(porcentaje);

        if (!barraAleatoria) {
            this.notifyObservers();
            porcentaje++;
        } else {
            porcentaje += random.nextInt(15);
        }

        if (porcentaje >= 100) {
            this.notifyObservers();
            detener();
        }
    }

    /**
     * @brief Método que permite añadir un observador
     * @param observer Clase que observa si hay cambios
     * @return void
     */
    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    /**
     * @brief Método que permite borrar un observador
     * @param observer Clase que observa si hay cambios
     * @return void
     */
    @Override
    public void removeObserver(Observer observer) {
        this.observer = null;
    }

    /**
     * @brief Método que permite notificar cambios a los observadores
     * @return void
     */
    @Override
    public void notifyObservers() {
        this.observer.update(this, this);
    }

    /**
     * @brief Método para determinar el valor de la variable porcentaje
     * @param porcentaje Indica el porcentaje de la barra de proceso
     * @return void
     */
    public void setPorcentaje(int porcentaje) {
        this.progressBar.setValue(porcentaje);
    }

    /**
     * @brief Método para obtener el valor de la variable porcentaje
     * @return int
     */
    public int getPorcentaje() {
        return porcentaje;
    }

}