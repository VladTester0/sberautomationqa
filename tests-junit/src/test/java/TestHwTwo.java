import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class TestHwTwo {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(TestHw.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void webDriverSettings() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://yandex.ru");
        driver.manage().window().maximize();
        String title = driver.getTitle();
        String expected_title = "Яндекс";
        assertEquals(title, expected_title);
        logger.info(title);
    }
    @Test
    public void getNumber() {
        driver.get("https://msk.tele2.ru/shop/number");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.cssSelector("#searchNumber"));
        element.click();
        element.sendKeys("97" + Keys.ENTER);
        WebElement numbers = new WebDriverWait(driver, 3)
                .until(driver -> driver.findElement(By.cssSelector(".product-group")));
        Boolean presence = driver.findElements(By.cssSelector(".phone-number-block")).size() > 0;
        if(presence == true) {
            logger.info("Элементы отображаются корректно");
        }
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер успешно закрыт");
        }
    }
}
