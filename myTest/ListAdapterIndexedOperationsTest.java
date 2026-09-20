//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;

public class ListAdapterIndexedOperationsTest
{
	private ListAdapter list;


	//tutti i metodi sono preceduti dalla creazione di una lista vuota
    @Before
    public void setUp()
    {
        list = new ListAdapter();
    }
	
	
	
	
	//GET
	// Verifico che get restituisca l'elemento nella posizione indicata
    @Test
    public void testGetValidIndex()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("B", list.get(1));
    }


    // Verifico get sul primo elemento
    @Test
    public void testGetFirstElement()
    {
        list.add("A");
        list.add("B");

        assertEquals("A", list.get(0));
    }
	
	
	// Verifico get sull'ultimo elemento
    @Test
    public void testGetLastElement()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("C", list.get(2));
    }


    // Verifico che get funzioni anche con elemento null
    @Test
    public void testGetNullElement()
    {
        list.add("A");
        list.add(null);

        assertNull(list.get(1));
    }
	
	
	// Verifico che get lanci eccezione con indice negativo
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNegativeIndex()
    {
        list.add("A");

        list.get(-1);
    }


    // Verifico che get lanci eccezione con indice uguale a size
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexEqualToSize()
    {
        list.add("A");

        list.get(1);
    }
	
	
	// Verifico che get lanci eccezione con indice maggiore di size
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexGreaterThenSize()
    {
        list.add("A");

        list.get(2);
    }
	
	
	// Verifico che get lanci eccezione su lista vuota
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOnEmptyList()
    {
        list.get(0);
    }
	
	
	
	
	
	
	//SET
	// Verifico che set sostituisca l'elemento nella posizione indicata
    @Test
    public void testSetValidIndex()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        list.set(1, "X");

        assertEquals("X", list.get(1));
    }
	
	
	// Verifico che set ritorni il vecchio elemento
    @Test
    public void testSetReturnsOldElement()
    {
        list.add("A");
        list.add("B");

        Object old = list.set(1, "X");

        assertEquals("B", old);
    }
	
	
	// Verifico che set non modifichi la dimensione della lista
    @Test
    public void testSetDoesNotChangeSize()
    {
        list.add("A");
        list.add("B");

        list.set(0, "X");

        assertEquals(2, list.size());
    }
	
	
	// Verifico set sul primo elemento
    @Test
    public void testSetFirstElement()
    {
        list.add("A");
        list.add("B");

        list.set(0, "X");

        assertEquals("X", list.get(0));
        assertEquals("B", list.get(1));
    }
	
	
	// Verifico set sull'ultimo elemento
    @Test
    public void testSetLastElement()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        list.set(2, "X");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("X", list.get(2));
    }
	
	
	// Verifico che set lanci eccezione con indice maggiore di size
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetIndexGreaterThanSize()
	{
		list.add("A");

		list.set(2, "X");
	}


	// Verifico set con valore null
    @Test
    public void testSetNullElement()
    {
        list.add("A");
        list.add("B");

        list.set(1, null);

        assertNull(list.get(1));
        assertEquals(2, list.size());
    }
	
	
	// Verifico che set possa sostituire un elemento null
	@Test
	public void testSetReplacesNullElement()
	{
		list.add("A");
		list.add(null);
		list.add("C");

		Object old = list.set(1, "B");

		assertNull(old);
		assertEquals("B", list.get(1));
		assertEquals(3, list.size());
	}


	// Verifico che set lanci eccezione con indice negativo
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetNegative()
    {
        list.add("A");

        list.set(-1, "X");
    }


    // Verifico che set lanci eccezione con indice uguale a size
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexEqualToSize()
    {
        list.add("A");

        list.set(1, "X");
    }


    // Verifico che set lanci eccezione su lista vuota
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOnEmptyList()
    {
        list.set(0, "X");
    }
	
	
	
	
	
	
	//ADD(INDEX, OBJECT)
	// Verifico inserimento in testa
    @Test
    public void testAddAtBeginning()
    {
        list.add("B");
        list.add("C");

        list.add(0, "A");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }
	
	// Verifico inserimento in mezzo
    @Test
    public void testAddAtMiddle()
    {
        list.add("A");
        list.add("C");

        list.add(1, "B");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }
	
	
	// Verifico inserimento in coda tramite indice uguale a size
    @Test
    public void testAddAtEnd()
    {
        list.add("A");
        list.add("B");

        list.add(2, "C");

        assertEquals(3, list.size());
        assertEquals("C", list.get(2));
    }


    // Verifico inserimento su lista vuota con indice 0
    @Test
    public void testAddAtIndexZeroOnEmptyList()
    {
        list.add(0, "A");

        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }
	
	
	// Verifico che add consenta null
    @Test
    public void testAddNullAtIndex()
    {
        list.add("A");
        list.add("B");

        list.add(1, null);

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertNull(list.get(1));
        assertEquals("B", list.get(2));
    }
	
	
	// Verifico che add sposti a destra gli elementi successivi
    @Test
    public void testAddAtIndexShiftsElementsRight()
    {
        list.add("A");
        list.add("B");
        list.add("D");

        list.add(2, "C");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals("D", list.get(3));
    }


	// Verifico che add lanci eccezione su lista vuota con indice maggiore di size
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIndexGreaterThanSizeOnEmptyList()
	{
		list.add(1, "A");
	}
	
	
    // Verifico che add lanci eccezione con indice negativo
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex()
    {
        list.add("A");

        list.add(-1, "X");
    }
	
	
	// Verifico che add lanci eccezione con indice maggiore di size
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexGreaterThanSize()
    {
        list.add("A");

        list.add(2, "X");
    }
	
	
	
	
	
	
	
	//REMOVE(INDEX)
	// Verifico rimozione tramite indice valido
    @Test
    public void testRemoveValidIndex()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        Object removed = list.remove(1);

        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }
	
	// Verifico rimozione del primo elemento
    @Test
    public void testRemoveFirstElement()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        Object removed = list.remove(0);

        assertEquals("A", removed);
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
        assertEquals("C", list.get(1));
    }
	
	// Verifico rimozione dell'ultimo elemento
    @Test
    public void testRemoveLastElement()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        Object removed = list.remove(2);

        assertEquals("C", removed);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }
	
	
	// Verifico remove con elemento null
    @Test
    public void testRemoveNullElementByIndex()
    {
        list.add("A");
        list.add(null);
        list.add("C");

        Object removed = list.remove(1);

        assertNull(removed);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }
	
	
	// Verifico che remove sposti a sinistra gli elementi successivi
    @Test
    public void testRemoveShiftsElementsLeft()
    {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        list.remove(1);

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("D", list.get(2));
    }
	
	
	// Verifico che remove lanci eccezione con indice maggiore di size
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveIndexGreaterThanSize()
	{
		list.add("A");

		list.remove(2);
	}
	
		
	// Verifico che remove lanci eccezione con indice negativo
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNegativeIndex()
    {
        list.add("A");

        list.remove(-1);
    }


    // Verifico che remove lanci eccezione con indice uguale a size
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexEqualToSize()
    {
        list.add("A");

        list.remove(1);
    }


    // Verifico che remove lanci eccezione su lista vuota
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOnEmptyList()
    {
        list.remove(0);
    }

}