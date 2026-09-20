//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myAdapter;

import java.util.*;
/**
 * Implementazione dell'interfaccia HListIterator per la classe ListAdapter.
 * Sono implementate anche le operazioni opzionali previste dall'interfaccia
 * ListIterator della versione J2SE 1.4.2.
 */
public class ListIteratorAdapter extends IteratorAdapter implements HListIterator
{
	/**
	 * Costruisce un list iterator sulla lista specificata, sfruttando il costruttore
	 * della classe madre.
	 *
	 * @param list lista su cui deve operare il list iterator
	 * @param index posizione iniziale del list iterator
	 * @throws NullPointerException se la lista specificata è null
	 * @throws IndexOutOfBoundsException se l'indice è minore di zero
	 *                                   oppure maggiore della dimensione
	 *                                   della lista
	 */
	public ListIteratorAdapter(HList list, int index)
    {
        super(list, index);
    }


	/**
	 * Verifica se il list iterator ha un elemento precedente.
	 *
	 * @return true se esiste un elemento precedente, false altrimenti
	 */
    public boolean hasPrevious()
    {
        return cur > 0;
    }
	
	
	
	/**
	 * Restituisce l'elemento precedente della lista e arretra
	 * la posizione dell'iteratore.
	 *
	 * @return elemento precedente della lista
	 * @throws NoSuchElementException se non esiste un elemento precedente
	 */
    public Object previous()
    {
        if(!hasPrevious())
        {
            throw new NoSuchElementException();
        }

        cur--;

        Object element = list.get(cur);

        lastR = cur;

        return element;
    }
	
	
	/**
	 * Restituisce l'indice dell'elemento che sarebbe restituito
	 * da una successiva chiamata a next().
	 *
	 * @return indice dell'elemento successivo, oppure la dimensione della lista
	 *         se il list iterator è alla fine della lista
	 */
    public int nextIndex()
    {
        return cur;
    }
	
	
	/**
	 * Restituisce l'indice dell'elemento che sarebbe restituito
	 * da una successiva chiamata a previous().
	 *
	 * @return indice dell'elemento precedente, oppure -1 se il list iterator
	 *         è all'inizio della lista
	 */
    public int previousIndex()
    {
        return cur - 1;
    }
	
	
	/**
	 * Sostituisce con l'elemento specificato l'ultimo elemento restituito
	 * da next() o previous().
	 *
	 * @param o elemento con cui sostituire l'ultimo elemento restituito
	 * @throws RuntimeException se non è ancora stato chiamato next()
	 *         o previous(), oppure se dopo l'ultima chiamata a next() o previous() è già stato
	 *         chiamato add() o remove()
	 */
    public void set(Object o)
    {
        if(lastR == -1)
        {
            throw new RuntimeException();
        }

        list.set(lastR, o);
    }
	
	
	/**
	 * Inserisce l'elemento specificato nella lista nella posizione corrente
	 * dell'iteratore. L'elemento viene inserito prima
	 * dell'elemento che sarebbe restituito da next().
	 * Dopo l'inserimento, una chiamata a previous() restituirebbe
	 * l'elemento appena aggiunto.
	 *
	 * @param o elemento da inserire nella lista
	 */
	public void add(Object o)
    {
        list.add(cur, o);
        cur++;
        lastR = -1;
    }
}