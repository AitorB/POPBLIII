/** @file PanelDatosTiempoReal.java
 *  @brief Clase para crear un panel que muestra los datos de la simulación realizada en tiempo real
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete PanelPractica
 */
package panelpractica;

/** @brief Librerías
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.digi.xbee.api.models.XBeeMessage;

import dialogos.DialogoDatosPractica;
import dialogos.DialogoOpcionesAlerta;
import interfaces.Observable;
import interfaces.Observer;
import panelconfiguracion.Circuito;
import panelconfiguracion.Coche;
import recursos.BarraProceso;
import recursos.Cronometro;
import recursos.Fisica;
import xBee.DispositivoXBee;

/**
 * @brief Clase PanelDatosTiempoReal
 */
public class PanelDatosTiempoReal extends JPanel implements ActionListener, Observer {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private DispositivoXBee dispositivoXBee;

	private JFrame ventana;
	private PanelDatosTabla panelDatosTabla;
	private Circuito circuito;
	private Coche coche;

	private JTextField textoDistanciaRecorrida;
	private JTextField textoVelocidad;
	private JTextField textoVueltasRueda;
	private JTextField textoPosicionServo;
	private JTextField textoRevolucionesMotor;
	private JTextField textoObstaculo;
	private JLabel completado;
	private BarraProceso barraProceso;
	private Cronometro cronometro;
	private JButton iniciar;
	private JButton detener;

	private double distanciaRecorrida;
	private int vueltasRueda;
	private int revolucionesMotor;
	private boolean obstaculo;
	private double porcentaje;
	
	private static final String ARIAL = "Arial";
	private static final String ERROR = "ERROR";
	
	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de la que se lanza el diálogo
	 * @param panelDatosTabla Referencia al panel que contiene la tabla de prácticas
	 */
	public PanelDatosTiempoReal(JFrame ventana, PanelDatosTabla panelDatosTabla) {
		super(new BorderLayout(10, 0));

		this.ventana = ventana;
		this.panelDatosTabla = panelDatosTabla;
		this.setPreferredSize(new Dimension(0, 230));

		this.add(crearPanelBotones(), BorderLayout.WEST);
		this.add(crearPanelDatos(), BorderLayout.CENTER);
	}

