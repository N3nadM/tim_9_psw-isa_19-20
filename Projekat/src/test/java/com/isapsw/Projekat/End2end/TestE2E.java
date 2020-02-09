package com.isapsw.Projekat.End2end;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class TestE2E {

    private WebDriver browser;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/isapsw/Projekat/End2end/resources/chromedriver.exe");
        browser = new ChromeDriver();

        browser.manage().window().maximize();

        browser.navigate().to("localhost:3000");
    }

    @Test
    public void nalazenjePregleda() throws InterruptedException {


        //pacijent pronalazi termin pregleda i rezervise ga
        browser.findElement(By.id("email")).sendKeys("pacijent@gmail.com");
        browser.findElement(By.id("password")).sendKeys("admin");
        browser.findElement(By.id("submit")).click();

        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.elementToBeClickable(By.id("zakazi")));
        browser.findElement(By.id("zakazi")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("lokacija")));
        browser.findElement(By.id("lokacija")).sendKeys("Zmaj");
        browser.findElement(By.id("pretrazi")).click();

        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("1")));
        browser.findElement(By.id("1")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("date-picker-dialog")));
        browser.findElement(By.id("date-picker-dialog")).sendKeys("04012020");
        browser.findElement(By.id("demo-simple-select")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("Fizijatrijski")));
        browser.findElement(By.id("Fizijatrijski")).click();
        browser.findElement(By.id("pretraga")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("6")));
        browser.findElement(By.id("6")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("01-Apr-2020 07:00:00")));
        browser.findElement(By.id("01-Apr-2020 07:00:00")).click();
        browser.findElement(By.id("zakaziPregled")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("potvrdi")));
        browser.findElement(By.id("potvrdi")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("zatvori")));
        browser.findElement(By.id("zatvori")).click();
        browser.findElement(By.id("dugmeLog")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
        browser.findElement(By.id("logout")).click();


        //pronalazenje sale kod admina
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        browser.findElement(By.id("email")).sendKeys("kristina.radojevic28@gmail.com");
        browser.findElement(By.id("password")).sendKeys("admin");
        browser.findElement(By.id("submit")).click();

        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("password-old")));
        browser.findElement(By.id("password-old")).sendKeys("admin");
        browser.findElement(By.id("password-n")).sendKeys("admin");
        browser.findElement(By.id("password")).sendKeys("admin");
        browser.findElement(By.id("potvrda")).click();

        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("zahtevi")));
        browser.findElement(By.id("zahtevi")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("2020-04-01 07:00:00")));
        browser.findElement(By.id("2020-04-01 07:00:00")).click();

        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.visibilityOfElementLocated(By.id("1")));
        browser.findElement(By.id("1")).click();
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollBy(0,1000)");
        browser.findElement(By.id("nadjiSestru")).click();
        (new WebDriverWait(this.browser, 10L)).until(ExpectedConditions.elementToBeClickable(By.id("sacuvaj")));
        browser.findElement(By.id("sacuvaj")).click();
    }

    @AfterMethod
    public void tearDown(){
    }
}
