package com.org.codewithsitangshu;

import com.org.codewithsitangshu.pages.home.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    // Log4j2 logger instance
    private static final Logger logger = LogManager.getLogger(HomePageTest.class);

    private HomePage homePage;
    private final String url = "https://vins-udemy.s3.amazonaws.com/ds/admin-template/admin-template.html";

    @BeforeTest
    public void setup() {
        logger.info("Setting up HomePageTest");
        this.homePage = new HomePage(this.driver);
    }

    @Test
    public void goTo() {
        logger.info("Navigating to URL: {}", url);
        this.driver.get(url);
        Assert.assertTrue(this.homePage.isAt(), "Unable to navigate to Home Page");
        logger.info("Successfully navigated to Home Page");
    }

    @Test(dependsOnMethods = "goTo",
            description = "Validate all Simple Toast Alerts")
    public void validateSimpleToastAlerts() {
        logger.info("Validating Simple Toast Alerts");
        this.homePage
                .getSimpleToastAlerts()
                .getToastAlertsValidator()
                .stream()
                .parallel()
                .map(validator -> validator.validate())
                .forEach(flag -> Assert.assertTrue(flag, "Unable to validate Simple Toast Alerts"));
        logger.info("Simple Toast Alerts validation completed");
    }

    @Test(dependsOnMethods = "validateSimpleToastAlerts",
            description = "Validate all Dismissal Alerts")
    public void validateDismissalAlert() {
        logger.info("Validating Dismissal Alerts");
        this.homePage
                .getDissmissalAlert()
                .getDissmissalAlertValidator()
                .stream()
                .map(validator -> validator.validate())
                .forEach(flag -> Assert.assertTrue(flag, "Unable to validate Dismissal Alerts"));
        logger.info("Dismissal Alerts validation completed");
    }

}
