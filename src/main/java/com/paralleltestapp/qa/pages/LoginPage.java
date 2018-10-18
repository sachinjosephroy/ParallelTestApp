package com.paralleltestapp.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.paralleltestapp.qa.base.Testbase;

public class LoginPage extends Testbase {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "")
	WebElement txtUsername;
	
	@FindBy(xpath = "")
	WebElement txtPassword;
	
	@FindBy(xpath = "")
	WebElement btnLogin;
	
	public String getTitle() {
		return driver.getTitle();
	}

}
