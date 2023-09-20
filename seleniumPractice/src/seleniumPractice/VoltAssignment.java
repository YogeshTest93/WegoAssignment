package seleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VoltAssignment {

	public String baseUrl = "https://www.saucedemo.com/";
	String driverPath = "C:\\Users\\yoges\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";
	public WebDriver driver;

	public void login(String username, String password) {
		// Login to the Website
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();
	}

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		login("standard_user", "secret_sauce");
	}

	@Test(priority = 1)
	public void addToCart() throws InterruptedException {
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		driver.findElement(By.name("checkout")).click();

		// Assert that the First Name field is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='First Name']")).isDisplayed());

		// Assert that the Last Name field is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//input[@data-test='lastName']")).isDisplayed());

		// Assert that the Zip/Postal Code field is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Zip/Postal Code']")).isDisplayed());

		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Delta");
		driver.findElement(By.xpath("//input[@data-test='lastName']")).sendKeys("User");
		driver.findElement(By.xpath("//input[@placeholder='Zip/Postal Code']")).sendKeys("560102");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// Assert that the Finish button is displayed on the Overview page
		Assert.assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Finish')]")).isDisplayed());

		driver.findElement(By.xpath("//button[contains(text(),'Finish')]")).click();
		Thread.sleep(1000);
	}

	@Test(priority = 2)
	public void removeFromCart() throws InterruptedException {
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']")).click();
		Thread.sleep(1000);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
