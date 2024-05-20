package com.org.codewithsitangshu;

import com.org.codewithsitangshu.driver.DriverManager;
import com.org.codewithsitangshu.driver.DriverModule;
import com.org.codewithsitangshu.driver.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    // Log4j2 logger instance
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    private DriverManager driverManager;
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        logger.info("Starting Driver Manager and setting up before suite");
        driverManager = DriverModule.getManager(DriverType.CHROME);
    }

    @BeforeTest
    public void beforeTest() {
        logger.info("Getting WebDriver instance before test");
        driver = driverManager.getDriver();
    }

    @AfterTest
    public void afterTest() {
        logger.info("Quitting WebDriver after test");
        driverManager.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("Stopping Driver Service after suite");
        driverManager.stopService();
    }

}
