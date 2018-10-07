/** @file PanelMovimiento.java
 *  @brief Clase para crear un panel que permite visualizar el panel de pruebas de movimiento
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete panelPruebas
 */
package panelpruebas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import dialogos.DialogoOpcionesAlerta;
import xBee.DispositivoXBee;

/**
 * @brief Clase PanelMovimiento
 */
public class PanelMovimiento extends Pruebas {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private JButton izquierda, centro, derecha;

	/**
	 * @brief Constructor
	 * @param titulo Título del panel
	 */
	public PanelMovimiento(String titulo) {
		super(titulo);
	}

	/**
	 * @brief Método del panel datos: contiene los botones para controlar la dirección del coche
	 * @return Component
	 */
	protected Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 15, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		izquierda = new JButton(" IZQUIERDA", new ImageIcon("iconos\\izquierda.png"));
		izquierda.setFont(new Font("Arial", Font.BOLD, 12));
		izquierda.setPreferredSize(new Dimension(180, 0));
		izquierda.setMnemonic(KeyEvent.VK_I);
		izquierda.setActionCommand("izquierda");
		izquierda.addActionListener(this);
		izquierda.setEnabled(false);

		centro = new JButton(" CENTRO", new ImageIcon("iconos\\centro.png"));
		centro.setFont(new Font("Arial", Font.BOLD, 12));
		centro.setPreferredSize(new Dimension(180, 0));
		centro.setMnemonic(KeyEvent.VK_D);
		centro.setActionCommand("centro");
		centro.addActionListener(this);
		centro.setEnabled(false);

		derecha = new JButton(" DERECHA", new ImageIcon("iconos\\derecha.png"));
		derecha.setFont(new Font("Arial", Font.BOLD, 12));
		derecha.setPreferredSize(new Dimension(180, 0));
		derecha.setMnemonic(KeyEvent.VK_D);
		derecha.setActionCommand("derecha");
		derecha.addActionListener(this);
		derecha.setEnabled(false);

		panel.add(izquierda);
		panel.add(centro);
		panel.add(derecha);

		return panel;
	}

	/**
	 * @brief Método para comprobar el estado de los botones y poder activarlos o desactivarlos
	 * @return Component
	 */
	private void comprobarEstadoBotones(String accion) {
		switch (accion) {
		case "iniciar":
			izquierda.setEnabled(true);
			centro.setEnabled(false);
			derecha.setEnabled(true);
			iniciar.setEnabled(false);
			detener.setEnabled(true);
			break;
			
		case "detener":
			izquierda.setEnabled(false);
			centro.setEnabled(false);
			derecha.setEnabled(false);
			iniciar.setEnabled(true);
			detener.setEnabled(false);
			break;
			
		case "izquierda":
			izquierda.setEnabled(false);
			centro.setEnabled(true);
			derecha.setEnabled(true);
			break;
			
		case "centro":
			centro.setEnabled(false);
			izquierda.setEnabled(true);
			derecha.setEnabled(true);
			break;
			
		case "derecha":
			derecha.setEnabled(false);
			izquierda.setEnabled(true);
			centro.setEnabled(true);
			break;
		}
	}

	/**
	 * @brief Método para tratar las acciones realizadas por el usuario
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (dispositivoXBee != null) {
			if (e.getActionCommand().equals("iniciar")) {
				if(!dispositivoXBee.comprobarEstado()) {
					comprobarEstadoBotones("iniciar");
	
					dispositivoXBee.iniciarXBee();
					dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.ADELANTE,
							DispositivoXBee.INICIAR, DispositivoXBee.DETENER);
				} else {
					new DialogoOpcionesAlerta(ventana, "¡Ya hay una prueba en curso!", "ERROR");
				}
				
			} else if (e.getActionCommand().equals("detener")) {
				comprobarEstadoBotones("detener");
				dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.DETENER,
						DispositivoXBee.DETENER, DispositivoXBee.DETENER);
				
				dispositivoXBee.detenerXBee();
			} else if (e.getActionCommand().equals("izquierda")) {
				comprobarEstadoBotones("izquierda");
				dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.IZQUIERDA,
						DispositivoXBee.INICIAR, 100);
				
			} else if (e.getActionCommand().equals("centro")) {
				comprobarEstadoBotones("centro");
				dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.ADELANTE,
						DispositivoXBee.INICIAR, DispositivoXBee.ADELANTE);
				
			} else if (e.getActionCommand().equals("derecha")) {
				comprobarEstadoBotones("derecha");
				dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.DERECHA,
						DispositivoXBee.INICIAR, 100);
			}
			
		} else {
			new DialogoOpcionesAlerta(ventana, "¡Dispositivo XBee no conectado!", "ERROR");
		}
	}

}