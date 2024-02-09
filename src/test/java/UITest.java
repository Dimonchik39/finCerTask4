import elements.LoginPage;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
//import java.util.concurrent.TimeUnit;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;

public class UITest {
    private static final String userEmail = "login";
    private static final String userPassword = "password";
    private static final String email = "login@login.ru";
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void initialization() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    }
    @BeforeEach
    void open() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://gb.ru/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void testNotEmail() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.authorization(userEmail, userPassword);
        Assertions.assertTrue(loginPage.getTextError());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/test/resources/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testNotPassword() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.inputNotPassword(email);
        Assertions.assertEquals("", loginPage.checkFieldPassword());
    }
    @AfterAll
    static void close() {
        driver.quit();
    }

//    @Test
//    void testGBNotEmailLogin() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//        //options.addArguments("--headless");
//        options.addArguments("start-maximized");
//        options.addArguments("--remote-allow-origins=*");
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.get("https://gb.ru/login");
//
//        driver.findElement(By.xpath("/html/body/div[2]/div[7]/div/form/div[1]/input")).sendKeys("login");
//        driver.findElement(By.xpath("/html/body/div[2]/div[7]/div/form/div[2]/input")).sendKeys("password");
//        driver.findElement(By.xpath("/html/body/div[2]/div[7]/div/form/div[4]/input")).click();
//
//        Assertions.assertFalse(driver.findElements(By.xpath("/html/body/div[2]/div[7]/div/form/div[1]/ul")).isEmpty());
//    }
//
//    @Test
//    void testGBWithoutPassword() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
//        //options.addArguments("--headless");
//        options.addArguments("start-maximized");
//        options.addArguments("--remote-allow-origins=*");
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.get("https://gb.ru/login");
//        driver.findElement(By.xpath("/html/body/div[2]/div[7]/div/form/div[1]/input")).sendKeys("login@login.ru");
//        driver.findElement(By.xpath("/html/body/div[2]/div[7]/div/form/div[4]/input")).click();
//
//        Assertions.assertFalse(driver.findElements(By.xpath("/html/body/div[2]/div[7]/div/form/div[2]/ul")).isEmpty());
//
//    }
}
