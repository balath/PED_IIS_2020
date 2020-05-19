/*************************************************************************************************************
 * La clase Almacen_Comunidades modela un almacén para la lista de comunidades sobre las que se calcula una  *
 * simulación.                                                                                               *
 *************************************************************************************************************/
import java.util.ArrayList;

public class Almacen_Comunidades {

    private ArrayList<Comunidad> comunidades;

    /**
     * Constructor de clase Almacen_Comunidades
     * @param datos Array de datos introducidos por usuario
     */
    public Almacen_Comunidades(Object[][] datos){
        comunidades = new ArrayList<>(datos[0].length);
        for (int i = 0; i < datos[Main.DataIndex.NOMBRE.ordinal()].length; i++) {
            comunidades.add(new Comunidad(
                    i,
                    (String)datos[Main.DataIndex.NOMBRE.ordinal()][i],
                    (int)datos[Main.DataIndex.POBLACION.ordinal()][i],
                    (int)datos[Main.DataIndex.PORCENTAJE_V.ordinal()][i]));
        }
    }

    /**
     * Devuelve una lista de las comunidades creadas en el almacén.
     * @return ArrayList con las comunidades del almacén.
     */
    public ArrayList<Comunidad> getAlmacen(){
        return comunidades;
    }
}
