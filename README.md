mumzworld
This project automates a typical e-commerce workflow on the Mumzworld webstore, covering essential steps from product search to user registration. The workflow includes searching for a product, adding it to the shopping cart, adjusting the quantity, and proceeding to checkout with a new user registration.

Scenario The automated test case performs the following actions:

Open the Mumzworld Webstore: Launches the browser and navigates to the Mumzworld homepage. Search for a Product: Searches for a specific product based on a given name. Add Product to the Bag: Adds the selected product to the shopping cart. View Bag: Navigates to the shopping bag page. Update Quantity: Increases the quantity of the selected product to 5. Proceed to Checkout: Initiates the checkout process. Register a New User: Completes a registration form for a new user to proceed with checkout.

Technologies Used:

TestNG 7.10.2: For test management and reporting. Selenium-Java 4.26.0: To interact with web elements and automate the browser. ExtentReports 5.1.2: For generating detailed and visually appealing test reports. Maven Surefire Plugin 3.0.0-M5: To run TestNG tests from Maven build.

Setup and Installation Clone the Repository:

**git clone "repository-url"

Pre-requisites:

Java 11 or higher Maven 3.6 or higher Internet access for Maven dependency downloads
