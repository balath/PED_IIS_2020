/**
 * La clase Comunidad modela el tipo de comunidad ya sea genérica, o de países, pueblos o provincias
 */
public class Comunidad {

    private static int ID_COUNTER = 00;

    private int id_Comunidad;
    private String nombre;
    private int poblacion;

    public enum EnumComunidad {
        COMUNIDAD, PAIS, PUEBLO, PROVINCIA
    }

    /*Constructor de la clase Comunidad*/
    public Comunidad(String nombre, int poblacion){
        id_Comunidad = ID_COUNTER++;
        this.nombre = nombre;
        this.poblacion = poblacion;
    }

    /* Devuelve el tipo de comunidad */
    public EnumComunidad getTipoComunidad(){
        return EnumComunidad.COMUNIDAD;
    }

    /* Devuelve el nombre de la comunidad */
    public String getNombre(){
        return nombre;
    }

    /* Devuelve la población total de la comunidad */
    public int getPoblacion(){
        return poblacion;
    }

    /* Devuelve el número de viajeros de la comunidad según el coeficiente dado como parametro de entrada */
    public int getViajeros(float promedio_V){
        return (int)(poblacion * (promedio_V / 100));
    }

    /* Modifica el nombre de la comunidad */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /* Modifica la población total de la comunidad */
    public void setPoblacion(int poblacion){
        this.poblacion = poblacion;
    }

    /* Devuelve un String con los datos de la comunidad */
    public String toString(){
        StringBuilder linea = new StringBuilder();
        return linea
                .append(getNombre())
                .appendCodePoint(9)
                .append(getPoblacion())
                .toString();
    }

}
