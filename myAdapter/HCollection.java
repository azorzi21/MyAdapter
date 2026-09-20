//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myAdapter;

/**
 * Interfaccia che riproduce localmente il comportamento dell'interfaccia
 * Collection della versione J2SE 1.4.2.
 */
public interface HCollection
{
	public int size();
	public boolean isEmpty();
	public boolean contains(Object o);
	public HIterator iterator();
	public Object[] toArray();
	public Object[] toArray(Object[] a);
	public boolean add(Object o);
	public boolean remove(Object o);
	public boolean containsAll(HCollection c);
	public boolean addAll(HCollection c);
	public boolean removeAll(HCollection c);
	public boolean retainAll(HCollection c);
	public void clear();
	public boolean equals(Object o);
	public int hashCode();
}