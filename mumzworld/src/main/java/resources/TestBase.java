package resources;

import org.openqa.selenium.WebDriver;
import mumzworld.pageobjects.ProductSearchToCheckout;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public ProductSearchToCheckout productSearchToCheckout; 

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("/Users/abdullah.sheikh/eclipse-workspace/mumzworld/src/main/java/resources/configuration.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setup() throws InterruptedException {
        // Initialize the driver through BrowserFactory and set the URL from properties
        driver = BrowserFactory.startBrowser("chrome", prop.getProperty("url"));
        
        productSearchToCheckout = new ProductSearchToCheckout(driver);
        
    }
    

	

	 

    @AfterMethod
    public void closeDriver() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
