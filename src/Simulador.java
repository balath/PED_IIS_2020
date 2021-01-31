/********************************************************************************************************************
 * La clase Simulador se encarga de calcular una simulación según el modelo descrito en el enunciado de la práctica *
 ********************************************************************************************************************/
import java.time.LocalDate;
        import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Simulador {

    /* El número de contactos que en promedio tenga cada infectado con personas no infectadas */
    private int E;
    /* La probabilidad de infectarse con un contacto */
    private float p;

    /**
     * Constructor de la clase Simulador
     * @param datos Array con todos los datos de la simlación.
     */
    public Simulador(Object[][] datos) {
        this.E = (Integer)datos[Main.DataIndex.E.ordinal()][0];
        this.p = (float) ((Integer)datos[Main.DataIndex.P.ordinal()][0] / 100.00);
    }

    /**
     * Este método gestiona el cálculo de la simulación desde una fecha concreta y devuelve el resultado de la misma.
     * @param comunidades sobre las que se desa realizar la simulación.
     * @param fecha del primer día de la simulación.
     * @param diasSimulacion es el número de dias sobre los que se desea realizar.
     * @return un objeto Resultado_Simulación con el resultado de los cálculos
     */
    public Resultado_Simulacion calculoTotal(ArrayList<Comunidad> comunidades, int diasSimulacion,  LocalDate fecha){
        if (comunidades.isEmpty() || diasSimulacion <= 0) {
            return null;
        }
        //Se crea el objeto donde se almacenará el resultado de la simulación.
        Resultado_Simulacion resultado = new Resultado_Simulacion(comunidades, fecha, diasSimulacion);
        int subtotal;
        LocalDate fechaActual = LocalDate.now();

        //El dia uno aparece un primer infectado en una de las comunidades(0 en el resto).
        for (Comunidad comunidad : comunidades) {
            resultado.setResultadoDiario(comunidad, fecha, 0);
        }
        resultado.setResultadoDiario(comunidades.get(0),fecha,2);

        //A partir del segundo día se calcula la expansión para cada comunidad durante los días de simulación.
        for (int i = 2; i <= diasSimulacion; i++) {
            fecha = fecha.plusDays(1);
            for (Comunidad comunidad : comunidades) {
                //Primero se comprueba si toda la población está infectada, en cuyo caso, según el modelo, el número
                //de infectados seguirá siendo el total de la población.
                if (comunidad.getPoblacion() == resultado.getInfectadosDiaComunidad(comunidad,fecha.minusDays(1))) {
                    resultado.setResultadoDiario(comunidad,fecha,comunidad.getPoblacion());
                    continue;
                }
                subtotal =  calculoInterno(resultado.getInfectadosDiaComunidad(comunidad,fecha.minusDays(1)))
                        + calculoViajeros(comunidad, resultado, fecha.minusDays(1));
                resultado.setResultadoDiario(comunidad, fecha, Math.min(subtotal, comunidad.getPoblacion()));
            }
        }
        return resultado;
    }

    /**
     * Este método gestiona el cálculo de la simulación desde la fecha actual y devuelve el resultado de la misma. Se
     * implementa únicamente con la llamada al método "simular(ArrayList<Comunidad>,LocalDate,int)" y la fecha actual.
     * @param comunidades sobre las que se desa realizar la simulación.
     * @param diasSimulacion es el número de dias sobre los que se desea realizar.
     * @return un objeto Resultado_Simulación con el resultado de los cálculos
     */
    public Resultado_Simulacion calculoTotal(ArrayList<Comunidad> comunidades, int diasSimulacion){
        return calculoTotal(comunidades, diasSimulacion,LocalDate.now());
    }

    /**
     * Aplica la fórmula de expansión para calcular los infectados internos de una comunidad.
     */
    private int calculoInterno(int infectadosDiaPrevio) {
        return (int)(infectadosDiaPrevio * (1 + E * p));
    }

    /**
     * Aplica la fórmula de expansión para calcular los infectados causados por viajeros de otras comunidades.
     */
    private int calculoViajeros(Comunidad comunidadPropia, Resultado_Simulacion resultados, LocalDate diaPrevio) {
        // Se utilizan los resultados de todas las comunidades excepto la propia.
        return resultados.getComunidades()
                .stream()
                .filter(comunidades -> !(comunidades.equals(comunidadPropia)))
                //Se mapean las comunidades filtradas para aplicar a cada una la fórmula N_v del enunciado
                .map(comunidad_V -> (int)
                        (E * p * comunidad_V.getViajeros()                              // E * p * V
                                * resultados.getInfectadosDiaComunidad(comunidad_V,diaPrevio)   // * Infectados comunidad origen
                                / comunidad_V.getPoblacion())                                   // / Población comunidad origen
                )
                //Finalmente se suman los resultados de aplicar las formulas a cada comunidad
                .reduce(0,Integer::sum);
    }
}
