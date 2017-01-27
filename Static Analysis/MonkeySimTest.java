import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.*;

// A test for the MonkeySim class

public class MonkeySimTest {

  // Tests MonkeySim's stringifyResults method to ensure that it
  // outputs an expected string when called twice with the same pair of monkeys

  @Test
  public void testStringifyResultsComparison() {
    MonkeySim sim = new MonkeySim();
    Monkey m1 = new Monkey();
    Monkey m2 = new Monkey();
    assertEquals(sim.stringifyResults(2,m1,m2),sim.stringifyResults(2,m1,m2));
  }

/////////////////////////////////////////////////////////////////////////

  // Tests MonkeySim's stringifyResults method to ensure that it
  // outputs the expected string result

  @Test
  public void testStringifyResultsOutput() {
    MonkeySim sim = new MonkeySim();
    Monkey m1 = new Monkey();
    Monkey m2 = new Monkey();
    assertEquals(sim.stringifyResults(1,m1,m2),"//Round 1: Threw banana from Monkey (#9 / ID 223501) to Monkey (#10 / ID 223502)");
  }

  /////////////////////////////////////////////////////////////////////////

    // Tests MonkeySim's stringifyResults method to ensure that it
    // outputs the expected result when id's are explicitly generated

    @Test
    public void testStringifyResultsWithIDSet() {
      MonkeySim sim = new MonkeySim();
      Monkey m1 = new Monkey();
      m1.generateId(5);
      Monkey m2 = new Monkey();
      m1.generateId(6);
      assertEquals(sim.stringifyResults(2,m1,m2),"//Round 2: Threw banana from Monkey (#11 / ID 223503) to Monkey (#12 / ID 223504)");
    }
}
