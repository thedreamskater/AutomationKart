package com.automationkart.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	File file=null;
	Properties prop;
	WebDriver driver=null;
	
	@BeforeSuite
	public void loadProperty()
	{
		try 
		{
			file = new File("src/main/resources/configuration.config");
			Boolean checkFile=file.exists();
			if(checkFile)
			{
				prop= new Properties();
				FileInputStream fis= new FileInputStream("src/main/resources/configuration.config");
				prop.load(fis);
				System.out.println("File loaded Successfully");
			}
			else
			{
				System.out.println("File not loaded");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Config File not Loaded");
		}
	}
	
	@Test
	public void browserLaunch()
	{
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
				
	}

}
