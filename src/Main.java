/**********************************************************************************************
 * Clase Main de la aplicación, que instancia la estructura de datos e inicia la interfaz     *
 **********************************************************************************************/
import java.beans.PropertyChangeListener;
import java.util.Locale;

public class Main {

    //Enumerado que codifica la configuración de los datos en el array donde se recogen de la entrada de usuario.
    public enum DataIndex{
        NOMBRE,POBLACION,PORCENTAJE_V,E,P,DIAS_SIMULACION,FECHA_INICIAL
    };

    public static void main(String[] args){
        Locale.setDefault(new Locale("es","ES"));
        Almacen_Simulaciones almacenSimulaciones = Almacen_Simulaciones.getSingletonInstance();
        PropertyChangeListener interfaz = new InterfazGrafica(almacenSimulaciones);
        // Se relaciona el modelo con la vista añadiendo la interfaz como auditor de los cambios en el almacén.
        almacenSimulaciones.addPropertyChangeListener(interfaz);
    }

    public static void entradaDatos(Object[][] datos, Almacen_Simulaciones almacenSimulaciones){
        Almacen_Comunidades comunidades = new Almacen_Comunidades(datos);
        Simulador simulador = new Simulador(datos);
        Resultado_Simulacion simulacion = simulador.calculoTotal(
                comunidades.getAlmacen(),
                (Integer)datos[Main.DataIndex.DIAS_SIMULACION.ordinal()][0]
        );
        almacenSimulaciones.addSimulacion(simulacion);
    }
}
