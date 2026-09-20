//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;

public class ListAdapterSearchTest
{
	private ListAdapter list;
	
	//tutti i metodi sono preceduti dalla creazione di una lista vuota
    @Before
    public void setUp() 
	{
        list = new ListAdapter();
    }
	
	
	//INDEXOF
	// Verifico che indexOf ritorni -1 su lista vuota
	@Test
	public void testIndexOfOnEmptyList()
	{
		assertEquals(-1, list.indexOf("A"));
	}
	
	
	// Verifico che indexOf ritorni l'indice corretto dell'elemento presente
	@Test
	public void testIndexOfExistingElement()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		assertEquals(1, list.indexOf("B"));
	}


	// Verifico che indexOf ritorni -1 se l'elemento non è presente
	@Test
	public void testIndexOfNotExistingElement()
	{
		list.add("A");
		list.add("B");

		assertEquals(-1, list.indexOf("C"));
	}
	
	
	// Verifico che indexOf ritorni la prima occorrenza dell'elemento
	@Test
	public void testIndexOfFirstOccurrence()
	{
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");

		assertEquals(0, list.indexOf("A"));
	}
	
	
	// Verifico che indexOf funzioni con elemento null presente
	@Test
	public void testIndexOfNullElement()
	{
		list.add("A");
		list.add(null);
		list.add("C");

		assertEquals(1, list.indexOf(null));
	}
	
	
	// Verifico che indexOf ritorni -1 se null non è presente
	@Test
	public void testIndexOfNullNotPresent()
	{
		list.add("A");
		list.add("B");

		assertEquals(-1, list.indexOf(null));
	}
	
	
	// Verifico che indexOf ritorni la prima occorrenza di null
	@Test
	public void testIndexOfFirstNullOccurrence()
	{
		list.add("A");
		list.add(null);
		list.add("B");
		list.add(null);

		assertEquals(1, list.indexOf(null));
	}
	
	
	// Verifico che indexOf usi equals per confrontare gli oggetti
	@Test
	public void testIndexOfUsesEquals()
	{
		String a1 = new String("A");
		String a2 = new String("A");

		list.add(a1);

		assertEquals(0, list.indexOf(a2));
	}
	
	
	
	//LASTINDEXOF
	// Verifico che lastIndexOf ritorni -1 su lista vuota
	@Test
	public void testLastIndexOfOnEmptyList()
	{
		assertEquals(-1, list.lastIndexOf("A"));
	}
	
	
	// Verifico che lastIndexOf ritorni l'indice dell'elemento presente
	@Test
	public void testLastIndexOfExistingElement()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		assertEquals(1, list.lastIndexOf("B"));
	}
	
	
	// Verifico che lastIndexOf ritorni -1 se l'elemento non è presente
	@Test
	public void testLastIndexOfNotExistingElement()
	{
		list.add("A");
		list.add("B");

		assertEquals(-1, list.lastIndexOf("C"));
	}

	
	// Verifico che lastIndexOf ritorni l'ultima occorrenza dell'elemento
	@Test
	public void testLastIndexOfLastOccurrence()
	{
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");

		assertEquals(2, list.lastIndexOf("A"));
	}
	
	
	// Verifico che lastIndexOf funzioni con elemento null presente
	@Test
	public void testLastIndexOfNullElement()
	{
		list.add("A");
		list.add(null);
		list.add("C");

		assertEquals(1, list.lastIndexOf(null));
	}
	
	
	// Verifico che lastIndexOf ritorni l'ultima occorrenza di null
	@Test
	public void testLastIndexOfLastNullOccurrence()
	{
		list.add("A");
		list.add(null);
		list.add("B");
		list.add(null);

		assertEquals(3, list.lastIndexOf(null));
	}
	
	
	// Verifico che lastIndexOf ritorni -1 se null non è presente
	@Test
	public void testLastIndexOfNullNotPresent()
	{
		list.add("A");
		list.add("B");

		assertEquals(-1, list.lastIndexOf(null));
	}
	
	
	// Verifico che lastIndexOf usi equals per confrontare gli oggetti
	@Test
	public void testLastIndexOfUsesEquals()
	{
		String a1 = new String("A");
		String a2 = new String("A");

		list.add(a1);

		assertEquals(0, list.lastIndexOf(a2));
	}
}