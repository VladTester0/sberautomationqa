import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TestHw {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(TestHw.class);


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void verifyWebAppTitle() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().window().getSize();
        driver.manage().window().setSize(new Dimension(800, 600));
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

    @Test
    public void Cookie() {
        driver.get("https://otus.ru/");
        driver.manage().addCookie(new Cookie("Otus1", "Value1"));
        driver.manage().addCookie(new Cookie("Otus2", "Value2"));
        Cookie cookie = new Cookie("Otus3", "Value3");
        driver.manage().addCookie(new Cookie("Otus4", "Value4"));

        logger.info(driver.manage().getCookies());
        logger.info(driver.manage().getCookieNamed("Otus1"));
        driver.manage().deleteCookieNamed("Otus2");
        driver.manage().deleteCookie(cookie);
        driver.manage().deleteAllCookies();
        logger.debug(driver.manage().getCookies().size());
    }

    @After
    public void setDownTwo() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер успешно закрыт");

        }
    }
}