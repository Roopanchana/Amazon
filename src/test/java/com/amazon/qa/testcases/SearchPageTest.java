package com.amazon.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import com.amazon.qa.pages.ProductPage;
import com.amazon.qa.pages.SearchPage;
import com.amazon.qa.util.AmazonUtil;


public class SearchPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	SearchPage searchPage;
	ProductPage productPage;
	
	//FileInputStream fileInput1;
	
	public SearchPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() 
	{
		initialization();
		productPage = new ProductPage();
		searchPage = new SearchPage();
		homePage= new HomePage();
		loginPage= new LoginPage();
		loginPage = homePage.login();
		searchPage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void verifyPageTitle()
	{
		Assert.assertFalse(homePage.verefypageTitle());	
		log.debug("Page title verification");
	}
	
	@Test(priority=2)
	public void veifyUserName() throws InterruptedException
	{
		Thread.sleep(5000);
		Assert.assertTrue(searchPage.verefyCorrectUserName());
		log.debug("User name verification");
	}
	
	@Test(priority=3)
	public void searchItem() throws IOException 
	{

		
		Map<String,String>testData = AmazonUtil.getMap();
		productPage= searchPage.searchProduct(testData.get("Product Name"));
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTb.get(1));
	    log.debug("Select product");	
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
