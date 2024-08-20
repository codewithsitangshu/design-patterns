package com.org.codewithsitangshu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.org.codewithsitangshu.pages.app.HomePage;
import com.org.codewithsitangshu.pages.app.PaymentScreen;
import com.org.codewithsitangshu.pages.app.payment.PaymentOptionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PaymentScreenTest extends BaseTest {

    private PaymentScreen paymentScreen;
    private HomePage homePage;

    @BeforeTest(dependsOnMethods = "initDriver")
    public void setPaymentScreen(){
        System.setProperty("env", "QA");
        //System.setProperty("env", "PROD");
        this.homePage = new HomePage(this.driver);
        this.paymentScreen = new PaymentScreen(this.driver);
    }

    @Test(dataProvider = "getData")
    public void paymentTest(String option, Map<String, String> paymentDetails){
        this.homePage.goTo();
        Assert.assertTrue(this.homePage.isAt(), "Unable to navigate to Home Page");

        this.paymentScreen.getUserInformation()
                .enterDetails("sitangshu", "pal", "example@example.com");
        this.paymentScreen.setPaymentOption(PaymentOptionFactory.get(option));
        this.paymentScreen.pay(paymentDetails);
        String orderNumber = this.paymentScreen.getOrderComponent().placeOrder();

        System.out.println(
                "Order Number : " + orderNumber
        );

        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

    }

    @DataProvider
    public Object[][] getData(){

        Map<String, String> cc = Maps.newHashMap();
        cc.put("cc", "1231231231");
        cc.put("year", "2023");
        cc.put("cvv", "123");

        Map<String, String> nb = Maps.newHashMap();
        nb.put("bank", "WELLS FARGO");
        nb.put("account", "myaccount123");
        nb.put("pin", "999");

        return new Object[][]{
                {"CC", cc} ,
                {"NB", nb}
        };
    }


}
