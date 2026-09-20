//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

import myAdapter.ListAdapter;
import myAdapter.HListIterator;

public class ListAdapterListIteratorTest
{
    private ListAdapter list;

    // Prima di ogni test viene creata una nuova lista vuota
    @Before
    public void setUp()
    {
        list = new ListAdapter();
    }


    // LISTITERATOR

    // Verifico che listIterator restituisca un iteratore non null
    @Test
    public void testListIteratorReturnsNotNull()
    {
        HListIterator it = list.listIterator();

        assertNotNull(it);
    }


    // Verifico che listIterator parta dall'indice 0
    @Test
    public void testListIteratorStartsAtZero()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();

        assertEquals(0, it.nextIndex());
        assertEquals(-1, it.previousIndex());
    }


	//LIST ITERATOR(INDEX)
    // Verifico che listIterator(index) parta dall'indice indicato
    @Test
    public void testListIteratorStartsAtGivenIndex()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HListIterator it = list.listIterator(1);

        assertEquals(1, it.nextIndex());
        assertEquals(0, it.previousIndex());
    }


    // Verifico che listIterator(size) sia valido e sia posizionato alla fine
    @Test
    public void testListIteratorAtEnd()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator(2);

        assertEquals(2, it.nextIndex());
        assertEquals(1, it.previousIndex());
        assertFalse(it.hasNext());
        assertTrue(it.hasPrevious());
    }


    // Verifico che listIterator lanci eccezione con indice negativo
    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorNegativeIndex()
    {
        list.add("A");

        list.listIterator(-1);
    }


    // Verifico che listIterator lanci eccezione con indice maggiore di size
    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorIndexGreaterThanSize()
    {
        list.add("A");

        list.listIterator(2);
    }






    // HASPREVIOUS

	// Verifico che hasPrevious ritorni false su lista vuota
	@Test
	public void testHasPreviousOnEmptyList()
	{
		HListIterator it = list.listIterator();

		assertFalse(it.hasPrevious());
	}


    // Verifico che hasPrevious sia false all'inizio della lista
    @Test
    public void testHasPreviousAtBeginning()
    {
        list.add("A");

        HListIterator it = list.listIterator();

        assertFalse(it.hasPrevious());
    }


    // Verifico che hasPrevious sia true se l'iteratore non è all'inizio
    @Test
    public void testHasPreviousAfterNext()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();

        it.next();

        assertTrue(it.hasPrevious());
    }


    // Verifico che hasPrevious sia true se l'iteratore parte dalla fine
    @Test
    public void testHasPreviousAtEnd()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator(list.size());

        assertTrue(it.hasPrevious());
    }


    // Verifico che hasPrevious non sposti l'iteratore
    @Test
    public void testHasPreviousDoesNotMoveIterator()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator(1);

        assertTrue(it.hasPrevious());
        assertTrue(it.hasPrevious());

        assertEquals("A", it.previous());
    }





    // PREVIOUS

    // Verifico che previous restituisca l'elemento precedente
    @Test
    public void testPreviousReturnsPreviousElement()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator(1);

        assertEquals("A", it.previous());
    }


    // Verifico che previous restituisca gli elementi in ordine inverso
    @Test
    public void testPreviousReturnsElementsInReverseOrder()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HListIterator it = list.listIterator(3);

        assertEquals("C", it.previous());
        assertEquals("B", it.previous());
        assertEquals("A", it.previous());
    }


    // Verifico che previous funzioni con elemento null
    @Test
    public void testPreviousReturnsNullElement()
    {
        list.add("A");
        list.add(null);
        list.add("C");

        HListIterator it = list.listIterator(2);

        assertNull(it.previous());
    }


    // Verifico che previous lanci eccezione all'inizio della lista
    @Test(expected = NoSuchElementException.class)
    public void testPreviousAtBeginning()
    {
        list.add("A");

        HListIterator it = list.listIterator();

        it.previous();
    }


    // Verifico che previous lanci eccezione su lista vuota
    @Test(expected = NoSuchElementException.class)
    public void testPreviousOnEmptyList()
    {
        HListIterator it = list.listIterator();

        it.previous();
    }




    // NEXTINDEX E PREVIOUSINDEX

    // Verifico gli indici iniziali
    @Test
    public void testInitialIndexes()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();

        assertEquals(0, it.nextIndex());
        assertEquals(-1, it.previousIndex());
    }


    // Verifico gli indici dopo next
    @Test
    public void testIndexesAfterNext()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();

        it.next();

        assertEquals(1, it.nextIndex());
        assertEquals(0, it.previousIndex());
    }


    // Verifico gli indici dopo previous
    @Test
    public void testIndexesAfterPrevious()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HListIterator it = list.listIterator(2);

        it.previous();

        assertEquals(1, it.nextIndex());
        assertEquals(0, it.previousIndex());
    }


    // Verifico gli indici alla fine della lista
    @Test
    public void testIndexesAtEnd()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator(2);

        assertEquals(2, it.nextIndex());
        assertEquals(1, it.previousIndex());
    }



    // SET

    // Verifico che set sostituisca l'ultimo elemento restituito da next
    @Test
    public void testSetAfterNext()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();

        assertEquals("A", it.next());

        it.set("X");

        assertEquals("X", list.get(0));
        assertEquals("B", list.get(1));
    }


    // Verifico che set sostituisca l'ultimo elemento restituito da previous
    @Test
    public void testSetAfterPrevious()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator(2);

        assertEquals("B", it.previous());

        it.set("X");

        assertEquals("A", list.get(0));
        assertEquals("X", list.get(1));
    }


    // Verifico che set possa impostare null
    @Test
    public void testSetNullElement()
    {
        list.add("A");

        HListIterator it = list.listIterator();

        it.next();
        it.set(null);

        assertNull(list.get(0));
    }


    // Verifico che set possa sostituire un elemento null
    @Test
    public void testSetReplacesNullElement()
    {
        list.add(null);

        HListIterator it = list.listIterator();

        assertNull(it.next());

        it.set("A");

        assertEquals("A", list.get(0));
    }


    // Verifico che set lanci eccezione se next o previous non sono stati chiamati
    @Test(expected = RuntimeException.class)
    public void testSetBeforeNextOrPrevious()
    {
        list.add("A");

        HListIterator it = list.listIterator();

        it.set("X");
    }


    // Verifico che set lanci eccezione subito dopo add
    @Test(expected = RuntimeException.class)
    public void testSetAfterAdd()
    {
        list.add("A");

        HListIterator it = list.listIterator();

        it.add("X");
        it.set("Y");
    }

	
	//Verifico il lancio di set dopo remove
	@Test(expected = RuntimeException.class)
	public void testListIteratorSetAfterRemove()
	{
		list.add("A");
		list.add("B");

		HListIterator it = list.listIterator();

		it.next();
		it.remove();
		it.set("X");
	}

	// ADD

    // Verifico che add inserisca l'elemento nella posizione corrente
    @Test
    public void testAddAtCurrentPosition()
    {
        list.add("A");
        list.add("C");

        HListIterator it = list.listIterator(1);

        it.add("B");

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }


    // Verifico che add all'inizio inserisca in testa
    @Test
    public void testAddAtBeginning()
    {
        list.add("B");

        HListIterator it = list.listIterator();

        it.add("A");

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }


    // Verifico che add alla fine inserisca in coda
    @Test
    public void testAddAtEnd()
    {
        list.add("A");

        HListIterator it = list.listIterator(list.size());

        it.add("B");

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }


    // Verifico che add consenta null
    @Test
    public void testAddNullElement()
    {
        list.add("A");
        list.add("C");

        HListIterator it = list.listIterator(1);

        it.add(null);

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertNull(list.get(1));
        assertEquals("C", list.get(2));
    }


    // Verifico che dopo add, previous restituisca l'elemento appena aggiunto
    @Test
    public void testPreviousAfterAddReturnsAddedElement()
    {
        list.add("A");
        list.add("C");

        HListIterator it = list.listIterator(1);

        it.add("B");

        assertEquals("B", it.previous());
    }


    // Verifico che dopo add, next non restituisca l'elemento appena aggiunto
    @Test
    public void testNextAfterAddReturnsFollowingElement()
    {
        list.add("A");
        list.add("C");

        HListIterator it = list.listIterator(1);

        it.add("B");

        assertEquals("C", it.next());
    }
	
	
    // REMOVE

    // Verifico che remove rimuova l'ultimo elemento restituito da previous
    @Test
    public void testRemoveAfterPrevious()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        HListIterator it = list.listIterator(2);

        assertEquals("B", it.previous());
        it.remove();

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }


    // Verifico che remove rimuova l'ultimo elemento restituito da next
    @Test
    public void testRemoveAfterNext()
    {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();

        assertEquals("A", it.next());
        it.remove();

        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }


    // Verifico che remove lanci eccezione se next o previous non sono stati chiamati
    @Test(expected = RuntimeException.class)
    public void testRemoveBeforeNextOrPrevious()
    {
        list.add("A");

        HListIterator it = list.listIterator();

        it.remove();
    }


    // Verifico che remove lanci eccezione subito dopo add
    @Test(expected = RuntimeException.class)
    public void testRemoveAfterAdd()
    {
        list.add("A");

        HListIterator it = list.listIterator();

        it.add("X");
        it.remove();
    }


    // Verifico che remove lanci eccezione se chiamato due volte dopo lo stesso next
    @Test(expected = RuntimeException.class)
    public void testRemoveTwiceAfterSameNext()
    {
        list.add("A");
		list.add("B");
        HListIterator it = list.listIterator();

        it.next();
        it.remove();
        it.remove();
    }
}