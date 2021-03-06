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
 * @brief Librer�as
 */

/**
 * @brief Clase BarraProceso
 */
public class BarraProceso extends JPanel implements ActionListener, Observable {
    /**
     * @brief N�mero de versi�n de la clase
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
     * @param orientacion Orientaci�n de la barra de proceso
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
     * @brief M�todo para iniciar el proceso
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
     * @brief M�todo para detener el proceso
     * @return void
     */
    public void detener() {
        cronometro.stop();
    }

    /**
     * @brief M�todo para reiniciar el proceso
     * @return void
     */
    public void reiniciar() {
        cronometro.stop();
        porcentaje = 0;
        progressBar.setValue(porcentaje);
        this.notifyObservers();
    }

    /**
     * @brief M�todo para tratar las acciones a realizar cuando se activa el cron�metro
     * @param e Evento de acci�n
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
     * @brief M�todo que permite a�adir un observador
     * @param observer Clase que observa si hay cambios
     * @return void
     */
    @Override
    public void addObserver(Observer observer) {
        this.observer = observer;
    }

    /**
     * @brief M�todo que permite borrar un observador
     * @param observer Clase que observa si hay cambios
     * @return void
     */
    @Override
    public void removeObserver(Observer observer) {
        this.observer = null;
    }

    /**
     * @brief M�todo que permite notificar cambios a los observadores
     * @return void
     */
    @Override
    public void notifyObservers() {
        this.observer.update(this, this);
    }

    /**
     * @brief M�todo para determinar el valor de la variable porcentaje
     * @param porcentaje Indica el porcentaje de la barra de proceso
     * @return void
     */
    public void setPorcentaje(int porcentaje) {
        this.progressBar.setValue(porcentaje);
    }

    /**
     * @brief M�todo para obtener el valor de la variable porcentaje
     * @return int
     */
    public int getPorcentaje() {
        return porcentaje;
    }

}