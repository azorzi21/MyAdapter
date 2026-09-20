//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;

public class ListAdapterBasicOperationsTest
{
	private ListAdapter list;
	
	//tutti i metodi sono preceduti dalla creazione di una lista vuota
    @Before
    public void setUp() 
	{
        list = new ListAdapter();
    }
	
	//SIZE
	//size 0 su lista vuota
	@Test
	public void testSizeOnEmptyList() 
    {
        assertEquals(0, list.size());
    }
	
	
	@Test
	//size=2 dopo 2 inserimenti
	public void testSizeAfterAdd() 
    {
        list.add("A");
        list.add("B");

        assertEquals(2, list.size());
    }
	
	
	//size=1 dopo 2 inserimenti e un remove
	@Test
	public void testSizeAfterInsertRemove() 
    {
        list.add("A");
        list.add("B");

        list.remove("A");

        assertEquals(1, list.size());
    }
	
	
	//size=0 dopo 2 inserimenti e 2 remove
	@Test
	public void testSizeAfterInsertRemove1() 
    {
        list.add("A");
        list.add("B");

        list.remove("A");
		list.remove("B");

        assertEquals(0, list.size());
    }
	
	
	//size=0 dopo clear
	@Test
	public void testSizeAfterClear() 
    {
        list.add("A");
        list.add("B");

        list.clear();

        assertEquals(0, list.size());
    }
	
	
	
	//ISEMPTY
	//isEmpty=true con lista vuota
	@Test
	public void testisEmptyOnEmptyList() 
    {
        assertTrue(list.isEmpty());
    }
	
	
	//isempyt=false dopo inserimento
	@Test
    public void testisEmptyAfterAdd() 
    {
        list.add("A");

        assertFalse(list.isEmpty());
    }
	
	
	//isEmpty=true dopo un inserimento e un remove
	@Test
	public void testisEmptyAfterRemove()
	{
		list.add("A");
		list.remove("A");
		
        assertTrue(list.isEmpty());
	}
	
	
	//isEmpty=false dopo 2 inserimenti e una remove
	@Test
	public void testisEmptyAfterAddAddRemove()
	{
		list.add("A");
		list.add("B");
		list.remove("A");
		
		assertFalse(list.isEmpty());
	}
	
	
	//isEmpty=true dopo inserimento e clearla clear
	@Test
    public void testisEmptyAfterClear() 
    {
        list.add("A");

        list.clear();

        assertTrue(list.isEmpty());
    }
	
	
	//ADD
	//verifico che ritorni true dopo un inserimento
	@Test
    public void testAddReturnsTrue() 
    {
        assertTrue(list.add("A"));
    }
	
	
	//verifico che aggiunge effettivamente l'elemento indicato in coda
	@Test
    public void testAddActuallyAddsElement() 
    {
        list.add("A");

        assertEquals("A", list.get(0));
    }
	
	//stessa verifica del test precedente con più oggetti
	@Test
    public void testAddKeepsInsertionOrder() 
    {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }
	
	
	
	// Verifico che add consenta elementi duplicati
	@Test
	public void testAddAllowsDuplicates()
	{
		list.add("A");
		list.add("A");

		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("A", list.get(1));
	}


	//verifico che funzioni l'inserimento di null
	@Test
    public void testAddNullElement() 
    {
        list.add(null);

        assertEquals(1, list.size());
        assertTrue(list.contains(null));
    }
	
	
	
	
	//REMOVE
	//verifico che avvenga la rimozione di un elemento presente
	@Test
    public void testRemoveExistingElement() 
    {
        list.add("A");

        assertTrue(list.remove("A"));
    }
	
	
	//verifico che se ho più elementi, la rimozione di uno vada a buon fine
	@Test
    public void testRemoveExistingElement1() 
    {
        list.add("A");
        list.add("B");

        list.remove("A");

        assertFalse(list.contains("A"));
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }
	

