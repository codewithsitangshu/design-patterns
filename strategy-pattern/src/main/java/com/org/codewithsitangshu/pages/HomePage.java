package com.org.codewithsitangshu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class HomePage extends BasePage {

    private final String url = "https://automationexercise.com/";

    @FindBy(xpath = "//a[normalize-space()='Home']")
    private WebElement home_button;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        await("Wait for Home page to display")
                .atMost(Duration.ofSeconds(20))
                .ignoreExceptions()
                .until(home_button::isDisplayed);
        return home_button.isDisplayed();
    }

    public void goTo() {
        driver.get(url);
    }

}
