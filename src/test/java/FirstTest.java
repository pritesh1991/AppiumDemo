import org.testng.annotations.Test;
import pages.WelcomePage;

public class FirstTest extends BaseTest{

    @Test
    public void firstTest() {
        WelcomePage welcomePage = new WelcomePage(threadLocalDriver.get());
        welcomePage.clickSignupCTA();
    }
}
