import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class frameAndActions {
	WebDriver driver;

	@BeforeTest
	public void invokeBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\harsha\\Desktop\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://qaclickacademy.com/practice.php");
	}

	@Test
	public void frame() throws InterruptedException {

		driver.switchTo().frame(driver.findElement(By.id("courses-iframe")));
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div/div/span/div/div[7]/div/div/div[2]")).click();
		System.out.println("no thanks clicked");
		driver.switchTo()
				.frame(driver.findElement(By.xpath("//iframe[@src='https://www.youtube.com/embed/t2T16A0Aueg']")));
		driver.findElement(By.xpath("//button[@aria-label='Play']")).click();
		driver.switchTo().defaultContent();
	}

	@Test
	public void actionsClass() {
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.id("mousehover"))).build().perform();
		a.moveToElement(driver.findElement(By.xpath("//a[@href='#top']"))).click().build().perform();
	}
}
