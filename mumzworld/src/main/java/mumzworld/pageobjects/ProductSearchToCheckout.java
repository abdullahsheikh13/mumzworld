package mumzworld.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;
import org.testng.Assert;

public class ProductSearchToCheckout {

    WebDriver driver;

    // Generate random data for test
    Random r = new Random();
    String firstname = "QA" + r.nextInt(100);
    String lastname = "test" + r.nextInt(1000);
    String email = "Qtest" + r.nextInt(1000) + "@mailinator.com";

    // Constructor to initialize driver and page elements
    public ProductSearchToCheckout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Initialize all @FindBy elements
    }

    // Define WebDriverWait as a local variable within methods to avoid global usage
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(60));  // Timeout in seconds
    }

    WebDriverWait wait = getWait();

    // Define WebElements
    @FindBy(xpath = "//*[@placeholder='Search for anything?']")
    WebElement search_input;

    @FindBy(xpath = "//*[contains(text(),'Munchkin - Fresh Food Feeder - Teal')]")
    WebElement product_name;

    @FindBy(xpath = "//form//*[contains(text(),'Add to Cart')]")
    WebElement add_to_cart;

    @FindBy(xpath = "//*[@title='View Cart']")
    WebElement view_cart;

    @FindBy(xpath = "//*[contains(@class,'ProductQuantity')]//input")
    WebElement increase_qty;

    @FindBy(xpath = "//*[text()='Proceed to Checkout']")
    WebElement check_out;

    @FindBy(xpath = "//a[text()='Create Account']")
    WebElement create_account;

    @FindBy(xpath = "//*[@placeholder='First name']")
    WebElement first_name;

    @FindBy(xpath = "//*[@placeholder='Last name']")
    WebElement last_name;

    @FindBy(xpath = "//*[@placeholder='Email']")
    WebElement email_input;

    @FindBy(xpath = "//*[@placeholder='Password']")
    WebElement password;

    @FindBy(xpath = "//*[@title='Create Account']")
    WebElement create_account_button;

    @FindBy(xpath = "//*[text()='Secure checkout']")
    WebElement secure_checkout;

    // Method to search for a product
    public void enterProductName(String productName) {
        WebDriverWait wait = getWait();
        
        // Wait until the search input is visible and interact with it
        wait.until(ExpectedConditions.visibilityOf(search_input));
        search_input.sendKeys(productName);
        
        // Wait for the search result to be ready
        search_input.sendKeys(Keys.ENTER);
        
        // Assertion to verify the search input contains the expected product name
        Assert.assertTrue(search_input.getAttribute("value").contains(productName), 
            "The product name in the search field doesn't match the expected value.");
    }

    // Method to add product to the cart
    public void addToCart() {
        
        // Wait for the product to be clickable and click on it
        wait.until(ExpectedConditions.elementToBeClickable(product_name)).click();
        
        // Wait for quantity input to be visible and clear any existing value
        wait.until(ExpectedConditions.visibilityOf(increase_qty));
        increase_qty.clear();
        
        // Send the desired quantity
        increase_qty.sendKeys("5");
        
        // Wait for 'Add to Cart' to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(add_to_cart)).click();
        
        // Wait for 'View Cart' to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(view_cart));
        
        view_cart.click();
        
        // Wait for 'Proceed to Checkout' to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(check_out));
        
        check_out.click();
        
 
    }

    // Method to create a new account
    public void createAccount() {
        // Wait for 'Create Account' link to be clickable and click it
        wait.until(ExpectedConditions.elementToBeClickable(create_account)).click();
        
        // Wait for 'First Name' field and enter random first name
        wait.until(ExpectedConditions.visibilityOf(first_name)).sendKeys(firstname);

        // Wait for 'Last Name' field and enter random last name
        wait.until(ExpectedConditions.visibilityOf(last_name)).sendKeys(lastname);
        
        // Wait for 'Email' field and enter random email
        wait.until(ExpectedConditions.visibilityOf(email_input)).sendKeys(email);
        
        // Wait for 'Password' field and enter password
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("Karachi0!");
        
        // Click on 'Create Account' button
        wait.until(ExpectedConditions.elementToBeClickable(create_account_button)).click();
        
        // Verify that 'Secure Checkout' is displayed after account creation
        Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement(secure_checkout, "Secure checkout")),
            "Secure checkout is not displayed as expected after account creation.");
    }
}
