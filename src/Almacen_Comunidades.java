/*************************************************************************************************************
 * La clase Almacen_Comunidades modela un almacén para la lista de comunidades sobre las que se calcula una  *
 * simulación.                                                                                               *
 *************************************************************************************************************/
import java.util.ArrayList;

public class Almacen_Comunidades {

    ArrayList<Comunidad> comunidades;

    /**
     * Constructor de clase Almacen_Comunidades
     * @param nombres Array de nombres de cada comunidad a crear.
     * @param poblaciones Poblaciones totales de cada comunidad a crear.
     * @pre (nombres[i] && poblaciones[i]) ϵ comunidad_i
     * @pre nombres.length == poblaciones.lenght
     */
    public Almacen_Comunidades(String[] nombres, Integer[] poblaciones){
        comunidades = new ArrayList<>(nombres.length);
        for (int i = 0; i < nombres.length; i++) {
            comunidades.add(new Comunidad(nombres[i],poblaciones[i]));
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
