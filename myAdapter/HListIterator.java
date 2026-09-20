//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myAdapter;

/**
 * Interfaccia che riproduce localmente il comportamento dell'interfaccia
 * ListIterator della versione J2SE 1.4.2.
 */
public interface HListIterator extends HIterator
{

	public boolean hasPrevious();
	public Object previous();
	public int nextIndex();
	public int previousIndex();
	public void set(Object o);
	public void add(Object o);
}