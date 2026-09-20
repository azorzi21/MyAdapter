//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

import myAdapter.ListAdapter;
import myAdapter.HIterator;
import myAdapter.IteratorAdapter;

public class ListAdapterIteratorTest
{
    private ListAdapter list;

    // Prima di ogni test viene creata una nuova lista vuota
    @Before
    public void setUp()
    {
        list = new ListAdapter();
    }



    // CONSTRUTTORE

    // Verifico che il costruttore lanci NullPointerException con lista null
    @Test(expected = NullPointerException.class)
    public void testIteratorConstructorNullList()
    {
        new IteratorAdapter(null);
    }




    // ITERATOR
    // Verifico che iterator restituisca un iteratore non null
    @Test
    public void testIteratorReturnsNotNull()
    {
        HIterator it = list.iterator();

        assertNotNull(it);
    }


    // Verifico che iterator su lista vuota non abbia elementi successivi
    @Test
    public void testIteratorOnEmptyListHasNoNext()
    {
        HIterator it = list.iterator();

        assertFalse(it.hasNext());
    }


    // Verifico che iterator su lista un elemento il successivo
    @Test
    public void testIteratorOnNonEmptyListHasNext()
    {
        list.add("A");

        HIterator it = list.iterator();

        assertTrue(it.hasNext());
    }




    // HASNEXT
    // Verifico che hasNext ritorni true prima di leggere tutti gli elementi
    @Test
    public void testHasNextBeforeEnd()
    {
        list.add("A");
        list.add("B");

        HIterator it = list.iterator();

        assertTrue(it.hasNext());

        it.next();

        assertTrue(it.hasNext());
    }


    // Verifico che hasNext ritorni false dopo aver letto tutti gli elementi
    @Test
    public void testHasNextAfterEnd()
    {
        list.add("A");
        list.add("B");

        HIterator it = list.iterator();

        it.next();
        it.next();

        assertFalse(it.hasNext());
    }


    // Verifico che hasNext non modifichi la posizione dell'iteratore
    @Test
    public void testHasNextDoesNotMoveIterator()
    {
        list.add("A");
        list.add("B");

        HIterator it = list.iterator();

        assertTrue(it.hasNext());

        assertEquals("A", it.next());
    }


	// Verifico che hasNext ritorni true anche se il prossimo elemento è null
	@Test
	public void testHasNextWithNullNextElement()
	{
		list.add(null);

		HIterator it = list.iterator();

		assertTrue(it.hasNext());
		assertNull(it.next());
		assertFalse(it.hasNext());
	}
	
	

    // NEXT

    // Verifico che next restituisca il primo elemento
    @Test
    public void testNextReturnsFirstElement()
    {
        list.add("A");
        list.add("B");

        HIterator it = list.iterator();

        assertEquals("A", it.next());
    }


    // Verifico che next restituisca gli elementi nell'ordine corretto
    @Test
    public void testNextReturnsElementsInOrder()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HIterator it = list.iterator();

        assertEquals("A", it.next());
        assertEquals("B", it.next());
        assertEquals("C", it.next());
    }


    // Verifico che next funzioni con elemento null
    @Test
    public void testNextReturnsNullElement()
    {
        list.add("A");
        list.add(null);
        list.add("C");

        HIterator it = list.iterator();

        assertEquals("A", it.next());
        assertNull(it.next());
        assertEquals("C", it.next());
    }


	// Verifico che next() avanzi l'iteratore senza modificare la lista 
	@Test 
	public void testNextDoesNotModifyList() 
	{ 
		list.add("A"); 
		list.add("B"); 
		list.add("C"); 
		
		HIterator it = list.iterator(); 
		
		assertEquals("A", it.next()); 
		assertEquals(3, list.size()); 
		assertEquals("A", list.get(0)); 
		assertEquals("B", list.get(1)); 
		assertEquals("C", list.get(2)); 
	}
	
	
    // Verifico che next lanci eccezione su lista vuota
    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyList()
    {
        HIterator it = list.iterator();

        it.next();
    }


    // Verifico che next lanci eccezione dopo la fine della lista
    @Test(expected = NoSuchElementException.class)
    public void testNextAfterEnd()
    {
        list.add("A");

        HIterator it = list.iterator();

        it.next();
        it.next();
    }




    // REMOVE


    // Verifico che remove rimuova l'ultimo elemento restituito da next
    @Test
    public void testRemoveAfterNextRemovesElement()
    {
        list.add("A");
        list.add("B");

        HIterator it = list.iterator();

        Object element = it.next();
        it.remove();

        assertEquals("A", element);
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }



    // Verifico che remove possa rimuovere il primo elemento
    @Test
    public void testRemoveFirstElement()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HIterator it = list.iterator();

        it.next();
        it.remove();

        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
        assertEquals("C", list.get(1));
    }


    // Verifico che remove possa rimuovere un elemento centrale
    @Test
    public void testRemoveMiddleElement()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HIterator it = list.iterator();

        it.next(); // A
        it.next(); // B
        it.remove();

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }


    // Verifico che remove possa rimuovere l'ultimo elemento
    @Test
    public void testRemoveLastElement()
    {
        list.add("A");
        list.add("B");

        HIterator it = list.iterator();

        it.next(); // A
        it.next(); // B
        it.remove();

        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }


    // Verifico che dopo remove l'iteratore continui correttamente
    @Test
    public void testIteratorContinues()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HIterator it = list.iterator();

        assertEquals("A", it.next());
        it.remove();

        assertEquals("B", it.next());
        assertEquals("C", it.next());
        assertFalse(it.hasNext());
    }


    // Verifico che remove possa essere chiamato una volta dopo ogni next valido
    @Test
    public void testRemoveAfterEachNext()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HIterator it = list.iterator();

        assertEquals("A", it.next());
        it.remove();

        assertEquals("B", it.next());
        it.remove();

        assertEquals("C", it.next());
        it.remove();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }


    // Verifico che remove funzioni anche con elemento null
    @Test
    public void testRemoveNullElement()
    {
        list.add("A");
        list.add(null);
        list.add("C");

        HIterator it = list.iterator();

        it.next(); // A
        it.next(); // null
        it.remove();

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }
	
	
	// Verifico che remove lanci eccezione se next non è ancora stato chiamato
    @Test(expected = RuntimeException.class)
    public void testRemoveBeforeNext()
    {
        list.add("A");

        HIterator it = list.iterator();

        it.remove();
    }
	
	
	// Verifico che remove lanci eccezione se chiamato due volte dopo lo stesso next
    @Test(expected = RuntimeException.class)
    public void testRemoveTwiceAfterSameNext()
    {
        list.add("A");
        list.add("B");

        HIterator it = list.iterator();

        it.next();
        it.remove();
        it.remove();
    }
}