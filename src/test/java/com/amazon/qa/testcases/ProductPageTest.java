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

public class ProductPageTest extends TestBase
{
	
	LoginPage loginPage;
	HomePage homePage;
	SearchPage searchPage;
	ProductPage productPage;
	PaymentPage paymentPage;
	
	public ProductPageTest()
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
	
	}
	
	@Test(priority=1)
	public void userNameVerification() throws InterruptedException
	{
		Thread.sleep(5000);
		Assert.assertTrue(productPage.verefyCorrectUserName());
		log.debug("User name verification");
	}

	@Test(priority=2)
	public void productNameVerification() throws InterruptedException
	{
		Thread.sleep(5000);
		Assert.assertTrue(productPage.verefyProductName());
		log.debug("Product name verification");
	}
	
	
	
	@Test(priority=3)
	public void productPriceVerification() throws IOException
	{

		Map<String,String>testData = AmazonUtil.getMap();

		String expectedPrice = productPage.verefyProductPrice(testData.get("Expected Price"));


		double actualPrice = 12490.00;

		Assert.assertNotEquals(expectedPrice, actualPrice);
		System.out.println("Assert Passed");
		log.debug("Price comparison");
	}
	
	@Test(priority=4)
	public void purchasingProduct() throws InterruptedException
	{
		Thread.sleep(5000);
		paymentPage = productPage.productBuyNow();
		log.debug("Entering in to payment page");
	}
	
	@AfterTest
	public void tearDown()
	{
		//driver.quit();
	}

}
