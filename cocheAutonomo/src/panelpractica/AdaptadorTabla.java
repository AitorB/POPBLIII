/** @file AdaptadorTabla.java
 *  @brief Clase que permite personalizar el aspecto con el que se muestra la información de la tabla
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete panelPractica
 */
package panelpractica;

import java.awt.Color;
/** @brief Librerías
 */
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @brief Clase AdaptadorTabla
 */
public class AdaptadorTabla extends DefaultTableCellRenderer {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Método para gestionar la visualización de la tabla
	 * @param tabla La tabla que estamos redibujando
	 * @param practica La practica que es está seleccionando
	 * @param seleccionado Devuelve true si se realizó una selección en la tabla
	 * @param foco Devuelve true si selección realizada tiene el foco
	 * @param fila Número de fila
	 * @param columna Número de columna
	 * @return Component
	 */
	@Override
	public Component getTableCellRendererComponent(JTable tabla, Object practica, boolean seleccionado, boolean foco,
			int fila, int columna) {

		super.getTableCellRendererComponent(tabla, practica, seleccionado, foco, fila, columna);
		
		if (seleccionado) {
			this.setForeground(Color.BLACK);
			this.setBackground(new Color(187, 196, 220));

		} else {
			if ((int) tabla.getValueAt(fila, 0) < 100) {
				this.setBackground(new Color(216, 166, 166));
				this.setForeground(Color.BLACK);
			} else {
				this.setBackground(new Color(174, 216, 166));
				this.setForeground(Color.BLACK);
			}
		}
		
		tabla.setRowHeight(28);
		this.setFont(new Font("Arial", Font.BOLD, 13));
		
		switch (columna) {
		case 0: case 5:
			this.setHorizontalAlignment(CENTER);
			this.setText(this.getText() + " %");
			break;
		case 1: 
			this.setHorizontalAlignment(CENTER);
			this.setText(this.getText() + " m");
			break;
		case 3: 
			this.setHorizontalAlignment(CENTER);
			this.setText(this.getText() + " Km/h");
			break;
		default:
			this.setHorizontalAlignment(CENTER);
			break;
		}

		return this;
	}

}