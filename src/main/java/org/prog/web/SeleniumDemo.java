package org.prog.web;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static javax.swing.UIManager.get;

public class SeleniumDemo {

    private static final String COOKIES_LINK = "//a[contains(@href,'technologies/cookies')]" ;

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("https://google.com/");
            List<WebElement> cookiesLink = driver.findElements(By.xpath(COOKIES_LINK));
            if (!cookiesLink.isEmpty()){
                driver.findElements(By.tagName("button"))
                        .get(4)
                        .click();
            }
            driver.get("https://allo.ua/");
            WebElement searchInput = driver.findElement(By.id("search-form__input"));
            searchInput.sendKeys("iPhone 16");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10l));
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",submitButton);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-card")));

    //        List<WebElement> productCode = driver.findElements(By.className("p-view__header-sku__code"));

            List<WebElement> productTitles = driver.findElements(By.cssSelector("product-card__title"));
            List<WebElement> productPrices = driver.findElements(By.className("v-pb"));

     //           WebElement firstCodProduct = driver.findElements(By.className("p-view__header-sku__code")).get(0);


                WebElement firstProduct = driver.findElements(By.className("product-card__title")).get(0);
                WebElement firstPrice = driver.findElements(By.className("v-pb")).get(0);

    //            String code = firstCodProduct.getText();
                String title = firstProduct.getText();
                String price = firstPrice.getText();

     //           System.out.println("ProductCode:" + code);
                System.out.println("Phone: " + title + ", price: " + price);



            System.out.println("done!");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
