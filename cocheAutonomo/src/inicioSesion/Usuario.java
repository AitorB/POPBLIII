/** @file Usuario.java
 *  @brief Clase que guarda información de un usuario
 *  @authors
 *  Nombre        | Apellido       | Email                                |
 *  ------------- | -------------- | ------------------------------------ |
 *  Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 *  Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 *  Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 *  Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 *  @date 23/01/2017
 */

/** @brief Paquete inicarSesion
 */
package inicioSesion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/** @brief Librerías
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

<<<<<<< HEAD
import panelconfiguracion.Circuito;
import panelconfiguracion.Coche;
=======
import main.Main;
import panelConfiguracion.Circuito;
import panelConfiguracion.Coche;
import panelConfiguracion.PanelConfiguracion;
>>>>>>> dev-docTestPanel

/**
 * @brief Clase Usuario
 */
public class Usuario implements Serializable {
	/**
	 * @brief Número de versión de la clase
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @brief Atributos
	 */
	private String nombre;
	private char[] contrasena;
	private List<Circuito> listaCircuitos;
	private Coche coche;

	/**
	 * @brief Constructor
	 * @param nombre Nombre del usuario
	 * @param contrasena Contraseña del usuario
	 */
	public Usuario(String nombre, char[] contrasena) {
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.listaCircuitos = new ArrayList<>();
		this.coche = null;
	}
	
	/**
	 * @brief Método para guardar usuarios
	 * @return void
	 */
	public void guardarListaUsuarios(Map<String, Usuario> mapaUsuarios) {
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(Main.FICHERO_ORIGINAL))) {
			writer.writeObject(mapaUsuarios);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brief Método que actualiza los datos del usuario en el fichero
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void actualizarUsuario(PanelConfiguracion panelConfiguracion, Usuario usuario) {
		Map<String, Usuario> mapaUsuarios = new HashMap<>();

		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(Main.FICHERO_ORIGINAL))) {
			mapaUsuarios = (Map<String, Usuario>) reader.readObject();

			usuario.setListaCircuitos(panelConfiguracion.getPanelCircuitos().getListaCircuitos());
			usuario.setCoche(panelConfiguracion.getPanelCoche().getCoche());
			mapaUsuarios.put(usuario.getNombre().toLowerCase(), usuario);

			try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(Main.FICHERO_ORIGINAL))) {
				writer.writeObject(mapaUsuarios);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @brief Método para obtener el valor de la variable nombre
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @brief Método para determinar el valor de la variable nombre
	 * @param nombre Nombre del usuario
	 * @return void
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @brief Método para obtener el valor de la variable contraseña
	 * @return char[]
	 */
	public char[] getContrasena() {
		return contrasena;
	}

	/**
	 * @brief Método para determinar el valor de la variable contraseña
	 * @param contrasena Contraseña del usuario
	 * @return void
	 */
	public void setContrasena(char[] contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @brief Método para obtener el valor de la variable listaCircuitos
	 * @return List<Circuito>
	 */
	public List<Circuito> getListaCircuitos() {
		return listaCircuitos;
	}

	/**
	 * @brief Método para determinar el valor de la variable listaCircuitos
	 * @param listaCircuitos Lista de los circuitos del usario
	 * @return void
	 */
	public void setListaCircuitos(List<Circuito> listaCircuitos) {
		this.listaCircuitos = listaCircuitos;
	}

	/**
	 * @brief Método para obtener el valor del objeto coche
	 * @return Coche
	 */
	public Coche getCoche() {
		return coche;
	}

	/**
	 * @brief Método para determinar el valor del objeto coche
	 * @param coche Datos del vehículo a utilizar por el usuario
	 * @return void
	 */
	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	
}