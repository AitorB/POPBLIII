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
	private static final String ARIAL = "Arial";
	private static final String IZQUIERDA = "izquierda";
	private static final String CENTRO = "centro";
	private static final String DERECHA = "derecha";
	private static final String INICIAR = "iniciar";
	private static final String DETENER = "detener";
	private JButton izquierdaBtn;
	private JButton centroBtn;
	private JButton derechaBtn;

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

		izquierdaBtn = new JButton(" IZQUIERDA", new ImageIcon("iconos\\izquierda.png"));
		izquierdaBtn.setFont(new Font(ARIAL, Font.BOLD, 12));
		izquierdaBtn.setPreferredSize(new Dimension(180, 0));
		izquierdaBtn.setMnemonic(KeyEvent.VK_I);
		izquierdaBtn.setActionCommand(IZQUIERDA);
		izquierdaBtn.addActionListener(this);
		izquierdaBtn.setEnabled(false);

		centroBtn = new JButton(" CENTRO", new ImageIcon("iconos\\centro.png"));
		centroBtn.setFont(new Font(ARIAL, Font.BOLD, 12));
		centroBtn.setPreferredSize(new Dimension(180, 0));
		centroBtn.setMnemonic(KeyEvent.VK_D);
		centroBtn.setActionCommand(CENTRO);
		centroBtn.addActionListener(this);
		centroBtn.setEnabled(false);

		derechaBtn = new JButton(" DERECHA", new ImageIcon("iconos\\derecha.png"));
		derechaBtn.setFont(new Font(ARIAL, Font.BOLD, 12));
		derechaBtn.setPreferredSize(new Dimension(180, 0));
		derechaBtn.setMnemonic(KeyEvent.VK_D);
		derechaBtn.setActionCommand(DERECHA);
		derechaBtn.addActionListener(this);
		derechaBtn.setEnabled(false);

		panel.add(izquierdaBtn);
		panel.add(centroBtn);
		panel.add(derechaBtn);

		return panel;
	}

	/**
	 * @brief Método para comprobar el estado de los botones y poder activarlos o desactivarlos
	 * @return Component
	 */
	private void comprobarEstadoBotones(String accion) {
		switch (accion) {
		case INICIAR:
			izquierdaBtn.setEnabled(true);
			centroBtn.setEnabled(false);
			derechaBtn.setEnabled(true);
			iniciar.setEnabled(false);
			detener.setEnabled(true);
			break;
			
		case DETENER:
			izquierdaBtn.setEnabled(false);
			centroBtn.setEnabled(false);
			derechaBtn.setEnabled(false);
			iniciar.setEnabled(true);
			detener.setEnabled(false);
			break;
			
		case IZQUIERDA:
			izquierdaBtn.setEnabled(false);
			centroBtn.setEnabled(true);
			derechaBtn.setEnabled(true);
			break;
			
		case CENTRO:
			centroBtn.setEnabled(false);
			izquierdaBtn.setEnabled(true);
			derechaBtn.setEnabled(true);
			break;
			
		case DERECHA:
			derechaBtn.setEnabled(false);
			izquierdaBtn.setEnabled(true);
			centroBtn.setEnabled(true);
			break;
		default: break;
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
			if (e.getActionCommand().equals(INICIAR)) {
				if(!dispositivoXBee.comprobarEstado()) {
					comprobarEstadoBotones(INICIAR);
	
					dispositivoXBee.iniciarXBee();
					dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.ADELANTE,
							DispositivoXBee.INICIAR, DispositivoXBee.DETENER);
				} else {
					new DialogoOpcionesAlerta(ventana, "¡Ya hay una prueba en curso!", "ERROR");
				}
				
			} else if (e.getActionCommand().equals(DETENER)) {
				comprobarEstadoBotones(DETENER);
				dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.DETENER,
						DispositivoXBee.DETENER, DispositivoXBee.DETENER);
				
				dispositivoXBee.detenerXBee();
			} else if (e.getActionCommand().equals(IZQUIERDA)) {
				comprobarEstadoBotones(IZQUIERDA);
				dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.IZQUIERDA,
						DispositivoXBee.INICIAR, 100);
				
			} else if (e.getActionCommand().equals(CENTRO)) {
				comprobarEstadoBotones(CENTRO);
				dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.ADELANTE,
						DispositivoXBee.INICIAR, DispositivoXBee.ADELANTE);
				
			} else if (e.getActionCommand().equals(DERECHA)) {
				comprobarEstadoBotones(DERECHA);
				dispositivoXBee.enviarDatosPrueba(DispositivoXBee.MOVIMIENTO, DispositivoXBee.DERECHA,
						DispositivoXBee.INICIAR, 100);
			}
			
		} else {
			new DialogoOpcionesAlerta(ventana, "¡Dispositivo XBee no conectado!", "ERROR");
		}
	}

}