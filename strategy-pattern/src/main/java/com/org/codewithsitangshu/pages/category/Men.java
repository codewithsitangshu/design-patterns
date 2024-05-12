package com.org.codewithsitangshu.pages.category;

import com.google.common.util.concurrent.Uninterruptibles;
import com.org.codewithsitangshu.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class Men extends BasePage implements Category {

    @FindBy(css = ".category-products #Men")
    private WebElement men_label;

    @FindBy(css = ".category-products a[href='#Men']")
    private WebElement men_link;

    public Men(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        return false;
    }

    @Override
    public boolean select() {
        men_link.click();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        return men_label.getAttribute("class")
                .contains("in");
    }

}
