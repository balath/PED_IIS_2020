/*************************************************************************************************************
 * La clase Almacen_Simulaciones modela un almacén para resultados de simulaciones que permite navegar entre *
 * las distintas simulaciones implementándolo a modo de lista por punto de interés mediante dos pilas.       *
 *************************************************************************************************************/
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

public class Almacen_Simulaciones {

    /* La cima de la Stack anterior, contendrá el elemento en la posicion previa a la actual.*/
    private Stack<Resultado_Simulacion> anterior;
    /* La cima de la Stack siguiente, contendrá el elemento en la posición actual, y debajo de este, se encontrará
    *  el elemento en la posición siguiente. */
    private Stack<Resultado_Simulacion> siguiente;

    private static Almacen_Simulaciones almacenSimulaciones;
    private PropertyChangeSupport soporteCambios;

    /**
     * Constructor de clase.
     */
    private Almacen_Simulaciones(){
        anterior = new Stack<>();
        siguiente = new Stack<>();
        soporteCambios = new PropertyChangeSupport(this);
    }

    /**
     * Constructor de clase que añade un primer resultado en la posición actual.
     * @param resultadoNuevo para añadir.
     */
    public Almacen_Simulaciones(Resultado_Simulacion resultadoNuevo){
        super();
        addSimulacion(resultadoNuevo);
    }

    public static Almacen_Simulaciones getSingletonInstance() {
        if (almacenSimulaciones == null){
            almacenSimulaciones = new Almacen_Simulaciones();
        }
        else{
            System.out.println("No se puede crear el almacén porque ya se ha creado");
        }
        return almacenSimulaciones;
    }

    /**
     * Método que añade un resultado nuevo al almacén en la posición actual.
     * @param resultadoNuevo para añadir.
     * @return resultado añadido.
     */
    public void addSimulacion(Resultado_Simulacion resultadoNuevo){
        while (!siguiente.empty()) { //Vaciar la pila siguiente.
            anterior.push(siguiente.pop());
        }
        siguiente.push(resultadoNuevo);  //Añadir el nuevo resultado como actual
        update(resultadoNuevo);   // Notificar cambio de propiedad a los auditores
    }

    /**
     * Método que notifica cambio de propiedad con el resultado situado en la posición actual.
     */
    public void getActual(){
        if (!siguiente.empty()) { //Se comprueba que la posición actual no sea nula.
            update(siguiente.peek());
        }
    }

    /**
     * Método que notifica cambio de propiedad con el resultado situado en la posición siguiente.
     */
    public void getSiguiente(){
        if (!(siguiente.size() <= 1)) {    //Se comprueba que exista un segundo elemento en la pila.
            anterior.push(siguiente.pop()); //Se mueven las posiciones
            getActual();                    //Llamada al resultado actual
        }
    }

    /**
     * Método que notifica cambio de propiedad con el resultado situado en la posición anterior.
     */
    public void getAnterior(){
        if (!anterior.empty()) { //Se comprueba que la posición anterior no sea nula.
            siguiente.push(anterior.pop());
            getActual();
        }
    }

    private void update(Resultado_Simulacion resultadoNuevo){
        soporteCambios.firePropertyChange("Resultado", null, resultadoNuevo);
    }

    public void addPropertyChangeListener(PropertyChangeListener auditor) {
        soporteCambios.addPropertyChangeListener(auditor);
    }

    public void removePropertyChangeListener(PropertyChangeListener auditor) {
        soporteCambios.removePropertyChangeListener(auditor);
    }

}
