/**********************************************************************************************
 * La clase Pais modela una subclase de comunidad compuesta por una serie de provincias       *
 **********************************************************************************************/
import java.util.HashSet;

public class Pais extends Comunidad {

    private HashSet<Provincia> provincias;

    /**
     * Constructor de clase Pais
     */
    public Pais(String nombre){
        super(nombre, 0);
        provincias = new HashSet<>();
    }

    @Override
    public EnumComunidad getTipoComunidad() {
        return EnumComunidad.PAIS;
    }

    @Override
    /**
     * Redefine el método que devuelve la población como el resultado de las poblaciones de las provincias
     */
    public int getPoblacion() {
        return provincias.stream().map(provincia -> provincia.getPoblacion()).reduce(0,Integer::sum);
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
