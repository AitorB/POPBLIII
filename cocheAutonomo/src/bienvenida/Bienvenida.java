/** @file Bienvenida.java
 *  @brief Clase que muestra una ventana de bienvenida al programa
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete bienvenida
 */
package bienvenida;

/** @brief Librerías
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.Observable;
import interfaces.Observer;
import main.Main;
import recursos.BarraProceso;
import recursos.FondoJPanel;

/**
 * @brief Clase Bienvenida
 */
public class Bienvenida extends JDialog implements Observer {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private static final int DIA_COPIA_SEGURIDAD = 5;
	private static final String FONDO_BIENVENIDA = "imagenes\\bienvenida.jpg";

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana principal de la que se lanza el diálogo
	 */
	public Bienvenida(JFrame ventana) {
		super(ventana, true);
		this.setContentPane(crearPanelPrincipal());
		this.setSize(new Dimension(Main.ANCHO_VENTANA, Main.ALTO_VENTANA));
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	/**
	 * @brief Método del panel principal: contiene el resto de paneles
	 * @return Container
	 */
	private Container crearPanelPrincipal() {
		Image fondo;
		fondo = Toolkit.getDefaultToolkit().createImage(FONDO_BIENVENIDA);

		JPanel panel = new FondoJPanel(fondo);
		panel.setLayout(new BorderLayout());

		panel.add(crearPanelBarraProceso(), BorderLayout.CENTER);
		panel.add(crearPanelAutores(), BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * @brief Método del panel barraProceso: Contiene la barra de proceso
	 * @return Component
	 */
	private Component crearPanelBarraProceso() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(590, 500, 200, 500));
		panel.setOpaque(false);
		
		BarraProceso barraProceso = new BarraProceso(800, 20, "HORIZONTAL", new Color(60, 80, 80), true, true);
		barraProceso.setOpaque(true);
		barraProceso.addObserver(this);
		
		barraProceso.iniciar(30);
		
		if(diaCorrecto()) {
			crearCopiaSeguridad();
		}
		
		panel.add(barraProceso);
		
		return panel;
	}

	/**
	 * @brief Método del panel autores: Muestra los autores del proyecto
	 * @return Component
	 */
	private Component crearPanelAutores() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));

		JLabel autores = new JLabel("AUTORES: Unai Iraeta, Julen Uribarren, Iker Mendi y Aitor Barreiro");
		autores.setForeground(Color.LIGHT_GRAY);
		autores.setFont(new Font("Arial", Font.BOLD, 12));

		panel.add(autores);

		panel.setOpaque(false);

		return panel;
	}

	/**
	 * @brief Método para comprobar si el día actual es el día seleccionado para realizar la copia de seguridad
	 * @return boolean
	 */
	private boolean diaCorrecto() {
		boolean realizarCopia = false;
		
		if(LocalDate.now().getDayOfWeek().getValue() == DIA_COPIA_SEGURIDAD){
			realizarCopia = true;
		}
		
		return realizarCopia;
	}
	
	/**
	 * @brief Método para realizar una copia de seguridad del fichero original
	 * @return void
	 */
	private void crearCopiaSeguridad() {
		File ficheroOriginal, ficheroCopia;
		
		ficheroOriginal = new File(Main.FICHERO_ORIGINAL);
		
		if(ficheroOriginal.exists()) {
			ficheroCopia = new File(Main.FICHERO_COPIA + "." + LocalDate.now().toString() + ".dat");

			if(!ficheroCopia.exists()) {

				try (InputStream reader = new FileInputStream(Main.FICHERO_ORIGINAL)) {
					try (OutputStream writer = new FileOutputStream(Main.FICHERO_COPIA + "." + LocalDate.now().toString() + ".dat")) {
						byte[] buffer = new byte[1024];
						int longitud;
						while ((longitud = reader.read(buffer)) > 0) {
							writer.write(buffer, 0, longitud);
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @brief Método para cambiar la opacidad de la ventana al cerrarla
	 * @return void
	 */
	private void cerrarVentana() {
		float valorOpacidad = 1;
		try {
			do {
				this.setOpacity(valorOpacidad);
				Thread.sleep(50);
				valorOpacidad -= 0.1;
			} while (valorOpacidad > 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.dispose();
		}
	}

	/**
	 * @brief Método que se ejecuta tras detectar un cambio en una clase observable
	 * @param observable Clase que es observada para saber cuando cambia
	 * @param objeto Referencia a la clase que ha tenido un cambio
	 * @return void
	 */
	@Override
	public void update(Observable observable, Object objeto) {
		cerrarVentana();
	}

}