	/**
	 * @brief Método del panel botones: contiene los botones iniciar y detener prueba real
	 * @return Component
	 */
	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 0, 5));
		panel.setPreferredSize(new Dimension(180, 0));

		panel.add(panelBotonIniciar());
		panel.add(panelBotonDetener());

		return panel;
	}

	/**
	 * @brief Método del panel boton iniciar: contiene el botón para iniciar el movimiento del coche
	 * @return Component
	 */
	private Component panelBotonIniciar() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());

		iniciar = new JButton("  INICIAR", new ImageIcon("iconos\\iniciar.png"));
		iniciar.setFont(new Font(ARIAL, Font.BOLD, 20));
		iniciar.setHorizontalAlignment(JButton.CENTER);
		iniciar.setMnemonic(KeyEvent.VK_I);
		iniciar.setActionCommand("iniciar");
		iniciar.addActionListener(this);

		panel.add(iniciar, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @brief Método del panel boton detener: contiene el botón para detener el coche
	 * @return Component
	 */
	private Component panelBotonDetener() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());

		detener = new JButton("  DETENER", new ImageIcon("iconos\\detener.png"));
		detener.setFont(new Font(ARIAL, Font.BOLD, 20));
		detener.setHorizontalAlignment(JButton.CENTER);
		detener.setMnemonic(KeyEvent.VK_D);
		detener.setActionCommand("detener");
		detener.addActionListener(this);
		detener.setEnabled(false);

		panel.add(detener, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @brief Método del panel datos: contiene los paneles que muestra datos recibidos del coche
	 * @return Component
	 */
	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new BorderLayout(0, 5));

		panel.add(crearPanelDatosCentro(), BorderLayout.CENTER);
		panel.add(crearPanelDatosSur(), BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * @brief Método del panel centro: contiene los paneles que muestran cada dato del coche
	 * @return Component
	 */
	private Component crearPanelDatosCentro() {
		JPanel panel = new JPanel(new GridLayout(2, 3, 5, 11));
		panel.setBorder(BorderFactory.createEtchedBorder());

		panel.add(crearPanelDistancia());
		panel.add(crearPanelVelocidad());
		panel.add(crearPanelServo());
		panel.add(crearPanelTiempo());
		panel.add(crearPanelVueltasRueda());
		panel.add(crearPanelRevolucionesMotor());

		return panel;
	}

	/**
	 * @brief Método del panel distancia: muestra la distancia recorrida por el coche
	 * @return Component
	 */
	private Component crearPanelDistancia() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		JLabel titulo = new JLabel("DISTANCIA RECORRIDA:");
		titulo.setFont(new Font(ARIAL, Font.BOLD, 12));
		titulo.setHorizontalAlignment(JTextField.CENTER);

		textoDistanciaRecorrida = new JTextField("0 m");
		textoDistanciaRecorrida.setFont(new Font(ARIAL, Font.BOLD, 20));
		textoDistanciaRecorrida.setForeground(new Color(255, 107, 107));
		textoDistanciaRecorrida.setHorizontalAlignment(JTextField.CENTER);
		textoDistanciaRecorrida.setBorder(null);
		textoDistanciaRecorrida.setEditable(false);
		textoDistanciaRecorrida.setBackground(Color.WHITE);

		panel.add(titulo);
		panel.add(textoDistanciaRecorrida);

		return panel;
	}

	/**
	 * @brief Método del panel velocidad: muestra a que velocidad se desplaza el coche
	 * @return Component
	 */
	private Component crearPanelVelocidad() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		JLabel titulo = new JLabel("VELOCIDAD ACTUAL:");
		titulo.setFont(new Font(ARIAL, Font.BOLD, 12));
		titulo.setHorizontalAlignment(JTextField.CENTER);

		textoVelocidad = new JTextField("0 Km/h");
		textoVelocidad.setFont(new Font(ARIAL, Font.BOLD, 20));
		textoVelocidad.setForeground(new Color(255, 107, 107));
		textoVelocidad.setHorizontalAlignment(JTextField.CENTER);
		textoVelocidad.setBorder(null);
		textoVelocidad.setEditable(false);
		textoVelocidad.setBackground(Color.WHITE);

		panel.add(titulo);
		panel.add(textoVelocidad);

		return panel;
	}

	/**
	 * @brief Método del panel servo: muestra la posición del servo
	 * @return Component
	 */
	private Component crearPanelServo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));

		JLabel titulo = new JLabel("POSICIÓN SERVO:");
		titulo.setFont(new Font(ARIAL, Font.BOLD, 12));
		titulo.setHorizontalAlignment(JTextField.CENTER);

		textoPosicionServo = new JTextField("0 %");
		textoPosicionServo.setFont(new Font(ARIAL, Font.BOLD, 20));
		textoPosicionServo.setForeground(new Color(255, 107, 107));
		textoPosicionServo.setHorizontalAlignment(JTextField.CENTER);
		textoPosicionServo.setBorder(null);
		textoPosicionServo.setEditable(false);
		textoPosicionServo.setBackground(Color.WHITE);

		panel.add(titulo);
		panel.add(textoPosicionServo);

		return panel;
	}

	/**
	 * @brief Método del panel tiempo: muestra el tiempo transcurrido desde el inicio de la prueba
	 * @return Component
	 */
	private Component crearPanelTiempo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

		JLabel titulo = new JLabel("TIEMPO TRANSCURRIDO:");
		titulo.setFont(new Font(ARIAL, Font.BOLD, 12));
		titulo.setHorizontalAlignment(JTextField.CENTER);

		cronometro = new Cronometro(new Color(255, 107, 107), new Font(ARIAL, Font.BOLD, 20), Color.WHITE);

		panel.add(titulo);
		panel.add(cronometro);

		return panel;
	}

	/**
	 * @brief Método del panel vueltas rueda: muestra el número de vueltas que ha dado la rueda
	 * @return Component
	 */
	private Component crearPanelVueltasRueda() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

		JLabel titulo = new JLabel("VUELTAS RUEDA:");
		titulo.setFont(new Font(ARIAL, Font.BOLD, 12));
		titulo.setHorizontalAlignment(JTextField.CENTER);

		textoVueltasRueda = new JTextField("0");
		textoVueltasRueda.setFont(new Font(ARIAL, Font.BOLD, 20));
		textoVueltasRueda.setForeground(new Color(255, 107, 107));
		textoVueltasRueda.setHorizontalAlignment(JTextField.CENTER);
		textoVueltasRueda.setBorder(null);
		textoVueltasRueda.setEditable(false);
		textoVueltasRueda.setBackground(Color.WHITE);

		panel.add(titulo);
		panel.add(textoVueltasRueda);

		return panel;
	}

	/**
	 * @brief Método del panel revoluciones motor: muestra las revoluciones del motor
	 * @return Component
	 */
	private Component crearPanelRevolucionesMotor() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 0, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

		JLabel titulo = new JLabel("REVOLUCIONES MOTOR:");
		titulo.setFont(new Font(ARIAL, Font.BOLD, 12));
		titulo.setHorizontalAlignment(JTextField.CENTER);

		textoRevolucionesMotor = new JTextField("0 %");
		textoRevolucionesMotor.setFont(new Font(ARIAL, Font.BOLD, 20));
		textoRevolucionesMotor.setForeground(new Color(255, 107, 107));
		textoRevolucionesMotor.setHorizontalAlignment(JTextField.CENTER);
		textoRevolucionesMotor.setBorder(null);
		textoRevolucionesMotor.setEditable(false);
		textoRevolucionesMotor.setBackground(Color.WHITE);

		panel.add(titulo);
		panel.add(textoRevolucionesMotor);

		return panel;
	}

	/**
	 * @brief Método del panel datos sur: contiene los paneles que muestran información del progreso y obstáculos
	 * @return Component
	 */
	private Component crearPanelDatosSur() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(0, 50));

		panel.add(crearPanelProgreso(), BorderLayout.CENTER);
		panel.add(crearPanelObstaculo(), BorderLayout.EAST);

		return panel;
	}

	/**
	 * @brief Método del panel progreso: muestra el cuanto ha avanzado el coche en el circuito
	 * @return Component
	 */
	private Component crearPanelProgreso() {
		JPanel panel = new JPanel(new BorderLayout());

		panel.add(crearPanelBarraProceso(), BorderLayout.CENTER);
		panel.add(crearPanelEstadoProceso(), BorderLayout.WEST);

		return panel;
	}

	/**
	 * @brief Método del panel estado proceso: muestra el porcentaje completado en la barra de proceso
	 * @return Component
	 */
	private Component crearPanelEstadoProceso() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		panel.setPreferredSize(new Dimension(170, 0));

		completado = new JLabel();
		completado.setText("0 % COMPLETADO");
		completado.setFont(new Font(ARIAL, Font.BOLD, 15));

		panel.add(completado, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @brief Método del panel barra proceso: muestra la barra de proceso
	 * @return Component
	 */
	private Component crearPanelBarraProceso() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

		barraProceso = new BarraProceso(0, 30, "HORIZONTAL", new Color(16, 137, 141), false, false);
		barraProceso.addObserver(this);

		panel.add(barraProceso, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @brief Método del panel obstáculo: muestra si el coche ha detectado un obstáculo o no
	 * @return Component
	 */
	private Component crearPanelObstaculo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

		JLabel titulo = new JLabel("OBSTÁCULO:");
		titulo.setFont(new Font(ARIAL, Font.BOLD, 12));
		titulo.setHorizontalAlignment(JTextField.CENTER);

		textoObstaculo = new JTextField("NO");
		textoObstaculo.setFont(new Font(ARIAL, Font.BOLD, 20));
		textoObstaculo.setForeground(new Color(255, 107, 107));
		textoObstaculo.setHorizontalAlignment(JTextField.CENTER);
		textoObstaculo.setBorder(null);
		textoObstaculo.setEditable(false);
		textoObstaculo.setBackground(Color.WHITE);

		panel.add(titulo);
		panel.add(textoObstaculo);

		return panel;
	}

	/**
	 * @brief Método para iniciar una nueva práctica
	 * @param dialogo Referencia al objeto dialogo
	 * @return void
	 */
	private void iniciarPractica(DialogoDatosPractica dialogo) {
		iniciar.setEnabled(false);
		detener.setEnabled(true);

		distanciaRecorrida = 0;
		cronometro.iniciar();
		vueltasRueda = 0;
		revolucionesMotor = dialogo.getRevoluciones();
		porcentaje = 0;
		barraProceso.setPorcentaje((int) porcentaje);
		obstaculo = false;

		textoDistanciaRecorrida.setText("0 m");
		textoVelocidad.setText("0 Km/h");
		textoPosicionServo.setText("0 %");
		textoRevolucionesMotor.setText(String.valueOf(revolucionesMotor) + " %");
		textoObstaculo.setText("NO");
	}

	/**
	 * @brief Método para detener la práctica en curso
	 * @return void
	 */
	private void detenerPractica() {
		iniciar.setEnabled(true);
		detener.setEnabled(false);
		cronometro.detener();
		
		anadirPracticaTabla();
		
		dispositivoXBee.detenerXBee();
	}

	/**
	 * @brief Método para leer los mensajes recibidos desde el dispositivo XBee remoto
	 * @param mensajeXBee Mensaje recibido de la XBee remota
	 * @return void
	 */
	private void leerMensajeXBee(XBeeMessage mensajeXBee) {
		byte[] arrayBytes = mensajeXBee.getData();
		int dato = arrayBytes[0];
 		double velocidad;

		switch (dato) {
		case DispositivoXBee.VUELTAS_RUEDA_PRACTICA:
			DecimalFormat formatoDecimal = new DecimalFormat("0.00");

			distanciaRecorrida += Math.PI * coche.getDiametroRueda() * 0.01;
			textoDistanciaRecorrida.setText(formatoDecimal.format(distanciaRecorrida) + " m");

			velocidad = Fisica.calcularVelocidadMedia(distanciaRecorrida, ((cronometro.getMinuto() * 60)
					+ cronometro.getSegundo() + ((cronometro.getMilesima() - 1) / 1000)));
			textoVelocidad.setText(formatoDecimal.format(velocidad) + " Km/h");

			vueltasRueda++;
			textoVueltasRueda.setText(String.valueOf(vueltasRueda));

			establecerPorcentaje();

			establecerServo();
			break;

		case DispositivoXBee.OBSTACULO_PRACTICA:
			obstaculo = true;
			textoObstaculo.setText("SÍ");
			detenerPractica();
			break;
			
		case DispositivoXBee.FINALIZAR_PRACTICA:
			detenerPractica();
			break;

		default:
			dispositivoXBee.enviarDatosCircuito(DispositivoXBee.MOTOR, DispositivoXBee.DETENER, DispositivoXBee.DETENER,
					DispositivoXBee.DETENER);
			
			detenerPractica();
			new DialogoOpcionesAlerta(ventana, "¡Datos incorrectos!", ERROR);
			break;
		}
	}

	/**
	 * @brief Método para calcular el porcentaje y mostrarlo por pantalla
	 * @return void
	 */
	private void establecerPorcentaje() {
		double porcentajeVueltas = Fisica.calcularVueltasTotal(circuito, coche) * 0.01;
		double sumaPorcentaje = 1 / porcentajeVueltas;

		porcentaje += sumaPorcentaje;

		if (vueltasRueda == Fisica.calcularVueltasTotal(circuito, coche)) {
			porcentaje = 100;
		}

		completado.setText(String.valueOf((int) porcentaje) + " %" + " COMPLETADO");
		barraProceso.setPorcentaje((int) porcentaje);
	}

	/**
	 * @brief Método para calcular la posición del servo y mostrarlo por pantalla
	 * @return void
	 */
	private void establecerServo() {
		if ((vueltasRueda > coche.getNumeroVueltasRecta())
				&& (vueltasRueda <= (coche.getNumeroVueltasRecta() + coche.getNumeroVueltasCurva()))) {
			textoPosicionServo.setText(String.valueOf(Fisica.calcularAnguloGiro(circuito, coche)) + " %");
		} else if ((vueltasRueda > (coche.getNumeroVueltasRecta() + coche.getNumeroVueltasCurva()))
				&& (vueltasRueda <= ((coche.getNumeroVueltasRecta() * 2) + coche.getNumeroVueltasCurva()))) {
			textoPosicionServo.setText("0 %");
		} else if ((vueltasRueda > ((coche.getNumeroVueltasRecta() * 2) + coche.getNumeroVueltasCurva()))
				&& (vueltasRueda < ((coche.getNumeroVueltasRecta() * 2) + (coche.getNumeroVueltasCurva() * 2)))) {
			textoPosicionServo.setText(String.valueOf(Fisica.calcularAnguloGiro(circuito, coche)) + " %");
		} else if (vueltasRueda == Fisica.calcularVueltasTotal(circuito, coche)) {
			textoPosicionServo.setText("0 %");
		}
	}

	/**
	 * @brief Método para añadir los datos obtenidos a la tabla de prácticas
	 * @return void
	 */
	private void anadirPracticaTabla() {
		Practica practica = new Practica((int) porcentaje, Math.floor(distanciaRecorrida * 100) / 100,
			String.format("%02d : %02d : %02d", cronometro.getMinuto(), cronometro .getSegundo(),
			cronometro.getMilesima() - 1), Math.floor(Fisica.calcularVelocidadMedia(distanciaRecorrida,
			((cronometro.getMinuto() * 60) + cronometro.getSegundo() + ((cronometro.getMilesima() - 1) / 1000))) * 100) / 100,
			vueltasRueda, revolucionesMotor, obstaculo);
		panelDatosTabla.getModeloTabla().insertar(practica);
	}

	/**
	 * @brief Método para tratar las acciones realizadas por el usuario
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("iniciar")) {
			if (dispositivoXBee != null) {
				if (!dispositivoXBee.comprobarEstado()) {
					crearDialogoDatosPractica();
				} else {
					new DialogoOpcionesAlerta(ventana, "¡Ya hay una prueba en curso!", ERROR);
				}
			} else {
				new DialogoOpcionesAlerta(ventana, "¡Dispositivo XBee no conectado!", ERROR);
			}
		} else if (e.getActionCommand().equals("detener")) {
			dispositivoXBee.enviarDatosCircuito(DispositivoXBee.MOTOR, DispositivoXBee.DETENER, DispositivoXBee.DETENER,
					DispositivoXBee.DETENER);
			
			detenerPractica();
		}
	}

	/**
	 * @brief Método que se ejecuta para crear un DialogoDatosPractica
	 * @return void
	 */
	public void crearDialogoDatosPractica() {
		DialogoDatosPractica dialogo = new DialogoDatosPractica(ventana, 450, 200, "INICIAR PRACTICA");
		if (dialogo.getIniciarPractica()) {
			iniciarPractica(dialogo);

			dispositivoXBee.iniciarXBee();
			dispositivoXBee.enviarDimensionesCircuito(DispositivoXBee.RECTA, coche.getNumeroVueltasRecta());
			dispositivoXBee.enviarDimensionesCircuito(DispositivoXBee.CURVA, coche.getNumeroVueltasCurva());
			dispositivoXBee.enviarDatosCircuito(DispositivoXBee.MOTOR, DispositivoXBee.ADELANTE,
					DispositivoXBee.INICIAR, revolucionesMotor);
			dispositivoXBee.setErrorEnvio(true);
			dispositivoXBee.enviarDatosCircuito(DispositivoXBee.SERVO,
					(dialogo.getBotonIzquierda().isSelected() ? DispositivoXBee.IZQUIERDA : DispositivoXBee.DERECHA),
					DispositivoXBee.INICIAR, Fisica.calcularAnguloGiro(circuito, coche));
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
		leerMensajeXBee((XBeeMessage) objeto);

		if (vueltasRueda == Fisica.calcularVueltasTotal(circuito, coche)) {
			detenerPractica();
		}
	}

	/**
	 * @brief Método para determinar el valor del objeto coche
	 * @param coche Datos del coche a utilizar por el usuario
	 * @return void
	 */
	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	/**
	 * @brief Método para determinar el valor del objeto circuito
	 * @param circuito Datos del circuito seleccionado
	 * @return void
	 */
	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}

	/**
	 * @brief Método para determinar el valor de la variable dispositivoXBee
	 * @param dispositivoXBee Dispositivo XBee utilizado para realizar la comunicación con el coche
	 * @return void
	 */
	public void setDispositivoXBee(DispositivoXBee dispositivoXBee) {
		this.dispositivoXBee = dispositivoXBee;
		this.dispositivoXBee.addObserver(this);
	}

}