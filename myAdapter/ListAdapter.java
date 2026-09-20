//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myAdapter;

import java.util.Vector;

/**
 * Adapter dell'interfaccia HList realizzato utilizzando come adaptee
 * un oggetto Vector compatibile con l'ambiente CLDC 1.1.
 */
public class ListAdapter implements HList, HCollection
{
	/**
	 * Struttura dati utilizzata per memorizzare gli elementi della lista.
	 */
	protected Vector adaptee;
	
	/**
	 * Verifica che l'indice specificato sia valido per accedere
	 * a un elemento già presente nella lista.
	 * L'indice deve essere compreso tra 0 incluso e size() escluso.
	 *
	 * @param index l'indice da controllare
	 * @throws IndexOutOfBoundsException se l'indice è minore di 0
	 *         oppure maggiore o uguale alla dimensione della lista
	 */
	private void checkIndex(int index) 
	{
		if(index < 0 || index >= size()) 
		{
			throw new IndexOutOfBoundsException();
		}
	}


	/**
	 * Verifica che l'indice specificato sia valido per inserire
	 * un elemento nella lista.
	 * L'indice deve essere compreso tra 0 incluso e size() incluso.
	 *
	 * @param index l'indice da controllare
	 * @throws IndexOutOfBoundsException se l'indice è minore di 0
	 *         oppure maggiore della dimensione della lista
	 */
	private void checkIndexPlus(int index) 
	{
		if(index < 0 || index > size()) 
		{
			throw new IndexOutOfBoundsException();
		}
	}
	
	
	/**
	 * Costruisce una nuova lista vuota utilizzando un vector
	 */
	public ListAdapter()
	{
			adaptee = new Vector();
	}
	
	
	/**
	 * Restituisce il numero di elementi presenti nella lista.
	 *
	 * @return il numero di elementi contenuti nella lista
	 */
	public int size() 
	{
		return adaptee.size();
	}
	

	/**
	 * Verifica se la lista non contiene elementi.
	 *
	 * @return true se la lista è vuota, false altrimenti
	 */
	public boolean isEmpty() 
	{
		return adaptee.isEmpty();
	}
	
	
	/**
	 * Verifica se la lista contiene l'elemento specificato.
	 *
	 * @param o l'elemento di cui verificare la presenza nella lista
	 * @return true se la lista contiene l'elemento specificato, false altrimenti
	 */
	public boolean contains(Object o) 
	{	
		return adaptee.contains(o);
	}
	
	
	/**
	 * Restituisce un iteratore sugli elementi della lista.
	 * L'iteratore viene inizializzato all'inizio della lista.
	 *
	 * @return un iteratore sugli elementi della lista
	 */
	public HIterator iterator() 
	{
		return new IteratorAdapter(this);
	}
	

	/**
	 * Restituisce un array contenente tutti gli elementi presenti nella lista,
	 * nello stesso ordine in cui compaiono nella lista.
	 *
	 * @return un array contenente tutti gli elementi della lista nell'ordine corretto
	 */
	public Object[] toArray() 
	{
		Object[] array = new Object[adaptee.size()];
		
		for(int i = 0; i < adaptee.size(); i++) 
		{
			array[i] = adaptee.elementAt(i);
		}

		return array;
	}
	
	
	/**
	 * Restituisce un array contenente tutti gli elementi presenti nella lista.
	 * Se l'array passato come parametro è sufficientemente grande, viene usato
	 * per contenere gli elementi (e si aggiunge un null alla fine); 
	 * altrimenti viene creato un nuovo array di tipo Object[].
	 * In questa implementazione non viene mantenuto il runtime type specifico
	 * dell'array passato come parametro.
	 *
	 * @param a l'array in cui inserire gli elementi della lista, se sufficientemente grande
	 * @return l'array contenente gli elementi della lista
	 * @throws NullPointerException se l'array passato è null
	 */
	public Object[] toArray(Object[] a) 
	{
		if (a == null) {
			throw new NullPointerException();
		}

		int s = size();

		if (a.length < s) {
			a = new Object[s];
		}

		for (int i = 0; i < s; i++) {
			a[i] = get(i);
		}

		if (a.length > s) {
			a[s] = null;
		}

		return a;
	}
	
	
	/**
	 * Aggiunge l'elemento specificato alla fine della lista.
	 *
	 * @param o l'elemento da aggiungere alla lista
	 * @return true, previsto dalla specifica dell'interfaccia Collection
	 */
	public boolean add(Object o) 
	{			
		adaptee.addElement(o);
		return true;
	}
	
	
	/**
	 * Rimuove dalla lista la prima occorrenza dell'elemento specificato,
	 * se presente.
	 * Se un elemento viene rimosso, tutti gli elementi successivi vengono
	 * spostati verso sinistra di una posizione.
	 *
	 * @param o l'elemento da rimuovere dalla lista
	 * @return true se la lista conteneva l'elemento specificato e questo è stato rimosso,
	 *         false altrimenti
	 */
	public boolean remove(Object o) 
	{
		return adaptee.removeElement(o);
	}
	

