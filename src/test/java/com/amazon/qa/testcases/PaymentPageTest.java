package com.amazon.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import com.amazon.qa.pages.PaymentPage;
import com.amazon.qa.pages.ProductPage;
import com.amazon.qa.pages.SearchPage;
import com.amazon.qa.util.AmazonUtil;

public class PaymentPageTest extends TestBase
{
	LoginPage loginPage;
	HomePage homePage;
	SearchPage searchPage;
	ProductPage productPage;
	PaymentPage paymentPage;
	
	public PaymentPageTest()
	{
		super();
	}
	
	@BeforeTest
	public void setUp() throws InterruptedException
	{
		initialization();
		homePage= new HomePage();
		loginPage= new LoginPage();
		productPage = new ProductPage();
		searchPage = new SearchPage();
		paymentPage= new PaymentPage();
		
		loginPage = homePage.login();
		searchPage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		
		Map<String, String> testData;
		try {
			testData = AmazonUtil.getMap();
			productPage= searchPage.searchProduct(testData.get("Product Name"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTb.get(1));
	    
	    Thread.sleep(5000);
		paymentPage = productPage.productBuyNow();
	}
	
	@Test(priority=1)
	public void paymentProcess() throws IOException, InterruptedException 
	{
		paymentPage.paymentStep();
		log.debug("Payment process");
	}
	
	@Test(priority=2)
	public void popupModalWindow() throws IOException 
	{
		
		driver.switchTo().frame(0);
		Map<String,String>testData = AmazonUtil.getMap();
		paymentPage.popupStep(testData.get("Card Number"));
		log.debug("Entering Card Number");
	}
	
	@Test(priority=3)
	public void invalCard()
	{
		String message=paymentPage.invalidCardNumber();
		Assert.assertEquals(message, "Card number is not correct.","Valid Number");
		log.debug("Card validation");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
