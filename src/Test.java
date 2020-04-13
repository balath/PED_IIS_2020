/**
 * Clase para pruebas
 */
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDate;

public class Test {

    private ArrayList<Comunidad> comunidades;

    public Test(int diasSimulacion) {
        comunidades = new ArrayList<>();
        test_Comunidad();
        test_ResultadoSimulacion(comunidades, diasSimulacion);
    }

    public void test_Comunidad(){

        comunidades.add(new Comunidad("Mercurio ", 120));
        comunidades.add(new Comunidad("Venus    ", 230));
        comunidades.add(new Comunidad("Marte    ", 340));
        comunidades.add(new Comunidad("Jupiter  ", 450));
        comunidades.add(new Comunidad("Saturno  ", 560));
        comunidades.add(new Comunidad("Urano    ", 670));
        comunidades.add(new Comunidad("Neptuno  ", 780));
        comunidades.add(new Comunidad("Vulcano  ", 890));

        System.out.println("Comunidades en la simulación:");
        comunidades.forEach(System.out::println);
        System.out.println("----------------------------------------------");
    }

    public void test_ResultadoSimulacion(ArrayList<Comunidad> comunidades, int diasSimulacion){
        ResultadoSimulacion testing = new ResultadoSimulacion(comunidades);
        LocalDate fecha = LocalDate.now();
        Random rand = new Random();

        for (int i = 0; i < diasSimulacion; i++) {
            LocalDate finalFecha = fecha;
            comunidades.forEach(comunidad ->
                    testing.addResultadoDiario(comunidad, finalFecha, rand.nextInt(comunidad.getPoblacion()))
            );
            fecha = fecha.plusDays(1);
        }
        fecha = LocalDate.now();
        for (int i = 0; i < diasSimulacion; i++) {
            System.out.println(testing.toString(fecha));
            fecha = fecha.plusDays(1);
        }

    }

}
