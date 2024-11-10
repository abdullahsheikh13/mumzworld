package mumzworld.tests;


import org.testng.annotations.Test;
import resources.TestBase;

public class ProductSearchToCheckoutTest extends TestBase {

	
	
	 @Test(priority = 0)
	 public void verify_ProductSearchToCheckoutTest() throws Exception {
		// Perform actions and assertions for the product search to checkout scenario
		productSearchToCheckout.enterProductName("feeder");
		
		// Add product to the cart, increase quantity and checkout
		productSearchToCheckout.addToCart();
		
		// Create an Account
		productSearchToCheckout.createAccount();
					 
	
	}

}
