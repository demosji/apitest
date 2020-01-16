package com.suiyang.uitest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class goapiLableAsPaginationTest {

    public static void main(String[] args) {

        String wdFilePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "chromedriver.exe";
        //String urlGotest = "https://gotest.hz.netease.com/web/#/home/project/case?projectId=251&moduleid=10279"; //直接打开客服一级目录页
        String urlGotest = "https://gotest.hz.netease.com/web/#/home/project/case?projectId=251&moduleid=13120";//售后一级目录
        File file = new File(wdFilePath);
        if (!file.exists()) {
            wdFilePath = "chromedriver.exe";
        }
        System.setProperty("webdriver.chrome.driver", wdFilePath);
        //默认启动最大化窗口
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(urlGotest);
        try {
            webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement openidBtn = webDriver.findElement(By.xpath("//button[contains(@class,\"ivu\")]"));
        openidBtn.click(); //这里要用click,用submit会有问题
        try {
            webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**step1.login*/
        WebElement corpBtn = webDriver.findElement(By.xpath("//a[contains(@href,\"corp\")]"));
        corpBtn.click();
        WebElement username = webDriver.findElement(By.id("corp_id_for_corpid"));
        WebElement password = webDriver.findElement(By.id("corp_id_for_corppw"));
        WebElement loginBtn = webDriver.findElement(By.xpath("//*[@id=\"corp\"]/form/div[4]/button"));
        //todo 改成配置文件读取方式
        username.sendKeys("jisuiyang");
        password.sendKeys("");
        loginBtn.submit();

        /**step2.点击页面中需要打标签的域一级路径*/
        webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div/div[1]/div[3]/div/div/ul[12]/li/div/div[1]/span[2]")).click(); //客服
       // webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div/div[1]/div[3]/div/div/ul[6]/li/div/div[1]/span[2]")).click(); //售后

        /**step3.用例分页处理*/
        int pageNum = 12; //需根据实际情况人工设定
        for (int i = 1; i <= pageNum; i++) {
            System.out.println("当前正在执行第"+i+"页");
            try {
                webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<WebElement> apiItemsList = webDriver.findElements(By.xpath("//*[@id='case-index-wrap']//*[@class='ivu-table-cell']//*[@class='action-btn-group']/span[contains(text(),'编辑')]"));
            if (apiItemsList.size() > 0) {
                //遍历每个分类下的接口编辑按钮
                for (int j = 0; j < apiItemsList.size(); j++) {
                    System.out.println("正在执行该页的第"+(j+1)+"个接口");
                    try {
                        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    List<WebElement> apiItemsListNew = webDriver.findElements(By.xpath("//*[@id='case-index-wrap']//*[@class='ivu-table-cell']//*[@class='action-btn-group']/span[contains(text(),'编辑')]"));
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", apiItemsListNew.get(j));
                    apiItemsListNew.get(j).click();
                    List<WebElement> testCaseTagList = webDriver.findElements(By.xpath("//*[@class='case-card f-pr']//*[@title='点击编辑标签']/span[contains(text(),'标签')]"));
                    for (WebElement wet : testCaseTagList) {
                        wet.click();
                        try {
                            webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        webDriver.findElement(By.xpath("//div[@class='ivu-modal-wrap']//li[2]//span[1]")).click();
                        webDriver.findElement(By.xpath("//div[@class='ivu-modal-wrap']//button[@class='ivu-btn ivu-btn-primary ivu-btn-large']/span[1]")).click();
                    }
                    webDriver.navigate().back();
                }
            }
            //向下拉到底//
            ((JavascriptExecutor) webDriver).executeScript("document.documentElement.scrollTop=10000");
            if (i < pageNum) {
                String nextPagePath = "//a[contains(text(),'" + (i + 1) + "')]";
                //webDriver.findElement(By.xpath("//*[@id=\"case-index-wrap\"]/div[2]/div[2]/div/ul/li["+(i+2)+"]")).click();
                webDriver.findElement(By.xpath(nextPagePath)).click();
            }
        }


    }


}
