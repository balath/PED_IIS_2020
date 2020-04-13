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

    private void simulaUnDia(ArrayList<Comunidad> comunidades){
        /*Aqui habrá que hacer un mapeo de las poblaciones de cada comunidad para el calculo de viajeros
        y quizás para el número de infectados*/
//        comunidades.forEach(
//                (comunidad) -> {
//                    comunidad.setInfectados(comunidad.getInfectados() * (1 + E * p));
//                }
//        );
    }



}
