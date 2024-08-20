package com.org.codewithsitangshu.pages.app;

import com.org.codewithsitangshu.pages.app.order.OrderComponentProxy;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import com.org.codewithsitangshu.pages.app.order.OrderComponent;
import com.org.codewithsitangshu.pages.app.payment.PaymentOption;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;


public class PaymentScreen {

    private WebDriver driver;
    @Getter
    private UserInformation userInformation;
    @Getter
    private OrderComponent orderComponent;
    private PaymentOption paymentOption;

    public PaymentScreen(final WebDriver driver){
        this.driver = driver;
        this.userInformation = new UserInformation(this.driver);
        this.orderComponent = new OrderComponentProxy(this.driver);
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
        PageFactory.initElements(driver, this.paymentOption);
    }

    public void pay(Map<String, String> paymentDetails){
        this.paymentOption.enterPaymentInformation(paymentDetails);
    }

}
