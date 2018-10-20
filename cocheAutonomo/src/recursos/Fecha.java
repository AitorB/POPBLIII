/** @file Fecha.java
 *  @brief Clase que permite crear un JPanel que muestra la fecha actual
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete recursos
 */
package recursos;

import java.awt.BorderLayout;
/** @brief Librerías
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/** @brief Clase Fecha
 */
public class Fecha extends JPanel implements ActionListener {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/** @brief Atributos
	 */
	private Locale localizacion;
	private JLabel textoFecha;

	/** 
	 * @brief Constructor de la clase
	 * @param colorLetra Color del texto que se muestra
	 * @param letra Fuente del texto que se muestra
	 * @param colorFondo Color del fondo del panel
	 */
	public Fecha(Color colorLetra, Font letra, Color colorFondo) {
		this.setLayout(new BorderLayout());
		this.setBackground(colorFondo);
		
		localizacion = Locale.getDefault();
		
		textoFecha = new JLabel();
		textoFecha.setHorizontalAlignment(JLabel.CENTER);
		textoFecha.setForeground(colorLetra);
		textoFecha.setFont(letra);

		this.add(textoFecha, BorderLayout.CENTER);
		
		iniciar();
	}

	/** @brief Método para iniciar la actualización de la fecha
	 *  @return void
	 */
	private void iniciar() { 
		Timer timer = new Timer(1000, this);

		timer.setInitialDelay(0);
		timer.start();
	}
		
	/** @brief Método para tratar las acciones a realizar cuando se activa el cronómetro
	 *  @param e Evento de acción
	 *  @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		LocalDate fechaActual = LocalDate.now();

		String diaSemana = fechaActual.getDayOfWeek().getDisplayName(TextStyle.FULL, localizacion).substring(0, 1).toUpperCase()
				+ fechaActual.getDayOfWeek().getDisplayName(TextStyle.FULL, localizacion).substring(1);
		int diaMes = fechaActual.getDayOfMonth();
		String mes = fechaActual.getMonth().getDisplayName(TextStyle.FULL, localizacion).substring(0, 1).toUpperCase()
				+ fechaActual.getMonth().getDisplayName(TextStyle.FULL, localizacion).substring(1);
		int anio = fechaActual.getYear();
		
		textoFecha.setText(diaSemana + ", " + diaMes + " de " + mes + " de " + anio);
	}
}