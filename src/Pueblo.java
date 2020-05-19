/***************************************************************************************************
 * La clase Pueblo modela una subclase de comunidad que representa la entidad mínima de esta clase *
 ***************************************************************************************************/

public class Pueblo extends Comunidad {
    /**
     * Constructor de clase Pueblo
     */
    public Pueblo(int id, String nombre, int poblacion, int porcentaje_V){
        super(id,nombre,poblacion,porcentaje_V);
    }

    @Override
    public Comunidad.EnumComunidad getTipoComunidad() {
        return Comunidad.EnumComunidad.PUEBLO;
    }

}
