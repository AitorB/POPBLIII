/**
 * @file PanelCoche.java
 * @brief Clase que crea un panel que permite gestionar los datos del coche
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

import dialogos.DialogoAnadirCoche;
import iniciosesion.Usuario;
import principal.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @brief Clase PanelCoche
 */
public class PanelCoche extends JPanel implements ActionListener {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private JFrame ventana;
    private Usuario usuario;
    private Coche coche;
    private JLabel textoModelo;
    private JTextField valorLongitud;
    private JTextField valorDistanciaEjes;
    private JTextField valorDiametroRueda;
    private JTextField valorMasa;
    private static final String FONT = "Arial";

    /**
     * @brief Constructor
     * @param ventana Referencia a la ventana de la que se lanza el dialogo
     * @param usuario Usuario con el que se ha iniciado sesion
     */
    public PanelCoche(JFrame ventana, Usuario usuario) {
        super(new BorderLayout(0, 10));

        this.ventana = ventana;
        this.usuario = usuario;
        cargarCoche();

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

        JLabel titulo = new JLabel("Configuracion del coche");
        titulo.setFont(new Font(FONT, Font.BOLD, 20));

        panel.add(titulo);

        return panel;
    }

    /**
     * @brief Metodo del panel datos: contiene los paneles para mostrar la informacion del coche
     * @return Component
     */
    private Component crearPanelDatos() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 10));

        panel.add(crearPanelImagenCoche());
        panel.add(crearPanelInfoCoche());

        return panel;
    }

    /**
     * @brief Metodo del panel imagen coche: muestra una imagen
     * @return Component
     */
    private Component crearPanelImagenCoche() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel imagen = new JLabel(new ImageIcon("imagenes\\coche_rc.jpg"));

        panel.setBackground(Color.WHITE);

        panel.add(imagen, BorderLayout.CENTER);

        return panel;
    }

    /**
     * @brief Metodo del panel informacion coche: muestra informacion del coche
     * @return Component
     */
    private Component crearPanelInfoCoche() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 5),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));

        panel.add(crearPanelDatosCocheNorte(), BorderLayout.NORTH);
        panel.add(crearPanelDatosCocheCentro(), BorderLayout.CENTER);

        return panel;
    }

    /**
     * @brief Metodo del panel norte izquierda: contiene un titulo
     * @return Component
     */
    private Component crearPanelDatosCocheNorte() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE),
                BorderFactory.createEmptyBorder(13, 0, 13, 0)));

        textoModelo = new JLabel("INTRODUCE UN COCHE");
        textoModelo.setFont(new Font(FONT, Font.BOLD, 20));

        if (coche != null) {
            textoModelo.setText(String.valueOf(coche.getModelo()));
        }

        panel.add(textoModelo);

        return panel;
    }

    /**
     * @brief Metodo del panel datos circuitos: muestra informacion del circuito seleccionado
     * @return Component
     */
    private Component crearPanelDatosCocheCentro() {
        JPanel panel = new JPanel((new GridLayout(2, 4, 10, 10)));

        JLabel textoLongitud = new JLabel("Longitud:");
        textoLongitud.setFont(new Font(FONT, Font.BOLD, 15));

        valorLongitud = new JTextField();
        valorLongitud.setFont(new Font(FONT, Font.BOLD, 17));
        valorLongitud.setHorizontalAlignment(JTextField.CENTER);
        valorLongitud.setBorder(null);
        valorLongitud.setEditable(false);
        valorLongitud.setBackground(Color.WHITE);

        JLabel textoDistanciaEjes = new JLabel("Distancia ejes:");
        textoDistanciaEjes.setFont(new Font(FONT, Font.BOLD, 15));

        valorDistanciaEjes = new JTextField();
        valorDistanciaEjes.setFont(new Font(FONT, Font.BOLD, 17));
        valorDistanciaEjes.setHorizontalAlignment(JTextField.CENTER);
        valorDistanciaEjes.setBorder(null);
        valorDistanciaEjes.setEditable(false);
        valorDistanciaEjes.setBackground(Color.WHITE);

        JLabel textoDiametroRueda = new JLabel("Diametro rueda:");
        textoDiametroRueda.setFont(new Font(FONT, Font.BOLD, 15));

        valorDiametroRueda = new JTextField();
        valorDiametroRueda.setFont(new Font(FONT, Font.BOLD, 17));
        valorDiametroRueda.setHorizontalAlignment(JTextField.CENTER);
        valorDiametroRueda.setBorder(null);
        valorDiametroRueda.setEditable(false);
        valorDiametroRueda.setBackground(Color.WHITE);

        JLabel textoMasa = new JLabel("Masa:");
        textoMasa.setFont(new Font(FONT, Font.BOLD, 15));

        valorMasa = new JTextField();
        valorMasa.setFont(new Font(FONT, Font.BOLD, 17));
        valorMasa.setHorizontalAlignment(JTextField.CENTER);
        valorMasa.setBorder(null);
        valorMasa.setEditable(false);
        valorMasa.setBackground(Color.WHITE);

        if (coche != null) {
            valorLongitud.setText(String.valueOf(coche.getLongitud()) + " cm");
            valorDistanciaEjes.setText(String.valueOf(coche.getDistanciaEjes()) + " cm");
            valorDiametroRueda.setText(String.valueOf(coche.getDiametroRueda()) + " cm");
            valorMasa.setText(String.valueOf(coche.getMasa()) + " g");
        }

        panel.add(textoLongitud);
        panel.add(valorLongitud);
        panel.add(textoDistanciaEjes);
        panel.add(valorDistanciaEjes);
        panel.add(textoDiametroRueda);
        panel.add(valorDiametroRueda);
        panel.add(textoMasa);
        panel.add(valorMasa);

        return panel;
    }

    /**
     * @brief Metodo del panel botones: contiene el boton configurar coche
     * @return Component
     */
    private Component crearPanelBotones() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton configurar = new JButton("  CONFIGURAR", new ImageIcon("iconos\\configurar.png"));
        configurar.setFont(new Font(FONT, Font.BOLD, 20));
        configurar.setMnemonic(KeyEvent.VK_O);
        configurar.setPreferredSize(new Dimension(0, 50));
        configurar.setActionCommand("configurar");
        configurar.addActionListener(this);

        panel.add(configurar, BorderLayout.CENTER);

        return panel;
    }

    /**
     * @brief Metodo para crear un dialogo que permite configurar el coche
     * @return void
     */
    private void configurarCoche() {
        DialogoAnadirCoche dialogo = new DialogoAnadirCoche(ventana, 350, 290, "DATOS DEL COCHE", coche);

        Coche nuevoCoche = dialogo.getNuevoCoche();

        if (nuevoCoche != null) {
            if (coche == null) {
                ((VentanaPrincipal) ventana).getPanelConfiguracion().getPanelCircuitos().setBotonAnadir(true);
            }
            this.coche = nuevoCoche;
            textoModelo.setText(String.valueOf(coche.getModelo()));
            valorLongitud.setText(String.valueOf(coche.getLongitud()) + " cm");
            valorDistanciaEjes.setText(String.valueOf(coche.getDistanciaEjes()) + " cm");
            valorDiametroRueda.setText(String.valueOf(coche.getDiametroRueda()) + " cm");
            valorMasa.setText(String.valueOf(coche.getMasa()) + " g");
        }
    }

    /**
     * @brief Metodo para cargar los datos del coche
     * @return void
     */
    private void cargarCoche() {
        coche = usuario.getCoche();
    }

    /**
     * @brief Metodo para tratar las acciones realizadas por el usuario
     * @param e Accion realizada por el usuario
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("configurar")) {
            configurarCoche();
        }
    }

    /**
     * @brief Metodo para obtener el valor del objeto coche
     * @return Coche
     */
    public Coche getCoche() {
        return coche;
    }

}