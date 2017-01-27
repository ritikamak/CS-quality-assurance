import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Systems-level, automated black-box tests for a Ruby compilation visualizer
 * using Selenium and JUnit.
 * Testing "parse" functionality. 
 * @author RitikaMaknoor
 *
 */

public class ParsingTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for ruby compilation visualizer (Hoodpopper) for each test
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}

	// Given that I am on the main page
	// When I click on the "Parse" button
	// Then I should be redirected to the "Parse Operation" page
	@Test 
	public void testParsePageButton(){

		// Look for "Parse" button and click
		WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		parseButton.click();

		// Check that the page you go to next contains the text "Parse Operation"
		// If it does not exist, or text is incorrect, fail test
		try {
			String parsePageText = driver.findElement(By.tagName("body")).getText();
			assertTrue(parsePageText.contains("Parse Operation"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "a=1" in Code textbox
	// When I click on the "parse" button
	// Then I should see non-whitespace/variable and value tokens in AST; should see "a", "1"
	@Test 
	public void testVarParseNonSpace(){

		// Enter "a=1" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a=1");

		// Look for "Parse" button and click
		WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		parseButton.click();

		// Check that "a", "1" in AST
		// Do so by checking that the page does contain the text "-a", "-1"
 		// If it does not exist, fail test
		try {
			String nonWhitespaceVars = driver.findElement(By.tagName("body")).getText();
			assertTrue(nonWhitespaceVars.contains("-a") && nonWhitespaceVars.contains("-1"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "a=1" in Code textbox
	// When I click on the "parse" button
	// Then I should see non-whitespace/equal-sign operator tokens in AST; should see "-="
	@Test 
	public void testEqOpParseNonSpace(){
//DEFECT DETECTED. Change to assertFalse for test to pass
		
		// Enter "a=1" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a=1");

		// Look for "Parse" button and click
		WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		parseButton.click();

		// Check that "=" in AST
		// Do so by checking that the page does contain the text "-="
 		// If it does not exist, fail test
		try {
			String nonWhitespaceEqualOp = driver.findElement(By.tagName("body")).getText();
			assertTrue(nonWhitespaceEqualOp.contains("-="));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "a+b*c" in Code textbox
	// When I click on the "parse" button
	// Then I should see non-whitespace/arithmetic operator tokens in AST; should see "-+", "-*" 
	@Test 
	public void testArithOpParseNonSpace(){

		// Enter "a+b*c" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a+b*c");

		// Look for "Parse" button and click
		WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		parseButton.click();

		// Check that "+", "*" in AST
		// Do so by checking that the page does contain the text "-+", "-*"
 		// If it does not exist, fail test
		try {
			String nonWhitespaceArithOp = driver.findElement(By.tagName("body")).getText();
			assertTrue(nonWhitespaceArithOp.contains("-+") && nonWhitespaceArithOp.contains("-*"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "puts 'Hello there!'" in Code textbox
	// When I click on the "parse" button
	// Then I should see non-whitespace/function identifier tokens in AST; should see "puts" 
	@Test 
	public void testFuncIdentParseNonSpace(){

		// Enter "puts \"Hello there!\"" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("puts \"Hello there!\"");

		// Look for "Parse" button and click
		WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		parseButton.click();

		// Check that "puts" in AST
		// Do so by checking that the page does contain the text "-puts"
		// If it does not exist, fail test
		try {
			String nonWhitespaceFuncIdent = driver.findElement(By.tagName("body")).getText();
			assertTrue(nonWhitespaceFuncIdent.contains("-puts"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "a = 1" (with spaces) in Code textbox
	// When I click on the "parse" button
	// Then I should not see whitespace tokens in AST; should not see " "
	@Test 
	public void testParseSpace(){

		// Enter "a = 1" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a = 1");

		// Look for "Parse" button and click
		WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		parseButton.click();

		// Check that " " not in AST
		// Do so by checking that the page does not contain the text " "
		// If it does exist, fail test
		try {
			String whitespaceVars = driver.findElement(By.tagName("body")).getText();
			assertFalse(whitespaceVars.contains("-\" \""));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

}
