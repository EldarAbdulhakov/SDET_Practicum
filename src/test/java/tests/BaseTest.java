package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTest {

    private WebDriver driver;
    private WebDriverWait wait5;

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    @BeforeMethod
    public void setUp() {
        try {
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1920, 1080));
        getDriver().get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            wait5 = null;
        }
    }
}