	/**
	 * Verifica se la lista contiene tutti gli elementi presenti nella collezione specificata.
	 *
	 * @param c la collezione contenente gli elementi di cui verificare la presenza
	 * @return true se la lista contiene tutti gli elementi della collezione specificata,
	 *         false altrimenti
	 * @throws NullPointerException se la collezione specificata è null
	 */
	public boolean containsAll(HCollection c) 
	{
		if(c == null)
		{
			throw new NullPointerException();
		}

		HIterator it = c.iterator();

		while(it.hasNext())
		{
			if(!contains(it.next()))
			{
				return false;
			}
		}

		return true;
	}
	
	
	/**
	 * Aggiunge alla fine della lista tutti gli elementi contenuti
	 * nella collezione specificata, mantenendo l'ordine restituito
	 * dall'iteratore della collezione stessa.
	 *
	 * @param c la collezione contenente gli elementi da aggiungere alla lista
	 * @return true se la lista viene modificata, false se la collezione specificata è vuota
	 * @throws NullPointerException se la collezione specificata è null
	 */
	public boolean addAll(HCollection c) 
	{
		if(c == null)
		{
			throw new NullPointerException();
		}

		Object[] elements = c.toArray();

		if(elements.length == 0)
		{
			return false;
		}

		for(int i = 0; i < elements.length; i++)
		{
			add(elements[i]);
		}

		return true;
	}
	
	
	/**
	 * Inserisce nella lista, a partire dall'indice specificato, tutti gli elementi
	 * contenuti nella collezione specificata, mantenendo l'ordine restituito
	 * dall'iteratore della collezione stessa.
	 * Gli elementi presenti dalla posizione indicata in poi vengono spostati
	 * verso destra.
	 *
	 * @param index l'indice a partire dal quale inserire gli elementi
	 * @param c la collezione contenente gli elementi da inserire nella lista
	 * @return true se la lista viene modificata, false se la collezione specificata è vuota
	 * @throws NullPointerException se la collezione specificata è null
	 * @throws IndexOutOfBoundsException se l'indice è minore di 0
	 *                                   oppure maggiore della dimensione della lista
	 */
	public boolean addAll(int index, HCollection c) 
	{
		checkIndexPlus(index);

		if(c == null)
		{
			throw new NullPointerException();
		}

		Object[] elements = c.toArray();

		if(elements.length == 0)
		{
			return false;
		}

		for(int i = 0; i < elements.length; i++)
		{
			add(index + i, elements[i]);
		}

		return true;
	}
	
	
	/**
	 * Rimuove dalla lista tutti gli elementi che sono contenuti
	 * nella collezione specificata.
	 *
	 * @param c la collezione contenente gli elementi da rimuovere dalla lista
	 * @return true se la lista viene modificata, false altrimenti
	 * @throws NullPointerException se la collezione specificata è null
	 */
	public boolean removeAll(HCollection c) 
	{
		if(c == null)
		{
			throw new NullPointerException();
		}

		boolean modified = false;

		HIterator it = this.iterator();

		while(it.hasNext())
		{
			Object elem = it.next();

			if(c.contains(elem))
			{
				it.remove();
				modified = true;
			}
		}

		return modified;
	}
	
	
	/**
	 * Mantiene nella lista solo gli elementi che sono contenuti
	 * nella collezione specificata.
	 * Tutti gli altri elementi vengono rimossi.
	 *
	 * @param c la collezione contenente gli elementi da mantenere nella lista
	 * @return true se la lista viene modificata, false altrimenti
	 * @throws NullPointerException se la collezione specificata è null
	 */
	public boolean retainAll(HCollection c) 
	{
		if(c == null)
		{
			throw new NullPointerException();
		}

		boolean modified = false;

		HIterator it = this.iterator();

		while(it.hasNext())
		{
			Object elem = it.next();

			if(!c.contains(elem))
			{
				it.remove();
				modified = true;
			}
		}

		return modified;
	}
	
	
	/**
	 * Rimuove tutti gli elementi presenti nella lista,
	 * rendendo la lista vuota
	 */
	public void clear() 
	{
		adaptee.removeAllElements();
	}
	
	
	/**
	 * Confronta l'oggetto specificato con la lista.
	 * Il metodo restituisce true se 
	 * l'oggetto specificato è una HList,
	 * ha la stessa dimensione di questa lista,
	 * contiene gli stessi elementi nello stesso ordine.
	 * Due elementi corrispondenti sono considerati uguali se sono entrambi null
	 * oppure se il metodo equals del primo elemento restituisce true.
	 *
	 * @param o l'oggetto da confrontare con questa lista
	 * @return true se l'oggetto specificato è uguale a questa lista,
	 *         false altrimenti
	 */
	public boolean equals(Object o) 
	{
		//verifico se sono lo stesso oggetto
		if (o == this)	//vengono confrontati i 2 riferimenti
		{
			return true;
		}

		//verifico se è una lista
		if (!(o instanceof HList))
		{
			return false;
		}

		//se arrivo qui, faccio il cast perchè so che è una lista
		HList second = (HList) o;

		//verifico che abbiamo la stessa dimensione
		if (second.size() != size())
		{
			return false;
		}

		HIterator it1 = this.iterator();
		HIterator it2 = second.iterator();
		
		
		while (it1.hasNext() && it2.hasNext())	
		{
			Object e1 = it1.next();
			Object e2 = it2.next();
			
			//devo prima verificare che non sia null perchè altrimenti equals crasherebbe
			//Trovato nella documentazione di Object.equals() (solo riferimenti non null)
			if (e1 == null)
			{
				if (e2 != null)
				{
					return false;
				}
			}
			else
			{
				if (!e1.equals(e2))
				{
					return false;
				}
			}
		}

		return true;
	}
	
	
	/**
	 * Restituisce il valore hash della lista, calcolato come:
	 * hashCode = 1;
	 * Iterator i = list.iterator();
	 * while(i.hasNext())
	 * {
	 *	Object obj = i.next();
	 *  hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode())
	 * }
	 * @return il valore hash della lista
	 */
	public int hashCode() 
	{
		int hashCode = 1;

		for(int i = 0; i < adaptee.size(); i++) 
		{
			Object obj = adaptee.elementAt(i);
			hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
		}

		return hashCode;
	}
	
	
	/**
	 * Restituisce l'elemento presente nella posizione specificata.
	 *
	 * @param index l'indice dell'elemento da restituire
	 * @return l'elemento presente nella posizione specificata
	 * @throws IndexOutOfBoundsException se l'indice è minore di 0
	 *         oppure maggiore o uguale alla dimensione della lista
	 */
	public Object get(int index)
	{
		checkIndex(index);
		return adaptee.elementAt(index);
	}
	
	
	/**
	 * Sostituisce l'elemento presente nella posizione specificata
	 * con l'elemento indicato.
	 *
	 * @param index l'indice dell'elemento da sostituire
	 * @param element l'elemento da inserire nella posizione specificata
	 * @return l'elemento precedentemente presente nella posizione specificata
	 * @throws IndexOutOfBoundsException se l'indice è minore di 0
	 *         oppure maggiore o uguale alla dimensione della lista
	 */
	public Object set(int index, Object element)
	{
		checkIndex(index);
		 
		Object oldElement = adaptee.elementAt(index);
		adaptee.setElementAt(element, index);
		return oldElement;
	}
	
	
	/**
	 * Inserisce l'elemento specificato nella posizione indicata.
	 * Gli elementi presenti da quella posizione in poi vengono spostati
	 * verso destra di una posizione.
	 *
	 * @param index l'indice in cui inserire l'elemento
	 * @param element l'elemento da inserire nella lista
	 * @throws IndexOutOfBoundsException se l'indice è minore di 0
	 *         oppure maggiore della dimensione della lista
	 */
	public void add(int index, Object element)
	{
		checkIndexPlus(index);
		adaptee.insertElementAt(element, index);
	}
	
	
	/**
	 * Rimuove l'elemento presente nella posizione specificata.
	 * Gli elementi successivi vengono spostati verso sinistra di una posizione.
	 *
	 * @param index l'indice dell'elemento da rimuovere
	 * @return l'elemento precedentemente presente nella posizione specificata
	 * @throws IndexOutOfBoundsException se l'indice è minore di 0
	 *         oppure maggiore o uguale alla dimensione della lista
	 */
	public Object remove(int index) 
	{	
		checkIndex(index);
		 
		Object oldElement = adaptee.elementAt(index);
		adaptee.removeElementAt(index);
		return oldElement;
	}
	
	
	/**
	 * Restituisce l'indice della prima occorrenza dell'elemento specificato
	 * nella lista.
	 * Se l'elemento non è presente, restituisce -1.
	 *
	 * @param o l'elemento da cercare nella lista
	 * @return l'indice della prima occorrenza dell'elemento specificato,
	 *         oppure -1 se l'elemento non è presente
	 */
	public int indexOf(Object o) 
	{
		return adaptee.indexOf(o);
	}


