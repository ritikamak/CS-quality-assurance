/*  Ritika Maknoor, David Bickford
    CS 1632
    Deliverable 4 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Pinning tests; systems-level automated white-box unit tests checking that behavior 
 * of modified method was not changed by refactoring; using JUnit.
 * @author RitikaMaknoor/DavidBickford
 *
 */

public class PinningTest {

    // Pinning test of modified method getFirstMonkey() in MonkeySim.java.
    // Tests that when method gets passed an empty list of Monkey objects, 
    // and then method call checks for reference to the first monkey object is in
    // that empty list, will return null. It should be empty (tested by ensuring
    // that the first element is null). 
    @Test
    public void testGetFirstMonkeyNull() throws Exception {
        // create new LinkedList for Monkey objects; empty list 
        List<Monkey> ml = new LinkedList<>();
        // call getFirstMonkey()
        assertNull(MonkeySim.getFirstMonkey(ml));
    }


    // Pinning test of modified method getFirstMonkey() in MonkeySim.java.
    // Tests that when a new list of Monkey objects is created and 2 Monkey
    // objects are added to the list (firstMonkey with id=0, then secondMonkey with id=1), 
    // checks to make sure that both are not referring to same Monkey object.
    // Should not reference the same object. 
    @Test
    public void testGetFirstMonkeyAssertNotSame() throws Exception {
        // create 2 Monkey objects
        Monkey firstMonkey = new Monkey();
        Monkey secondMonkey = new Monkey();
        // create new LinkedList for Monkey objects; add 2 Monkeys to list
        List<Monkey> ml = new LinkedList<>();
        ml.add(firstMonkey);
        ml.add(secondMonkey);
        // call getFirstMonkey() on list to return Monkey with id 1
        Monkey currMonkey = MonkeySim.getFirstMonkey(ml);
        // check that a Monkey with id0 (firstMonkey) is not the same object referenced as a
        // Monkey with id1 (currMonkey)
        assertNotSame(currMonkey, firstMonkey);
    }


    // Pinning test of modified method getFirstMonkey() in MonkeySim.java.
    // Tests that when a new list of Monkey objects is created and 2 Monkey
    // objects are added to the list (firstMonkey with id=0, then secondMonkey with id=1), 
    // checks to make sure that a Monkey with id int value of 0 is not equal to a Monkey
    // with id int value of 1. Should not have the same id values. 
    @Test
    public void testGetFirstMonkeyAssertNotEquals() {
        // create 2 Monkey objects
        Monkey firstMonkey = new Monkey();
        Monkey secondMonkey = new Monkey();
        // create new LinkedList for Monkey objects; add 2 Monkeys to list
        List<Monkey> ml = new LinkedList<>();
        ml.add(firstMonkey);
        ml.add(secondMonkey);
        // call getFirstMonkey() on list to return Monkey with id 1
        Monkey currMonkey = MonkeySim.getFirstMonkey(ml);
        // check that a Monkey with id0 (firstMonkey) is not equal to a
        // Monkey with id1 (currMonkey)
        assertNotEquals(currMonkey, firstMonkey);
    }


    // Pinning test of modified method stringifyResults() in MonkeySim.java.
    // Tests that when stringifyResults() is passed in set of parameters, method returns
    // correct String version of the round and information about it.  
    // Tested by checking that output String returned from method call equals String created 
    // in unit test itself. Checked by initializing 2 Monkey objects with int values 0 & 1 
    // passed into generateId(). Various methods then called on the 2 Monkey objects when 
    // creating output String.
    // It should return a String of exact same format and values.  
    @Test
    public void testStringifyResultsMonkey1To2() throws NoIdException {
        // create 2 Monkey objects
        Monkey firstMonkey = new Monkey();
        // generateId(0) -> Monkey #0
        firstMonkey.generateId(0);
        Monkey secondMonkey = new Monkey();
        // generateId(1) -> Monkey #1
        secondMonkey.generateId(1);
        // get ID values of each monkey
        int firstId = firstMonkey.getId();
        int secondId = secondMonkey.getId();
        // create correct String to compare against
        String output = "//Round 1: Threw banana from Monkey (#" + firstMonkey.getMonkeyNum() + " / ID "
                + firstId + ") to Monkey (#" + secondMonkey.getMonkeyNum() + " / ID " + secondId + ")";
        // call stringifyResults()
        assertEquals(MonkeySim.stringifyResults(1, firstMonkey, secondMonkey), output);
    }


