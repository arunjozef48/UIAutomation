package com.test;

import com.utils.ReadConfig;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestNotFound extends BaseTest {

    ReadConfig readconfig=new ReadConfig();

    public String baseURL=readconfig.getApplicationURL();


    static Logger log = Logger.getLogger(TestLogin.class.getName());

    @Test
    public void notFound(){
        String expcetedTitle = "Execute Automation";

        driver.get(baseURL);

        WebElement login = driver.findElement(By.xpath("//*[@id=\"cssmenu\"]/ul/li/a/span"));
        login.click();

        WebElement pageText = driver.findElement(By.xpath("/html/body/h1"));
        String actualText = pageText.getText();

        validateExpectedToActual(expcetedTitle,actualText);
    }
}
