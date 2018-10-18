package com.paralleltestapp.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.paralleltestapp.qa.pages.LoginPage;

public class Testbase {
	
	public WebDriver driver;
	public LoginPage login;
	
	@BeforeMethod
	public void initializePages() {
		login = new LoginPage(driver);
	}
	
	@BeforeTest
	@Parameters("myBrowser")
	public void getBrowser(String myBrowser) {
		String envName = System.getProperty("os.name");
		System.out.println("Environment Name is: " + envName);
		if(System.getProperty("os.name").equals("Windows 10")) {
			if(myBrowser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Selenium Grid\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(myBrowser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C:\\Selenium Grid\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(myBrowser.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", "C:\\Selenium Grid\\MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
			}
		}
		else if(System.getProperty("os.name").equals("Windows 7")) {
			if(myBrowser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Selenium Grid\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(myBrowser.equalsIgnoreCase("firefox")) {
				System.out.println("Windows 7 system does not have firefox browser installed on it");
			}
			else if(myBrowser.equalsIgnoreCase("edge")) {
				System.out.println("Windows 7 system does not have edge browser installed on it");
			}
		}
		driver.get("http://freecrm.com/index.html");
	}
	
	@AfterMethod
	public void closeBrowser() {
		if(driver != null) {
			driver.quit();
		}
	}

}
