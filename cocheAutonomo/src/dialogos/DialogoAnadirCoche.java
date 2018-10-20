/**
 * @file DialogoAnadirCoche.java
 * @brief Clase para crear un dialogo que permite introducir datos del coche
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete dialogos
 */

/** @brief Paquete dialogos
 */
package dialogos;

import panelconfiguracion.Coche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @brief Clase DialogoAnadirCoche
 */
public class DialogoAnadirCoche extends DialogoAnadir {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private static final String ERROR = "ERROR";
    private Coche coche;
    private Coche nuevoCoche;
    private JTextField modelo;
    private JTextField longitud;
    private JTextField distanciaEjes;
    private JTextField diametroRueda;
    private JTextField masa;


    /**
     * @brief Constructor
     * @param ventana Referencia a la ventana de tipo JFrame
     * @param anchura Anchura del dialogo
     * @param altura Altura del dialogo
     * @param titulo Titulo del dialogo
     * @param coche Referencia al objeto coche ya introducido
     */
    public DialogoAnadirCoche(JFrame ventana, int anchura, int altura, String titulo, Coche coche) {
        super(ventana, anchura, altura, titulo);
        this.coche = coche;
        if (coche != null) {
            mostrarCoche();
        }
        super.mostrarVentana();
    }

    /**
     * @brief Metodo del panel datos: contiene los datos que el usuario debe introducir
     * @return Component
     */
    protected Component crearPanelDatos() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 0, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        modelo = new JTextField();
        modelo.setHorizontalAlignment(JTextField.RIGHT);
        modelo.addActionListener(event -> longitud.requestFocusInWindow());

        longitud = new JTextField();
        longitud.setHorizontalAlignment(JTextField.RIGHT);
        longitud.addActionListener(event -> distanciaEjes.requestFocusInWindow());

        distanciaEjes = new JTextField();
        distanciaEjes.setHorizontalAlignment(JTextField.RIGHT);
        distanciaEjes.addActionListener(event -> diametroRueda.requestFocusInWindow());

        diametroRueda = new JTextField();
        diametroRueda.setHorizontalAlignment(JTextField.RIGHT);
        diametroRueda.addActionListener(event -> masa.requestFocusInWindow());

        masa = new JTextField();
        masa.setHorizontalAlignment(JTextField.RIGHT);
        masa.addActionListener(e -> aceptar.requestFocusInWindow());

        panel.add(new JLabel("Modelo:"));
        panel.add(modelo);
        panel.add(new JLabel("Longitud (cm):"));
        panel.add(longitud);
        panel.add(new JLabel("distancia entre ejes(cm):"));
        panel.add(distanciaEjes);
        panel.add(new JLabel("Diametro de la rueda (cm):"));
        panel.add(diametroRueda);
        panel.add(new JLabel("Masa (g):"));
        panel.add(masa);

        return panel;
    }

    /**
     * @brief Metodo para comprobar que los campos estan vacios
     * @return boolean
     */
    protected boolean camposVacios() {
        boolean camposVacios = false;

        if ((modelo.getText().isEmpty() || longitud.getText().isEmpty() || distanciaEjes.getText().isEmpty()
                || diametroRueda.getText().isEmpty()) || masa.getText().isEmpty()) {
            camposVacios = true;
        }

        return camposVacios;
    }

    /**
     * @brief Metodo para comprobar que los datos introducidos son correctos
     * @return boolean
     */
    private boolean comprobarDatos() {
        boolean datosValidos = true;

        try {
            if (Double.parseDouble(longitud.getText()) < 15 || Double.parseDouble(longitud.getText()) > 100) {
                datosValidos = false;
                new DialogoOpcionesAlerta(this,
                        "¡Datos incorrectos! Valor longitud: mayor a 15 centimetros y menor a 100 centimetros", ERROR);
                longitud.setText(null);
                longitud.requestFocusInWindow();

            } else if (Double.parseDouble(distanciaEjes.getText()) < 10 || Double.parseDouble(distanciaEjes.getText()) > 50) {
                datosValidos = false;
                new DialogoOpcionesAlerta(this,
                        "¡Datos incorrectos! Valor distancia entre ejes: mayor a 10 centimetros y menor a 50 centimetros", ERROR);
                distanciaEjes.setText(null);
                distanciaEjes.requestFocusInWindow();

            } else if (Double.parseDouble(diametroRueda.getText()) < 2 || Double.parseDouble(diametroRueda.getText()) > 20) {
                datosValidos = false;
                new DialogoOpcionesAlerta(this,
                        "¡Datos incorrectos! Valor diametro de rueda: mayor a 2 centimetros y menor a 20 centimetros", ERROR);
                diametroRueda.setText(null);
                diametroRueda.requestFocusInWindow();
            } else if (Double.parseDouble(masa.getText()) < 100 || Double.parseDouble(masa.getText()) > 10000) {
                datosValidos = false;
                new DialogoOpcionesAlerta(this, "¡Datos incorrectos! Valor masa: mayor a 100 g y menor a 10000 g", ERROR);
                masa.setText(null);
                masa.requestFocusInWindow();
            }
        } catch (NumberFormatException e) {
            datosValidos = false;
            new DialogoOpcionesAlerta(this, "¡Formato de datos no valido!", ERROR);
            longitud.setText(null);
            distanciaEjes.setText(null);
            diametroRueda.setText(null);
            masa.setText(null);
            longitud.requestFocusInWindow();
        }

        return datosValidos;
    }

    /**
     * @brief Metodo para mostrar datos del coche si ya ha sido previamente introducido
     * @return void
     */
    private void mostrarCoche() {
        modelo.setText(coche.getModelo());
        longitud.setText(String.valueOf(coche.getLongitud()));
        distanciaEjes.setText(String.valueOf(coche.getDistanciaEjes()));
        diametroRueda.setText(String.valueOf(coche.getDiametroRueda()));
        masa.setText(String.valueOf(coche.getMasa()));
    }

    /**
     * @brief Metodo para crear un nuevo coche
     * @return void
     */
    private void crearCoche() {
        nuevoCoche = new Coche(modelo.getText(), Double.valueOf(longitud.getText()), Double.valueOf(distanciaEjes.getText()),
                Double.valueOf(diametroRueda.getText()), Double.valueOf(masa.getText()));
    }

    /**
     * @brief Metodo para tratar las acciones realizadas por el usuario
     * @param e Accion realizada por el usuario
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("aceptar")) {
            if (!camposVacios()) {
                if (comprobarDatos()) {
                    this.crearCoche();
                    this.dispose();
                }
            } else {
                new DialogoOpcionesAlerta(this, "Rellena todos los campos", ERROR);
                modelo.selectAll();
                modelo.requestFocusInWindow();
            }
        }
        if (e.getActionCommand().equals("cancelar")) {
            this.dispose();
        }
    }

    /**
     * @brief Metodo para obtener el valor de la variable nuevoCoche
     * @return Coche
     */
    public Coche getNuevoCoche() {
        return nuevoCoche;
    }

}