package com.org.codewithsitangshu.pages.home;

import com.org.codewithsitangshu.pages.BasePage;
import com.org.codewithsitangshu.pages.Validator;
import com.org.codewithsitangshu.pages.home.validator.ValidateToastAlerts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class SimpleToastAlerts extends BasePage {

    // Log4j2 logger instance
    private static final Logger logger = LogManager.getLogger(SimpleToastAlerts.class);

    // WebElements for different types of alerts
    @FindBy(css = ".button-box button.btn-info")
    private WebElement button_info;

    @FindBy(css = "div.jq-icon-info")
    private WebElement info_alert;

    @FindBy(css = ".button-box button.btn-warning")
    private WebElement button_warning;

    @FindBy(css = "div.jq-icon-warning")
    private WebElement warning_alert;

    @FindBy(css = ".button-box button.btn-success")
    private WebElement button_success;

    @FindBy(css = "div.jq-icon-success")
    private WebElement success_alert;

    @FindBy(css = ".button-box button.btn-danger")
    private WebElement button_danger;

    @FindBy(css = "div.jq-icon-error")
    private WebElement danger_alert;

    public SimpleToastAlerts(WebDriver driver) {
        super(driver);
    }

    /**
     * Checks if the Simple Toast Alerts page is loaded.
     *
     * @return true if the Simple Toast Alerts page is loaded, false otherwise
     */
    @Override
    public boolean isAt() {
        logger.info("Checking if Simple Toast Alerts page is loaded");
        // Implement logic to check if the Simple Toast Alerts page is loaded
        return false; // Change this logic based on your actual implementation
    }

    /**
     * Retrieves the list of validators for Simple Toast Alerts.
     *
     * @return List of Validator objects for toast alerts
     */
    public List<Validator> getToastAlertsValidator() {
        logger.info("Getting Simple Toast Alerts validators");
        // Initialize validators for each type of alert
        return Arrays.asList(
                new ValidateToastAlerts(button_info, info_alert),
                new ValidateToastAlerts(button_warning, warning_alert),
                new ValidateToastAlerts(button_success, success_alert),
                new ValidateToastAlerts(button_danger, danger_alert)
        );
    }

}
