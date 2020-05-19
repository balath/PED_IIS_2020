/**********************************************************************************************
 * La clase Provincia modela una subclase de comunidad compuesta por una serie de pueblos     *
 **********************************************************************************************/
import java.util.HashSet;

public class Provincia extends Comunidad {

    private HashSet<Pueblo> pueblos;

    /**
     * Constructor de clase Provincia
     */
    public Provincia(int id, String nombre, int poblacion, int porcentaje_V){
        super(id, nombre, poblacion,porcentaje_V);
        pueblos = new HashSet<>();
    }

    @Override
    public Comunidad.EnumComunidad getTipoComunidad() {
        return Comunidad.EnumComunidad.PROVINCIA;
    }

    /**
     * Método que asocia un pueblo a la provincia
     * @return true si se ha asociado con exito
     *         false si ya existía un pueblo asociado con ese nombre
     */
    public boolean addPueblo(Pueblo nuevoPueblo){
        if (pueblos.add(nuevoPueblo)) {
            poblacion += nuevoPueblo.getPoblacion();
            return true;
        }
        return false;
    }

    /**
     * Getter que devuelve el conjunto de los pueblos que pertenecen a una provincia
     */
    public HashSet<Pueblo> getPueblos(){
        return pueblos;
    }
}
