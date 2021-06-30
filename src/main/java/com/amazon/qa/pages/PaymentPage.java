package com.amazon.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class PaymentPage extends TestBase{
	
	@FindBy(xpath="//span[text()='Add Debit/Credit/ATM Card']")
	WebElement selectCardOption;
	
	@FindBy(xpath="//a[text()='Add a credit or debit card']")
	WebElement addCard;
	
	@FindBy(xpath="//div[@class='a-popover-wrapper']")
	WebElement modal;
	
	//cardNumberField
	@FindBy(xpath="//input[@type='tel']")
	WebElement cardNumberField;
	
	@FindBy(xpath=".//input[@class='a-button-input']")
	WebElement submitCardNumber;
	
	@FindBy(xpath="//span[text()='Card number is not correct.']")
	WebElement invalidCardMessage;
	
	@FindBy(xpath="//div[@class='a-spacing-base']")
	WebElement deliveryOption;
	
	
	
	public  PaymentPage()
	{
	PageFactory.initElements(driver, this);
	}
	
	public boolean verefypagePageHeading() throws InterruptedException
	{

		Thread.sleep(5000);
		return deliveryOption.isDisplayed();
	}
	
	public String paymentStep() throws InterruptedException
	{
		selectCardOption.click();
		Thread.sleep(5000);
		
		addCard.click();
		
		Thread.sleep(5000);
		return null;
		
		
	}
	
	public String popupStep(String cno) 
	{
		driver.switchTo().frame(0);
		cardNumberField.click();
		cardNumberField.sendKeys(cno);
		submitCardNumber.click();
		
		
		return cno;
	}
	
	public String invalidCardNumber()
	{
		
		String sr="";
		if(driver.findElements(By.xpath("//span[text()='Card number is not correct.']")).size()<10)
		{
			System.out.println("Invalid Card Number");
			sr=invalidCardMessage.getText();
		}
		else
		{
			System.out.println("Not Found Eror");
		}
		return sr;
	}
}
