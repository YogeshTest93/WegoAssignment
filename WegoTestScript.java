package seleniumPractice;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WegoTestScript {
	
//	Write the Automation script using selenium java 

//	1-Go to the https://www.wego.co.in/
//	2-Click on the Hotel tab
//	3-Select the destination
//	4-Select the check-in and check-out date 
//	5-Click on the Guest and Rooms tab and add 3 Adults and 1 Children
//	6-Click on the Search button
//	7-Click on the Book on Wego
//	8-Click on the selected hotel 
//	9-Click on the Book now for the above hotel 
//	10-new Guest window should be open

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yoges\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @Test
    public void wegoTest() throws InterruptedException {
        driver.get("https://www.wego.co.in");

        click(By.xpath("//div[@class='MBVliIsBBbxmDVLpsQle']//a[2]"));
        scroll(0, 300);

        WebElement from = driver.findElement(By.xpath("//div[@class='ZlcMoHGmPHipHKub6G1Z']//input"));
        from.clear();
        from.sendKeys("Dubai");
        from.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        from.sendKeys(Keys.ENTER);

        click(By.xpath("(//div[@class='vSZlELYMnb5m1ZWm5i8Y'])[1]"));
        click(By.xpath("(//div[@class='nZBfJFGt62XKC2RYJfB6 Pa29ddYZ3YFicPBiUZHJ'])[5]"));
        click(By.xpath("(//div[@class='nZBfJFGt62XKC2RYJfB6 Pa29ddYZ3YFicPBiUZHJ'])[12]"));
        click(By.xpath("//div[@class='SYfbHoSRdiQcDSHSJnXZ']"));
        Thread.sleep(2000);
        click(By.xpath("//div[@class='lhqZyDaUoOjpEBJ_s6q1 FbeDw1lh8CHZElDwNvVq']//button[2]"));
        click(By.xpath("(//button[@class='wFeoDfTPl0_Q2nQgzl3A'])[2]"));
        click(By.xpath("//div[@class='SQOdmOkvQkUo9dSrI2WA']//button[2]"));
        click(By.xpath("//button[@type='submit']"));

        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        switchToChildWindow(parent, s);
        Thread.sleep(5000);
        click(By.xpath("//div[@title='Book on Wego']"));
        Thread.sleep(2000);
        click(By.xpath("(//div[@class='AFFzK4WGZ9n2c_jIiR5X'])[1]"));
        Thread.sleep(3000);
        scroll(0, 400);
        click(By.xpath("(//a[contains(text(),\"book now\")])[1]"));
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//div[@class='GbBBzEjLhrkbH5aS2_Aa'])[1]")).sendKeys("Delta");
        driver.findElement(By.xpath("(//div[@class='GbBBzEjLhrkbH5aS2_Aa'])[2]")).sendKeys("Testing");
        driver.findElement(By.xpath("(//div[@class='GbBBzEjLhrkbH5aS2_Aa'])[3]")).sendKeys("Delta@gmail.com");
        driver.findElement(By.xpath("//input[@id=phoneNumber")).sendKeys("0123456789");
        WebElement country = driver.findElement(By.id("country-name"));
        country.sendKeys("India");
        country.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        country.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Reusable method to click an element
    private void click(By locator) {
        driver.findElement(locator).click();
    }

    // Reusable method to scroll the page
    private void scroll(int xOffset, int yOffset) {
        js.executeScript("window.scrollBy(" + xOffset + "," + yOffset + ")");
    }

    // Reusable method to switch to a child window
    private void switchToChildWindow(String parentHandle, Set<String> windowHandles) {
        Iterator<String> iterator = windowHandles.iterator();
        while (iterator.hasNext()) {
            String childHandle = iterator.next();
            if (!childHandle.equals(parentHandle)) {
                driver.switchTo().window(childHandle);
                break;
            }
        }
    }
}