package test;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.Globals;
import page.CartPage;
import page.HomePage;
import page.LoginPage;

public class AddItem {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static HomePage homePage;
    private static CartPage cartPage;

    //Before All Tests
    @BeforeClass
    public static void beforeLogin(){
        //Type webdriver filename, path:
        driver = new ChromeDriver();
        loginPage =new LoginPage(driver);
        homePage=new HomePage(driver);
        cartPage=new CartPage(driver);
    }

    //BEfore each test
    @Before
    public void goToLoginPage(){
        //STEP 1: Login first
        loginPage.login("standard_user","secret_sauce");
    }

    @Test
    //Add Random Items to Cart test steps:
    //1. Login to SauceDemo
    //2. Add n random items to cart, record the item name
    //3. Open cart, record the item name
    //4. Compare item name in step 2 and step 3
    public void addRandomItemsToCart(){
            //STEP 2: Add items to cart
            //Define the number of random items needs to be added to cart
            int numberOfItem=3;

            //Make sure if number of item is less than or equal to the total of items in Home Page
            int maxItems=homePage.getInventoryItemSize();
            if (numberOfItem>maxItems){
                Assert.assertFalse("Number of requested unrepeated random number cannot be larger than the total Item",true);
            }
            //Generate the items' indexes randomly, e.g. 1,2,5
            int[] itemIndex=Globals.getUnrepeatedRandomItemIndex(numberOfItem,1,maxItems);

            //Add item to cart and record the name
            List<String> itemNamesInList=homePage.addItemsToCart(itemIndex);

            //STEP 3: Open cart and record the item name in cart
            //Click Cart Icon
            homePage.openCart();
            //Assert that browser is in cart page
            cartPage.assertInCartPage();
            //Record the item name in cart
            List<String> itemNamesInCart=cartPage.getCartItemNames();

            //Task 2
            //STEP 4: Check if items' name in cart are the same with the ones that were truly added
            Assert.assertArrayEquals(itemNamesInList.toArray(),itemNamesInCart.toArray());
 
    }

    //After each test
    @After
    public void clearCache(){
        driver.manage().deleteAllCookies();
    }

    //After all tests
    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}
