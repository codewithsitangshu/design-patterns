package com.org.codewithsitangshu.pages.app;

import com.org.codewithsitangshu.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserInformation extends BasePage {

    @FindBy(id = "fn")
    private WebElement firstName;

    @FindBy(id = "ln")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement email;

    public UserInformation(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        return false;
    }

    public void enterDetails(String fn, String ln, String email){
        this.firstName.sendKeys(fn);
        this.lastName.sendKeys(ln);
        this.email.sendKeys(email);
    }


}
