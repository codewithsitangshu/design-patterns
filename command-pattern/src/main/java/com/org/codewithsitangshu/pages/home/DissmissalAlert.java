package com.org.codewithsitangshu.pages.home;

import com.org.codewithsitangshu.pages.BasePage;
import com.org.codewithsitangshu.pages.Validator;
import com.org.codewithsitangshu.pages.home.validator.ValidateDissmissalAlert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class DissmissalAlert extends BasePage {

    // Log4j2 logger instance
    private static final Logger logger = LogManager.getLogger(DissmissalAlert.class);

    // WebElements for different types of alerts
    @FindBy(xpath = "//*[@class='card-title' and text()='Dissmissal Alert']" +
            "/parent::*//div[contains(@class,'alert-success')]")
    private WebElement alert_success;

    @FindBy(xpath = "//*[@class='card-title' and text()='Dissmissal Alert']" +
            "/parent::*//div[contains(@class,'alert-danger')]")
    private WebElement alert_danger;

    @FindBy(xpath = "//*[@class='card-title' and text()='Dissmissal Alert']" +
            "/parent::*//div[contains(@class,'alert-warning')]")
    private WebElement alert_warning;

    @FindBy(xpath = "//*[@class='card-title' and text()='Dissmissal Alert']" +
            "/parent::*//div[contains(@class,'alert-info')]")
    private WebElement alert_info;

    public DissmissalAlert(WebDriver driver) {
        super(driver);
    }

    /**
     * Checks if the Dissmissal Alert page is loaded.
     *
     * @return true if the Dissmissal Alert page is loaded, false otherwise
     */
    @Override
    public boolean isAt() {
        logger.info("Checking if Dissmissal Alert page is loaded");
        // Implement logic to check if the Dissmissal Alert page is loaded
        return false; // Change this logic based on your actual implementation
    }

    /**
     * Retrieves the list of validators for Dissmissal Alerts.
     *
     * @return List of Validator objects for dissmissal alerts
     */
    public List<Validator> getDissmissalAlertValidator() {
        logger.info("Getting Dissmissal Alert validators");
        // Initialize validators for each type of alert
        return Arrays.asList(
                new ValidateDissmissalAlert(alert_success),
                new ValidateDissmissalAlert(alert_danger),
                new ValidateDissmissalAlert(alert_warning),
                new ValidateDissmissalAlert(alert_info)
        );
    }

}