	/**
	 * Restituisce l'indice dell'ultima occorrenza dell'elemento specificato
	 * nella lista.
	 * Se l'elemento non è presente, restituisce -1.
	 *
	 * @param o l'elemento da cercare nella lista
	 * @return l'indice dell'ultima occorrenza dell'elemento specificato,
	 *         oppure -1 se l'elemento non è presente
	 */
	public int lastIndexOf(Object o) 
	{
		return adaptee.lastIndexOf(o);
	}
	
	/**
	 * Restituisce un list iterator sugli elementi della lista.
	 * L'iteratore viene inizializzato all'inizio della lista.
	 *
	 * @return un list iterator sugli elementi della lista
	 */
	public HListIterator listIterator() 
	{
		return new ListIteratorAdapter(this, 0);
	}
	
	
	/**
	 * Restituisce un list iterator sugli elementi della lista, inizializzato
	 * nella posizione specificata.
	 * L'indice indica la posizione dell'elemento che verrebbe restituito
	 * da una successiva chiamata a next.
	 *
	 * @param index indice iniziale dell'iteratore
	 * @return un list iterator sugli elementi della lista, inizializzato
	 *         alla posizione specificata
	 * @throws IndexOutOfBoundsException se l'indice è minore di 0
	 *         oppure maggiore della dimensione della lista
	 */
	public HListIterator listIterator(int index) 
	{
		checkIndexPlus(index);
		return new ListIteratorAdapter(this, index);
	}
	
	
	
