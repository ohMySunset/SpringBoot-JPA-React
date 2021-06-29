package org.zerock.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {

    @Test
    public void testOpen() throws Exception{
        WebDriver driver = new ChromeDriver();
        driver.get("http://gs25.gsretail.com/gscvs/ko/main");
        Thread.sleep(2000);

        WebElement e1 = driver.findElement(By.cssSelector(".brd_gswsn"));
        e1.click();
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        WebElement e2 = driver.findElement(By.cssSelector(".search_bar"));
        e2.click();
        e2.sendKeys("clio");
        Thread.sleep(2000);

        WebElement e3 = driver.findElement(By.cssSelector(".search_btn"));
        e3.click();


    }
}
