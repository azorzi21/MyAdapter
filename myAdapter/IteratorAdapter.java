//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myAdapter;

import java.util.*;
/**
 * Implementazione dell'interfaccia HIterator per la classe ListAdapter.
 * Sono implementate anche le operazioni opzionali previste dall'interfaccia
 * Iterator della versione J2SE 1.4.2.
 */
public class IteratorAdapter implements HIterator
{
	/**
	 * Lista sulla quale opera l'iteratore.
	 */
	protected HList list;
	
	/**
	 * Indice del prossimo elemento da restituire tramite next().
	 */
    protected int cur;
	
	/**
	 * Indice dell'ultimo elemento restituito dall'iteratore.
	 * Vale -1 quando non è possibile eseguire remove().
	 */
	protected int lastR;
	
	//sono stati creati 2 costruttori per facilitare poi lo sviluppo di ListIteratorAdapter
	
	/**
	 * Costruisce un iteratore sulla lista specificata, posizionato
	 * prima del primo elemento.
	 *
	 * @param list lista su cui deve operare l'iteratore
	 * @throws NullPointerException se la lista specificata è null
	 */
	public IteratorAdapter(HList list)
    {
        this(list, 0);
    }
	
	
	/**
	 * Costruisce un iteratore sulla lista specificata, posizionato
	 * nell'indice indicato (prima dell'elemento in posizione index
	 * oppure dopo l'ultimo elemento se index è uguale a size).
	 *
	 * @param list lista su cui deve operare l'iteratore
	 * @param index posizione iniziale dell'iteratore
	 * @throws NullPointerException se la lista specificata è null
	 * @throws IndexOutOfBoundsException se l'indice è minore di zero
	 *         oppure maggiore della dimensione della lista
	 */
	protected IteratorAdapter(HList list, int index)
	{
		if (list == null)
		{
			throw new NullPointerException();
		}

		if (index < 0 || index > list.size())
		{
			throw new IndexOutOfBoundsException();
		}

		this.list = list;
		this.cur = index;
		this.lastR = -1;
	}
	
	
	/**
	 * Verifica se l'iteratore ha un elemento successivo.
	 *
	 * @return true se esiste un elemento successivo, false altrimenti
	 */
	public boolean hasNext()
	{
		return cur < list.size();
	}
	
	
	/**
	 * Restituisce l'elemento successivo della lista e avanza
	 * la posizione dell'iteratore.
	 *
	 * @return elemento successivo della lista
	 * @throws NoSuchElementException se non esiste un elemento successivo
	 */
	public Object next()
	{
		if (!hasNext())
        {
            throw new NoSuchElementException();
        }

        Object element = list.get(cur);

        lastR = cur;
        cur++;

        return element;
	}
	
	
	/**
	 * Rimuove dalla lista l'ultimo elemento restituito dall'iteratore.
	 * Il metodo può essere chiamato una sola volta dopo ogni chiamata
	 * valida a next().
	 *
	 * @throws RuntimeException se next() non è ancora stato chiamato
	 *         oppure se remove() è già stato chiamato dopo l'ultima chiamata a next()
	 */
	public void remove()
	{
		if (lastR == -1)
        {
            throw new RuntimeException();
        }

        list.remove(lastR);

        if (lastR < cur)
        {
            cur--;
        }

        lastR = -1;
	}
}