/*************************************************************************************************************
 * La clase Comunidad modela el tipo de comunidad ya sea genérica, o de países, pueblos o provincias         *
 *************************************************************************************************************/
public class Comunidad extends Object{

    private int id;
    protected String nombre;
    protected int poblacion;
    protected int porcentaje_V;

    public enum EnumComunidad {
        COMUNIDAD, PAIS, PUEBLO, PROVINCIA
    }

    /**
     * Constructor de clase Comunidad
     */
    public Comunidad(int id, String nombre, int poblacion, int porcentaje_V){
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.porcentaje_V = porcentaje_V;
    }

    /**
     * Devuelve un enum con el tipo de comunidad
     * @return [COMUNIDAD|PAIS|PUEBLO|PROVINCIA]
     */
    public EnumComunidad getTipoComunidad(){
        return EnumComunidad.COMUNIDAD;
    }

    /**
     * Devuelve el número id de la comunidad
     * @return número id
     */
    public int getId(){
        return id;
    }

    /**
     * Devuelve el nombre de la comunidad
     * @return nombre
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Devuelve la población total de la comunidad
     * @return poblacion
     */
    public int getPoblacion(){
        return poblacion;
    }

    /**
     * Devuelve el número de viajeros de la comunidad según el porcentaje_V
     * @return numero de viajeros de la comunidad
     */
    public int getViajeros(){
        return (int)(poblacion * (((float)porcentaje_V) / 100));
    }

    /**
     * Modifica el nombre de la comunidad
     * @param nombre nuevo
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Modifica la población total de la comunidad
     * @param poblacion total nueva
     */
    public void setPoblacion(int poblacion){
        this.poblacion = poblacion;
    }

    /**
     * Modifica el porcentaje de viajeros de la comunidad
     * @param porcentaje_V nuevo
     */
    public void setPorcentaje_V(int porcentaje_V){
        this.porcentaje_V = porcentaje_V;
    }
}
