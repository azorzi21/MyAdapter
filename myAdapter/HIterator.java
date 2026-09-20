//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myAdapter;

/**
 * Interfaccia che riproduce localmente il comportamento dell'interfaccia
 * Iterator della versione J2SE 1.4.2.
 */
public interface HIterator
{
	public boolean hasNext();
	public Object next();
	public void remove();
}