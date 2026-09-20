//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;

public class ListAdapterToArrayTest
{
	private ListAdapter list;

    @Before
    public void setUp()
    {
        list = new ListAdapter();
    }
	
	
	//TOARRAY()
	//verifico che una lista vuota ritorni una lista vuota
	@Test
    public void testToArrayOnEmptyList()
    {
        Object[] array = list.toArray();

        assertEquals(0, array.length);
    }
	
	
	//verifico che gli elementi nelll'arrray siano in ordine
	@Test
    public void testToArrayReturnsAllElementsInOrder()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        Object[] array = list.toArray();

        assertEquals(3, array.length);
        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
        assertEquals("C", array[2]);
    }
	
	
	//verifico con elementi null
	@Test
    public void testToArrayWithNullElement()
    {
        list.add("A");
        list.add(null);
        list.add("C");

        Object[] array = list.toArray();

        assertEquals(3, array.length);
        assertEquals("A", array[0]);
        assertNull(array[1]);
        assertEquals("C", array[2]);
    }
	
	//verifico che l'array è indipendende dalla lista dopo la sua creazione
	@Test
    public void testToArrayIsIndependent()
    {
        list.add("A");
        list.add("B");

        Object[] array = list.toArray();

        array[0] = "X";

        assertEquals("A", list.get(0));
    }
	
	
	//verifico la correttenza anche con i duplicati
	@Test
	public void testToArrayWithDuplicateElements()
	{
		list.add("A");
		list.add("B");
		list.add("A");

		Object[] array = list.toArray();

		assertEquals(3, array.length);
		assertEquals("A", array[0]);
		assertEquals("B", array[1]);
		assertEquals("A", array[2]);
	}
	
	
	
	//TOARRAY(OBJECT[] A)
	//verifico che con array della dimensione esatta venga riutilizzato lo stesso array
    @Test
    public void testToArrayObjSizeArray()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        Object[] input = new Object[3];
        Object[] result = list.toArray(input);

        assertSame(input, result);
        assertEquals(3, result.length);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);
        assertEquals("C", result[2]);
    }


    //verifico che con array più grande venga riutilizzato lo stesso array
    //e venga inserito null subito dopo l'ultimo elemento della lista
    @Test
    public void testToArrayObjLargerArray()
    {
        list.add("A");
        list.add("B");

        Object[] input = new Object[4];
        input[0] = "X";
        input[1] = "Y";
        input[2] = "Z";
        input[3] = "W";

        Object[] result = list.toArray(input);

        assertSame(input, result);
        assertEquals(4, result.length);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);
        assertNull(result[2]);
        assertEquals("W", result[3]);
    }


    //verifico che con array troppo piccolo venga creato un nuovo array
    @Test
    public void testToArrayObjSmallerArray()
    {
        list.add("A");
        list.add("B");
        list.add("C");

        Object[] input = new Object[1];
        Object[] result = list.toArray(input);

        assertNotSame(input, result);
        assertEquals(3, result.length);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);
        assertEquals("C", result[2]);
    }


    //verifico il comportamento con lista vuota e array vuoto
    @Test
    public void testToArrayObjEmptyListEmptyArray()
    {
        Object[] input = new Object[0];
        Object[] result = list.toArray(input);

        assertSame(input, result);
        assertEquals(0, result.length);
    }


    //verifico il comportamento con lista vuota e array più grande
    @Test
    public void testToArrayObjEmptyListLargerArray()
    {
        Object[] input = new Object[3];
        input[0] = "A";
        input[1] = "B";
        input[2] = "C";

        Object[] result = list.toArray(input);

        assertSame(input, result);
        assertEquals(3, result.length);
        assertNull(result[0]);
        assertEquals("B", result[1]);
        assertEquals("C", result[2]);
    }


    //verifico che vengano copiati correttamente anche elementi null
    @Test
    public void testToArrayObjNullElement()
    {
        list.add("A");
        list.add(null);
        list.add("C");

        Object[] input = new Object[3];
        Object[] result = list.toArray(input);

        assertSame(input, result);
        assertEquals("A", result[0]);
        assertNull(result[1]);
        assertEquals("C", result[2]);
    }


    //verifico che vengano copiati correttamente anche elementi duplicati
    @Test
    public void testToArrayObjDuplicateElements()
    {
        list.add("A");
        list.add("B");
        list.add("A");

        Object[] input = new Object[3];
        Object[] result = list.toArray(input);

        assertSame(input, result);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);
        assertEquals("A", result[2]);
    }


    //verifico che l'array ottenuto sia indipendente dalla lista
    @Test
    public void testToArrayObjectIsIndependent()
    {
        list.add("A");
        list.add("B");

        Object[] input = new Object[2];
        Object[] result = list.toArray(input);

        result[0] = "X";

        assertEquals("A", list.get(0));
    }


    //verifico che venga lanciata NullPointerException se l'array passato è null
    @Test(expected = NullPointerException.class)
    public void testToArrayObjNullObj()
    {
        list.add("A");

        list.toArray(null);
    }
}