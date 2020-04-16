/**************************************************
 * Clase para realizar pruebas sobre PED_IIS_2020 *
 **************************************************/
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.time.LocalDate;

public class Test {

    private ArrayList<Comunidad> testComunidades;
    private Resultado_Simulacion testResultados;
    private Simulador testSimulador;

    public Test(int diasSimulacion) {
        Random num = new Random();
        testComunidades = new ArrayList<>();
        test_Comunidad();

        testSimulador = new Simulador(5,50,10);
        testResultados = test_Simulador(testComunidades,LocalDate.now(),diasSimulacion);

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TablaDatos.createAndShowGUI(testResultados);
            }
        });

//        test_printResultados(testResultados,diasSimulacion);

    }

    public void test_Comunidad(){

        testComunidades.add(new Comunidad("Mercurio ", 1205981));
        testComunidades.add(new Comunidad("Venus    ", 2302548));
        testComunidades.add(new Comunidad("Marte    ", 3402548));
        testComunidades.add(new Comunidad("Jupiter  ", 4502569));
        testComunidades.add(new Comunidad("Saturno  ", 5603584));
        testComunidades.add(new Comunidad("Urano    ", 6703698));
        testComunidades.add(new Comunidad("Neptuno  ", 7803685));
        testComunidades.add(new Comunidad("Vulcano  ", 8905968));

        System.out.println("Comunidades en la simulación:");
        testComunidades.forEach(System.out::println);
        System.out.println("----------------------------------------------");
    }

    public void test_ResultadoSimulacion(ArrayList<Comunidad> comunidades, int diasSimulacion) {
        LocalDate fecha = LocalDate.now();
        Random rand = new Random();

        for (int i = 0; i < diasSimulacion; i++) {
            LocalDate finalFecha = fecha;
            comunidades.forEach(comunidad ->
                    testResultados.setResultadoDiario(comunidad, finalFecha, rand.nextInt(comunidad.getPoblacion()))
            );
            fecha = fecha.plusDays(1);
        }
    }

    public void test_printResultados(Resultado_Simulacion resultado, int diasSimulacion){
        LocalDate fecha = LocalDate.now();
        for (int i = 0; i < diasSimulacion; i++) {
            System.out.println(resultado.toString(fecha));
            fecha = fecha.plusDays(1);
        }

    }

    public Resultado_Simulacion test_Simulador(ArrayList<Comunidad> comunidades, LocalDate fecha, int diasSimulacion){
        return testSimulador.simular(comunidades,fecha,diasSimulacion);
    }

}
