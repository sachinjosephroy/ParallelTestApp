package com.paralleltestapp.qa.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	@Parameters({"OS"})
	public void getBrowser(String OS) throws MalformedURLException {
		DesiredCapabilities cap = null;
		if(OS.equals("Win7")) {
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://192.168.1.240:5566/wd/hub"), cap);
		}
		else if(OS.equals("Win10")) {
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://192.168.1.226:5569/wd/hub"), cap);
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
