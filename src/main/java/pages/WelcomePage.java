package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import pageobjects.WelcomePageObject;

public class WelcomePage{
    WelcomePageObject welcomePageObject;

    public WelcomePage(AppiumDriver driver) {
        welcomePageObject = new WelcomePageObject();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this.welcomePageObject);
    }

    public void clickSignupCTA() {
        welcomePageObject.signupBtn.click();
        Reporter.log("Signup CTA clicked", true);
    }
}
