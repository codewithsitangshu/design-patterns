package com.org.codewithsitangshu.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    /**
     * Constructor for BasePage class.
     *
     * @param driver The WebDriver instance to be used by the page.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebDriverWait with a timeout of 20 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        logger.info("BasePage initialized.");
    }

    /**
     * Abstract method to be implemented by subclasses to verify if the page is at the expected state.
     *
     * @return true if the page is at the expected state, false otherwise.
     */
    public abstract boolean isAt();

    // You can add additional common methods or utilities for all pages here.
}
