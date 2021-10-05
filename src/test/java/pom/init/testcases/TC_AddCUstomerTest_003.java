package pom.init.testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.init.pageobject.AddCustomerPage;
import pom.init.pageobject.LoginPage;

import java.io.IOException;
import java.util.Random;

public class TC_AddCUstomerTest_003 extends  BaseClass {

    @Test
    public void addNewCustomer() throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        lp.setPassword(password);
        lp.clickSubmit();

        Thread.sleep(3000);

        AddCustomerPage addcust = new AddCustomerPage(driver);

        addcust.clickAddNewCustomer();

        addcust.custName("Pavan");
        addcust.custgender("male");
        addcust.custdob("10","15","1985");
        Thread.sleep(3000);
        addcust.custaddress("INDIA");
        addcust.custcity("HYD");
        addcust.custstate("AP");
        addcust.custpinno("5000074");
        addcust.custtelephoneno("9878990091");

        String email = randomString()+"@gmail.com";
        addcust.custemailid(email);
        addcust.custpassword("abcdef");
        addcust.custsubmit();
        Thread.sleep(3000);

        boolean res = driver.getPageSource().contains("Customer Registered Successfully!");
        if (res=true){
            Assert.assertTrue(true);
        }else{
            captureScreenshot(driver,"addNewCustomer");
            Assert.fail();
        }


    }

}
