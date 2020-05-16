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
        siguiente.push(resultadoNuevo);
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
        update(resultadoNuevo);
    }

    /**
     * Método que devuelve el resultado situado en la posición actual.
     * @return resultado actual.
     */
    public Resultado_Simulacion getActual(){
        if (siguiente.empty()) { //Se comprueba que la posición actual no sea nula.
            return null;
        }
        update(siguiente.peek());
        return siguiente.peek();
    }

    /**
     * Método que devuelve el resultado situado en la posición siguiente.
     * @return resultado siguiente.
     */
    public Resultado_Simulacion getSiguiente(){
        if (siguiente.size() <= 1) { //Se comprueba que exista un segundo elemento en la pila.
            return null;
        }
        anterior.push(siguiente.pop());
        return getActual();
    }

    /**
     * Método que devuelve el resultado situado en la posición anterior.
     * @return resultado anterior.
     */
    public Resultado_Simulacion getAnterior(){
        if (anterior.empty()) { //Se comprueba que la posición anterior no sea nula.
            return null;
        }
        siguiente.push(anterior.pop());
        return getActual();
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
