package com.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class HomePage extends TestBase  {

	@FindBy(xpath="//title[contains(text(), 'Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in')]")
	WebElement pageTitle;
	@FindBy(xpath="//a[starts-with(@data-nav-role,'signin')]")
	WebElement loginfield;
	
	public HomePage()
	{
	PageFactory.initElements(driver, this);
	}



	public boolean verefypageTitle()
	{
		return pageTitle.isDisplayed();

	}
	
	public LoginPage login()
	{

	loginfield.click();
	return new LoginPage();




	}
	
}
