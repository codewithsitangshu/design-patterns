package com.org.codewithsitangshu.pages.home.validator;

import com.google.common.util.concurrent.Uninterruptibles;
import com.org.codewithsitangshu.pages.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class ValidateToastAlerts implements Validator {

    // Log4j2 logger instance
    private static final Logger logger = LogManager.getLogger(ValidateToastAlerts.class);

    private final WebElement alert_button;
    private final WebElement alert_toast;

    public ValidateToastAlerts(final WebElement alert_button, final WebElement alert_toast) {
        this.alert_button = alert_button;
        this.alert_toast = alert_toast;
    }

    @Override
    public boolean validate() {
        logger.info("Validating Toast Alert");

        // Wait for Alert button to display
        await("Wait for Alert button to display")
                .atMost(Duration.ofSeconds(2))
                .until(this.alert_button::isDisplayed);
        boolean isButtonDisplayed = this.alert_button.isDisplayed();
        logger.info("Alert button is displayed: {}", isButtonDisplayed);

        // Click on Alert button to trigger the toast alert
        this.alert_button.click();
        logger.info("Clicked on Alert button");

        // Check if Alert toast appears
        boolean isAlertToastAppear = this.alert_toast.isDisplayed();
        logger.info("Alert toast is displayed: {}", isAlertToastAppear);

        // Wait for a brief moment to observe the toast alert
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);

        // Check if Alert toast disappears after some time
        boolean isAlertToastDisappear = this.alert_toast.isDisplayed();
        logger.info("Alert toast is still displayed after waiting: {}", isAlertToastDisappear);

        // Return validation result: button displayed, toast appeared initially, and disappears after waiting
        return isButtonDisplayed && isAlertToastAppear && (!isAlertToastDisappear);
    }

}
