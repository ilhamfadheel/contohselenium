package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.HomePage;
import page.LoginPage;

public class Login {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static HomePage homePage;

    //Before All Tests
    @BeforeClass
    public static void beforeLogin(){
        //Type webdriver filename, path:
        driver = new ChromeDriver();
        loginPage=new LoginPage(driver);
        homePage=new HomePage(driver);
    }

    //BEfore each test
    // @Before
    // public void beforeLogin(){
    //     //do something
    // }


    // There are positive test and negative test suites in this code:
    // 1. Successfully login: standard_login(), performance_glitch_login()
    // 2. Failed to login: locked_out_user_login(), no_username_login(), 
    //      no_password_login(), not_registered_login()
    @Test
    public void standard_login(){
        //Login with valid credentials
        loginPage.login ("standard_user","secret_sauce");
        //Assert if user can see Home page      
        homePage.assertInHomePage();
    }

    @Test
    public void performance_glitch_login(){
        //Login with account and experience glitch
        loginPage.login ("performance_glitch_user","secret_sauce");
        //Assert if user can see Home page  
        homePage.assertInHomePage();
    }

    @Test
    public void locked_out_user_login(){
        //Login with credentials that is locked out
        loginPage.login ("locked_out_user","secret_sauce");
        //Assert if error message correct
        String expectedErrorMsg="Epic sadface: Sorry, this user has been locked out.";
        loginPage.assertErrorMessage(expectedErrorMsg);
    }

    @Test
    public void no_username_login(){
        //Login with empty username
        loginPage.login ("","secret_sauce");
        //Assert if error message correct
        String expectedErrorMsg="Epic sadface: Username is required";
        loginPage.assertErrorMessage(expectedErrorMsg);
    }

    @Test
    public void no_password_login(){
        //Login with empty password
        loginPage.login ("locked_out_user","");
        //Assert if error message correct
        String expectedErrorMsg="Epic sadface: Password is required";
        loginPage.assertErrorMessage(expectedErrorMsg);
    }

    @Test
    public void not_registered_login(){
        //Login with unregistered credentials
        loginPage.login ("username","password");
        //Assert if error message correct
        String expectedErrorMsg="Epic sadface: Username and password do not match any user in this service";
        loginPage.assertErrorMessage(expectedErrorMsg);
    }

    //After each test
    @After
    public void clearCache(){
        //Delete cookies to logout user
        driver.manage().deleteAllCookies();
    }

    //After all tests
    @AfterClass
    public static void closeBrowser(){
        //Terminate the WebDriver
        driver.quit();
    }

   
}