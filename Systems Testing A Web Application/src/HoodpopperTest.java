import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Systems-level, automated black-box tests for a Ruby compilation visualizer
 * using Selenium and JUnit.
 * Testing Hoodpopper's general functionality.  
 * @author RitikaMaknoor
 *
 */

public class HoodpopperTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for ruby compilation visualizer (Hoodpopper) for each test
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}

	// Given that I am on the main page
	// When I view the buttons on the page
	// Then I see that it contains "Tokenize", "Parse", and "Compile" buttons
	@Test
	public void testHasCorrectButtonOptions() {
		
		// Check for Tokenize, Parse, and Compile buttons by their element names/xpaths
		// If these are not all found, fail test
		try {
			driver.findElement(By.name("commit"));
			driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
			driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the "Tokenize Operation" page
	// When I click on the "Back" link
	// Then I should be redirected back to the "main" page
	// Then if navigate().forward() should get back on "Tokenize Operation" page
	@Test 
	public void testTokenizePageBackLink(){

		// Look for "Tokenize" button and click
		WebElement tokenizeButton = driver.findElement(By.name("commit"));
		tokenizeButton.click();

		// Look for "Back" link and click
		// Then navigate().forward()
		WebElement backLink = driver.findElement(By.linkText("Back"));
		backLink.click();
		driver.navigate().forward();

		// Check that the page you go to next contains the text "Tokenize Operation"
		// If it does not exist, or text is incorrect, fail test
		try {
			String tokenizePageText = driver.findElement(By.tagName("body")).getText();
			assertTrue(tokenizePageText.contains("Tokenize Operation"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the "Parse Operation" page
	// When I click on the "Back" link
	// Then I should be redirected back to the "main" page
	// Then if navigate().forward() should get back on "Parse Operation" page
	@Test 
	public void testParsePageBackLink(){

		// Look for "Parse" button and click
		WebElement parseButton = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		parseButton.click();

		// Look for "Back" link and click
		// Then navigate().forward()
		WebElement backLink = driver.findElement(By.linkText("Back"));
		backLink.click();
		driver.navigate().forward();

		// Check that the page you go to next contains the text "Parse Operation"
		// If it does not exist, or text is incorrect, fail test
		try {
			String parsePageText = driver.findElement(By.tagName("body")).getText();
			assertTrue(parsePageText.contains("Parse Operation"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the "Compile Operation" page
	// When I click on the "Back" link
	// Then I should be redirected back to the "main" page
	// Then if navigate().forward() should get back on "Compile Operation" page
	@Test 
	public void testCompilePageBackLink(){

		// Look for "Compile" button and click
		WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		compileButton.click();

		// Look for "Back" link and click
		// Then navigate().forward()
		WebElement backLink = driver.findElement(By.linkText("Back"));
		backLink.click();
		driver.navigate().forward();

		// Check that the page you go to next contains the text "Compile Operation"
		// If it does not exist, or text is incorrect, fail test
		try {
			String compilePageText = driver.findElement(By.tagName("body")).getText();
			assertTrue(compilePageText.contains("Compile Operation"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the "Compile Operation" page
	// When I click on the "Back" link
	// Then I should be redirected back to the "main" page
	// All of the available elements on this "main" page should be same as before; test if Code textbox avail
	@Test 
	public void testBackLinkElements(){
//DEFECT DETECTED. Comment out line of code in try block for test to pass

		// Look for "Compile" button and click
		WebElement compileButton = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		compileButton.click();

		// Look for "Back" link and click
		WebElement backLink = driver.findElement(By.linkText("Back"));
		backLink.click();

		// Look for Code textbox on this page
		// If it does not exist, then page must not be the accurate "Back" state, fail test
		try {
			driver.findElement(By.id("code_code"));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

	// Given that I am on the main page
	// When I type text into Code textbox, click a button, come back to main, and attempt to edit the text,
	// Then my previously entered text should be present in this textbox still
	@Test
	public void testTextboxSavedBack() {
//DEFECT DETECTED. Change to assertFalse for test to pass
		
		// Look for "Code" textbox
		// Initialize variable "first_name" as "Ritika"
		driver.findElement(By.id("code_code")).sendKeys("first_name = Ritika");

		// Look for "Tokenize" button and click
		WebElement tokenizeButton = driver.findElement(By.name("commit"));
		tokenizeButton.click();

		// Return back to main page
		driver.navigate().back();

		// Look for "Tokenize" button and click again
		WebElement tokenizeButtonSec = driver.findElement(By.name("commit"));
		tokenizeButtonSec.click();

		// Check that "first_name" and "Ritika" tokens present in list of tokens still
		// Do so by checking that the page does contain the texts "first_name" and "Ritika"
		// If they both do not exist, fail test
		try {
			String textSavedBack = driver.findElement(By.tagName("body")).getText();
			assertTrue((textSavedBack.contains("first_name")) && (textSavedBack.contains("Ritika")));
		} catch (NoSuchElementException ex) {
			fail();
		}
	}

}