	/**
	 * Restituisce una vista della porzione di lista compresa tra
	 * fromIndex incluso e toIndex escluso.
	 * La sottolista restituita è collegata alla lista originale.
	 *
	 * @param fromIndex indice iniziale della sottolista, incluso
	 * @param toIndex indice finale della sottolista, escluso
	 * @return una vista della porzione specificata della lista
	 * @throws IndexOutOfBoundsException se fromIndex è minore di 0,
	 *         se toIndex è maggiore della dimensione della lista,
	 *         oppure se fromIndex è maggiore di toIndex
	 */
	public HList subList(int fromIndex, int toIndex)
	{
		if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex)
		{
			throw new IndexOutOfBoundsException();
		}

		return new SubListAdapter(this, fromIndex, toIndex - fromIndex);
	}
		





	/**
	 * La sottolista rappresenta una porzione della lista originale e traduce
	 * i propri indici negli indici della lista tramite un offset iniziale.
	 * Le modifiche effettuate tramite la sottolista si riflettono anche
	 * sulla lista.
	 */
	private class SubListAdapter implements HList
	{
		/**
		* Lista padre sulla quale si basa la sottolista.
		*/
		private HList parent;
		
		/**
		* Indice iniziale della sottolista rispetto alla lista padre.
		*/
		private int from;
		
		/**
		 * Numero di elementi attualmente contenuti nella sottolista.
		 */
		private int subSize;


		/**
		 * Costruisce una sottolista legata a una lista padre.
		 *
		 * @param parent lista padre su cui si basa la sottolista
		 * @param from indice iniziale della sottolista nella lista padre
		 * @param subSize dimensione iniziale della sottolista
		 */
		private SubListAdapter(HList parent, int from, int subSize)
		{
			this.parent = parent;
			this.from = from;
			this.subSize = subSize;
		}


		/**
		 * Verifica che l'indice indicato sia valido per accedere
		 * a un elemento della sottolista.
		 *
		 * @param index indice da controllare
		 * @throws IndexOutOfBoundsException se l'indice è minore di zero
		 *         oppure maggiore o uguale alla dimensione della sottolista
		 */
		private void checkIndex(int index)
		{
			if(index < 0 || index >= subSize)
			{
				throw new IndexOutOfBoundsException();
			}
		}


		/**
		 * Verifica che l'indice indicato sia valido per inserire
		 * un elemento nella sottolista.
		 *
		 * @param index indice da controllare
		 * @throws IndexOutOfBoundsException se l'indice è minore di zero
		 *                                   oppure maggiore della dimensione
		 *                                   della sottolista
		 */
		private void checkIndexPlus(int index)
		{
			if(index < 0 || index > subSize)
			{
				throw new IndexOutOfBoundsException();
			}
		}
		
		
		/**
		 * Restituisce il numero di elementi presenti nella sottolista.
		 *
		 * @return numero di elementi della sottolista
		 */
		public int size()
		{
			return subSize;
		}


		/**
		 * Verifica se la sottolista non contiene elementi.
		 *
		 * @return true se la sottolista è vuota, false altrimenti
		 */
		public boolean isEmpty()
		{
			return subSize == 0;
		}


		/**
		 * Verifica se la sottolista contiene l'elemento specificato.
		 *
		 * @param o elemento di cui verificare la presenza nella sottolista
		 * @return true se la sottolista contiene l'elemento specificato,
		 *         false altrimenti
		 */
		public boolean contains(Object o)
		{
			return indexOf(o) != -1;
		}
		
		
		/**
		 * Restituisce un iteratore sugli elementi della sottolista.
		 * L'iteratore viene inizializzato all'inizio della sottolista.
		 *
		 * @return un iteratore sugli elementi della sottolista
		 */
		public HIterator iterator()
		{
			return new IteratorAdapter(this);
		}
		
		
		/**
		 * Restituisce un array contenente tutti gli elementi della sottolista
		 * nell'ordine corretto.
		 *
		 * @return array contenente tutti gli elementi della sottolista
		 */
		public Object[] toArray()
		{
			Object[] array = new Object[subSize];

			for(int i = 0; i < subSize; i++)
			{
				array[i] = get(i);
			}

			return array;
		}



		/**
		 * Restituisce un array contenente tutti gli elementi presenti nella sottolista,
		 * nello stesso ordine in cui compaiono.
		 * Se l'array passato come parametro è sufficientemente grande, viene usato
		 * per contenere gli elementi; altrimenti viene creato un nuovo array di tipo Object[].
		 * Se l'array passato è più grande della sottolista, la posizione immediatamente
		 * successiva all'ultimo elemento copiato viene impostata a null.
		 * In questa implementazione non viene mantenuto il runtime type specifico
		 * dell'array passato come parametro.
		 *
		 * @param a array in cui inserire gli elementi della sottolista, se ha dimensione sufficiente
		 * @return array contenente tutti gli elementi della sottolista
		 * @throws NullPointerException se l'array passato come parametro è null
		 */
		public Object[] toArray(Object[] a)
		{
			if(a == null)
			{
				throw new NullPointerException();
			}

			if(a.length < subSize)
			{
				a = new Object[subSize];
			}

			for(int i = 0; i < subSize; i++)
			{
				a[i] = parent.get(from + i);
			}

			if(a.length > subSize)
			{
				a[subSize] = null;
			}

			return a;
		}
		
		
		/**
		 * Aggiunge l'elemento specificato alla fine della sottolista.
		 * @param o elemento da aggiungere
		 * @return true, poiché la sottolista viene modificata
		 */
		public boolean add(Object o)
		{
			add(subSize, o);
			return true;
		}
		
		
		/**
		 * Rimuove dalla sottolista la prima occorrenza dell'elemento specificato,
		 * se presente.
		 *
		 * @param o elemento da rimuovere dalla sottolista
		 * @return true se la sottolista è stata modificata, false altrimenti
		 */
		public boolean remove(Object o)
		{
			int index = indexOf(o);

			if(index == -1)
			{
				return false;
			}

			remove(index);
			return true;
		}
		
		
		/**
		 * Verifica se la sottolista contiene tutti gli elementi della collezione
		 * specificata.
		 *
		 * @param c collezione di cui verificare la presenza degli elementi
		 * @return true se la sottolista contiene tutti gli elementi della collezione,
		 *         false altrimenti
		 * @throws NullPointerException se la collezione specificata è null
		 */
		public boolean containsAll(HCollection c)
		{
			if(c == null)
			{
				throw new NullPointerException();
			}

			HIterator it = c.iterator();

			while(it.hasNext())
			{
				if(!contains(it.next()))
				{
					return false;
				}
			}

			return true;
		}
		
		
		/**
		 * Aggiunge alla fine della sottolista tutti gli elementi della collezione
		 * specificata, nell'ordine restituito dal suo iteratore.
		 *
		 * @param c collezione contenente gli elementi da aggiungere
		 * @return true se la sottolista è stata modificata, false altrimenti
		 * @throws NullPointerException se la collezione specificata è null
		 */
		public boolean addAll(HCollection c)
		{
			return addAll(subSize, c);
		}
		
		
		/**
		 * Inserisce nella sottolista, a partire dalla posizione specificata,
		 * tutti gli elementi della collezione indicata.
		 *
		 * @param index indice in cui inserire il primo elemento della collezione
		 * @param c collezione contenente gli elementi da aggiungere
		 * @return true se la sottolista è stata modificata, false altrimenti
		 * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti consentiti
		 *         per l'inserimento nella sottolista
		 * @throws NullPointerException se la collezione specificata è null
		 */
		public boolean addAll(int index, HCollection c)
		{
			checkIndexPlus(index);

			if(c == null)
			{
				throw new NullPointerException();
			}

			Object[] elements = c.toArray();

			if(elements.length == 0)
			{
				return false;
			}

			for(int i = 0; i < elements.length; i++)
			{
				add(index + i, elements[i]);
			}

			return true;
		}
		
		
		/**
		 * Rimuove dalla sottolista tutti gli elementi che sono contenuti
		 * nella collezione specificata.
		 *
		 * @param c collezione contenente gli elementi da rimuovere
		 * @return true se la sottolista è stata modificata, false altrimenti
		 * @throws NullPointerException se la collezione specificata è null
		 */
		public boolean removeAll(HCollection c)
		{
			if(c == null)
			{
				throw new NullPointerException();
			}

			boolean modified = false;
			
			//scorro al contrario per evitare di "perdere" elementi
			for(int i = subSize - 1; i >= 0; i--)
			{
				if(c.contains(get(i)))
				{
					remove(i);
					modified = true;
				}
			}

			return modified;
		}
		
		
		/**
		 * Mantiene nella sottolista solo gli elementi che sono contenuti
		 * nella collezione specificata.
		 *
		 * @param c collezione contenente gli elementi da mantenere
		 * @return true se la sottolista è stata modificata, false altrimenti
		 * @throws NullPointerException se la collezione specificata è null
		 */
		public boolean retainAll(HCollection c)
		{
			if(c == null)
			{
				throw new NullPointerException();
			}

			boolean modified = false;

			//scorro al contrario, come prima
			for(int i = subSize - 1; i >= 0; i--)
			{
				if(!c.contains(get(i)))
				{
					remove(i);
					modified = true;
				}
			}

			return modified;
		}
		
		
		/**
		 * Rimuove tutti gli elementi dalla sottolista.
		 * Le modifiche si riflettono anche sulla lista padre.
		 */
		public void clear()
		{
			for(int i = subSize - 1; i >= 0; i--)
			{
				remove(i);
			}
		}
		
		
		/**
		 * Confronta l'oggetto specificato con la sottolista.
		 * Il confronto restituisce true se l'oggetto è una HList con la stessa
		 * dimensione e gli stessi elementi nello stesso ordine.
		 *
		 * @param o oggetto da confrontare con la sottolista
		 * @return true se l'oggetto specificato è uguale alla sottolista,
		 *         false altrimenti
		 */
		public boolean equals(Object o)
		{
			if(o == this)
			{
				return true;
			}

			if(!(o instanceof HList))
			{
				return false;
			}

			HList other = (HList) o;

			if(other.size() != subSize)
			{
				return false;
			}

			for(int i = 0; i < subSize; i++)
			{
				Object e1 = get(i);
				Object e2 = other.get(i);

				if(e1 == null)
				{
					if(e2 != null)
					{
						return false;
					}
				}
				else
				{
					if(!e1.equals(e2))
					{
						return false;
					}
				}
			}

			return true;
		}
		
		
		/**
		 * Restituisce il valore hash della sottolista.
		 * Il calcolo è basato sugli elementi contenuti nella sottolista
		 * e sul loro ordine.
		 *
		 * @return valore hash della sottolista
		 */
		public int hashCode()
		{
			int hashCode = 1;

			for(int i = 0; i < subSize; i++)
			{
				Object obj = get(i);
				hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
			}

			return hashCode;
		}
		
		
		/**
		 * Restituisce l'elemento nella posizione specificata della sottolista.
		 *
		 * @param index indice dell'elemento da restituire
		 * @return elemento presente nella posizione specificata
		 * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
		 *         della sottolista
		 */
		public Object get(int index)
		{
			checkIndex(index);
			return parent.get(from + index);
		}


		/**
		 * Sostituisce l'elemento nella posizione specificata della sottolista
		 * con l'elemento indicato.
		 *
		 * @param index indice dell'elemento da sostituire
		 * @param element nuovo elemento da inserire
		 * @return elemento precedentemente presente nella posizione specificata
		 * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
		 *         della sottolista
		 */
		public Object set(int index, Object element)
		{
			checkIndex(index);
			return parent.set(from + index, element);
		}
		
		
		/**
		 * Inserisce l'elemento indicato nella posizione specificata della sottolista.
		 * Gli elementi successivi vengono spostati di una posizione verso destra.
		 * La modifica si riflette anche sulla lista padre.
		 *
		 * @param index indice in cui inserire l'elemento
		 * @param element elemento da inserire
		 * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti consentiti
		 *         per l'inserimento nella sottolista
		 */
		public void add(int index, Object element)
		{
			checkIndexPlus(index);
			parent.add(from + index, element);
			subSize++;
		}

		
		/**
		 * Rimuove l'elemento nella posizione specificata della sottolista.
		 * Gli elementi successivi vengono spostati di una posizione verso sinistra.
		 * La modifica si riflette anche sulla lista padre.
		 *
		 * @param index indice dell'elemento da rimuovere
		 * @return elemento rimosso dalla sottolista
		 * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
		 *         della sottolista
		 */
		public Object remove(int index)
		{
			checkIndex(index);
			Object old = parent.remove(from + index);
			subSize--;
			return old;
		}

		
		/**
		 * Restituisce l'indice della prima occorrenza dell'elemento specificato
		 * nella sottolista.
		 *
		 * @param o elemento da cercare
		 * @return indice della prima occorrenza dell'elemento nella sottolista,
		 *         oppure -1 se l'elemento non è presente
		 */
		public int indexOf(Object o)
		{
			for(int i = 0; i < subSize; i++)
			{
				Object current = get(i);

				if(current == null)
				{
					if(o == null)
					{
						return i;
					}
				}
				else
				{
					if(current.equals(o))
					{
						return i;
					}
				}
			}

			return -1;
		}


		/**
		 * Restituisce l'indice dell'ultima occorrenza dell'elemento specificato
		 * nella sottolista.
		 *
		 * @param o elemento da cercare
		 * @return indice dell'ultima occorrenza dell'elemento nella sottolista,
		 *         oppure -1 se l'elemento non è presente
		 */
		public int lastIndexOf(Object o)
		{
			//parto dalla fine (ultima occorrenza)
			for(int i = subSize - 1; i >= 0; i--)
			{
				Object current = get(i);

				if(current == null)
				{
					if(o == null)
					{
						return i;
					}
				}
				else
				{
					if(current.equals(o))
					{
						return i;
					}
				}
			}

			return -1;
		}
		
		
		/**
		 * Restituisce un list iterator sugli elementi della sottolista.
		 * L'iteratore viene inizializzato all'inizio della sottolista.
		 *
		 * @return un list iterator sugli elementi della sottolista
		 */
		public HListIterator listIterator()
		{
			return new ListIteratorAdapter(this, 0);
		}


		/**
		 * Restituisce un list iterator sugli elementi della sottolista,
		 * inizializzato nella posizione specificata.
		 * L'indice indica la posizione, all'interno della sottolista,
		 * dell'elemento che verrebbe restituito da una successiva chiamata a next.
		 *
		 * @param index indice iniziale dell'iteratore nella sottolista
		 * @return un list iterator sugli elementi della sottolista,
		 *         inizializzato alla posizione specificata
		 * @throws IndexOutOfBoundsException se l'indice è minore di 0
		 *         oppure maggiore della dimensione della sottolista
		 */		
		public HListIterator listIterator(int index)
		{
			checkIndexPlus(index);
			return new ListIteratorAdapter(this, index);
		}
		
		
		
		/**
		 * Restituisce una vista della porzione della sottolista compresa tra
		 * fromIndex incluso e toIndex escluso.
		 * La nuova sottolista rimane collegata alla sottolista corrente e,
		 * di conseguenza, anche alla lista principale.
		 *
		 * @param fromIndex indice iniziale della nuova sottolista, incluso
		 * @param toIndex indice finale della nuova sottolista, escluso
		 * @return una vista della porzione specificata della sottolista
		 * @throws IndexOutOfBoundsException se fromIndex è minore di 0,
		 *         se toIndex è maggiore della dimensione della sottolista,
		 *         oppure se fromIndex è maggiore di toIndex
		 */
		public HList subList(int fromIndex, int toIndex)
		{
			if(fromIndex < 0 || toIndex > subSize || fromIndex > toIndex)
			{
				throw new IndexOutOfBoundsException();
			}

			return new SubListAdapter(this, fromIndex, toIndex - fromIndex);
		}
	}
}

