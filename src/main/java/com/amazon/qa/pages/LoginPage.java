package com.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(xpath="//title[contains(text(), 'Amazon Sign In')]")
	WebElement pageTitle;

	@FindBy(xpath="//input[starts-with(@name,'email')]")
	WebElement username;

	@FindBy(xpath="//input[starts-with(@class,'a-button-input')]")
	WebElement submitButton;

	@FindBy(xpath="//input[starts-with(@name,'password')]")
	WebElement password;



	@FindBy(xpath="//input[starts-with(@class,'a-button-input')]")
	WebElement submitButton1;



	public LoginPage ()
	{
	PageFactory.initElements(driver, this);

	}
	
	public boolean verefypageTitle()
	{
		return pageTitle.isDisplayed();

	}
	
	public SearchPage login(String un, String pwd ) 
	{
	username.sendKeys(un);
	submitButton.click();
	password.sendKeys(pwd);
	submitButton1.click();
	
	return new SearchPage();



	}

}
