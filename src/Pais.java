/**********************************************************************************************
 * La clase Pais modela una subclase de comunidad compuesta por una serie de provincias       *
 **********************************************************************************************/
import java.util.HashSet;

public class Pais extends Comunidad {

    private HashSet<Provincia> provincias;

    /**
     * Constructor de clase Pais
     */
    public Pais(int id, String nombre, int poblacion, int porcentaje_V){
        super(id, nombre, poblacion,porcentaje_V);
        provincias = new HashSet<>();
    }

    @Override
    public EnumComunidad getTipoComunidad() {
        return EnumComunidad.PAIS;
    }

    /**
     * Método que asocia una provincia al país e incrementa la población total del país con
     * la de la provincia nueva.
     * @return true si se ha asociado con exito
     *         false si ya existía una provincia asociada con ese nombre
     */
    public boolean addProvincia(Provincia nuevaProvincia){
        if (provincias.add(nuevaProvincia)) {
            poblacion += nuevaProvincia.getPoblacion();
            return true;
        }
        return false;
    }

    /**
     * Getter que devuelve el conjunto de las provincias que forman un pais
     */
    public HashSet<Provincia> getProvincias(){
        return provincias;
    }

}
