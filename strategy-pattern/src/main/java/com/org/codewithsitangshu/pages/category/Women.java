package com.org.codewithsitangshu.pages.category;

import com.google.common.util.concurrent.Uninterruptibles;
import com.org.codewithsitangshu.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class Women extends BasePage implements Category {

    @FindBy(css = ".category-products #Women")
    private WebElement women_label;

    @FindBy(css = ".category-products a[href='#Women']")
    private WebElement women_link;

    public Women(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isAt() {
        return false;
    }

    @Override
    public boolean select() {
        women_link.click();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        return women_label.getAttribute("class")
                .contains("in");
    }
}
