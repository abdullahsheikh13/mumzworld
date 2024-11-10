package resources;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	static WebDriver driver;

	public static WebDriver startBrowser(String browserName, String url) throws InterruptedException {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = WebDriverManager.chromedriver().create(); // Launch Chrome
		} else if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
			driver = WebDriverManager.firefoxdriver().create(); // Launch Firefox
		} else if (browserName.equalsIgnoreCase("ie") || browserName.equalsIgnoreCase("internet explorer")) {
			driver = WebDriverManager.iedriver().create(); // Launch IE
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browserName);
		}

		driver.manage().window().maximize(); // Maximize window
		driver.manage().deleteAllCookies(); // Delete all cookies
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

		driver.get(url); // Navigate to URL
		
		

        
		return driver;
	}

}
