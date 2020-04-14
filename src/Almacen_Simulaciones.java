/*************************************************************************************************************
 * La clase Almacen_Simulaciones modela un almacén para resultados de simulaciones que permite navegar entre *
 * las distintas simulaciones implementándolo a modo de lista por punto de interés mediante dos pilas.       *
 *************************************************************************************************************/
import java.util.Stack;

public class Almacen_Simulaciones {

    /* La cima de la Stack anterior, contendrá el elemento en la posicion previa a la actual.*/
    private Stack<Resultado_Simulacion> anterior;
    /* La cima de la Stack siguiente, contendrá el elemento en la posición actual, y debajo de este, se encontrará
    *  el elemento en la posición siguiente. */
    private Stack<Resultado_Simulacion> siguiente;

    /**
     * Constructor de clase.
     */
    public Almacen_Simulaciones(){
        anterior = new Stack<>();
        siguiente = new Stack<>();
    }

    /**
     * Constructor de clase que añade un primer resultado en la posición actual.
     * @param resultadoNuevo para añadir.
     */
    public Almacen_Simulaciones(Resultado_Simulacion resultadoNuevo){
        super();
        siguiente.push(resultadoNuevo);
    }

    /**
     * Método que añade un resultado nuevo al almacén en la posición actual.
     * @param resultadoNuevo para añadir.
     * @return resultado añadido.
     */
    public Resultado_Simulacion addSimulacion(Resultado_Simulacion resultadoNuevo){
        if (siguiente.empty()) { //Si la pila siguiente esta vacía el parámetro se apila en ella.
            siguiente.push(resultadoNuevo);
        } else { //Si no está vacía, primero se mueve el elemento que ocupe la cima a la pila de elementos anteriores.
            anterior.push(siguiente.pop());
            siguiente.push(resultadoNuevo);
        }
        return resultadoNuevo;
    }

    /**
     * Método que devuelve el resultado situado en la posición actual.
     * @return resultado actual.
     */
    public Resultado_Simulacion getActual(){
        if (siguiente.empty()) { //Se comprueba que la posición actual no sea nula.
            return null;
        }
        return siguiente.peek();
    }

    /**
     * Método que devuelve el resultado situado en la posición siguiente.
     * @return resultado siguiente.
     */
    public Resultado_Simulacion getSiguiente(){
        if (siguiente.size() <= 1) { //Se comprueba que la posición siguiente no sea nula.
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
}
