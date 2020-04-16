
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TablaDatos extends JPanel {

    private boolean DEBUG = false;

    public TablaDatos(Resultado_Simulacion resultado) {
        super(new GridLayout(1,0));
        int columnas =  1 + resultado.getComunidades().size() + 1;

        //Definición de la cabecera de la tabla, con la primera columna para las fechas, las siguientes para las
        //comunidades, y la última para la población total
        Comunidad[] comunidades = resultado.getComunidades().toArray(Comunidad[]::new);
        String[] columnNames = new String[columnas];
        columnNames[0] = " ";
        for (int i = 0; i < comunidades.length; i++) {
            columnNames[i+1] = comunidades[i].getNombre();
        }
        columnNames[columnNames.length-1] =  "TOTAL";

        LocalDate fecha = resultado.getFechaInicio();
        Object[][] data = new Object[resultado.getDiasSimulacion() +1][columnas];
        //Configuración de la columna de fechas
        for (int i = 1; i <= resultado.getDiasSimulacion(); i++) {
            data[i][0] = fecha.format(DateTimeFormatter.ofPattern("dd' - 'LLLL"));
            fecha = fecha.plusDays(1);
        }
        //Recuperación de los resultados
        for (int i = 0; i < comunidades.length; i++) {
            fecha = resultado.getFechaInicio();
            data[0][i+1] = String.valueOf(comunidades[i].getPoblacion()) + " hab.";
            for (int j = 1; j <= resultado.getDiasSimulacion(); j++) {
                data[j][i+1] = String.valueOf(resultado.getInfectadosDiaComunidad(comunidades[i],fecha)) + " -> " + resultado.getPorcentajeDiaComunidad(comunidades[i],fecha)+"%";
                fecha = fecha.plusDays(1);
            }
        }
        //Recuperación de los datos totales
        //Cuidado con paloma que me han dicho que es de goma

        fecha = resultado.getFechaInicio();
        data[0][columnas-1] = resultado.getMuestraTotal().toString() + "hab.";
        for (int i = 1; i <= resultado.getDiasSimulacion() ; i++) {
            data[i][columnas-1] = resultado.getInfectadosDiaTotal(fecha).toString() + " -> " + resultado.getPorcentajeDiaTotal(fecha) +"%";
            fecha = fecha.plusDays(1);
        }

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI(Resultado_Simulacion resultado) {
        //Create and set up the window.
        JFrame frame = new JFrame("Simulador de expansión exponencial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TablaDatos newContentPane = new TablaDatos(resultado);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}