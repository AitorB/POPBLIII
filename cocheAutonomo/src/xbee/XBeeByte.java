/**
 * @file XBeeByte.java
 * @brief Clase que transforma un numero decimal en un array de Bytes
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete xbee
 */

/** @brief Paquete xbee
 */
package xbee;

/**
 * @brief Clase XBeeByte
 */
public class XBeeByte {
    /**
     * @brief Atributos
     */
    byte[] datos;

    /**
     * @brief Constructor
     * @param numero Numero decimal (0-255) que se desea convertir a binario
     */
    public XBeeByte(int numero) {
        this.datos = new byte[1];
        this.datos[0] = (byte) numero;
    }

    /**
     * @brief Metodo para obtener el valor de la variable datos
     * @return byte[]
     */
    public byte[] getDatos() {
        return datos;
    }

}