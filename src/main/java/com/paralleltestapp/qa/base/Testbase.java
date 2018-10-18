package com.paralleltestapp.qa.base;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.paralleltestapp.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Testbase {
	
	public WebDriver driver;
	public LoginPage login;
	public ExtentReports extent;
	public ExtentTest test;
	
	String extentReportPath = "C:\\ExtentReports From Win10\\ParallelTestAppReport.html";
	String extentConfigPath = "C:\\ExtentReports From Win10\\extent-config.xml";
	
	@BeforeSuite
	public void setUpSuite() {
		extent = new ExtentReports(extentReportPath);
		extent.loadConfig(new File(extentConfigPath));
	}
	
	@BeforeMethod
	public void initializePages(Method method) {
		test = extent.startTest(this.getClass().getSimpleName() + " :: " + method.getName(), method.getName());
		test.assignAuthor("Sachin Roy");
		test.assignCategory("Functional Test");
		login = new LoginPage(driver);
	}
	
	@BeforeTest
	@Parameters({"myBrowser", "OS", "remoteURL"})
	public void getBrowser(String myBrowser, String OS, String remoteURL) throws MalformedURLException {
		DesiredCapabilities cap = null;
		if(remoteURL.equals("http://192.168.1.240:5566/wd/hub")) {
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://192.168.1.240:5566/wd/hub"), cap);
		}
		else if(remoteURL.equals("http://192.168.1.226:5569/wd/hub")) {
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://192.168.1.226:5569/wd/hub"), cap);
		}
		else if(remoteURL.equals("http://192.168.1.226:5568/wd/hub")) {
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WIN10);
			driver = new RemoteWebDriver(new URL("http://192.168.1.226:5568/wd/hub"), cap);
		}
		else if(remoteURL.equals("http://192.168.1.240:5567/wd/hub")) {
			cap = DesiredCapabilities.internetExplorer();
			cap.setBrowserName("ie");
			cap.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL("http://192.168.1.240:5567/wd/hub"), cap);
		}
		driver.get("http://freecrm.com/index.html");
	}
	
	@AfterMethod
	public void closeBrowser(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Testing failed");
			extent.endTest(test);
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Testing passed");
			extent.endTest(test);
		}
		if(driver != null) {
			driver.quit();
		}
	}
	
	@AfterSuite
	public void tearDownSuite() {
		extent.flush();
		extent.close();
	}

}
