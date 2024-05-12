package com.org.codewithsitangshu;

import com.org.codewithsitangshu.driver.DriverManager;
import com.org.codewithsitangshu.driver.DriverModule;
import com.org.codewithsitangshu.driver.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    private DriverManager driverManager;
    protected WebDriver driver;

    @BeforeSuite
    public void beforeTest() {
        driverManager = DriverModule.getManager(DriverType.CHROME);
    }

    @BeforeTest
    public void beforeMethod() {
        driver = driverManager.getDriver();
    }

    @AfterTest
    public void afterTest() {
        driverManager.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        driverManager.stopService();
    }

}
