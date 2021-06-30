package com.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class SearchPage extends TestBase{
	
	@FindBy(xpath="//*[contains(text(),'Hello, Roopa')]")
	WebElement userName;
	
	@FindBy(xpath="//title[contains(text(), 'Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in')]")
	WebElement pageTitle;

	@FindBy(xpath="//input[starts-with(@aria-label,'Search')]")
	WebElement searchField;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement submitSearch;
	
	@FindBy(xpath="//img[contains(@src ,'https://m.media-amazon.com/images/I/71IPFP9WJIL._AC_UY218_.jpg')]")
	WebElement selectProduct;
	

	
	public SearchPage()
	{
	PageFactory.initElements(driver, this);
	}
	
	public boolean verefypageTitle()
	{
		return pageTitle.isDisplayed();
	}
	
	public boolean verefyCorrectUserName()
	{
		return userName.isDisplayed();

	}
	
	public ProductPage searchProduct(String sr)
	{
		searchField.click();
		searchField.sendKeys(sr);
		submitSearch.click();
		selectProduct.click();
		return new ProductPage();

	}
	
}
