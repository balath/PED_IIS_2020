/************************************************************************************************************
 * La clase Simulaciones modela un almacén para resultados de simulaciones que permite navegar entre las    *
 * distintas simulaciones implementándolo a modo de lista por punto de interés mediante dos pilas.          *
 ************************************************************************************************************/
import java.util.Stack;

public class Simulaciones {

    /* La cima de la Stack anterior, contendrá el elemento en la posicion previa a la actual.*/
    private Stack<ResultadoSimulacion> anterior;
    /* La cima de la Stack siguiente, contendrá el elemento en la posición actual, y debajo de este, se encontrará
    *  el elemento en la posición siguiente. */
    private Stack<ResultadoSimulacion> siguiente;

    /**
     * Constructor de clase.
     */
    public Simulaciones(){
        anterior = new Stack<>();
        siguiente = new Stack<>();
    }

    /**
     * Constructor de clase que añade un primer resultado en la posición actual.
     * @param resultado_nuevo para añadir.
     */
    public Simulaciones(ResultadoSimulacion resultado_nuevo){
        super();
        siguiente.push(resultado_nuevo);
    }

    /**
     * Método que añade un resultado nuevo al almacén en la posición actual.
     * @param resultado_nuevo para añadir.
     * @return resultado añadido.
     */
    public ResultadoSimulacion addSimulacion(ResultadoSimulacion resultado_nuevo){
        if (siguiente.empty()) { //Si la pila siguiente esta vacía el parámetro se apila en ella.
            siguiente.push(resultado_nuevo);
        } else { //Si no está vacía, primero se mueve el elemento que ocupe la cima a la pila de elementos anteriores.
            anterior.push(siguiente.pop());
            siguiente.push(resultado_nuevo);
        }
        return resultado_nuevo;
    }

    /**
     * Método que devuelve el resultado situado en la posición actual.
     * @return resultado actual.
     */
    public ResultadoSimulacion getActual(){
        if (siguiente.empty()) { //Se comprueba que la posición actual no sea nula.
            return null;
        }
        return siguiente.peek();
    }

    /**
     * Método que devuelve el resultado situado en la posición siguiente.
     * @return resultado siguiente.
     */
    public ResultadoSimulacion getSiguiente(){
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
    public ResultadoSimulacion getAnterior(){
        if (anterior.empty()) { //Se comprueba que la posición anterior no sea nula.
            return null;
        }
        siguiente.push(anterior.pop());
        return getActual();
    }
}
