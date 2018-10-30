import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebElements {

	WebDriver driver;

	@BeforeTest
	public void invokeBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\harsha\\Desktop\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://qaclickacademy.com/practice.php");
	}

	/* Radio buttons dynamically */
	@Test
	public void radioButtons() {

		System.out.println(driver.findElements(By.xpath("//input[@name='radioButton']")).size());
		int count = driver.findElements(By.xpath("//input[@name='radioButton']")).size();
		for (int i = 0; i < count; i++) {
			String text = driver.findElements(By.xpath("//input[@name='radioButton']")).get(i).getAttribute("value");

			if (text.equals("radio2")) {

				driver.findElements(By.xpath("//input[@name='radioButton']")).get(i).click();
			}

		}

	}

	/* JavaScriptExecutor to handle elements whose webCode is hidden */
	// Selecting India from the list of Countries
	@Test
	public void jsExecutorExample() {
		driver.findElement(By.id("autocomplete")).sendKeys("Ind");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"autocomplete\").value;";
		String text = (String) js.executeScript(script);
		// System.out.println(text);
		int i = 0;
		while (!text.equalsIgnoreCase("India")) {
			i++;
			driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
			text = (String) js.executeScript(script);
			System.out.println(text);
			if (i > 10) {
				break;

			}
			if (i > 10) {
				System.out.println("Element not found");
			}
		}

	}

	/* DropDowns */

	@Test
	public void dropDowns() {
		Select s = new Select(driver.findElement(By.id("dropdown-class-example")));
		s.selectByValue("option2");
	}

	/* checkBoxes validation using assertions */
	@Test
	public void checkBoxes() {
		driver.findElement(By.id("checkBoxOption2")).click();
		Assert.assertTrue(driver.findElement(By.id("checkBoxOption2")).isSelected());
	}

	/* handling alerts pop up */
	@Test
	public void alertPopup() {
		driver.findElement(By.id("name")).sendKeys("Harsha");
		driver.findElement(By.xpath("//input[@id='alertbtn']")).click();
		driver.switchTo().alert().accept();
	}

	/* Handling Hidden elements */
	@Test
	public void displayed() 
	{
		int count = driver.findElements(By.id("displayed-text")).size();
		if (count > 0) 
		{
			driver.findElement(By.id("show-textbox")).click();
			Assert.assertTrue(driver.findElement(By.id("displayed-text")).isDisplayed());
			driver.findElement(By.id("displayed-text")).sendKeys("Hidden element text");
		} 
		else 
		{
			System.out.println("element not present");
		}

	}
	
	/* switching frame example */
	

	/* Limiting the scope of WebDriver and open links in new tabs */
	@Test
	public void scope() {

		System.out.println(driver.findElements(By.tagName("a")).size());
		WebElement footerdriver = driver.findElement(By.id("gf-BIG")); // no. of links in the footer of the page
		System.out.println(footerdriver.findElements(By.tagName("a")).size());
		WebElement columndriver = driver.findElement(By.xpath("//table//tbody//tr//td[1]//ul")); // no. of links in the
																									// first column of
																									// the footer page
		System.out.println(columndriver.findElements(By.tagName("a")).size());

		// open all links in columndriver in different tabs

		for (int i = 1; i < columndriver.findElements(By.tagName("a")).size(); i++) {

			String clickOnNewTabs = Keys.chord(Keys.CONTROL, Keys.ENTER);
			columndriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnNewTabs);
		}
	}

	/* Using Iterator concept to handle multiple windows and fetch their titles */
	@Test(dependsOnMethods = { "scope" })
	public void getTitle() throws InterruptedException {
		Thread.sleep(5000L);
		Set<String> s = driver.getWindowHandles();
		Iterator<String> it = s.iterator();
		String parentID = it.next();
		while (it.hasNext()) {

			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());

		}
		driver.switchTo().window(parentID);
	}
	
	

	/*@AfterTest
	public void teardown() {
		driver.quit();

	}*/

}
