import java.util.HashSet;

public class Pais extends Comunidad {

    private HashSet<Provincia> provincias;

    /**
     * Constructor de clase Pais
     */
    public Pais(String nombre, int poblacion){
        super(nombre, poblacion);
        provincias = new HashSet<>();
    }

    @Override
    public EnumComunidad getTipoComunidad() {
        return EnumComunidad.PAIS;
    }

    /**
     * Método que asocia una provincia al país
     * @return true si se ha asociado con exito
     *         false si ya existía una provincia asociada con ese nombre
     */
    public boolean addProvincia(Provincia nuevaProvincia){
        return provincias.add(nuevaProvincia);
    }

    /**
     * Getter que devuelve el conjunto de las provincias que forman un pais
     */
    public HashSet<Provincia> getProvincias(){
        return provincias;
    }

}
