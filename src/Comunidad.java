/*************************************************************************************************************
 * La clase Comunidad modela el tipo de comunidad ya sea genérica, o de países, pueblos o provincias         *
 *************************************************************************************************************/
public class Comunidad extends Object{

    private String nombre;
    private int poblacion;

    public enum EnumComunidad {
        COMUNIDAD, PAIS, PUEBLO, PROVINCIA
    }

    /**
     * Constructor de clase Comunidad
     */
    public Comunidad(String nombre, int poblacion){
        this.nombre = nombre;
        this.poblacion = poblacion;
    }

    /**
     * Devuelve un enum con el tipo de comunidad
     * @return [COMUNIDAD|PAIS|PUEBLO|PROVINCIA]
     */
    public EnumComunidad getTipoComunidad(){
        return EnumComunidad.COMUNIDAD;
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
     * Devuelve el número de viajeros de la comunidad según el coeficiente dado como parametro de entrada
     * @param coeficiente_V es el porcentaje aplicable sobre el total de la población.
     * @return numero de viajeros de la comunidad
     */
    public int getViajeros(float coeficiente_V){
        return (int)(poblacion * (coeficiente_V / 100));
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
     * Devuelve un String con los datos de la comunidad
     * @return String con el formato:
     *         [nombre]    [poblacion]
     */
    public String toString(){
        StringBuilder linea = new StringBuilder();
        return linea
                .append(getNombre())
                .appendCodePoint(9)
                .append(getPoblacion())
                .toString();
    }

}