    // Pinning test of modified method stringifyResults() in MonkeySim.java.
    // Tests that when stringifyResults() is passed in set of parameters, method returns
    // correct String version of the round and information about it.  
    // Tested by checking that output String returned from method call equals String created 
    // in unit test itself. Checked by initializing 2 Monkey objects with int values 0 & 2 
    // passed into generateId(). Various methods then called on the 2 Monkey objects when 
    // creating output String.
    // It should return a String of exact same format and values.  
    @Test
    public void testStringifyResultsMonkey1To3() throws NoIdException {
        // create 2 Monkey objects
        Monkey firstMonkey = new Monkey();
        // generateId(0) -> Monkey #0
        firstMonkey.generateId(0);
        Monkey thirdMonkey = new Monkey();
        // generateId(2) -> Monkey #2
        thirdMonkey.generateId(2);
        // get ID values of each monkey
        int firstId = firstMonkey.getId();
        int secondId = thirdMonkey.getId();
        // create correct String to compare against
        String output = "//Round 1: Threw banana from Monkey (#" + firstMonkey.getMonkeyNum() + " / ID "
                + firstId + ") to Monkey (#" + thirdMonkey.getMonkeyNum() + " / ID " + secondId + ")";
        // call stringifyResults()
        assertEquals(MonkeySim.stringifyResults(1, firstMonkey, thirdMonkey), output);
    }


    // Pinning test of modified method stringifyResults() in MonkeySim.java.
    // Tests that when stringifyResults() is passed in set of parameters, method returns
    // correct String version of the round and information about it.  
    // Tested by checking that output String returned from method call equals String created 
    // in unit test itself. Checked by initializing 2 Monkey objects with int values 1 & 2 
    // passed into generateId(). Various methods then called on the 2 Monkey objects when 
    // creating output String.
    // It should return a String of exact same format and values.  
    @Test
    public void testStringifyResultsMonkey2To3() throws NoIdException {
        // create 2 Monkey objects
        Monkey secondMonkey = new Monkey();
        // generateId(1) -> Monkey #1
        secondMonkey.generateId(1);
        Monkey thirdMonkey = new Monkey();
        // generateId(2) -> Monkey #2
        thirdMonkey.generateId(2);
        // get ID values of each monkey
        int firstId = secondMonkey.getId();
        int secondId = thirdMonkey.getId();
        // create correct String to compare against
        String output = "//Round 1: Threw banana from Monkey (#" + secondMonkey.getMonkeyNum() + " / ID "
                + firstId + ") to Monkey (#" + thirdMonkey.getMonkeyNum() + " / ID " + secondId + ")";
        // call stringifyResults()
        assertEquals(MonkeySim.stringifyResults(1, secondMonkey, thirdMonkey), output);
    }


    // Pinning test of modified method stringifyResults() in MonkeySim.java.
    // Tests that when stringifyResults() is passed in set of parameters, method returns
    // a return value that is in fact not null.   
    // Tested by checking output returned from method call which is passed 2 properly
    // initialized Monkey objects (initialized with int values 0 & 1 passed into generateId()).
    // It should return a String using the properly instantiated Monkey objects; should not return null.   
    @Test
    public void testStringifyResultsNotNull() {
        // create 2 Monkey objects
        Monkey firstMonkey = new Monkey();
        // generateId(0) -> Monkey #0
        firstMonkey.generateId(0);
        Monkey secondMonkey = new Monkey();
        // generateId(1) -> Monkey #1
        secondMonkey.generateId(1);
        // call stringifyResults()
        assertNotNull(MonkeySim.stringifyResults(1, firstMonkey, secondMonkey));
    }


    // Pinning test of modified method generateId() in Monkey.java.
    // Tests that when generateId() is passed in int 0 as monkey number, 
    // it returns correct id int value for this given monkey. 
    // It should return int value 223492 (this is 0+223492).
    @Test
    public void testGenerateId() {
        // create Monkey object
        Monkey firstMonkey = new Monkey();
        // call generateId()
        assertEquals(firstMonkey.generateId(0), 223492);
    }


    // Pinning test of modified method generateId() in Monkey.java.
    // Tests that when generateId() is passed in int 1 as monkey number, 
    // it does not return 1 as being the id int value for this given monkey as well. 
    // It should do work and return int value 223493 (this is 1+223492); not 1.  
    @Test
    public void testGenerateIdNotEquals() {
        // create Monkey object
        Monkey secondMonkey = new Monkey();
        // call generateId()
        assertNotEquals(secondMonkey.generateId(1), 1);
    }


    // Pinning test of modified method generateId() in Monkey.java.
    // Tests that when generateId() is passed in int 0 as monkey number, 
    // it does not return null.
    // It should do work return an int value for the valid int passed in. 
    @Test
    public void testGenerateIdNotNull() {
        // create Monkey object
        Monkey thirdMonkey = new Monkey();
        // call generateId()
        assertNotNull(thirdMonkey.generateId(2));
    }

}
