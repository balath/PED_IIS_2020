import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.time.LocalDate;

public class GraficoDatos {

    private JFreeChart totales;
    private JFreeChart porcentajes;
    final private XYSeriesCollection seriesTotales;
    final private XYSeriesCollection seriesPorcentajes;

    /**
     * Constructor de GraficoDatos, que recibe un resultado y configura las gráficas de acuerdo a los datos provistos
     * @param resultado de una simulación
     */
    public GraficoDatos(Resultado_Simulacion resultado) {
        seriesTotales = new XYSeriesCollection();
        seriesPorcentajes = new XYSeriesCollection();
        configurarSeries(resultado);
        crearChartTotales();
        crearChartPorcentajes();
    }

    /**
     * Método que configura las series de datos para cada gráfica a partir del resultado pasado por parámetro.
     * @param resultado de una simulación
     */
    private void configurarSeries(Resultado_Simulacion resultado) {
        LocalDate fecha;

        //Se crean las series de datos de cada comunidad, y se van añadiendo a la colección de series correspondiente.
        for (Comunidad comunidad : resultado.getComunidades()) {
            fecha = resultado.getFechaInicio();
            final XYSeries serieTotalesComunidad = new XYSeries(comunidad.getNombre());
            final XYSeries seriePorcentajesComunidad = new XYSeries(comunidad.getNombre());
            for (int i = 1; i <= resultado.getDiasSimulacion(); i++) {
                serieTotalesComunidad.add(i, resultado.getInfectadosDiaComunidad(comunidad, fecha));
                seriePorcentajesComunidad.add(i,resultado.getPorcentajeDiaComunidad(comunidad,fecha));
                fecha = fecha.plusDays(1);
            }
            seriesTotales.addSeries(serieTotalesComunidad);
            seriesPorcentajes.addSeries(seriePorcentajesComunidad);
        }
    }

    /**
     * Método que crea el gráfico de resultados totales.
     */
    private void crearChartTotales() {
        totales = ChartFactory.createXYLineChart(
                "Infectados totales",
                "Días",
                "Resultados",
                seriesTotales,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    /**
     * Método que crea el gráfico de porcentajes sobre la población total.
     */
    private void crearChartPorcentajes() {
        porcentajes = ChartFactory.createXYLineChart(
                "Porcentaje de infectados",
                "Días",
                "%",
                seriesPorcentajes,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    /**
     * Método que devuelve la gráfica de resultados totales
     * @return gráfica de totales de la clase JFreeChart
     */
    public JFreeChart getTotales(){
        return totales;
    }

    /**
     * Método que devuelve la gráfica de porcentajes
     * @return gráfica de porcentajes de la clase JFreeChart
     */
    public JFreeChart getPorcentajes(){
        return porcentajes;
    }
}
