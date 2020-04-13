import java.util.HashSet;

public class Provincia extends Comunidad {

    private HashSet<Pueblo> pueblos;

    /**
     * Constructor de clase Provincia
     */
    public Provincia(String nombre, int poblacion){
        super(nombre, poblacion);
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
        return pueblos.add(nuevoPueblo);
    }

    /**
     * Getter que devuelve el conjunto de los pueblos que pertenecen a una provincia
     */
    public HashSet<Pueblo> getPueblos(){
        return pueblos;
    }
}
