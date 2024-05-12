package com.org.codewithsitangshu.pages.category;

import com.google.common.util.concurrent.Uninterruptibles;
import com.org.codewithsitangshu.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class Kids extends BasePage implements Category {

    @FindBy(css = ".category-products #Kids")
    private WebElement kids_label;

    @FindBy(css = ".category-products a[href='#Kids']")
    private WebElement kids_link;

    public Kids(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        return false;
    }

    @Override
    public boolean select() {
        kids_link.click();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        return kids_label.getAttribute("class")
                .contains("in");
    }

}
