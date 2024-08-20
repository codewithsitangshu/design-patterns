package com.org.codewithsitangshu.pages.app.order;

import com.org.codewithsitangshu.pages.TestEnvironment;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderComponentProxy implements OrderComponent {

    private static final List<TestEnvironment> restrictEnvironmentList;
    private OrderComponent orderComponent;

    static{
        restrictEnvironmentList = new ArrayList<>();
        restrictEnvironmentList.add(TestEnvironment.PROD);
        restrictEnvironmentList.add(TestEnvironment.STAGING);
    }

    public OrderComponentProxy(WebDriver driver){
        String currentEnv = System.getProperty("env"); // DEV / QA / PROD / STAGING
        if(!restrictEnvironmentList.contains(currentEnv.toUpperCase())){
            this.orderComponent = new OrderComponentReal(driver);
        }
    }

    @Override
    public String placeOrder() {
        if(Objects.nonNull(this.orderComponent)){
            return this.orderComponent.placeOrder();
        }else{
            return "SKIPPED";
        }
    }
}
