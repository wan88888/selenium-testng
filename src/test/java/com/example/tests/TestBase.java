package com.example.tests;

import com.example.core.CustomWebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    @BeforeMethod
    public void setUp() {
        CustomWebDriverManager.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        CustomWebDriverManager.quitDriver();
    }
}