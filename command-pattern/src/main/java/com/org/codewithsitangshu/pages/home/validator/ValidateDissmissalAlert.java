package com.org.codewithsitangshu.pages.home.validator;

import com.google.common.util.concurrent.Uninterruptibles;
import com.org.codewithsitangshu.pages.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class ValidateDissmissalAlert implements Validator {

    // Log4j2 logger instance
    private static final Logger logger = LogManager.getLogger(ValidateDissmissalAlert.class);

    private final WebElement dissmissalAlert;

    public ValidateDissmissalAlert(final WebElement dissmissalAlert) {
        this.dissmissalAlert = dissmissalAlert;
    }

    @Override
    public boolean validate() {
        logger.info("Validating Dissmissal Alert");

        // Wait for Dissmissal Alert to display
        await("Wait for Dissmissal Alert to display")
                .atMost(Duration.ofSeconds(2))
                .until(this.dissmissalAlert::isDisplayed);

        // Check if Dissmissal Alert is displayed
        boolean isDissmissalAlertDisplayed = this.dissmissalAlert.isDisplayed();
        logger.info("Dissmissal Alert is displayed: {}", isDissmissalAlertDisplayed);

        // Perform dismiss action (click on close button)
        this.dissmissalAlert.findElement(By.cssSelector("button.close")).click();
        logger.info("Clicked on close button of Dissmissal Alert");

        // Wait briefly to allow dismissal animation or transition
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        // Check if Dissmissal Alert is still present (should not be)
        boolean isDissmissalAlertDisappear;
        try {
            isDissmissalAlertDisappear = dissmissalAlert.isDisplayed();
            logger.info("Dissmissal Alert is still displayed after dismissal");
        } catch (NoSuchElementException e) {
            isDissmissalAlertDisappear = false;
            logger.info("Dissmissal Alert is no longer present on the page");
        }

        // Return validation result: should be displayed before dismissal and disappear after
        return isDissmissalAlertDisplayed && (!isDissmissalAlertDisappear);
    }
}
