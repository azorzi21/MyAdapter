//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


import myAdapter.ListAdapter;

public class ListAdapterCollectionTest
{
	private ListAdapter list;
	private ListAdapter other;	//utilizzo un ListAdapter perchè implementa HCollection
								//ed è inerente con il contesto

	// Prima di ogni test vengono create 2 liste vuote
	@Before
	public void setUp()
	{
		list = new ListAdapter();
		other = new ListAdapter();
	}
	
	
	//CONTAINSALL
	// Verifico che containsAll ritorni true con collezione vuota
	@Test
	public void testContainsAllEmptyCollection()
	{
		list.add("A");
		list.add("B");
		assertTrue(list.containsAll(other));	
	}
		
	
	// Verifico che containsAll ritorni true se lista e collection contengono gli stessi elementi
	@Test
	public void testContainsAllEqualElements()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		other.add("A");
		other.add("B");
		other.add("C");

		assertTrue(list.containsAll(other));
	}
	
	
	// Verifico che containsAll ritorni true se tutti gli elementi della Collection sono presenti
	@Test
	public void testContainsAllAllElementsPresent()
	{
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");

		other.add("A");
		other.add("B");
		other.add("C");

		assertTrue(list.containsAll(other));
	}
	
	
	// Verifico che containsAll ritorni false se almeno un elemento manca
	@Test
	public void testContainsAllWithMissingElement()
	{
		list.add("A");
		list.add("B");

		other.add("A");
		other.add("C");

		assertFalse(list.containsAll(other));
	}
	
	
	// Verifico che containsAll funzioni con null presente
	@Test
	public void testContainsAllWithNullElement()
	{
		list.add("A");
		list.add(null);
		list.add("C");

		other.add(null);

		assertTrue(list.containsAll(other));
	}
	
	
	// Verifico che containsAll ritorni false con null non presente
	@Test
	public void testContainsAllWithNullNotPresent()
	{
		list.add("A");
		list.add("B");

		other.add(null);

		assertFalse(list.containsAll(other));
	}
	
	
	//verifico che funzioni con se stessa
	@Test
	public void testContainsAllWithItself()
	{
		list.add("A");
		list.add("B");

		assertTrue(list.containsAll(list));
	}


	// Verifico che containsAll lanci eccezione con collezione null
	@Test(expected = NullPointerException.class)
	public void testContainsAllWithNullCollection()
	{
		list.containsAll(null);
	}
	
	
	
	
	//ADDALL(COLLECTION)
	// Verifico che addAll ritorni false con collezione vuota
	@Test
	public void testAddAllWithEmptyCollection()
	{
		list.add("A");

		assertFalse(list.addAll(other));
		assertEquals(1, list.size());
		assertEquals("A", list.get(0));
	}
	
	
	// Verifico che addAll aggiunga tutti gli elementi in coda
	@Test
	public void testAddAllAddsElementsAtEnd()
	{
		list.add("A");

		other.add("B");
		other.add("C");

		assertTrue(list.addAll(other));

		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
	}
	
	
	// Verifico che addAll mantenga l'ordine degli elementi aggiunti
	@Test
	public void testAddAllKeepsOrder()
	{
		other.add("A");
		other.add("B");
		other.add("C");

		list.addAll(other);

		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
	}
	
	
	// Verifico che addAll funzioni con elementi null
	@Test
	public void testAddAllWithNullElement()
	{
		other.add("A");
		other.add(null);
		other.add("C");

		list.addAll(other);

		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertNull(list.get(1));
		assertEquals("C", list.get(2));
	}
	
	
	//verifico che addAll funzioni con se stessa
	@Test
	public void testAddAllWithItself()
	{
		list.add("A");
		list.add("B");

		assertTrue(list.addAll(list));

		assertEquals(4, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("A", list.get(2));
		assertEquals("B", list.get(3));
	}
	
	
	// Verifico che addAll lanci eccezione con collezione null
	@Test(expected = NullPointerException.class)
	public void testAddAllWithNullCollection()
	{
		list.addAll(null);
	}
	
	
	
	
	
	//ADDALL(INDEX, COLLECTION)
	// Verifico addAll in testa
	@Test
	public void testAddAllAtBeginning()
	{
		list.add("C");

		other.add("A");
		other.add("B");

		assertTrue(list.addAll(0, other));

		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
	}
	
	
	// Verifico addAll in mezzo alla lista
	@Test
	public void testAddAllAtMiddle()
	{
		list.add("A");
		list.add("D");

		other.add("B");
		other.add("C");

		assertTrue(list.addAll(1, other));

		assertEquals(4, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
		assertEquals("D", list.get(3));
	}
	
	
	// Verifico addAll in coda con indice uguale a size
	@Test
	public void testAddAllAtEnd()
	{
		list.add("A");

		other.add("B");
		other.add("C");

		assertTrue(list.addAll(1, other));

		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
	}
	
	
	// Verifico addAll con collezione vuota
	@Test
	public void testAddAllAtIndexWithEmptyCollection()
	{
		list.add("A");
		list.add("B");

		assertFalse(list.addAll(1, other));

		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
	}
	
	
	// Verifico addAll con null
	@Test
	public void testAddAllAtIndexWithNullElement()
	{
		list.add("A");
		list.add("D");

		other.add("B");
		other.add(null);
		other.add("C");

		list.addAll(1, other);

		assertEquals(5, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertNull(list.get(2));
		assertEquals("C", list.get(3));
		assertEquals("D", list.get(4));
	}
	
	
	//verifico che addAll funzioni con se stessa
	@Test
	public void testAddAllAtIndexWithItself()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		assertTrue(list.addAll(1, list));

		assertEquals(6, list.size());
		assertEquals("A", list.get(0));
		assertEquals("A", list.get(1));
		assertEquals("B", list.get(2));
		assertEquals("C", list.get(3));
		assertEquals("B", list.get(4));
		assertEquals("C", list.get(5));
	}


	// Verifico che addAll lanci eccezione con indice negativo
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddAllAtNegativeIndex()
	{
		other.add("A");

		list.addAll(-1, other);
	}


	// Verifico che addAll lanci eccezione con indice maggiore di size
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddAllAtIndexGreaterThanSize()
	{
		list.add("A");
		other.add("B");

		list.addAll(2, other);
	}
	
	
	// Verifico che addAll lanci eccezione con collezione null
	@Test(expected = NullPointerException.class)
	public void testAddAllAtIndexWithNullCollection()
	{
		list.addAll(0, null);
	}
	
	
	
	
	//REMOVEALL
	// Verifico che removeAll rimuova gli elementi presenti nella collezione
	@Test
	public void testRemoveAllRemovesElements()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		other.add("A");
		other.add("C");

		assertTrue(list.removeAll(other));

		assertEquals(1, list.size());
		assertEquals("B", list.get(0));
	}
	
	
	// Verifico che removeAll ritorni false se non rimuove nulla
	@Test
	public void testRemoveAllWithNoCommonElements()
	{
		list.add("A");
		list.add("B");

		other.add("C");
		other.add("D");

		assertFalse(list.removeAll(other));

		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
	}
	
	
	// Verifico che removeAll rimuova tutte le occorrenze presenti
	@Test
	public void testRemoveAllRemovesAllOccurrences()
	{
		list.add("A");
		list.add("B");
		list.add("A");
		list.add("C");

		other.add("A");

		assertTrue(list.removeAll(other));

		assertEquals(2, list.size());
		assertEquals("B", list.get(0));
		assertEquals("C", list.get(1));
	}
	
	
	// Verifico removeAll con null
	@Test
	public void testRemoveAllWithNullElement()
	{
		list.add("A");
		list.add(null);
		list.add("B");

		other.add(null);

		assertTrue(list.removeAll(other));

		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
	}
	
	
	// Verifico removeAll con collezione vuota
	@Test
	public void testRemoveAllWithEmptyCollection()
	{
		list.add("A");
		list.add("B");

		assertFalse(list.removeAll(other));

		assertEquals(2, list.size());
	}
	
	
	//verifico removeAll con se stessa
	@Test
	public void testRemoveAllWithItself()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		assertTrue(list.removeAll(list));

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	
	// Verifico che removeAll lanci eccezione con collezione null
	@Test(expected = NullPointerException.class)
	public void testRemoveAllWithNullCollection()
	{
		list.removeAll(null);
	}






	//RETAINALL
	// Verifico che retainAll mantenga solo gli elementi presenti nella collezione
	@Test
	public void testRetainAllKeepsOnlyCommonElements()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		other.add("A");
		other.add("C");

		assertTrue(list.retainAll(other));

		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
	}
	
	
	// Verifico che retainAll ritorni false se la lista non cambia
	@Test
	public void testRetainAllReturnsFalseWhenListDoesNotChange()
	{
		list.add("A");
		list.add("B");

		other.add("A");
		other.add("B");
		other.add("C");

		assertFalse(list.retainAll(other));

		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
	}
	
	
	// Verifico che retainAll svuoti la lista se non ci sono elementi comuni
	@Test
	public void testRetainAllWithNoCommonElements()
	{
		list.add("A");
		list.add("B");

		other.add("C");
		other.add("D");

		assertTrue(list.retainAll(other));

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	
	// Verifico retainAll con null
	@Test
	public void testRetainAllWithNullElement()
	{
		list.add("A");
		list.add(null);
		list.add("B");

		other.add(null);

		assertTrue(list.retainAll(other));

		assertEquals(1, list.size());
		assertNull(list.get(0));
	}
	
	
	// Verifico retainAll con collezione vuota
	@Test
	public void testRetainAllWithEmptyCollection()
	{
		list.add("A");
		list.add("B");

		assertTrue(list.retainAll(other));

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	
	// Verifico retainAll su lista vuota
	@Test
	public void testRetainAllOnEmptyList()
	{
		other.add("A");

		assertFalse(list.retainAll(other));

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}


	//verifico retainAll con se stessa
	@Test
	public void testRetainAllWithItself()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		assertFalse(list.retainAll(list));

		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
		assertEquals("C", list.get(2));
	}


	// Verifico che retainAll lanci eccezione con collezione null
	@Test(expected = NullPointerException.class)
	public void testRetainAllWithNullCollection()
	{
		list.retainAll(null);
	}
}