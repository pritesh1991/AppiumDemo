package pageobjects;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class WelcomePageObject {
    @AndroidFindBy(id = "btn_signup")
    public WebElement signupBtn;
}
