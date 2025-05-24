package appium.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class DriverFactory {
    public AppiumDriverLocalService appiumDriverLocalService;
    protected AppiumDriver getDriver() {
        AppiumDriver driver;
        String platform = "android";

        appiumDriverLocalService = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withAppiumJS(new File("/opt/homebrew/bin/appium"))
                .build();
        appiumDriverLocalService.start();
        try {
            switch (platform) {
                case "android" -> {
                    UiAutomator2Options options = new UiAutomator2Options();
                    options.setAutomationName("UiAutomator2")
                            .setPlatformName("Android")
                            .setApp(getAndroidPath())
                            .setUdid("emulator-5554");

                    driver = new AndroidDriver(new URL(appiumDriverLocalService.getUrl().toString()), options);
                    return driver;
                }
                case "ios" -> {
                    XCUITestOptions options = new XCUITestOptions()

                            .setApp(getiOSPath())
                            .setAutomationName("XCUITest")
                            .setPlatformName("iOS");
                    driver = new IOSDriver(new URL(appiumDriverLocalService.getUrl().toString()), options);
                    return driver;

                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getAndroidPath() {
        return Paths.get("app", "gojek.apk").toAbsolutePath().toString();
    }
    public String getiOSPath() {
        return Paths.get("app", "coffee-timer.app").toAbsolutePath().toString();
    }
}
