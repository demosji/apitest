package com.suiyang.uitest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * goapi测试用例打上标签
 */
public class goapiLableTest {

    public static void main(String[] args) {

        String wdFilePath = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"chromedriver.exe";
        String urlGotest = "https://gotest.hz.netease.com/web/#/home/project/case?projectId=251&moduleid=13159";

        File file = new File(wdFilePath);
        if (!file.exists()){
            wdFilePath = "chromedriver.exe";
        }
        System.setProperty("webdriver.chrome.driver",wdFilePath);
        WebDriver webDriver = new ChromeDriver();
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
        //login
        WebElement corpBtn = webDriver.findElement(By.xpath("//a[contains(@href,\"corp\")]"));
        corpBtn.click();
        WebElement username = webDriver.findElement(By.id("corp_id_for_corpid"));
        WebElement password = webDriver.findElement(By.id("corp_id_for_corppw"));
        WebElement loginBtn = webDriver.findElement(By.xpath("//*[@id=\"corp\"]/form/div[4]/button"));
        //todo 改成配置文件读取方式
        username.sendKeys("jisuiyang");
        password.sendKeys("Loveme0911!");
        loginBtn.submit();
        //定位到售后-api目录
        webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div/div[1]/div[3]/div/div/ul[6]/li/div/div[1]/span[1]/i")).click();
        webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div/div[1]/div[3]/div/div/ul[6]/li/ul[1]/li/div/div[1]/span[1]/i")).click();
//        webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div/div[1]/div[3]/div/div/ul[12]/li/div/div[1]/span[1]/i")).click();
//        webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div/div[1]/div[3]/div/div/ul[12]/li/ul[4]/li/div/div[1]/span[2]")).click();


        try {
            webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //遍历左侧api分类
        List<WebElement> apiCategoryList = webDriver.findElements(By.xpath("//ul[1]//li[1]//*[@class='ivu-tree-children']"));
        System.out.println("共计找到"+apiCategoryList.size()+"个分类类目需要进行更新标签操作");
        int apicount = 0;
        int i =0;
        for (WebElement wbc : apiCategoryList){
            System.out.println("开始更新第"+i+"接口类："+wbc.getText());
            i++;
            wbc.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          //  ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();",wbc);
            List<WebElement> apiItemsList = webDriver.findElements(By.xpath("//*[@id='case-index-wrap']//*[@class='ivu-table-cell']//*[@class='action-btn-group']/span[contains(text(),'编辑')]"));
            System.out.println(apiItemsList.size());
            apicount+=apiItemsList.size();
            if (apiItemsList.size()>0){
                //遍历每个分类下的接口编辑按钮
                for(int j =0; j<apiItemsList.size();j++){
                    List<WebElement> apiItemsListNew = webDriver.findElements(By.xpath("//*[@id='case-index-wrap']//*[@class='ivu-table-cell']//*[@class='action-btn-group']/span[contains(text(),'编辑')]"));
                    ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();",apiItemsListNew.get(j));
                    apiItemsListNew.get(j).click();
                    List<WebElement> testCaseTagList = webDriver.findElements(By.xpath("//*[@class='case-card f-pr']//*[@title='点击编辑标签']"));
                    for (WebElement wet : testCaseTagList){
                        wet.click();
                        try {
                            webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        webDriver.findElement(By.xpath("//div[@class='ivu-modal-wrap']//li[2]//span[1]")).click();
                        webDriver.findElement(By.xpath("//div[@class='ivu-modal-wrap']//button[@class='ivu-btn ivu-btn-primary ivu-btn-large']/span[1]")).click();
                    }
                    webDriver.navigate().back();
                }


            }

        }
        System.out.println("总共需要更新数量为"+apicount);





    }




}
