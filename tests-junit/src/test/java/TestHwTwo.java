import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class TestHwTwo extends BaseSetUp {

    @Test
    public void checkingTitleTest() {
        driver.get("https://yandex.ru");
        driver.manage().window().maximize();
        String actual_title = driver.getTitle();
        String expected_title = "Яндекс";
        assertEquals("Wrong title is discovered",expected_title,actual_title);
    }
    @Test
    public void displayingNumbersTest() {
        driver.get("https://msk.tele2.ru/shop/number");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.cssSelector("#searchNumber"));
        element.click();
        element.sendKeys("97" + Keys.ENTER);
        WebElement numbers = new WebDriverWait(driver, 3)
                .until(driver -> driver.findElement(By.cssSelector(".product-group")));
        numbers.isDisplayed();
        Boolean presence = driver.findElements(By.cssSelector(".phone-number-block")).size() > 0;
        if(presence) {
            logger.info("Элементы отображаются корректно");
        }
        else logger.info("Элементы не отображаются корректно");
        element.clear();
        String placeholder = driver.findElement(By.xpath("//span[@class='error-text']")).getText();
        String expected_placeholder = "Введите минимум 2 цифры";
        assertEquals("Error-message is not displayed",expected_placeholder,placeholder);
    }
    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер успешно закрыт");
        }
    }
}
