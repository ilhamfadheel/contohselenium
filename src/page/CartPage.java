package page;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;
    String itemName_xpath="(//div[@class='inventory_item_name'])";

    By itemName_loc=By.xpath(itemName_xpath);
    public By itemName_loc(int index){
        return By.xpath(itemName_xpath+"["+index+"]");
    }

    public CartPage(WebDriver driver){
        this.driver=driver;
    }

    //Get the number of inventory items in Cart Page
    public int getInventoryItemSize(){
        return (driver.findElements(itemName_loc).size());
    }

    public void assertInCartPage(){
        //Define expected values
        String expectedURL="https://www.saucedemo.com/cart.html";
        String expectedPageTitle="YOUR CART";

        //Assert URL is as expected
        Assert.assertEquals(this.driver.getCurrentUrl(),expectedURL);
        //Assert Page Title is as expected
        Assert.assertEquals(this.driver.findElement(Header.pageTitle_loc).getText(),expectedPageTitle);
    
    }

    //Get item names in the cart page
    public List<String> getCartItemNames(){
        //Get the item names in cart
        List<String> result=new ArrayList<String>();
        for (int i=1; i<=getInventoryItemSize(); i++){
            //Get the item names using XPath
            result.add(driver.findElement(itemName_loc(i)).getText());
        }
        
        return result;
    }
}
