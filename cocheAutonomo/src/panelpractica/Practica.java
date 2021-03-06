/**
 * @file Practica.java
 * @brief Clase que permite guardar informacion de la practica real realizada por el coche en el circuito ovalado
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete panelPractica
 */

/** @brief Paquete panelPractica
 */
package panelpractica;

/** @brief Librerias
 */

import java.io.Serializable;

/**
 * @brief Clase Practica
 */
public class Practica implements Serializable {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private int porcentaje;
    private double distanciaRecorrida;
    private String tiempo;
    private double velocidadMedia;
    private int vueltasRueda;
    private int revolucionesMotor;
    private boolean obstaculo;
    private String observaciones;

    /**
     * @brief Constructor
     * @param porcentaje Valor que indica el porcentaje completado del circuito
     * @param distanciaRecorrida Distancia en metros recorrida por el coche
     * @param tiempo Tiempo transcurrido desde el inicio al final de la practica
     * @param velocidadMedia Velocidad media del trayecto
     * @param vueltasRueda Vueltas que ha dado la rueda para completar la distancia recorrida
     * @param revolucionesMotor Revoluciones a las que ha girado el motor
     * @param obstaculo Indica si se ha detectado o no un obstaculo
     */
    public Practica(int porcentaje, double distanciaRecorrida, String tiempo, double velocidadMedia,
                    int vueltasRueda, int revolucionesMotor, boolean obstaculo) {
        this.porcentaje = porcentaje;
        this.distanciaRecorrida = distanciaRecorrida;
        this.tiempo = tiempo;
        this.velocidadMedia = velocidadMedia;
        this.vueltasRueda = vueltasRueda;
        this.revolucionesMotor = revolucionesMotor;
        this.obstaculo = obstaculo;
        this.observaciones = "";
    }

    /**
     * @brief Metodo para obtener la clase del tipo de dato que contiene la columna
     * @param indice indice de columna
     * @return Class<?>
     */
    public Class<?> getFieldClass(int indice) {
        switch (indice) {
            case 0:
                return Integer.class;
            case 1:
                return Double.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            case 4:
                return Double.class;
            case 5:
                return Double.class;
            case 6:
                return Boolean.class;
            case 7:
                return String.class;
            default:
                return null;
        }
    }

    /**
     * @brief Metodo para obtener el valor la columna seleccionada
     * @param columna indice de columna
     * @return Object
     */
    public Object getFieldAt(int columna) {
        switch (columna) {
            case 0:
                return Integer.valueOf(porcentaje);
            case 1:
                return Double.valueOf(distanciaRecorrida);
            case 2:
                return tiempo;
            case 3:
                return Double.valueOf(velocidadMedia);
            case 4:
                return Integer.valueOf(vueltasRueda);
            case 5:
                return Integer.valueOf(revolucionesMotor);
            case 6:
                return Boolean.valueOf(obstaculo);
            case 7:
                return observaciones;
            default:
                return null;
        }
    }

    /**
     * @brief Metodo para obtener el valor de la variable porcentaje
     * @return int
     */
    public int getPorcentaje() {
        return porcentaje;
    }

    /**
     * @brief Metodo para determinar el valor de la variable porcentaje
     * @param porcentaje Valor que indica el porcentaje completado del circuito
     * @return void
     */
    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * @brief Metodo para obtener el valor de la variable distanciaRecorrida
     * @return double
     */
    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    /**
     * @brief Metodo para determinar el valor de la variable distanciaRecorrida
     * @param distanciaRecorrida Distancia en metros recorrida por el coche
     * @return void
     */
    public void setDistanciaRecorrida(double distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    /**
     * @brief Metodo para obtener el valor de la variable tiempo
     * @return String
     */
    public String getTiempo() {
        return tiempo;
    }

    /**
     * @brief Metodo para determinar el valor de la variable tiempo
     * @param tiempo Tiempo transcurrido desde el inicio al final de la practica
     * @return void
     */
    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @brief Metodo para obtener el valor de la variable velocidadMedia
     * @return double
     */
    public double getVelocidadMedia() {
        return velocidadMedia;
    }

    /**
     * @brief Metodo para determinar el valor de la variable velocidadMedia
     * @param velocidadMedia Velocidad media del trayecto
     * @return void
     */
    public void setVelocidadMedia(double velocidadMedia) {
        this.velocidadMedia = velocidadMedia;
    }

    /**
     * @brief Metodo para obtener el valor de la variable vueltasRueda
     * @return int
     */
    public int getVueltasRueda() {
        return vueltasRueda;
    }

    /**
     * @brief Metodo para determinar el valor de la variable vueltasRueda
     * @param vueltasRueda Vueltas que ha dado la rueda para completar la distancia recorrida
     * @return void
     */
    public void setVueltasRueda(int vueltasRueda) {
        this.vueltasRueda = vueltasRueda;
    }

    /**
     * @brief Metodo para obtener el valor de la variable revolucionesMotor
     * @return int
     */
    public int getRevolucionesMotor() {
        return revolucionesMotor;
    }

    /**
     * @brief Metodo para determinar el valor de la variable revolucionesMotor
     * @param revolucionesMotor Revoluciones a las que ha girado el motor
     * @return void
     */
    public void setRevolucionesMotor(int revolucionesMotor) {
        this.revolucionesMotor = revolucionesMotor;
    }

    /**
     * @brief Metodo para obtener el valor de la variable obstaculo
     * @return boolean
     */
    public boolean isObstaculo() {
        return obstaculo;
    }

    /**
     * @brief Metodo para determinar el valor de la variable obstaculo
     * @param obstaculo Indica si se ha detectado o no un obstaculo
     * @return void
     */
    public void setObstaculo(boolean obstaculo) {
        this.obstaculo = obstaculo;
    }

    /**
     * @brief Metodo para obtener el valor de la variable observaciones
     * @return String
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @brief Metodo para determinar el valor de la variable observaciones
     * @param observaciones Observaciones del usuario relativas a la practica realizada
     * @return void
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}