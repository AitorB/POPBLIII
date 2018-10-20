/** @file PanelDibujo.java
 *  @brief Clase que muestra la representación gráfica del circuito
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete panelSimulacion
 */
package panelsimulacion;
 
/** @brief Librerías
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @brief Clase PanelDibujo
 */
public class PanelDibujo extends JPanel implements ActionListener {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private static final int MARGEN_IZQUIERDA = 35;
	private static final int MARGEN_ARRIBA = 35;
	private static final int RADIO = 150;
	private static final int RECTA = 540;
	private static final int ANCHO_CARRETERA = 110;
	private static final int RADIO_EXTERIOR = RADIO + (ANCHO_CARRETERA / 2);
	private static final int RADIO_INTERIOR = RADIO - (ANCHO_CARRETERA / 2);
	private static final int ANCHO_COCHE = 20;
	private static final int CENTRAR_X = ANCHO_COCHE /2;
	private static final double CENTESIMA_PARTE = (2 * RECTA + 2 * Math.PI * RADIO) / 100;

	private Timer cronometro;
	private double velocidadX;
	private double angulo;
	private double coordenadaX;
	private double coordenadaY;
	private boolean enRecta;
	private boolean curvaDerecha;
	private double centrarY = ANCHO_COCHE /(double)2;
	

	/**
	 * @brief Constructor
	 */
 	public PanelDibujo() {
		posicionInicial();
	}

	/**
	 * @brief Método inicializar la posición del coche
	 * @return void
	 */
	private void posicionInicial() {
		this.centrarY = Math.abs(centrarY);
		this.velocidadX = CENTESIMA_PARTE;
		this.enRecta = true;
		this.curvaDerecha = true;
		this.angulo = -Math.PI / 2;
		this.coordenadaX = MARGEN_IZQUIERDA + RADIO_EXTERIOR + (RECTA / (double)2) - CENTRAR_X;
		this.coordenadaY = MARGEN_ARRIBA + (2 * RADIO_EXTERIOR) - (ANCHO_CARRETERA /(double) 2) - centrarY;
	}

	/**
	 * @brief Método que pone el coche en movimiento
	 * @param duracionSegundos Tiempo que tarda en dar la vuelta
	 * @return void
	 */
	public void moverCoche(double duracionSegundos) {
		posicionInicial();

		cronometro = new Timer((int) Math.round((duracionSegundos * 1000) / 100), this);
		cronometro.start();
	}

	/**
	 * @brief Método parar el coche
	 * @return void
	 */
	public void pararCronometro() {
		posicionInicial();
		this.repaint();
		
		cronometro.stop();
	}

	/**
	 * @brief Método que determina como se tiene que mostrar el panel
	 * @param g Objeto de la clase graphics que permite dibujar en pantalla 
	 * @return void
	 */
	@Override
	public void paint(Graphics g) {
		int anchura = this.getWidth();
		int altura = this.getHeight();

		g.setColor(Color.white);
		g.fillRect(0, 0, anchura, altura);

		g.setColor(Color.BLACK);
		g.fillRect(MARGEN_IZQUIERDA + RADIO_EXTERIOR, MARGEN_ARRIBA, RECTA, 2 * RADIO_EXTERIOR);
		g.fillOval(MARGEN_IZQUIERDA, MARGEN_ARRIBA, 2 * RADIO_EXTERIOR, 2 * RADIO_EXTERIOR);
		g.fillOval(MARGEN_IZQUIERDA + RECTA, MARGEN_ARRIBA, 2 * RADIO_EXTERIOR, 2 * RADIO_EXTERIOR);

		g.setColor(Color.WHITE);
		g.fillRect(MARGEN_IZQUIERDA + ANCHO_CARRETERA + RADIO_INTERIOR, MARGEN_ARRIBA + ANCHO_CARRETERA, RECTA,
				2 * RADIO_INTERIOR);
		g.fillOval(MARGEN_IZQUIERDA + ANCHO_CARRETERA, MARGEN_ARRIBA + ANCHO_CARRETERA, 2 * RADIO_INTERIOR,
				2 * RADIO_INTERIOR);
		g.fillOval(MARGEN_IZQUIERDA + RECTA + ANCHO_CARRETERA, MARGEN_ARRIBA + ANCHO_CARRETERA, 2 * RADIO_INTERIOR,
				2 * RADIO_INTERIOR);

		g.setColor(Color.red);
		g.fillOval((int) coordenadaX, (int) coordenadaY, ANCHO_COCHE, ANCHO_COCHE);

		setOpaque(false);
	}

