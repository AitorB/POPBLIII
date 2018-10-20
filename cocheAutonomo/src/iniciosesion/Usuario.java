/**
 * @file Usuario.java
 * @brief Clase que guarda informacion de un usuario
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete inicarSesion
 */

/** @brief Paquete inicarSesion
 */
package iniciosesion;

import panelconfiguracion.Circuito;
import panelconfiguracion.Coche;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Clase Usuario
 */
public class Usuario implements Serializable {
    /**
     * @brief Numero de version de la clase
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
     * @param contrasena Contrasena del usuario
     */
    public Usuario(String nombre, char[] contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.listaCircuitos = new ArrayList<>();
        this.coche = null;
    }

    /**
     * @brief Metodo para obtener el valor de la variable nombre
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @brief Metodo para determinar el valor de la variable nombre
     * @param nombre Nombre del usuario
     * @return void
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @brief Metodo para obtener el valor de la variable contrasena
     * @return char[]
     */
    public char[] getContrasena() {
        return contrasena;
    }

    /**
     * @brief Metodo para determinar el valor de la variable contrasena
     * @param contrasena Contrasena del usuario
     * @return void
     */
    public void setContrasena(char[] contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @brief Metodo para obtener el valor de la variable listaCircuitos
     * @return List<Circuito>
     */
    public List<Circuito> getListaCircuitos() {
        return listaCircuitos;
    }

    /**
     * @brief Metodo para determinar el valor de la variable listaCircuitos
     * @param listaCircuitos Lista de los circuitos del usario
     * @return void
     */
    public void setListaCircuitos(List<Circuito> listaCircuitos) {
        this.listaCircuitos = listaCircuitos;
    }

    /**
     * @brief Metodo para obtener el valor del objeto coche
     * @return Coche
     */
    public Coche getCoche() {
        return coche;
    }

    /**
     * @brief Metodo para determinar el valor del objeto coche
     * @param coche Datos del vehiculo a utilizar por el usuario
     * @return void
     */
    public void setCoche(Coche coche) {
        this.coche = coche;
    }

}