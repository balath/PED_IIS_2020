/************************************************************************************************************
 * La clase Resultado_Simulacion modela la estructura de datos que tiene un resultado de simulación: Una    *
 * serie de comunidades, con los resultados para cada dia de la simulación                                  *
 ************************************************************************************************************/
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.time.LocalDate;

public class Resultado_Simulacion extends Object {

    //Mapa de resultados por día para cada comunidad.
    private HashMap<Comunidad,HashMap<LocalDate,Integer>> resultados;

     /**
     * Constructor de la clase que crea el mapa de comunidades y resultados.
     * @param comunidades Lista de comunidades sobre las que se calculará el resultado.
     */
    public Resultado_Simulacion(ArrayList<Comunidad> comunidades) {
        resultados = new HashMap<>();
        comunidades.forEach(comunidad -> resultados.put(comunidad,new HashMap<>()));
    }

    /**
     * Añade un resultado diario.
     * @param comunidad Comunidad en la que se introduce
     * @param dia Fecha para la que se introduce
     * @param infectados Total de infectados por comunidad para la fecha.
     */
    public void setResultadoDiario(Comunidad comunidad, LocalDate dia, int infectados) {
        resultados.get(comunidad).put(dia,infectados);
    }

    /**
     * Devuelve los infectados en una comunidad para una fecha.
     * @param comunidad sobre la que se desea recuperar el dato.
     * @param fecha para la que se pide el resultado.
     * @return número de infectados según parámetros.
     */
    public int getInfectadosDiaComunidad(Comunidad comunidad, LocalDate fecha){
        return resultados.get(comunidad).get(fecha);
    }

    /**
     * Devuelve el porcentaje de infectados en un día sobre la población total.
     * @param comunidad sobre la que se desea calcular el porcentaje
     * @param fecha para la que se pide el resultado
     * @return porcentaje de infectados según parámetros
     */
    public int getPorcentajeDiaComunidad(Comunidad comunidad, LocalDate fecha){
        return getInfectadosDiaComunidad(comunidad, fecha) * 100 / comunidad.getPoblacion();
    }

    /**
     * Devuelve un Set de las comunidades participantes en la simulación.
     * @return Set de comunidades
     */
    public Set<Comunidad> getComunidades() {
        return resultados.keySet();
    }

    /**
     * Devuelve la población total de todas las comunidades sobre la que se realiza la simulación.
     * @return muestra total
     */
    public int getMuestraTotal(){
        return resultados.keySet().stream().map(comunidad -> comunidad.getPoblacion()).reduce(0,Integer::sum);
    }

    /**
     * Devuelve el total de infectados para todas las comunidades durante el periodo de simulación.
     * @return total de infectados diario
     */
    public int getInfectadosDiaTotal(LocalDate fecha){
        return resultados
                .values()
                .stream()
                .map(results -> results.get(fecha))
                .reduce(0,Integer::sum);
    }

    /**
     * Devuelve el porcentaje de infectados en un día sobre la población total.
     * @param fecha sobre la que se desea calcular
     * @return porcentaje
     */
    public int getPorcentajeDiaTotal(LocalDate fecha){
        return getInfectadosDiaTotal(fecha) *100 / getMuestraTotal();
    }

    /**
     * Devuelve los resultados para cada comunidad de la simulación y para el total, en una fecha dada.
     * @param fecha de la que se desean recuperar los datos
     * @return String con los datos en el formato:
     *          Resultados a fecha [fecha]
     *          [nombre_comunidad]  [poblacion] [infectados]    [porcentaje]
     *          .
     *          .
     *          TOTAL:      [poblacion] [infectados]      [porcentaje]
     */
    public String toString(LocalDate fecha){
        StringBuilder out = new StringBuilder();
        out.append("Resultados a fecha ").append(fecha.toString()).appendCodePoint(10);
        resultados.forEach(
                (comunidad,mapa) ->
                {
                    out.append(comunidad.toString()).appendCodePoint(9);
                    out.append(getInfectadosDiaComunidad(comunidad,fecha)).appendCodePoint(9);
                    out.append(getPorcentajeDiaComunidad(comunidad,fecha)).append("% ").appendCodePoint(10);
                }
        );
        out.append("TOTAL:     ")
                .append(getMuestraTotal())
                .appendCodePoint(9)
                .append(getInfectadosDiaTotal(fecha))
                .appendCodePoint(9)
                .append(getPorcentajeDiaTotal(fecha)).append("% ")
                .appendCodePoint(10);
        return out.toString();
    }
}
