import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class TestWaits {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(TestHw.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void testBootstrap() throws InterruptedException {
        final String URL = "https://ng-bootstrap.github.io/#/components/alert/examples";
        final String ERROR_MESSAGE = "Messages are equal";
        String button = "//button[contains(text(),'Change message')]";
        String alertTextBefore;
        String alertTextAfter;

        driver.get(URL);
        logger.info(String.format("Открыта страница %s", URL));
        WebElement element = driver.findElement(By.xpath(button));
        logger.info("Кнопка найдена");

        alertTextBefore = getAlertText(element);
        logger.info(String.format("Alert text %s", alertTextBefore));

        logger.info("Начато ожидание");
        Thread.sleep(1500);
        logger.info("Ожидание закончено");


        alertTextAfter = getAlertText(element);
        logger.info(String.format("Alert text %s", alertTextAfter));

        Assert.assertNotEquals(alertTextBefore, alertTextAfter, ERROR_MESSAGE);

    }

    private String getAlertText(WebElement element) {
        String alert = "//ngb-alert[contains(text(), 'Message successfully changed')]";
        element.click();
        WebElement alertBox = driver.findElement(By.xpath(alert));
        new WebDriverWait(driver, 4).until(visibilityOf(alertBox));  //ждём появления бокса
        return alertBox.getText();
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер успешно закрыт");
        }

    }
}
