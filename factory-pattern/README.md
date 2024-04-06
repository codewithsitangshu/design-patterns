# Factory Design Pattern

## Introduction

Factory Pattern is one of the creation Patterns. It is mostly used when we need to create an object from one of several possible classes that share a common implements an interface. It creates objects without exposing the instantiation logic to the user. We, as the user, refer to the newly created object through a common interface.

![Alt Text](driver.png)

Here interface is **WebDriver** and all these **Chrome/Firefox/Safari/IE** driver concrete classes implement this WebDriver interface. We are able to refer to the, ChromeDriver/FirefoxDriver etc, instance through the WebDriver interface without much change in the code.


## Problem in Creating WebDriver Instance:

Even though the above concrete classes implements a common interface, We need to follow different approaches in creating an instance from one of these concrete classes which depends on the browser instance we might want to use.

For ex: ChromeDriver requires a Driver Server setup but Firefox (till version 45) does not need anything.

```Java
WebDriver driver = new FirefoxDriver();
```

The above code simply works – but the below code might not unless we set driver server executable.

```Java
WebDriver driver = new ChromeDriver();
```

But we know how to solve!!

The easiest solution is – to put all the logic in a if else block!! right?


``` Java

if (Browser.equals("chrome")) {

    // logic to start the driver service
    // then to create driver etc

} else if (Browser.equals("firefox")) {

    // logic to start the driver service
    // then to create driver etc

} else if (Browser.equals("ie")) {


} else if (Browser.equals("safari")) {


} else if (Browser.equals("phantomjs")) {


}

```
It might look like an easy solution. But it is really NOT. When we have to start/stop the Driver Service ourselves, the code becomes significantly larger and very difficult to maintain. (This will create more boiler plate code and lots of if else blocks.)

## Factory Pattern in Creating WebDriver Instance:

The Factory Pattern can effectively address the issue mentioned by abstracting away the creation logic of WebDriver instances from the test classes, providing them with a simple interface to obtain the necessary driver instance. To implement this, we introduce an abstract class called DriverManager. Test classes can then utilize this abstract class to retrieve a WebDriver instance without needing to concern themselves with the specifics of how the drivers are actually created.

Here's a brief overview of the architecture:

![Alt Text](factory-partten.png)


Lets see the concrete implementation.


## Driver Manager:

``` Java
public abstract class DriverManager {

    protected WebDriver driver;
    protected abstract void startService();
    protected abstract void stopService();
    protected abstract void createDriver();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }
}
```

## Chrome Driver Manager:

```Java
public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chService;

    @Override
    public void startService() {
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    public void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(chService, capabilities);
    }

}
```

## Driver Types:

```Java
public enum DriverType {
    CHROME,
    FIREFOX,
    IE,
    SAFARI;
}
```

## Driver Manager Factory:

```Java
public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new SafariDriverManager();
                break;
        }
        return driverManager;

    }
}
```

## Base Test Class:

```Java
public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    DriverManager driverManager;
    protected WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void beforeTest(@Optional("Chrome") String browser) {
        logger.info("Starting beforeTest method...");
        try {
            logger.info("Initializing driver manager...");
            driverManager = DriverModule.getManager(DriverType.valueOf(browser.toUpperCase()));
            logger.info("Driver manager initialized successfully.");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid browser type provided: " + browser, e);
            throw new IllegalArgumentException("Invalid browser type provided: " + browser, e);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.info("Starting beforeMethod method...");
        driver = driverManager.getDriver();
        logger.info("WebDriver instance obtained successfully.");
    }

    @AfterTest
    public void afterTest() {
        logger.info("Starting afterTest method...");
        driverManager.quitDriver();
        logger.info("WebDriver quit successfully.");
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("Starting afterSuite method...");
        driverManager.stopService();
        logger.info("Driver service stopped successfully.");
    }
}
```

## Test Class:

```Java
public class GoogleTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(GoogleTest.class);

    @Test
    public void launchGoogleTest() {
        logger.info("Launching Google Test...");
        driver.get("https://www.google.com");
        logger.info("Navigated to Google homepage.");
        String title = driver.getTitle();
        logger.info("Page title: {}", title);
        Assert.assertEquals(title, "Google", "Page title mismatch.");
        logger.info("Google Test completed successfully.");
    }

    @Test(dependsOnMethods = "launchGoogleTest")
    public void enterInSearchBox() {
        logger.info("Entering text in search box...");
        driver.findElement(By.name("q")).click();
        logger.info("Clicked on search box.");
        Uninterruptibles.sleepUninterruptibly(1, SECONDS);
        driver.findElement(By.name("q")).sendKeys("Selenium");
        logger.info("Entered 'Selenium' in search box.");
    }

}
```

If the test class has to use Firefox, just use as given below.

```Java
driverManager = DriverManagerFactory.getManager(DriverType.FIREFOX);
```

## Summary:

By using Factory Pattern, we completely hide the creational logic of the browser / service instances to the test classes. If we get a new requirement to add a new browser, say PhantomJS, should not be a big deal. We just need to create a PhantomJSDriverManager which extends DriverManager. [Ofcourse you have to add PhantomJS in DriverType]