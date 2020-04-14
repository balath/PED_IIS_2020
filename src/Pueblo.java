/***************************************************************************************************
 * La clase Pueblo modela una subclase de comunidad que representa la entidad mínima de esta clase *
 ***************************************************************************************************/
import java.util.HashSet;

public class Pueblo extends Comunidad {
    /**
     * Constructor de clase Pueblo
     */
    public Pueblo(String nombre, int poblacion){
        super(nombre,poblacion);
    }

    @Override
    public Comunidad.EnumComunidad getTipoComunidad() {
        return Comunidad.EnumComunidad.PUEBLO;
    }

}
