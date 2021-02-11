import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class TestCookies {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(TestCookies.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.MILLISECONDS);
    }

    @Test
    public void cookieSet() {
        driver.get("https://otus.ru");
        driver.manage().addCookie(new Cookie("Otus1", "Value1"));
        driver.manage().addCookie(new Cookie("Otus2", "Value2"));
        Cookie cookie = new Cookie("Otus3", "Value3");
        driver.manage().addCookie(cookie);
        driver.manage().addCookie(new Cookie("Otus4", "Value4"));
        logger.info(driver.manage().getCookies().size());
        logger.info(driver.manage().getCookieNamed("Otus1"));
        driver.manage().deleteCookieNamed("Otus2");
        driver.manage().deleteCookie(cookie);
        driver.manage().deleteAllCookies();
        logger.info(driver.manage().getCookies().size());

    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер успешно закрыт");
        }
    }
}
