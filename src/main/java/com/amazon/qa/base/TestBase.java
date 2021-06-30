package com.amazon.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.amazon.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log;
	
	public TestBase()
	{
		 prop = new Properties();
		 try {
			FileInputStream ip = new FileInputStream("C:\\Users\\ROOPANCHANA KS\\eclipse-workspace\\AmazonAutomation\\src\\main\\java\\com\\amazon\\qa\\config\\amazon.properties");
			prop.load(ip);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void initialization() 
	{
		String browserName = prop.getProperty("Browser");
		System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		log = Logger.getLogger("Amazon logger");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITWAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("amazoneurl"));
		
	}
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}

}
