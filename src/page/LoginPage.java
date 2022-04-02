package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    //Define locators in Login Page
    By username_loc=By.id("user-name");
    By password_loc=By.id("password");
    By loginButton_loc=By.name("login-button");
    By errorMsg_loc=By.xpath("//div[@class='error-message-container error']");


    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //Login method, adding webdriver as parameter so that other classes can reuse the method
    public void login(String username, String password){
        driver.get("https://www.saucedemo.com/");

        //Get Username and password input field
        WebElement username_elm=this.driver.findElement(this.username_loc);
        WebElement password_elm=this.driver.findElement(this.password_loc);
        
        //Type the username and password into the field
        username_elm.sendKeys(username);
        password_elm.sendKeys(password);

        //Find the login button
        WebElement loginButton=this.driver.findElement(this.loginButton_loc);

        //Click the login button
        loginButton.click();
    }

    // Method to check if the Login error message matches the Expected Error Message
    public void assertErrorMessage(String expectedErrorMsg){
        Assert.assertEquals(driver.findElement(this.errorMsg_loc).getText(),expectedErrorMsg);
    }
    
}
