//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

import myAdapter.ListAdapter;

public class ListAdapterConstructorTest 
{

    private ListAdapter list;

	//tutti i metodi sono preceduti dalla creazione di una lista vuota
    @Before
    public void setUp() 
	{
        list = new ListAdapter();
    }


	//verifico che la lista sia vuota con Size
    @Test
    public void testConstructorSizeZero() 
	{
        assertEquals(0, list.size());
    }


	//Verifico che la lista sia vuota con isEmpty
    @Test
    public void testConstructorIsEmpty() 
	{
        assertTrue(list.isEmpty());
    }


	//Verifico che la lista sia vuota con toArray
    @Test
    public void testConstructorToArray() 
	{
        Object[] array = list.toArray();

        assertEquals(0, array.length);
    }


	//Verifico che la lista sia vuota con il risultato dell'hash
    @Test
    public void testConstructorHashCode() 
	{
        assertEquals(1, list.hashCode());
    }
	
	
	// Verifico che l'iteratore di una lista vuota non abbia elementi successivi
	@Test
	public void testConstructorIteratorHasNextFalse()
	{
		assertFalse(list.iterator().hasNext());
	}
	
	
	//Verifico che la lista sia vuota con il lancio dell'eccezione di get
    @Test(expected = IndexOutOfBoundsException.class)
    public void testConstructorGet() 
	{
        list.get(0);
    }


	//Verifico che la lista sia vuota con il lancio dell'eccezione di set (indice troppo grande)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testConstructorSet() 
	{
        list.set(0, "A");
    }


	//Verifico che la lista sia vuota con il lancio dell'eccezione di remove (indice troppo grande)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testConstructorRemove() 
	{
        list.remove(0);
    }
	

	//Verifico che la lista sia vuota con il lancio dell'eccezione di next
	@Test(expected = NoSuchElementException.class)
	public void testConstructorNext()
	{
		list.iterator().next();
	}
}