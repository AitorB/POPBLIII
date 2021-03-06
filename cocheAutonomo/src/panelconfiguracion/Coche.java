/**
 * @file Coche.java
 * @brief Clase que permite guardar informacion del coche a utilizar
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete panelConfiguracion
 */

/** @brief Paquete panelConfiguracion
 */
package panelconfiguracion;

/** @brief Librerias
 */

import java.io.Serializable;

/**
 * @brief Clase Coche
 */
public class Coche implements Serializable {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private String modelo;
    private double longitud;
    private double distanciaEjes;
    private double diametroRueda;
    private double masa;
    private int numeroVueltasRecta;
    private int numeroVueltasCurva;

    /**
     * @brief Constructor
     * @param modelo Modelo para identificar el coche a utilizar
     * @param longitud Longitud del coche en centimetros
     * @param distanciaEjes Distancia de separacion entre los ejes en centimetros
     * @param diametroRueda Diametro de la rueda del coche en centimetros
     * @param masa Masa del coche en gramos
     */
    public Coche(String modelo, double longitud, double distanciaEjes, double diametroRueda, double masa) {
        this.modelo = modelo;
        this.longitud = longitud;
        this.distanciaEjes = distanciaEjes;
        this.diametroRueda = diametroRueda;
        this.masa = masa;
    }

    /**
     * @brief Metodo para obtener el valor de la variable modelo
     * @return String
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @brief Metodo para determinar el valor de la variable modelo
     * @param modelo Nombre del coche
     * @return void
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @brief Metodo para obtener el valor de la variable longitud
     * @return double
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @brief Metodo para determinar el valor de la variable longitud
     * @param longitud Longitud del coche en centimetros
     * @return void
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * @brief Metodo para obtener el valor de la variable distanciaEjes
     * @return double
     */
    public double getDistanciaEjes() {
        return distanciaEjes;
    }

    /**
     * @brief Metodo para determinar el valor de la variable distanciaEjes
     * @param distanciaEjes Anchura del coche en centimetros
     * @return void
     */
    public void setDistanciaEjes(double distanciaEjes) {
        this.distanciaEjes = distanciaEjes;
    }

    /**
     * @brief Metodo para obtener el valor de la variable diametroRueda
     * @return double
     */
    public double getDiametroRueda() {
        return diametroRueda;
    }

    /**
     * @brief Metodo para determinar el valor de la variable diametroRueda
     * @param diametroRueda Diametro de la rueda del coche en centimetros
     * @return void
     */
    public void setDiametroRueda(double diametroRueda) {
        this.diametroRueda = diametroRueda;
    }

    /**
     * @brief Metodo para obtener el valor de la variable masa
     * @return double
     */
    public double getMasa() {
        return masa;
    }

    /**
     * @brief Metodo para determinar el valor de la variable masa
     * @param masa Masa del coche en gramos
     * @return void
     */
    public void setMasa(double masa) {
        this.masa = masa;
    }

    /**
     * @brief Metodo para obtener el valor de la variable numeroVueltasRecta
     * @return int
     */
    public int getNumeroVueltasRecta() {
        return numeroVueltasRecta;
    }

    /**
     * @brief Metodo para determinar el valor de la variable numeroVueltasRecta
     * @param numeroVueltasRecta Numero de vueltas que tiene que dar la rueda para completar la recta del circuito
     * @return void
     */
    public void setNumeroVueltasRecta(int numeroVueltasRecta) {
        this.numeroVueltasRecta = numeroVueltasRecta;
    }

    /**
     * @brief Metodo para obtener el valor de la variable numeroVueltasCurva
     * @return int
     */
    public int getNumeroVueltasCurva() {
        return numeroVueltasCurva;
    }

    /**
     * @brief Metodo para determinar el valor de la variable numeroVueltasCurva
     * @param numeroVueltasCurva Numero de vueltas que tiene que dar la rueda para completar la curva del circuito
     * @return void
     */
    public void setNumeroVueltasCurva(int numeroVueltasCurva) {
        this.numeroVueltasCurva = numeroVueltasCurva;
    }
}