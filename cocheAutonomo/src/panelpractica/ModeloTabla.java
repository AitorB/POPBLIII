/**
 * @file ModeloTabla.java
 * @brief Clase para gestionar los datos de la tabla
 * @authors Nombre        | Apellido       | Email                                |
 * ------------- | -------------- | ------------------------------------ |
 * Iker	      | Mendi          | iker.mendi@alumni.mondragon.edu      |
 * Julen	      | Uribarren	   | julen.uribarren@alumni.mondragon.edu |
 * Unai          | Iraeta         | unai.iraeta@alumni.mondragon.edu     |
 * Aitor         | Barreiro       | aitor.barreirom@alumni.mondragon.edu |
 * @date 23/01/2017
 * @brief Paquete PanelPractica
 */

/** @brief Paquete PanelPractica
 */
package panelpractica;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Librerias
 */

/**
 * @brief Clase ModeloTabla
 */
public class ModeloTabla extends AbstractTableModel {
    /**
     * @brief Numero de version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * @brief Atributos
     */
    private static final int COLUMNA_OBSERVACIONES = 7;

    private ModeloColumnasTabla modeloColumnas;
    private List<Practica> listaPracticas;

    /**
     * @brief Constructor
     * @param modeloColumnas Indica como mostrar los datos en cada columna
     */
    public ModeloTabla(ModeloColumnasTabla modeloColumnas) {
        super();
        listaPracticas = new ArrayList<>();
        this.modeloColumnas = modeloColumnas;
    }

    /**
     * @brief Metodo para insertar una fila en la tabla
     * @param practica Datos de la practica realizada
     */
    public void insertar(Practica practica) {
        listaPracticas.add(practica);
        this.fireTableDataChanged();
    }

    /**
     * @brief Metodo para borrar una fila de la tabla
     * @param indiceFila Indica el numero de fila
     */
    public void borrar(int indiceFila) {
        listaPracticas.remove(indiceFila);
        this.fireTableDataChanged();
    }

    /**
     * @brief Metodo para obtener el numero de columnas de la tabla
     * @return int
     */
    @Override
    public int getColumnCount() {
        return modeloColumnas.getColumnCount();
    }

    /**
     * @brief Metodo para obtener el numero de filas de la tabla
     * @return int
     */
    @Override
    public int getRowCount() {
        return listaPracticas.size();
    }

    /**
     * @brief Metodo para obtener el objeto de una fila y columna concretas
     * @param fila Indica el numero de fila
     * @param columna Indica el numero de columna
     * @return Object
     */
    @Override
    public Object getValueAt(int fila, int columna) {
        Practica practica = listaPracticas.get(fila);

        return practica.getFieldAt(columna);
    }

    /**
     * @brief Metodo para determinar el valor de una fila y columna concretas
     * @param dato Tipo de dato
     * @param fila Indica el numero de fila
     * @param columna Indica el numero de columna
     * @return void
     */
    @Override
    public void setValueAt(Object dato, int fila, int columna) {
        listaPracticas.get(fila).setObservaciones((String) dato);
        this.fireTableCellUpdated(fila, columna);
    }

    /**
     * @brief Metodo para determinar si una fila o columna es editable
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int indiceFila, int indiceColumna) {
        boolean editable = false;

        if (indiceColumna == COLUMNA_OBSERVACIONES) {
            editable = true;
        }

        return editable;
    }

    /**
     * @brief Metodo para obtener la clase del tipo de dato que contiene la columna
     * @param indiceColumna Indica el numero de columna
     * @return Class<?>
     */
    @Override
    public Class<?> getColumnClass(int indiceColumna) {
        return getValueAt(0, indiceColumna).getClass();
    }

    /**
     * @brief Metodo para determinar el valor de la lista de practicas
     * @param listaPracticas Lista de practicas del circuito
     * @return void
     */
    public void setListaPracticas(List<Practica> listaPracticas) {
        this.listaPracticas = listaPracticas;
    }

}