package com.example.tests;

import com.example.core.CustomWebDriverManager;
import org.openqa.selenium.WebDriver;
import com.example.pages.LoginPage;
import org.testng.annotations.*;
import static org.testng.Assert.*;
public class LoginTest extends TestBase {
    private WebDriver driver;
    private LoginPage loginPage;
    private static final String LOGIN_URL = "https://the-internet.herokuapp.com/login";

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        this.driver = CustomWebDriverManager.getDriver();
        loginPage = new LoginPage();
        loginPage.navigateToLoginPage(LOGIN_URL);
    }

    @Test(description = "测试成功登录场景")
    public void testSuccessfulLogin() {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        assertTrue(driver.getCurrentUrl().contains("/secure"), "登录后未跳转到安全页面");
    }

    @Test(description = "测试登录失败场景 - 无效用户名")
    public void testLoginWithInvalidUsername() {
        loginPage.login("invaliduser", "SuperSecretPassword!");
        assertTrue(loginPage.isErrorMessageDisplayed(), "错误消息未显示");
        assertTrue(loginPage.getErrorMessageText().contains("invalid"), "错误消息内容不正确");
    }

    @Test(description = "测试登录失败场景 - 无效密码")
    public void testLoginWithInvalidPassword() {
        loginPage.login("tomsmith", "wrongpassword");
        assertTrue(loginPage.isErrorMessageDisplayed(), "错误消息未显示");
        assertTrue(loginPage.getErrorMessageText().contains("invalid"), "错误消息内容不正确");
    }
}