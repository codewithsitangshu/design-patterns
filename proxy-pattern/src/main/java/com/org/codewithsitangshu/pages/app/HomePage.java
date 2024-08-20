package com.org.codewithsitangshu.pages.app;

import com.org.codewithsitangshu.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class HomePage extends BasePage {

    private final String url = "https://vins-udemy.s3.amazonaws.com/ds/strategy.html";

    @FindBy(xpath = "//img[@src='amazon.jpeg']")
    private WebElement home_image;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        await("Wait for Home page to display")
                .atMost(Duration.ofSeconds(20))
                .ignoreExceptions()
                .until(home_image::isDisplayed);
        return home_image.isDisplayed();
    }

    public void goTo() {
        driver.get(url);
    }

}
