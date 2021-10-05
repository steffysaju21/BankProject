package pom.init.testcases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.init.pageobject.LoginPage;
import pom.init.utilities.XLUtils;

import java.io.IOException;
import java.lang.reflect.Array;

public class TC_loginddt_002 extends BaseClass{
    @Test(dataProvider = "LoginData")
    public void loginDDT(String user,String pwd) throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        Logger.info("username provided");
        lp.setUserName(user);
        Logger.info("password provided");
        lp.setPassword(pwd);
        lp.clickSubmit();

        Thread.sleep(3000);

        if(isAlertPresent()){
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.fail();
            Logger.warn("Login Failed");
        }else{
            Assert.assertTrue(true);
            Logger.info("Login passed");
            lp.clickLogout();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
        }

    }

    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e){
            return false;
        }

    }

    @DataProvider(name = "LoginData")
    Object[][] getData() throws IOException {
        String path =System.getProperty("user.dir")+"/src/test/java/pom/init/testdata/Book1.xlsx";
        int rownum = XLUtils.getRowCount(path,"Sheet1");
        int colcount = XLUtils.getCellCount(path,"Sheet1",1);

        String[][] logindata =new String[rownum][colcount];
        for (int i =1; i<=rownum;i++){
            for(int j =0; j<colcount;j++){
                logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return logindata;
    }
}
