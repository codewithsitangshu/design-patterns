package com.org.codewithsitangshu;

import com.org.codewithsitangshu.pages.HomePage;
import com.org.codewithsitangshu.pages.category.SelectCategory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SelectCategoryTest extends BaseTest {

    private HomePage homePage;
    private SelectCategory selectCategory;

    @BeforeMethod
    public void init() {
        this.homePage = new HomePage(this.driver);
        this.selectCategory = new SelectCategory(this.driver);
    }

    @Test
    public void goTo() {
        this.homePage.goTo();
        driver.manage().window().maximize();
        Assert.assertTrue(this.homePage.isAt(), "Unable to navigate to Home Page");
    }

    @Test(dependsOnMethods = "goTo", dataProvider = "dp")
    public void selectCategory(String category) {
        Assert.assertTrue(this.selectCategory.select(category),
                "unable to select category as : " + category);
    }

    @DataProvider
    public Object[] dp() {
        return new String[]{"Women", "Men", "Kids"};
    }

}
