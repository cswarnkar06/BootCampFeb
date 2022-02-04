package com.dd.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.dd.base.TestBase;

public class LoginTest extends TestBase {

@Test
public void testLoginAsManager() throws InterruptedException {
	
	
	driver.findElement(By.cssSelector(or.getProperty("bmlbtn"))).click();
	Thread.sleep(3000);
	
}


}
