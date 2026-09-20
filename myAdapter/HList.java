//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myAdapter;

/**
 * Interfaccia che riproduce localmente il comportamento dell'interfaccia
 * List della versione J2SE 1.4.2.
 */
public interface HList extends HCollection
{
	public Object[] toArray(Object[] a);
	public boolean containsAll(HCollection c);
	public boolean addAll(HCollection c);
	public boolean addAll(int index, HCollection c);
	public boolean removeAll(HCollection c);
	public boolean retainAll(HCollection c);
	public boolean equals(Object o);
	public int hashCode();
	public Object get(int index);
	public Object set(int index, Object element);
	public void add(int index, Object element);
	public Object remove(int index);
	public int indexOf(Object o);
	public int lastIndexOf(Object o);
	public HListIterator listIterator();
	public HListIterator listIterator(int index);
	public HList subList(int fromIndex, int toIndex);
}