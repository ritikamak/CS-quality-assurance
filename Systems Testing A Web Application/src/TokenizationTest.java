import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Systems-level, automated black-box tests for a Ruby compilation visualizer
 * using Selenium and JUnit.
 * Testing "tokenize" functionality. 
 * @author RitikaMaknoor
 *
 */

public class TokenizationTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for ruby compilation visualizer (Hoodpopper) for each test
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}

	// Given that I am on the main page
	// When I click on the "Tokenize" button
	// Then I should be redirected to the "Tokenize Operation" page
	@Test 
	public void testTokenizePageButton(){

		// Look for "Tokenize" button and click
		WebElement tokenizeButton = driver.findElement(By.name("commit"));
		tokenizeButton.click();

		// Check that the page you go to next contains the text "Tokenize Operation"
		// If it does not exist, or text is incorrect, fail test
		try {
			String tokenizePageText = driver.findElement(By.tagName("body")).getText();
			assertTrue(tokenizePageText.contains("Tokenize Operation"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "a=1" (no spaces) in Code textbox
	// When I click on the "tokenize" button
	// Then I should not see any ":on_sp" tokens in list of tokens
	@Test 
	public void testVarAssignNoSpace(){

		// Enter "a=1" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a=1");

		// Look for "Tokenize" button and click
		WebElement tokenizeButton = driver.findElement(By.name("commit"));
		tokenizeButton.click();

		// Check that no ":on_sp" tokens in list of tokens
		// Do so by checking that the page does not contain the text ":on_sp"
		String noSpaces = driver.findElement(By.tagName("body")).getText();
		assertFalse(noSpaces.contains(":on_sp"));
	}

	// Given that I am on the main page
	// When I type in "a = 1" (with spaces) in Code textbox 
	// When I click on the "tokenize" button
	// Then I should see ":on_sp" tokens in list of tokens
	@Test 
	public void testVarAssignWithSpace(){

		// Enter "a = 1" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a = 1");

		// Look for "Tokenize" button and click
		WebElement tokenizeButton = driver.findElement(By.name("commit"));
		tokenizeButton.click();

		// Check that ":on_sp" tokens present in list of tokens
		// Do so by checking that the page does contain the text ":on_sp"
		// If it does not exist, fail test
		try {
			String yesSpaces = driver.findElement(By.tagName("body")).getText();
			assertTrue(yesSpaces.contains(":on_sp"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "a * b" in Code textbox
	// When I click on the "tokenize" button
	// Then I should see ":on_op" tokens in list of tokens
	@Test 
	public void testOperatorToken(){

		// Enter "a = 1" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("a * b");

		// Look for "Tokenize" button and click
		WebElement tokenizeButton = driver.findElement(By.name("commit"));
		tokenizeButton.click();

		// Check that ":on_op" token present in list of tokens
		// Do so by checking that the page does contain the text ":on_op"
		// If it does not exist, fail test
		try {
			String yesOpToken = driver.findElement(By.tagName("body")).getText();
			assertTrue(yesOpToken.contains(":on_op"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "puts 'Hello there!'" in Code textbox
	// When I click on the "tokenize" button
	// Then I should see ":on_ident" tokens in list of tokens; bc puts is a function
	@Test 
	public void testIdentifierToken(){

		// Enter "puts \"Hello there!\"" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("puts \"Hello there!\"");

		// Look for "Tokenize" button and click
		WebElement tokenizeButton = driver.findElement(By.name("commit"));
		tokenizeButton.click();

		// Check that ":on_ident" token present in list of tokens
		// Do so by checking that the page does contain the text ":on_ident"
		// If it does not exist, fail test
		try {
			String yesIdentToken = driver.findElement(By.tagName("body")).getText();
			assertTrue(yesIdentToken.contains(":on_ident"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type in "Hello world" in Code textbox 
	// When I click on the "tokenize" button
	// Then I should see "Hello" and "world" as 2 seperate tokens in list
	@Test 
	public void testCreateTokensFunctionality(){

		// Enter "Hello world" in Code textbox
		driver.findElement(By.id("code_code")).sendKeys("Hello world");

		// Look for "Tokenize" button and click
		WebElement tokenizeButton = driver.findElement(By.name("commit"));
		tokenizeButton.click();

		// Check that "Hello" and "world" tokens present in list of tokens
		// Do so by checking that the page does contain the texts "Hello" and "world"
		// If they both do not exist, fail test
		try {
			String yesTokens = driver.findElement(By.tagName("body")).getText();
			assertTrue(yesTokens.contains("Hello") && yesTokens.contains("world"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

}
