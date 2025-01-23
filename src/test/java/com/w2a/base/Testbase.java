package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.w2a.utilities.ExcelReader;

public class Testbase {
	
/*WebDriver - done
 * Properties - done
 * Logs - log4j jar file in pom, .log file (manual logs in application and automation logs in Selenium), log4j properties(Standard file by provided by Apache as log4j appenders ), logger class
 *  
 * 
 * 
 * 
 */
	
	public static WebDriver driver;
	public static Properties config =  new Properties();
	public static Properties OR =  new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	@BeforeSuite
	public void setUp() {
		
		if(driver==null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("firefox")) {
				driver = new FirefoxDriver();
			}else if(config.getProperty("browser").equals("chrome")) {
				driver = new ChromeDriver();
				log.debug("Chrome Launched");
			}else if(config.getProperty("browser").equals("ie")) {
					driver = new InternetExplorerDriver();
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to "+config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("Duration"))));
			wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			
		}

	}
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
		driver.quit();
	}
		log.debug("test Executed !!!");
	}

}
