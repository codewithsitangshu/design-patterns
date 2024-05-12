package com.org.codewithsitangshu.pages.category;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class SelectCategory {

    private final Map<String, Category> categories = new HashMap<>();

    public SelectCategory(WebDriver driver) {
        categories.put("Women",new Women(driver));
        categories.put("Men",new Men(driver));
        categories.put("Kids",new Kids(driver));
    }

    public boolean select(String category) {
        return categories.get(category).select();
    }

}