/***************************************************************************************************
 * La clase Pueblo modela una subclase de comunidad que representa la entidad mínima de esta clase *
 ***************************************************************************************************/
import java.util.HashSet;

public class Pueblo extends Comunidad {
    /**
     * Constructor de clase Pueblo
     */
    public Pueblo(int id, String nombre, int poblacion){
        super(id,nombre,poblacion,0);
    }

    @Override
    public Comunidad.EnumComunidad getTipoComunidad() {
        return Comunidad.EnumComunidad.PUEBLO;
    }

}
