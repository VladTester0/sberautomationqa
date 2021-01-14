import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestHw {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(TestHw.class);

 /*   @Test
    public void log(){
        logger.error("Я не информационный лог");
    }
 */

   @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }
 /*   @Test
    public void openPage() {
      driver.get("https://otus.ru/");
      logger.info("Открыта страница OTUS");
    }
 */
    @Test
    public void verifyWebAppTitle() {
     WebDriver driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     driver.manage().window().maximize();
     driver.get("https://otus.ru/");
     logger.info("Страница OTUS успешно открыта");
     String title = driver.getTitle();
     System.out.println("Title is " + title);
     String expected_title = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
     Assert.assertEquals(title, expected_title);
     logger.info("Заголовки совпадают");
      }
    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер успешно закрыт");
        }
    }
}
