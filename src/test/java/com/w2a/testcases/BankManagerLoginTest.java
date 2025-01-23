package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.Testbase;

public class BankManagerLoginTest extends Testbase {
	@Test
	public void LoginAsBankManager() throws InterruptedException {
		log.debug("Inside Login test");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login not succesfull");
		
		log.debug("Login succesful");
	}

}
