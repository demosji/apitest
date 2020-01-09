package com.suiyang.uitest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * goapi测试用例打上标签
 */
public class goapiLableTest {

    public static void main(String[] args) {

        String wdFilePath = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"chromedriver.exe";
        String urlGotest = "https://gotest.hz.netease.com/web/#/home/project/case/edit?projectId=251&moduleid=13159&apiId=295650";

        File file = new File(wdFilePath);
        if (!file.exists()){
            wdFilePath = "chromedriver.exe";
        }
        System.setProperty("webdriver.chrome.driver",wdFilePath);
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(urlGotest);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement openidBtn = webDriver.findElement(By.xpath("//button[contains(@class,\"ivu\")]"));
        openidBtn.click(); //这里要用click,用submit会有问题
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //login
        WebElement corpBtn = webDriver.findElement(By.xpath("//a[contains(@href,\"corp\")]"));
        corpBtn.click();
        WebElement username = webDriver.findElement(By.id("corp_id_for_corpid"));
        WebElement password = webDriver.findElement(By.id("corp_id_for_corppw"));
        WebElement loginBtn = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[5]/form/div[4]/button"));
        username.sendKeys("jisuiyang");
        password.sendKeys("xxx!");
        loginBtn.submit();




    }




}
