/**
 * @file PanelSimulacion.java
 * @brief Clase para crear un panel que permite visualizar la simulacion del circuito seleccionado
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete panelSimulacion
 */

/** @brief Paquete panelSimulacion
 */
package panelsimulacion;

/** @brief Librerias
 */

import interfaces.Observable;
import interfaces.Observer;
import panelconfiguracion.Circuito;
import panelconfiguracion.Coche;
import recursos.BarraProceso;
import recursos.Fisica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @brief Clase PanelSimulacion
 */
public class PanelSimulacion extends JPanel implements ActionListener, Observer {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private static final String ARIAL = "Arial";
    private JFrame ventana;
    private Circuito circuito;
    private Coche coche;
    private PanelDibujo panelDibujo;
    private JButton iniciar;
    private JButton detener;
    private JLabel nombreCircuito;
    private JLabel estado;
    private BarraProceso barraProceso;

    /**
     * @brief Constructor
     * @param ventana Ventana principal del programa
     */
    public PanelSimulacion(JFrame ventana) {
        this.setLayout(new BorderLayout(0, 10));
        this.ventana = ventana;

        this.add(crearPanelTitulo(), BorderLayout.NORTH);
        this.add(crearPanelDatos(), BorderLayout.CENTER);
        this.add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    /**
     * @brief Metodo del panel titulo: contiene un titulo
     * @return Component
     */
    private Component crearPanelTitulo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEtchedBorder());

        nombreCircuito = new JLabel();
        nombreCircuito.setFont(new Font(ARIAL, Font.BOLD, 20));

        panel.add(nombreCircuito);

        return panel;
    }

    /**
     * @brief Metodo del panel datos: contiene los paneles para mostrar la barra de proceso y el circuito
     * @return Component
     */
    private Component crearPanelDatos() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));

        panel.add(crearPanelProceso(), BorderLayout.WEST);
        panel.add(crearPanelDibujo(), BorderLayout.CENTER);

        return panel;
    }

    /**
     * @brief Metodo del panel proceso: contiene la barra de proceso y su estado
     * @return Component
     */
    private Component crearPanelProceso() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setPreferredSize(new Dimension(200, 0));

        barraProceso = new BarraProceso(205, 460, "VERTICAL", new Color(16, 137, 141), false, false);
        barraProceso.addObserver(this);

        panel.add(crearPanelEstado(), BorderLayout.NORTH);
        panel.add(barraProceso, BorderLayout.CENTER);

        return panel;
    }

    /**
     * @brief Metodo del panel estado: contiene el estado de la barra de proceso
     * @return Component
     */
    private Component crearPanelEstado() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        estado = new JLabel();
        estado.setText("0 % COMPLETADO");
        estado.setFont(new Font(ARIAL, Font.BOLD, 15));

        panel.add(estado);

        return panel;
    }

    /**
     * @brief Metodo del panel dibujo: contiene la animacion de la simulacion
     * @return Component
     */
    private Component crearPanelDibujo() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        panelDibujo = new PanelDibujo();

        panel.add(panelDibujo, BorderLayout.CENTER);

        return panel;
    }

    /**
     * @brief Metodo del panel botones: contiene los botones iniciarBtn y detenerBtn
     * @return Component
     */
    private Component crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        panel.setPreferredSize(new Dimension(0, 50));

        iniciar = new JButton("  INICIAR", new ImageIcon("iconos\\iniciarBtn.png"));
        iniciar.setFont(new Font(ARIAL, Font.BOLD, 20));
        iniciar.setMnemonic(KeyEvent.VK_I);
        iniciar.setActionCommand("iniciarBtn");
        iniciar.addActionListener(this);

        detener = new JButton("  DETENER", new ImageIcon("iconos\\detenerBtn.png"));
        detener.setFont(new Font(ARIAL, Font.BOLD, 20));
        detener.setMnemonic(KeyEvent.VK_D);
        detener.setActionCommand("detenerBtn");
        detener.addActionListener(this);
        detener.setEnabled(false);

        panel.add(iniciar);
        panel.add(detener);

        return panel;
    }

    /**
     * @brief Metodo para iniciar la simulacion
     * @return void
     */
    public void iniciarSimulacion() {
        Fisica.calcularDistancia(circuito);
        Fisica.calcularVelocidadMaxima(circuito);
        Fisica.calcularVueltasRecta(circuito, coche);
        Fisica.calcularVueltasCurva(circuito, coche);
        Fisica.calcularTiempo(circuito);

        panelDibujo.moverCoche(circuito.getTiempoRecorrido());
        barraProceso.iniciar(circuito.getTiempoRecorrido());
    }

    /**
     * @brief Metodo para crear un dialogo que permite visualizar los datos de la simulacion completada
     * @return void
     */
    public void mostrarResultado() {
        new PanelResultado(ventana, circuito);
        circuito.setSimulacionRealizada(true);
    }

    /**
     * @brief Metodo para tratar las acciones realizadas por el usuario
     * @param e Accion realizada por el usuario
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("iniciarBtn")) {
            iniciar.setEnabled(false);
            detener.setEnabled(true);
            iniciarSimulacion();
        } else if (e.getActionCommand().equals("detenerBtn")) {
            iniciar.setEnabled(true);
            detener.setEnabled(false);
            panelDibujo.pararCronometro();
            barraProceso.reiniciar();
        }
    }

    /**
     * @brief Metodo que se ejecuta tras detectar un cambio en una clase observable
     * @param observable Clase que es observada para saber cuando cambia
     * @param objeto Referencia a la clase que ha tenido un cambio
     * @return void
     */
    @Override
    public void update(Observable observable, Object objeto) {
        estado.setText(String.valueOf(barraProceso.getPorcentaje()) + " %" + " COMPLETADO");
        if (barraProceso.getPorcentaje() == 100) {
            iniciar.setEnabled(true);
            detener.setEnabled(false);
            barraProceso.detener();
            mostrarResultado();
        }
    }

    /**
     * @brief Metodo para determinar el valor de la variable circuito
     * @param circuito Circuito seleccionado por el usuario
     * @return void
     */
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
        nombreCircuito.setText("Simulacion del circuito: " + circuito.getNombre());
    }

    /**
     * @brief Metodo para determinar el valor de la variable coche
     * @param coche Coche introducido por el usuario
     * @return void
     */
    public void setCoche(Coche coche) {
        this.coche = coche;
    }

}