import appium.driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends DriverFactory {
    public ThreadLocal<AppiumDriver> threadLocalDriver = new ThreadLocal<>();
    AppiumDriver driver;

    @BeforeTest
    public void setUp() {
        driver = getDriver();
        threadLocalDriver.set(driver);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            Reporter.log("Quitting driver", true);
            driver.quit();
            threadLocalDriver.remove();
        }
        if (appiumDriverLocalService != null) {
            Reporter.log("Stopping Appium", true);
            appiumDriverLocalService.stop();
        }
    }
}
