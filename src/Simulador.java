import java.time.LocalDate;
import java.util.ArrayList;

public class Simulador {
    /**
     * Variables de la simulacion, se aclara en el comentario la letra asignada
     * en la fórmula de la descripción de la práctica.
     */
    /* El número de contactos que en promedio tenga cada infectado con personas no infectadas */
    private int E;
    /* La probabilidad de infectarse con un contacto */
    private int p;
    /* Porcentaje de habitantes de cada comunidad que viaja diariamente a cada una de las otras comunidades */
    private int promedio_V;

    public Simulador(int E, int p, int promedio_V) {
        this.E = E;
        this.p = p;
        this.promedio_V = promedio_V;
    }

    private void simular(ArrayList<Comunidad> comunidades, LocalDate fecha, int diasSimulacion){
        if (comunidades.isEmpty() || diasSimulacion <= 0) {
            return;
        }
        Resultado_Simulacion resultado = new Resultado_Simulacion(comunidades);
        //El dia uno aparece un primer infectado en una de las comunidades, 0 en el resto.
        comunidades.forEach(comunidad -> resultado.setResultadoDiario(comunidad,fecha,0));
        resultado.setResultadoDiario(comunidades.get(0),fecha,1);
        /**
         * por aqui
         */



    }



}
