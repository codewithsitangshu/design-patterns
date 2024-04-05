package org.iam.sitangshupal.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import org.iam.sitangshupal.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;

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