	/**
	 * @brief Método para tratar las acciones a realizar cuando se activa el cronómetro
	 * @param e Evento de acción
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (enRecta) {
			coordenadaX += velocidadX;
		} else {
			enCurva();
		}
		if (coordenadaX >= MARGEN_IZQUIERDA + RADIO_EXTERIOR + RECTA
				&& coordenadaX <= MARGEN_IZQUIERDA + RADIO_EXTERIOR + RECTA + CENTESIMA_PARTE
				&& coordenadaY == MARGEN_ARRIBA + (2 * RADIO_EXTERIOR) - (ANCHO_CARRETERA /(double) 2) - (centrarY)) {
			enRecta = false;
			curvaDerecha = true;
		} else if (coordenadaX >= MARGEN_IZQUIERDA + RADIO_EXTERIOR + RECTA
				&& coordenadaX <= MARGEN_IZQUIERDA + RADIO_EXTERIOR + RECTA + CENTESIMA_PARTE
				&& coordenadaY == MARGEN_ARRIBA + (ANCHO_CARRETERA / (double)2) + (centrarY)) {
			enRecta = true;
			curvaDerecha = false;
			velocidadX = -velocidadX;
		} else if (coordenadaX >= MARGEN_IZQUIERDA + RADIO_EXTERIOR
				&& coordenadaX <= MARGEN_IZQUIERDA + RADIO_EXTERIOR + CENTESIMA_PARTE
				&& coordenadaY == MARGEN_ARRIBA + (ANCHO_CARRETERA /(double) 2)+(centrarY)) {
			enRecta = false;
		} else if (coordenadaX >= MARGEN_IZQUIERDA + RADIO_EXTERIOR - CENTESIMA_PARTE
				&& coordenadaX <= MARGEN_IZQUIERDA + RADIO_EXTERIOR 
				&& coordenadaY == MARGEN_ARRIBA + (2 * RADIO_EXTERIOR) - (ANCHO_CARRETERA /(double) 2)-(centrarY)) {
			enRecta = true;
			curvaDerecha = true;
			velocidadX = -velocidadX;
		} else if (coordenadaX >= MARGEN_IZQUIERDA + RADIO_EXTERIOR + (RECTA /(double) 2) - CENTESIMA_PARTE
				&& coordenadaX <= MARGEN_IZQUIERDA + RADIO_EXTERIOR + (RECTA / 2)
				&& coordenadaY >= MARGEN_ARRIBA + (2 * RADIO_EXTERIOR) - (ANCHO_CARRETERA /(double) 2) -(centrarY)) {
			cronometro.stop();
		}
		this.repaint();
	}

	public void enCurva() {
		angulo += CENTESIMA_PARTE / RADIO;
		
		if (angulo >= Math.PI / 2) {
			angulo = -Math.PI / 2;
		} else if (angulo <= 0.065 && angulo >= -0.065){
			centrarY = -centrarY;
		}
		if (curvaDerecha) {
			coordenadaX = MARGEN_IZQUIERDA + RADIO_EXTERIOR + RECTA + (double)Math.round(Math.cos(angulo) * (RADIO - CENTRAR_X));
			coordenadaY = MARGEN_ARRIBA + RADIO_EXTERIOR - (double)Math.round(Math.sin(angulo) * (RADIO - centrarY));	
		} else {
			coordenadaX = MARGEN_IZQUIERDA + RADIO_EXTERIOR - (double)Math.round(Math.cos(angulo) * (RADIO + CENTRAR_X));
			coordenadaY = MARGEN_ARRIBA + RADIO_EXTERIOR + (double)Math.round(Math.sin(angulo) * (RADIO - centrarY));
		}
	}
}