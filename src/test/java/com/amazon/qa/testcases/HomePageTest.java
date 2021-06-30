package com.amazon.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;



	public HomePageTest()
	{
	super();
	}
	
	@BeforeTest
	public void setUp()
	{
		initialization();
		homePage= new HomePage();
	}
	
	
	@Test(priority=1)
	public void PageTitleVerification()
	{
		Assert.assertFalse(homePage.verefypageTitle());
		log.debug("Page title verification");

	}
	
	
	@Test(priority=2)
	public void loginClick() 
	{

		loginPage = homePage.login();
		log.debug("Login to Aamazon");
	}

	
	@AfterTest
	public void tearDown()
	{
	//driver.quit();
	}



}
