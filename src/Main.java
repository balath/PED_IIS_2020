import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Main {

    public enum DataIndex {NOMBRE,POBLACION,PORCENTAJE_V,E,P,DIAS_SIMULACION,FECHA_INICIAL};

    private static Almacen_Simulaciones almacenSimulaciones;

    public static void main(String[] args){
        Locale.setDefault(new Locale("es","ES"));
        //Se instancian el almacen de simulaciones y la GUI, y se relaciona el modelo (los resultados de las
        // simulaciones almacenadas) con la vista mediante la .
        almacenSimulaciones = Almacen_Simulaciones.getSingletonInstance();
        InterfazGrafica interfaz = new InterfazGrafica(almacenSimulaciones);
        almacenSimulaciones.addPropertyChangeListener(interfaz);
    }

    public static void entradaDatos(Object[][] datos){
        Almacen_Comunidades comunidades = new Almacen_Comunidades(datos);
        Simulador simulador = new Simulador(datos);
        Resultado_Simulacion simulacion = simulador.calculoTotal(
                comunidades.getAlmacen(),
                (Integer)datos[DataIndex.DIAS_SIMULACION.ordinal()][0]
        );
        almacenSimulaciones.addSimulacion(simulacion);
    }


}
