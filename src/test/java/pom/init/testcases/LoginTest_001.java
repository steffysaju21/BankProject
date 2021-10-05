package pom.init.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.init.pageobject.LoginPage;
import pom.init.testcases.BaseClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginTest_001 extends BaseClass {

    @Test
    public void loginTest() throws IOException {
        Logger.info("URL is opened");
        LoginPage lp = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        lp.setUserName(username);
        Logger.info("Enter Username");
        lp.setPassword(password);
        Logger.info("Enter password");
        lp.clickSubmit();
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//head//title")));
        if(driver.getTitle().equals("Guru99 Bank Manager HomePage")){
            Assert.assertTrue(true);
            Logger.info("Login passed");
        }else {
            captureScreenshot(driver,"loginTest");
            Assert.fail();
            Logger.info("Login test failed");
        }

    }
}