	//verifico che ritorni false la rimozione di un elemento non presente
	@Test
    public void testRemoveNonExistingElement() 
    {
        list.add("A");

        assertFalse(list.remove("B"));
    }
	
	
	//verifico che venga eliminata solo la prima occorrenza
	@Test
    public void testRemoveOnlyFirstOccurrence() 
    {
        list.add("A");
        list.add("B");
        list.add("A");

        list.remove("A");

        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
    }
	
	
	// Verifico che remove su lista vuota ritorni false
	@Test
	public void testRemoveFromEmptyList()
	{
		assertFalse(list.remove("A"));
	}
	
	
	// Verifico che remove non modifichi la lista se l'elemento non è presente
	@Test
	public void testRemoveNonExistingDoesNotModifyList()
	{
		list.add("A");
		list.add("B");

		assertFalse(list.remove("C"));

		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));
	}


	//verifico la remove di null
	@Test
    public void testRemoveNullElement() 
    {
        list.add("A");
        list.add(null);

        assertTrue(list.remove(null));
        assertFalse(list.contains(null));
        assertEquals(1, list.size());
    }
	
	
	// Verifico che remove(null) ritorni false se null non è presente
	@Test
	public void testRemoveNullNotPresent()
	{
		list.add("A");

		assertFalse(list.remove(null));
		assertEquals(1, list.size());
	}


	//CLEAR
	//verifico che funzioni su una lista già vuota
	@Test
    public void testClearEmptyList() 
    {
        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }


	//verifico che funzioni su una lista con elementi
    @Test
    public void testClearRemovesAllElements() 
    {
        list.add("A");
        list.add("B");
        list.add("C");

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertFalse(list.contains("A"));
        assertFalse(list.contains("B"));
        assertFalse(list.contains("C"));
    }
	
	
	//verifico che funzioni su una lista che aveva elementi
	@Test
	public void testClearNowEmptyList()
	{
		list.add("A");
        list.add("B");
        list.add("C");

		list.remove("A");
		list.remove("B");
		list.remove("C");

        list.clear();
	}
	
	// Verifico che clear funzioni anche dopo add e remove
	@Test
	public void testClearAfterMixedOperations()
	{
		list.add("A");
		list.add("B");
		list.add("C");

		list.remove("B");
		list.clear();

		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains("A"));
		assertFalse(list.contains("C"));
	}


	
	//CONTAINS
	//Verifico che ritorni false se vuota
    @Test
    public void testContainsOnEmptyList() 
    {
        assertFalse(list.contains("A"));
    }
	
	
	//verifico che ritorni true con un elemento presente
	@Test
    public void testContainsExistingElement() 
    {
        list.add("A");

        assertTrue(list.contains("A"));
    }


	//verifico che ritorni false con un elemento non presente
    @Test
    public void testContainsNotExistingElement() 
    {
        list.add("A");

        assertFalse(list.contains("B"));
    }
	
	
	//verifico che funzioni anche su lista con più elementi
	@Test 
	public void testContainsMoreElements()
	{
		list.add("A");
		list.add("B");
		
		assertTrue(list.contains("A"));
		assertTrue(list.contains("B"));
	}


	//verifico che ritorni true con null presente
    @Test
    public void testContainsNullElement() 
    {
        list.add(null);

        assertTrue(list.contains(null));
    }
	
	// Verifico ritorni false se null non è presente
	@Test
	public void testContainsNullNotPresent()
	{
		list.add("A");

		assertFalse(list.contains(null));
	}
	
	
	
	//EQUALS
	// Verifica che due liste con gli stessi elementi nello stesso ordine siano uguali
	@Test
	public void testEqualsSameElements()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add("B");
		list.add("C");

		other.add("A");
		other.add("B");
		other.add("C");

		assertTrue(list.equals(other));
	}


	// Verifica che il metodo equals sia simmetrico
	@Test
	public void testEqualsReverse()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add("B");

		other.add("A");
		other.add("B");

		assertTrue(list.equals(other));
		assertTrue(other.equals(list));
	}


	// Verifica che due liste vuote siano considerate uguali
	@Test
	public void testEqualsEmptyLists()
	{
		ListAdapter other = new ListAdapter();

		assertTrue(list.equals(other));
	}


	// Verifica che una lista sia uguale a se stessa
	@Test
	public void testEqualsWithItself()
	{
		list.add("A");
		list.add("B");

		assertTrue(list.equals(list));
	}


	// Verifica che una lista non sia uguale a null
	@Test
	public void testEqualsNull()
	{
		list.add("A");

		assertFalse(list.equals(null));
	}


	// Verifica che una lista non sia uguale a un oggetto di tipo diverso
	@Test
	public void testEqualsDifferentType()
	{
		list.add("A");

		assertFalse(list.equals("A"));
	}


	// Verifica che due liste con dimensione diversa non siano uguali.
	@Test
	public void testEqualsDifferentSize()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add("B");

		other.add("A");
		other.add("B");
		other.add("C");

		assertFalse(list.equals(other));
	}


	// Verifica che due liste con stessa dimensione ma elementi diversi non siano uguali.
	@Test
	public void testEqualsDifferentElements()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add("B");
		list.add("C");

		other.add("A");
		other.add("X");
		other.add("C");

		assertFalse(list.equals(other));
	}


	// Verifica che l'ordine degli elementi sia rilevante nel confronto tra liste.
	@Test
	public void testEqualsDifferentOrder()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add("B");

		other.add("B");
		other.add("A");

		assertFalse(list.equals(other));
	}


	// Verifica che equals gestisca correttamente elementi null nella stessa posizione.
	@Test
	public void testEqualsNullElement()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add(null);
		list.add("C");

		other.add("A");
		other.add(null);
		other.add("C");

		assertTrue(list.equals(other));
	}


	// Verifica che due liste non siano uguali se null è presente solo in una delle due.
	@Test
	public void testEqualsNullElement1()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add(null);
		list.add("C");

		other.add("A");
		other.add("B");
		other.add("C");

		assertFalse(list.equals(other));
	}




	// HASHCODE

	// Verifica se due liste sono uguali, devono avere lo stesso hashCode.
	@Test
	public void testHashCodeEqualLists()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add("B");
		list.add("C");

		other.add("A");
		other.add("B");
		other.add("C");

		assertTrue(list.equals(other));
		assertEquals(list.hashCode(), other.hashCode());
	}


	// Verifica che due liste vuote abbiano lo stesso hashCode.
	@Test
	public void testHashCodeEmptyLists()
	{
		ListAdapter other = new ListAdapter();

		assertEquals(list.hashCode(), other.hashCode());
	}


	// Verifica che hashCode gestisca correttamente elementi null.
	@Test
	public void testHashCodeNullElement()
	{
		ListAdapter other = new ListAdapter();

		list.add("A");
		list.add(null);
		list.add("C");

		other.add("A");
		other.add(null);
		other.add("C");

		assertEquals(list.hashCode(), other.hashCode());
	}
	

	// Verifica che l'hashCode cambi dopo una modifica strutturale semplice.
	@Test
	public void testHashCodeAfterModify()
	{
		int oldHashCode = list.hashCode();

		list.add("A");

		assertFalse(oldHashCode == list.hashCode());
		assertEquals(96, list.hashCode());
	}
	
}