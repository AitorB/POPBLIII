/** @file PanelConfiguracion.java
 *  @brief Clase para crear un panel que permite introducir circuitos y configurar los par�metros del coche
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete panelConfiguracion
 */
package panelconfiguracion;

/** @brief Librer�as
 */
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import iniciosesion.Usuario;

/**
 * @brief Clase PanelConfiguracion
 */
public class PanelConfiguracion extends JPanel {
	/**
	 * @brief N�mero de versi�n de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private PanelCircuitos panelCircuitos;
	private PanelCoche panelCoche;
	
	/**
	 * @brief Constructor
	 * @param ventana Referencia a la ventana de la que se lanza el di�logo
	 * @param usuario Usuario con el que se ha iniciado sesi�n
	 */
	public PanelConfiguracion(JFrame ventana, Usuario usuario) {
		this.setLayout(new GridLayout(1, 2, 20, 0));

		panelCircuitos = new PanelCircuitos(ventana, usuario);
		panelCoche = new PanelCoche(ventana, usuario);
				
		this.add(panelCircuitos);
		this.add(panelCoche);
	}

	/**
	 * @brief M�todo para obtener el valor del panel circuitos
	 * @return PanelCircuitos
	 */
	public PanelCircuitos getPanelCircuitos() {
		return panelCircuitos;
	}
	
	/**
	 * @brief M�todo para obtener el valor del panel coche
	 * @return PanelCoche
	 */
	public PanelCoche getPanelCoche() {
		return panelCoche;
	}
	
}