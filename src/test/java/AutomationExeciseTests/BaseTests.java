package AutomationExeciseTests;

import AutomationExercisePages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.time.Duration;

public abstract class BaseTests {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl = "https://automationexercise.com/";

    protected TopNavPage topNavPage;
    protected PageHelper pageHelper;
    protected NewUserPage newUserPage;
    protected AccountInfoPage accountInfoPage;
    protected AccountCreatedPage accountCreatedPage;
    protected Actions actions;
    protected LoginPage loginPage;
    protected ContactUsPage contactUsPage;
    protected ProductsPage productsPage;
    protected ProductDetailsPage productDetailsPage;
    protected CartPage cartPage;
    protected RecommendedItemsPage recommendedItemsPage;
    protected CheckoutPage checkoutPage;
    protected PaymentPage paymentPage;
    protected BottomNavPage bottomNavPage;


    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        topNavPage = new TopNavPage(driver, wait);
        pageHelper = new PageHelper(driver, wait);
        newUserPage = new NewUserPage(driver, wait);
        accountInfoPage = new AccountInfoPage(driver, wait);
        accountCreatedPage = new AccountCreatedPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        actions = new Actions(driver);
        contactUsPage = new ContactUsPage(driver, wait);
        productsPage = new ProductsPage(driver, wait);
        productDetailsPage = new ProductDetailsPage(driver, wait);
        cartPage = new CartPage(driver, wait);
        recommendedItemsPage = new RecommendedItemsPage(driver, wait);
        checkoutPage = new CheckoutPage(driver, wait);
        paymentPage = new PaymentPage(driver, wait);
        bottomNavPage = new BottomNavPage(driver, wait);
    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get(baseUrl);
    }
    @AfterMethod
    public void afterMethod(){

    }
    @AfterClass
    public void afterClass() throws InterruptedException {

        Thread.sleep(5000);
        driver.quit();
    }
    public void CheckUrl(String route){
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl+route,"Url is incorrect");
    }
    public ExpectedCondition<Boolean> filepresent() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                File f = new File("C:\\Users\\Sejn\\Downloads\\invoice.txt");
                return f.exists();
            }
            public String toString() {
                return "file to be present within the time specified";
            }
        };
    }
}
