//ZORZI ALESSANDRO N° MATRICOLA: 2137471

package myTest;

import java.util.*;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class TestRunner
{
	public static void main(String[] argv)
	{
		org.junit.runner.Result res = org.junit.runner.JUnitCore.runClasses(ListAdapterConstructorTest.class, 
																			ListAdapterBasicOperationsTest.class, 
																			ListAdapterToArrayTest.class,
																			ListAdapterIndexedOperationsTest.class,
																			ListAdapterCollectionTest.class,
																			ListAdapterSearchTest.class,
																			ListAdapterIteratorTest.class,
																			ListAdapterListIteratorTest.class,
																			ListAdapterSubListTest.class);
		
		System.out.println("Ho eseguito " + res.getRunCount() + " test");
		
		System.out.println("Ho eseguito i test in " + res.getRunTime() + " millisecondi");
		
		System.out.println("Sono falliti " + res.getFailureCount() + " test");
		
		List l = res.getFailures();
		
		ListIterator li = l.listIterator();
		
		while(li.hasNext())
		{
			Failure f = (Failure) li.next();
			System.out.println(f);
		}
	}
}

