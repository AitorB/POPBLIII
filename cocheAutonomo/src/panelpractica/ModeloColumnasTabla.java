/**
 * @file ModeloColumnasTabla.java
 * @brief Clase que permite personalizar cada columna de la tabla
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

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 * @brief Clase ModeloColumnasTabla
 */
public class ModeloColumnasTabla extends DefaultTableColumnModel {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private AdaptadorTabla adaptador;

    /**
     * @brief Constructor
     * @param adaptador Adaptador para personalizar el aspecto con el que se muestra la informacion de la tabla
     */
    public ModeloColumnasTabla(AdaptadorTabla adaptador) {
        super();
        this.adaptador = adaptador;

        this.addColumn(crearColumna("COMPLETADO", 0, 75));
        this.addColumn(crearColumna("DISTANCIA", 1, 65));
        this.addColumn(crearColumna("TIEMPO", 2, 60));
        this.addColumn(crearColumna("VELOCIDAD", 3, 70));
        this.addColumn(crearColumna("VUELTAS RUEDA", 4, 90));
        this.addColumn(crearColumna("REVOLUCIONES", 5, 90));
        this.addColumn(crearColumna("OBSTACULO", 6, 60));
        this.addColumn(crearColumna("OBSERVACIONES", 7, 200));
    }

    /**
     * @brief Metodo para crear cada columna de la tabla
     * @param texto Titulo de la columna
     * @param indice Numero de columna
     * @param ancho Ancho de la columna
     * @return TableColumn
     */
    private TableColumn crearColumna(String texto, int indice, int ancho) {
        TableColumn columna = new TableColumn(indice, ancho);

        columna.setHeaderValue(texto);
        columna.setPreferredWidth(ancho);
        columna.setCellRenderer(adaptador);

        return columna;
    }

}