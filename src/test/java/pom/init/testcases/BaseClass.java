package pom.init.testcases;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pom.init.utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    ReadConfig readConfig = new ReadConfig();
    public String baseurl=readConfig.getApplicationURL();
    public String username = readConfig.getUserName();
    public String password = readConfig.getPassword();
    public static WebDriver driver;
    public static Logger Logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br){
        Logger = org.apache.log4j.Logger.getLogger("BankProject");
        PropertyConfigurator.configure("Log4j.properties");

       if(br.equals("chrome")){
           WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
       }else if(br.equals("edge")){
           WebDriverManager.edgedriver().setup();
           driver = new EdgeDriver();
       }
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseurl);
        driver.manage().window().maximize();


    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }


    public void captureScreenshot(WebDriver driver, String tname) throws IOException{
        TakesScreenshot ts = (TakesScreenshot)  driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir")+"/Screenshot/"+tname+".png");
        FileUtils.copyFile(source,target);
        System.out.println("Screenshot taken");
    }

    public String randomString(){
        String generatedString1 =   RandomStringUtils.randomAlphabetic(8);
        return generatedString1;
    }

    public String randomNum(){
        String generatedString2 =   RandomStringUtils.randomNumeric(4);
        return generatedString2;
    }
}


