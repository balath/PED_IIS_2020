/************************************************************************************************************
 * La clase Resultado_Simulacion modela la estructura de datos que tiene un resultado de simulación: Una    *
 * serie de comunidades, con los resultados para cada dia de la simulación                                  *
 ************************************************************************************************************/
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Resultado_Simulacion extends Object {

    //Mapa de resultados por día para cada comunidad.
    private HashMap<Comunidad,HashMap<LocalDate,Integer>> resultados;
    private LocalDate fechaInicial;
    private int diasSimulacion;

     /**
     * Constructor de la clase que crea el mapa de comunidades y resultados.
     * @param comunidades Lista de comunidades sobre las que se calculará el resultado.
     */
    public Resultado_Simulacion(ArrayList<Comunidad> comunidades, LocalDate fechaInicial, int diasSimulacion) {
        resultados = new HashMap<>();
        comunidades.forEach(comunidad -> resultados.put(comunidad,new HashMap<>()));
        this.fechaInicial = fechaInicial;
        this.diasSimulacion = diasSimulacion;
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
        int infectados = getInfectadosDiaComunidad(comunidad, fecha);
        return infectados == comunidad.getPoblacion()? 100 : (infectados * 100) / comunidad.getPoblacion();
    }

    /**
     * Devuelve un Array ordenado de las comunidades participantes en la simulación.
     * @return Array de comunidades
     */
    public ArrayList<Comunidad> getComunidades() {
        return (ArrayList<Comunidad>) resultados.keySet()
                .stream()
                .sorted((com1,com2) -> ((Integer)com1.getId()).compareTo(com2.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Devuelve la población total de todas las comunidades sobre la que se realiza la simulación.
     * @return muestra total
     */
    public BigInteger getMuestraTotal(){
        return resultados.keySet()
                .stream()
                .map(comunidad -> new BigInteger(String.valueOf(comunidad.getPoblacion())))
                .reduce(BigInteger.ZERO,BigInteger::add);
    }

    /**
     * Método que devuelve la fecha de inicio de un resultado de simulación.
     * @return
     */
    public LocalDate getFechaInicio(){
        return fechaInicial;
    }

    /**
     * Método que devuelve los dias totales del resultado de la simulación.
     * @return
     */
    public int getDiasSimulacion(){
        return diasSimulacion;
    }

    /**
     * Devuelve el total de infectados para todas las comunidades durante el periodo de simulación.
     * @return total de infectados diario
     */
    public BigInteger getInfectadosDiaTotal(LocalDate fecha){
        return resultados
                .values()
                .stream()
                .map(results -> new BigInteger(String.valueOf(results.get(fecha))))
                .reduce(BigInteger.ZERO,BigInteger::add);
    }

    /**
     * Devuelve el porcentaje de infectados en un día sobre la población total.
     * @param fecha sobre la que se desea calcular
     * @return porcentaje
     */
    public BigInteger getPorcentajeDiaTotal(LocalDate fecha){
        return (getInfectadosDiaTotal(fecha).multiply(BigInteger.valueOf(100))).divide(getMuestraTotal());
    }

}
