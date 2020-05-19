/*************************************************************************************************************
 * La clase TablaDatos configura la vista de los resultados en formato de tabla                              *
 *************************************************************************************************************/
import javax.swing.JTable;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TablaDatos {

    private JTable tabla;

    /**
     * El constructor recibe un resultado por parámetro y crea la tabla en base a los datos recibidos.
     * @param resultado para mostrar en vista tabla
     */
    public TablaDatos(Resultado_Simulacion resultado) {
        //Definición de la cabecera de la tabla
        Comunidad[] comunidades = resultado.getComunidades().toArray(Comunidad[]::new);
        int columnas =  comunidades.length + 2;
        String[] cabecera = new String[columnas];
        cabecera[0] = " ";                               // - 1ª columna para fechas
        for (int i = 0; i < comunidades.length; i++) {      // - 2ª y ss. para las comunidades
            cabecera[i+1] = comunidades[i].getNombre();
        }
        cabecera[cabecera.length-1] =  "TOTAL";       // - Última para la población total

        //Completado de la columna de fechas
        LocalDate fecha = resultado.getFechaInicio();
        Object[][] datos = new Object[resultado.getDiasSimulacion() +1][columnas];
        for (int i = 1; i <= resultado.getDiasSimulacion(); i++) {
            datos[i][0] = fecha.format(DateTimeFormatter.ofPattern("dd' - 'LLLL"));
            fecha = fecha.plusDays(1);
        }
        //Recuperación de los resultados para cada comunidad
        for (int i = 0; i < comunidades.length; i++) {
            fecha = resultado.getFechaInicio();
            datos[0][i+1] = String.valueOf(comunidades[i].getPoblacion()) + " hab.";
            for (int j = 1; j <= resultado.getDiasSimulacion(); j++) {
                datos[j][i+1] = String.valueOf(resultado.getInfectadosDiaComunidad(comunidades[i],fecha)) + " -> "
                                              + resultado.getPorcentajeDiaComunidad(comunidades[i],fecha)+"%";
                fecha = fecha.plusDays(1);
            }
        }
        //Recuperación de los datos totales
        fecha = resultado.getFechaInicio();
        datos[0][columnas-1] = resultado.getMuestraTotal().toString() + " hab.";
        for (int i = 1; i <= resultado.getDiasSimulacion() ; i++) {
            datos[i][columnas-1] = resultado.getInfectadosDiaTotal(fecha).toString() + " -> "
                                  + resultado.getPorcentajeDiaTotal(fecha) +"%";
            fecha = fecha.plusDays(1);
        }
        //Creación y asignación de los datos a la tabla
        tabla = new JTable(datos, cabecera);
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabla.setFillsViewportHeight(true);
    }

    /**
     * Método que devuelve la tabla
     * @return Tabla de la clase JTable con el resultado de una simulación.
     */
    public JTable getTabla(){
        return tabla;
    }
}