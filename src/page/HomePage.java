package page;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    //Define XPath
    String item_xpath="(//div[@class='inventory_item'])";
    String itemName_xpath="//div[@class='inventory_item_name']";

    //Define locators in Home Page
    By inventoryItem_loc=By.xpath(item_xpath);
    public By cartIcon_loc=By.className("shopping_cart_link");

    //Get locators by index
    //Add to cart button locator
    public By itemAddButton_loc(int index){
        return By.xpath(item_xpath+"["+index+"]"+"//button");
    }
    //Title Name locator
    By itemName_loc(int index){
        return By.xpath(item_xpath+"["+index+"]"+itemName_xpath);
    }

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    //Define Methods that are commonly used

    //Assert if the webdriver is displaying the expected Homepage:
    //1. Page title is PRODUCTS
    //2. Page URL is https://www.saucedemo.com/inventory.html
    //3. There are inventory items displayed in the page.
    public void assertInHomePage(){
        //Define expected values
        String expectedURL="https://www.saucedemo.com/inventory.html";
        String expectedPageTitle="PRODUCTS";

        //Assert URL is as expected
        Assert.assertEquals(this.driver.getCurrentUrl(),expectedURL);
        //Assert Page Title is as expected
        Assert.assertEquals(this.driver.findElement(Header.pageTitle_loc).getText(),expectedPageTitle);
        //Assert Inventory List is not empty
        Assert.assertTrue("There are no Inventory items in the page.",getInventoryItemSize()>8);
    }

    //Get the number of inventory items in Home Page
    public int getInventoryItemSize(){
        return (driver.findElements(inventoryItem_loc).size());
    }

    //Get the name of inventory item by index
    public String getItemTitle(int index){
        return driver.findElement(itemName_loc(index)).getText();
    }

    public List<String> addItemsToCart(int...itemIndeces){
        //Add item to cart and record the added item
        List<String> result=new ArrayList<String>();
        for (int itemIndex: itemIndeces){
            //Record the item name
            result.add(getItemTitle(itemIndex));

            //Click add to cart
            driver.findElement(itemAddButton_loc(itemIndex)).click();
        }
        return result;
    }
}
