/**************************************************
 * Clase para realizar pruebas sobre PED_IIS_2020 *
 **************************************************/
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDate;

public class Test {

    private ArrayList<Comunidad> testComunidades;
    private Resultado_Simulacion testResultados;
    private Simulador testSimulador;

    public Test(int diasSimulacion) {
        Random num = new Random();
        testComunidades = new ArrayList<>();
        testResultados = new Resultado_Simulacion(testComunidades);
        testSimulador = new Simulador(num.nextInt(7),50,10);

        test_Comunidad();
        test_printResultados(test_Simulador(testComunidades,LocalDate.now(),diasSimulacion),diasSimulacion);
        //test_ResultadoSimulacion(comunidades, diasSimulacion);
    }

    public void test_Comunidad(){

        testComunidades.add(new Comunidad("Mercurio ", 120));
        testComunidades.add(new Comunidad("Venus    ", 230));
        testComunidades.add(new Comunidad("Marte    ", 340));
        testComunidades.add(new Comunidad("Jupiter  ", 450));
        testComunidades.add(new Comunidad("Saturno  ", 560));
        testComunidades.add(new Comunidad("Urano    ", 670));
        testComunidades.add(new Comunidad("Neptuno  ", 780));
        testComunidades.add(new Comunidad("Vulcano  ", 890));

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
