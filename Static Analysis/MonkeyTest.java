import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

// A test for the Monkey class

public class MonkeyTest {

/////////////////////////////////////////////////////////////////////

// Tests Monkey object to ensure checkIfPrime() method returns
// true when passed a prime value

  @Test
  public void testCheckIfPrime() {
    Monkey monkey1 = new Monkey();
    assertTrue(monkey1.checkIfPrime(7));
  }

/////////////////////////////////////////////////////////////////////

  // Tests Monkey object to ensure checkIfPrime() method returns
  // false when passed a non-prime value

  @Test
  public void testCheckIfNotPrime() {
    Monkey monkey1 = new Monkey();
    assertFalse(monkey1.checkIfPrime(6));
  }

/////////////////////////////////////////////////////////////////////

  // Tests Monkey object to ensure nextMonkey() method returns
  // 1 when thisMonkeyNum variable is not expliitly set

  @Test
  public void testNextMonkeyImplicit() {
    Monkey monkey1 = new Monkey();
    assertEquals(monkey1.getMonkeyNum(),1);
  }

/////////////////////////////////////////////////////////////////////

// Tests Monkey object to ensure nextMonkey() method returns
// 4 when thisMonkeyNum variable is expliitly set to 4

    @Test
    public void testNextMonkey() {
      Monkey monkey1 = new Monkey();
      monkey1.setMonkeyNum(4);
      assertEquals(monkey1.getMonkeyNum(),4);
    }

/////////////////////////////////////////////////////////////////////

  // Tests Monkey's generateIdRefactored method to ensure that it
  // outputs an expected id number

  @Test
  public void testGenerateIdExpectedValue() {
    Monkey monkey = new Monkey();
    int id = monkey.generateId(100);
    assertEquals(id,223592);
  }

/////////////////////////////////////////////////////////////////////

  // Tests Monkey's generateIdRefactored method to ensure that it
  // maintains consistency with the non-refactored version

  @Test
  public void testGenerateIdToUnrefactoredMethod() {
    Monkey monkey1 = new Monkey();
    Monkey monkey2 = new Monkey();
    int id1 = monkey1.generateId(100);
    int id2 = monkey2.generateId(100);
    assertEquals(id1,id2);
  }

/////////////////////////////////////////////////////////////////////

  // Tests Monkey object to ensure that two generic objects
  // each are still provided with unique numbers when initialized

  @Test
  public void testGeneratedMonkeys() {
    Monkey monkey1 = new Monkey();
    Monkey monkey2 = new Monkey();
    int id1 = monkey1.getMonkeyNum();
    int id2 = monkey2.getMonkeyNum();
    assertNotEquals(id1,id2);
  }
}
