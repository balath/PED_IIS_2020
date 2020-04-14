/********************************************************************************************************************
 * La clase Simulador se encarga de calcular una simulación según el modelo descrito en el enunciado de la práctica *
 ********************************************************************************************************************/
import java.time.LocalDate;
import java.util.ArrayList;

public class Simulador {

    /* El número de contactos que en promedio tenga cada infectado con personas no infectadas */
    private int E;
    /* La probabilidad de infectarse con un contacto */
    private float p;
    /* Porcentaje de habitantes de cada comunidad que viaja diariamente a cada una de las otras comunidades */
    private int promedio_V;

    /**
     * Constructor de la clase Simulador
     * @param E es el número de contactos que en promedio tenga cada infectado con personas no infectadas.
     * @param p es la probabilidad de infectarse con un contacto.
     * @param promedio_V de habitantes de cada comunidad que viaja diariamente a cada una de las otras comunidades.
     */
    public Simulador(int E, int p, int promedio_V) {
        this.E = E;
        this.p = ((float)p)/100;
        this.promedio_V = promedio_V;
    }

    /**
     * Este método gestiona el cálculo de la simulación desde una fecha concreta y devuelve el resultado de la misma.
     * @param comunidades sobre las que se desa realizar la simulación.
     * @param fecha del primer día de la simulación.
     * @param diasSimulacion es el número de dias sobre los que se desea realizar.
     * @return un objeto Resultado_Simulación con el resultado de los cálculos
     */
    public Resultado_Simulacion simular(ArrayList<Comunidad> comunidades, LocalDate fecha, int diasSimulacion){
        if (comunidades.isEmpty() || diasSimulacion <= 0) {
            return null;
        }
        //Se crea el objeto donde se almacenará el resultado de la simulación.
        Resultado_Simulacion resultado = new Resultado_Simulacion(comunidades);

        //El dia uno aparece un primer infectado en una de las comunidades(0 en el resto).
        for (Comunidad comunidad : comunidades) {
            resultado.setResultadoDiario(comunidad, fecha, 0);
        }
        resultado.setResultadoDiario(comunidades.get(0),fecha,1);

        //A partir del segundo día se calcula la expansión para cada comunidad durante los dias de simulación.
        for (int i = 2; i <= diasSimulacion; i++) {
            fecha = fecha.plusDays(1);
            System.out.println(fecha.toString());
            for (Comunidad comunidad : comunidades) {
                resultado.setResultadoDiario(comunidad, fecha, calculoTotal(comunidad, resultado, fecha));
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
    public Resultado_Simulacion simular(ArrayList<Comunidad> comunidades, int diasSimulacion){
        return simular(comunidades,LocalDate.now(),diasSimulacion);
    }

    /**
     * Método privado que llama a los métodos de cálculo de los infectados internos de una comunidad y causados
     * por los viajeros y devuelve el total.
     * @param comunidad sobre la que se realizan los cálculos.
     * @param resultados ya calculados sobre los dias previos.
     * @param fecha sobre la que se quiere obtener el resultado.
     * @return resultado de infectados según los parámetros pasados.
     */
    private int calculoTotal(Comunidad comunidad, Resultado_Simulacion resultados, LocalDate fecha){
        int total = calculoInterno(resultados.getInfectadosDiaComunidad(comunidad,fecha.minusDays(1)))
                    + calculoViajeros(comunidad, resultados, fecha.minusDays(1));
        return Math.min(total, comunidad.getPoblacion());
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
        // El calculo de los infectados por viajeros desde el punto de vista de una comunidad, implica a todos
        //los viajeros del resto de comunidades, por lo que el calculo se realiza sobre la coleccion de comunidades:
        return resultados.getComunidades().stream()
                //Primero se filtran las comunidades para quitar a la comunidad propia del computo:
                .filter(comunidades -> !comunidades.equals(comunidadPropia))
                //Luego, se mapean las comunidades filtradas en el resultado para cada una de aplicar la fórmula N_v
                .map(comunidadV -> (int)
                        (E * p * comunidadV.getViajeros(promedio_V)                     // E * p * V
                        * resultados.getInfectadosDiaComunidad(comunidadV,diaPrevio)   // Infectados comunidad origen
                        / comunidadV.getPoblacion())                                   // Población comunidad origen
                )
                //Finalmente se suman los resultados de aplicar las formulas a cada comunidad
                .reduce(0,Integer::sum);
    }
}
