package com.org.codewithsitangshu.pages.home;

import com.org.codewithsitangshu.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class HomePage extends BasePage {

    // Log4j2 logger instance
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    // WebElement for the Home button
    @FindBy(xpath = "//*[@class='row page-titles']//*[text()='Home']")
    private WebElement home_button;

    private SimpleToastAlerts simpleToastAlerts;
    private DissmissalAlert dissmissalAlert;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieves the SimpleToastAlerts instance for the Home page.
     *
     * @return SimpleToastAlerts instance
     */
    public SimpleToastAlerts getSimpleToastAlerts() {
        return new SimpleToastAlerts(this.driver);
    }

    /**
     * Retrieves the DissmissalAlert instance for the Home page.
     *
     * @return DissmissalAlert instance
     */
    public DissmissalAlert getDissmissalAlert() {
        return new DissmissalAlert(this.driver);
    }

    /**
     * Checks if the Home page is loaded.
     *
     * @return true if the Home page is loaded, false otherwise
     */
    @Override
    public boolean isAt() {
        logger.info("Checking if Home page is loaded");
        await("Wait for Home page to display")
                .atMost(Duration.ofSeconds(20))
                .ignoreExceptions()
                .until(home_button::isDisplayed);
        boolean isHomePageDisplayed = home_button.isDisplayed();
        logger.info("Home page is displayed: {}", isHomePageDisplayed);
        return isHomePageDisplayed;
    }

}
