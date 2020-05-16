import javax.swing.JTable;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TablaDatos {

    private JTable tabla;

    public TablaDatos(Resultado_Simulacion resultado) {
        //Definición de la cabecera de la tabla, con la primera columna para las fechas, las siguientes para las
        //comunidades, y la última para la población total
        Comunidad[] comunidades = resultado.getComunidades().toArray(Comunidad[]::new);
        int columnas =  1 + comunidades.length + 1;
        String[] columnNames = new String[columnas];
        columnNames[0] = " ";
        for (int i = 0; i < comunidades.length; i++) {
            columnNames[i+1] = comunidades[i].getNombre();
        }
        columnNames[columnNames.length-1] =  "TOTAL";

        //Definición de la columna de fechas
        LocalDate fecha = resultado.getFechaInicio();
        Object[][] data = new Object[resultado.getDiasSimulacion() +1][columnas];
        for (int i = 1; i <= resultado.getDiasSimulacion(); i++) {
            data[i][0] = fecha.format(DateTimeFormatter.ofPattern("dd' - 'LLLL"));
            fecha = fecha.plusDays(1);
        }

        //Recuperación de los resultados para cada comunidad
        for (int i = 0; i < comunidades.length; i++) {
            fecha = resultado.getFechaInicio();
            data[0][i+1] = String.valueOf(comunidades[i].getPoblacion()) + " hab.";
            for (int j = 1; j <= resultado.getDiasSimulacion(); j++) {
                data[j][i+1] = String.valueOf(resultado.getInfectadosDiaComunidad(comunidades[i],fecha)) + " -> "
                                              + resultado.getPorcentajeDiaComunidad(comunidades[i],fecha)+"%";
                fecha = fecha.plusDays(1);
            }
        }

        //Recuperación de los datos totales
        fecha = resultado.getFechaInicio();
        data[0][columnas-1] = resultado.getMuestraTotal().toString() + "hab.";
        for (int i = 1; i <= resultado.getDiasSimulacion() ; i++) {
            data[i][columnas-1] = resultado.getInfectadosDiaTotal(fecha).toString() + " -> "
                                  + resultado.getPorcentajeDiaTotal(fecha) +"%";
            fecha = fecha.plusDays(1);
        }

        tabla = new JTable(data, columnNames);
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabla.setFillsViewportHeight(true);
    }

    public JTable getTabla(){
        return tabla;
    }
}