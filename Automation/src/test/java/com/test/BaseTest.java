package com.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;
import com.utils.DriverProvider;
import com.utils.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    ReadConfig readconfig=new ReadConfig();
    public static WebDriver driver;

    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chrome") String br)
    {
        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");

        if(br.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
            driver=new ChromeDriver();
        }
        else if(br.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
            driver = new FirefoxDriver();
        }
        else if(br.equals("ie"))
        {
            System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverProvider.seetDriverInstance(driver);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }

    public String randomestring()
    {
        String generatedstring= RandomStringUtils.randomAlphabetic(8);
        return(generatedstring);
    }

    public static String randomeNum() {
        String generatedString2 = RandomStringUtils.randomNumeric(4);
        return (generatedString2);
    }

    public void validateExpectedToActual(String exp, String act){
        logger.info("Expected is: " + exp);
        logger.info("Actual is: " + act);
        Assert.assertEquals(exp, act);
    }
}
