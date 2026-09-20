//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import myAdapter.HIterator;
import myAdapter.HList;
import myAdapter.HListIterator;
import myAdapter.ListAdapter;

public class ListAdapterSubListTest
{
    private ListAdapter list;

    @Before
    public void setUp()
    {
        list = new ListAdapter();

        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
    }


    //verifico che subList ritorni una vista con gli elementi nel range 
    @Test
    public void testSubListCorrectRange()
    {
        HList sub = list.subList(1, 4);

        assertEquals(3, sub.size());
        assertFalse(sub.isEmpty());

        assertEquals("B", sub.get(0));
        assertEquals("C", sub.get(1));
        assertEquals("D", sub.get(2));
    }


    //verifico che sia possibile creare una subList vuota (fromIndex == toIndex)
    @Test
    public void testSubListEmpty()
    {
        HList sub = list.subList(2, 2);

        assertEquals(0, sub.size());
        assertTrue(sub.isEmpty());
    }


    //verifico che set sulla subList modifichi anche la lista madre
    @Test
    public void testSubListSetReflect()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        Object old = sub.set(1, "X");

        assertEquals("C", old);

        assertEquals("X", sub.get(1));
        assertEquals("X", list.get(2));
    }


    //verifico che una modifica non strutturale sia visibile sulla sublist
    @Test
    public void testParentListSetReflect1()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        list.set(2, "X");

        assertEquals("X", sub.get(1));
    }


    //verifico che add(index, Object) sulla subList aggiunga nel punto corretto (anche nella madre)
    @Test
    public void testSubListAddByIndexReflect()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        sub.add(1, "X");

        assertEquals(4, sub.size());
        assertEquals("B", sub.get(0));
        assertEquals("X", sub.get(1));
        assertEquals("C", sub.get(2));
        assertEquals("D", sub.get(3));

        assertEquals(6, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("X", list.get(2));
        assertEquals("C", list.get(3));
        assertEquals("D", list.get(4));
        assertEquals("E", list.get(5));
    }


    //verifico che add(Object) sulla subList aggiunga alla fine della subList
    @Test
    public void testSubListAddObjectReflect()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        assertTrue(sub.add("X"));

        assertEquals(4, sub.size());
        assertEquals("X", sub.get(3));

        assertEquals(6, list.size());
        assertEquals("X", list.get(4));
        assertEquals("E", list.get(5));
    }


    //verifico che remove(index) rimuova l'elemento corretto dalla subList (e anche dalla madre)
    @Test
    public void testSubListRemoveByIndexReflect()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        Object removed = sub.remove(1);

        assertEquals("C", removed);

        assertEquals(2, sub.size());
        assertEquals("B", sub.get(0));
        assertEquals("D", sub.get(1));

        assertEquals(4, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("D", list.get(2));
        assertEquals("E", list.get(3));
    }


    //verifico che clear sulla subList rimuova tutti e solo gli elementi del suo range
    @Test
    public void testSubListClearOnlySubRange()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        sub.clear();

        assertEquals(0, sub.size());

        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("E", list.get(1));
    }


    //TEST RIASSUNTIVO PER I METODI DI RICERCA
    // contains, indexOf e lastIndexOf sono già testati in modo completo su ListAdapter.
    // I controlli vanno fatti sul fatto che la ricerca avvenga nel range specifico
	// e che gli indici restituiti siano riferiti alla sublist
    @Test
    public void testSubListSearchOnlySubRange()
    {
        list.clear();

        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");
        list.add("A");
        list.add("D");

        HList sub = list.subList(1, 5); // [B, A, C, A]

        assertTrue(sub.contains("B"));
        assertTrue(sub.contains("A"));
        assertTrue(sub.contains("C"));

        assertFalse(sub.contains("D"));

        assertEquals(1, sub.indexOf("A"));
        assertEquals(3, sub.lastIndexOf("A"));

        assertEquals(0, sub.indexOf("B"));
        assertEquals(2, sub.indexOf("C"));

        assertEquals(-1, sub.indexOf("D"));
        assertEquals(-1, sub.lastIndexOf("D"));
    }


    //TEST RIASSUNTIVO PER I METODI BULK
    //containsAll, addAll, removeAll e retainAll sono metodi già testati su ListAdapter.
    //I controlli fanno fatti sul range e sulla modifica della madre
    @Test
    public void testSubListCollectionOperations()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        ListAdapter contained = new ListAdapter();
        contained.add("B");
        contained.add("D");

        ListAdapter notContained = new ListAdapter();
        notContained.add("B");
        notContained.add("E");

        assertTrue(sub.containsAll(contained));
        assertFalse(sub.containsAll(notContained));

        ListAdapter toAdd = new ListAdapter();
        toAdd.add("X");
        toAdd.add("Y");

        assertTrue(sub.addAll(toAdd));

        assertEquals(5, sub.size());
        assertEquals("B", sub.get(0));
        assertEquals("C", sub.get(1));
        assertEquals("D", sub.get(2));
        assertEquals("X", sub.get(3));
        assertEquals("Y", sub.get(4));

        assertEquals("X", list.get(4));
        assertEquals("Y", list.get(5));
        assertEquals("E", list.get(6));

        ListAdapter toRemove = new ListAdapter();
        toRemove.add("B");
        toRemove.add("Y");
        toRemove.add("E");

        assertTrue(sub.removeAll(toRemove));

        assertEquals(3, sub.size());
        assertEquals("C", sub.get(0));
        assertEquals("D", sub.get(1));
        assertEquals("X", sub.get(2));

        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("D", list.get(2));
        assertEquals("X", list.get(3));
        assertEquals("E", list.get(4));

        ListAdapter toRetain = new ListAdapter();
        toRetain.add("D");

        assertTrue(sub.retainAll(toRetain));

        assertEquals(1, sub.size());
        assertEquals("D", sub.get(0));

        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("D", list.get(1));
        assertEquals("E", list.get(2));
    }


    //verifico che una subList di una subList gestisca correttamente gli offset.
    //Questo test è molto importante perché evidenzia eventuali errori nella somma degli indici.
    @Test
    public void testSubListOfSubList()
    {
        HList sub = list.subList(1, 5);      // [B, C, D, E]
        HList subSub = sub.subList(1, 3);    // [C, D]

        assertEquals(2, subSub.size());
        assertEquals("C", subSub.get(0));
        assertEquals("D", subSub.get(1));

        subSub.set(0, "X");

        assertEquals("X", subSub.get(0));
        assertEquals("X", sub.get(1));
        assertEquals("X", list.get(2));
    }


	//verifico che toArray() restituisca solo gli elementi della SubList
    @Test
    public void testSubListToArray()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        Object[] array = sub.toArray();

        assertEquals(3, array.length);
        assertEquals("B", array[0]);
        assertEquals("C", array[1]);
        assertEquals("D", array[2]);
    }


    //verifico toArray(Object[]) con vettore più grande
    @Test
    public void testSubListToArrayObject()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        Object[] input = new Object[5];
        input[0] = "X";
        input[1] = "Y";
        input[2] = "Z";
        input[3] = "W";
        input[4] = "K";

        Object[] result = sub.toArray(input);

        assertSame(input, result);

        assertEquals("B", result[0]);
        assertEquals("C", result[1]);
        assertEquals("D", result[2]);
        assertNull(result[3]);
        assertEquals("K", result[4]);
    }
	
	
	
    //TEST RIASSUNTIVO PER iterator E listIterator:
    //Gli iteratori sono già testati in modo completo su ListAdapter.
    //Controllo che l'iteratore scorra solo la visita con indici relativi alla sublist
    @Test
    public void testSubListIterators()
    {
        HList sub = list.subList(1, 4); // [B, C, D]

        HIterator it = sub.iterator();

        assertTrue(it.hasNext());
        assertEquals("B", it.next());
        assertEquals("C", it.next());
        assertEquals("D", it.next());
        assertFalse(it.hasNext());

        HListIterator lit = sub.listIterator(1);

        assertTrue(lit.hasNext());
        assertTrue(lit.hasPrevious());

        assertEquals(1, lit.nextIndex());
        assertEquals(0, lit.previousIndex());

        assertEquals("C", lit.next());
        assertEquals("C", lit.previous());
        assertEquals("B", lit.previous());
        assertFalse(lit.hasPrevious());
    }

	


    //TEST RIASSUNTIVO PER equals E hashCode:
    //Il test completi di equals/hashCode sono già stati fatti su ListAdapter.
    //Qui controllo solo che la SubList abbia lo stesso comportamento di una lista.
    @Test
    public void testSubListEqualsAndHashCode()
    {
        HList sub = list.subList(1, 4);

        ListAdapter other = new ListAdapter();
        other.add("B");
        other.add("C");
        other.add("D");

        assertTrue(sub.equals(other));
        assertTrue(other.equals(sub));
        assertEquals(other.hashCode(), sub.hashCode());
    }


    //verifico le eccezioni nella creazione della subList.
    @Test
    public void testSubListException()
    {
        try {
            list.subList(-1, 3);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //ok
        }

        try {
            list.subList(1, 6);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //ok
        }

        try {
            list.subList(4, 2);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //ok
        }
    }


    //verifico le eccezioni sui metodi indicizzati della subList
    @Test
    public void testSubListIndexedException()
    {
        HList sub = list.subList(1, 4); // size = 3

        try {
            sub.get(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //ok
        }

        try {
            sub.get(3);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //ok
        }

        try {
            sub.set(3, "X");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //ok
        }

        try {
            sub.add(4, "X");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //ok
        }

        try {
            sub.remove(3);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            //ok
        }
    }
}