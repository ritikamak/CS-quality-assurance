import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Systems-level, automated black-box tests for a Ruby compilation visualizer
 * using Selenium and JUnit.
 * Testing "compile" functionality. 
 * @author RitikaMaknoor
 *
 */

public class CompilationTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for ruby compilation visualizer (Hoodpopper) for each test
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}

	// Given that I am on the main page
	// When I click on the "Compile" button
	// Then I should be redirected to the "Compile Operation" page
	@Test 
	public void testCompilePageButton(){

		// Look for "Compile" button and click
		WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		compileButton.click();

		// Check that the page you go to next contains the text "Compile Operation"
		// If it does not exist, or text is incorrect, fail test
		try {
			String compilePageText = driver.findElement(By.tagName("body")).getText();
			assertTrue(compilePageText.contains("Compile Operation"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "a+1/4" in Code textbox
	// When I click on the "compile" button
	// Then I should see appropriate arithmetic operator instructions; should see "opt_plus", "opt_div", "putobject" 
	@Test 
	public void testArithOpCompileInstructions(){

		// Enter "a+1/4" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a+1/4");

		// Look for "Compile" button and click
		WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		compileButton.click();

		// Check that "opt_plus", "opt_div", "putobject" in instructions
		// Do so by checking that the page does contain the text "opt_plus", "opt_div", "putobject"
 		// If all do not exist, fail test
		try {
			String compileArithOp = driver.findElement(By.tagName("body")).getText();
			assertTrue(compileArithOp.contains("opt_plus") && compileArithOp.contains("opt_div") && compileArithOp.contains("putobject"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "a+" in Code textbox
	// When I click on the "compile" button
	// Then I should see appropriate error code 
	@Test 
	public void testErrorArithOpCompile(){

		// Enter "a+" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a+");

		// Look for "Compile" button and click
		WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		compileButton.click();

		// Check that an error message displays
		// Do so by checking if page contains the text "trace"; this instruction should never occur because no compilation should begin
 		// If exists on page, fail test
		try {
			String compileErrorArithOp = driver.findElement(By.tagName("body")).getText();
			assertFalse(compileErrorArithOp.contains("trace"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "I have + 4dollars" in Code textbox
	// When I click on the "compile" button
	// Then I should see appropriate error code; cannot concatenate in this way- with ints written next to chars, no quotes
	@Test 
	public void testErrorConcatenation(){

		// Enter "I have + 4dollars" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("I have + 4dollars");

		// Look for "Compile" button and click
		WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		compileButton.click();

		// Check that an error message displays
		// Do so by checking if page contains the text "trace"; this instruction should never occur because no compilation should begin
 		// If exists on page, fail test
		try {
			String compileErrorConcat = driver.findElement(By.tagName("body")).getText();
			assertFalse(compileErrorConcat.contains("trace"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "puts 'Hello there!'" in Code textbox
	// When I click on the "compile" button
	// Then I should see appropriate instructions; should see "putstring" 
	@Test 
	public void testPutsCompileInstructions(){

		// Enter "puts \"Hello there!\"" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("puts \"Hello there!\"");

		// Look for "Compile" button and click
		WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		compileButton.click();

		// Check that "putstring" in instructions
		// Do so by checking that the page does contain the text "putstring"
 		// If it does not exist, fail test
		try {
			String compilePuts = driver.findElement(By.tagName("body")).getText();
			assertTrue(compilePuts.contains("putstring"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "puts 'Hello there!" in Code textbox
	// When I click on the "compile" button
	// Then I should see appropriate error code; cannot compile- not closing quote 
	@Test 
	public void testErrorPutsSyntax(){

		// Enter "puts \"Hello there! in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("puts \"Hello there!");

		// Look for "Compile" button and click
		WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		compileButton.click();

		// Check that an error message displays
		// Do so by checking if page contains the text "trace"; this instruction should never occur because no compilation should begin
 		// If exists on page, fail test
		try {
			String compileErrorPutsSyntax = driver.findElement(By.tagName("body")).getText();
			assertFalse(compileErrorPutsSyntax.contains("trace"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}
	
}
