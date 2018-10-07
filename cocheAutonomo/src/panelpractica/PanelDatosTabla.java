/** @file PanelDatosTabla.java
 *  @brief Clase que muestra una tabla con las estadísticas de las pruebas reales realizadas en el circuito
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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import dialogos.DialogoOpcionesAlerta;
import dialogos.DialogoOpcionesConfirmar;

/**
 * @brief Clase PanelDatosTabla
 */
public class PanelDatosTabla extends JPanel implements ActionListener {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private JFrame ventana;
	private JTable tabla;
	private ModeloTabla modeloTabla;
	
	private static final String ARIAL = "Arial";

	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de la que se lanza el diálogo
	 */
	public PanelDatosTabla(JFrame ventana) {
		super(new BorderLayout(10, 0));
		this.ventana = ventana;
		
		this.add(crearPanelTitulo(), BorderLayout.WEST);
		this.add(crearPanelDatos(), BorderLayout.CENTER);
	}

	/**
	 * @brief Método del panel título: contiene un título
	 * @return Component
	 */
	private Component crearPanelTitulo() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 0, 5));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setPreferredSize(new Dimension(180, 0));

		JLabel textoSuperior = new JLabel("DATOS");
		textoSuperior.setFont(new Font(ARIAL, Font.BOLD, 20));
		textoSuperior.setHorizontalAlignment(JLabel.CENTER);
		textoSuperior.setVerticalAlignment(JLabel.BOTTOM);

		JLabel textoInferior = new JLabel("REALES");
		textoInferior.setFont(new Font(ARIAL, Font.BOLD, 20));
		textoInferior.setHorizontalAlignment(JLabel.CENTER);
		textoInferior.setVerticalAlignment(JLabel.NORTH);

		panel.add(textoSuperior);
		panel.add(textoInferior);

		return panel;
	}

	/**
	 * @brief Método del panel datos: contiene los datos de la simulación
	 * @return Component
	 */
	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new BorderLayout(10, 0));
		panel.setBorder(BorderFactory.createEtchedBorder());

		panel.add(crearPanelTabla(), BorderLayout.CENTER);
		panel.add(crearPanelBorrar(), BorderLayout.EAST);

		return panel;
	}

	/**
	 * @brief Método del panel tabla: contiene la tabla
	 * @return Component
	 */
	private Component crearPanelTabla() {
		JScrollPane panelScroll = new JScrollPane();
		AdaptadorTabla adaptador = new AdaptadorTabla();
		ModeloColumnasTabla modeloColumnas = new ModeloColumnasTabla(adaptador);
		modeloTabla = new ModeloTabla(modeloColumnas);
		
		tabla = new JTable(modeloTabla, modeloColumnas);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.getTableHeader().setResizingAllowed(false);
		tabla.setFillsViewportHeight(true);

		((JLabel)tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tabla.getTableHeader().setFont(new Font(ARIAL, Font.BOLD, 12));
		tabla.getTableHeader().setBackground(Color.BLACK);
		
		panelScroll.setViewportView(tabla);
		
		return panelScroll;
	}

	/**
	 * @brief Método del panel borrar: contiene un botón que permite borrar datos de la tabla
	 * @return Component
	 */
	private Component crearPanelBorrar() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10),
				BorderFactory.createEtchedBorder()));

		JButton borrar = new JButton("BORRAR");
		borrar.setFont(new Font(ARIAL, Font.BOLD, 20));
		borrar.setMnemonic(KeyEvent.VK_B);
		borrar.setPreferredSize(new Dimension(124, 0));
		borrar.setActionCommand("borrar");
		borrar.addActionListener(this);

		panel.add(borrar, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * @brief Método para tratar las acciones realizadas por el usuario
	 * @param e Acción realizada por el usuario
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("borrar")) {
			int indice = tabla.getSelectedRow();
			
			if (indice != -1) {
				DialogoOpcionesConfirmar dialogo = new DialogoOpcionesConfirmar(ventana, "¿Desea borrar la práctica seleccionada?", "PREGUNTA");
				if(dialogo.getAceptar()) {
					modeloTabla.borrar(indice);
				}
			} else {
				new DialogoOpcionesAlerta(ventana, "¡Selecciona la practica que deseas borrar!", "ERROR");
			}		
		}
		if(modeloTabla.getRowCount() > 0) {
			tabla.setRowSelectionInterval(modeloTabla.getRowCount() - 1, modeloTabla.getRowCount() - 1);
			tabla.changeSelection(tabla.getRowCount() - 1, 0, false, false);
		}
	}
	
	/**
	 * @brief Método para obtener el modelo de la tabla
	 * @return ModeloTabla
	 */
	public ModeloTabla getModeloTabla() {
		return modeloTabla;
	}
	
	/**
	 * @brief Método para indicar al modelo la lista de prácticas a utilizar
	 * @param listaPracticas Lista de prácticas del circuito seleccionado
	 * @return void
	 */
	public void setListaModelo(List<Practica> listaPracticas) {
		modeloTabla.setListaPracticas(listaPracticas);
	}
	
}