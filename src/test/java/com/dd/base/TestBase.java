
package com.dd.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.velocity.runtime.directive.Parse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;

	@BeforeMethod
	@BeforeSuite
	public void setUp() throws IOException {

		if (driver == null) {

			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			or.load(fis);

		}
		System.out.println(config.getProperty("browser"));
		System.out.println(System.getProperty("user.dir"));
		if (config.getProperty("browser").equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
					+ "\\src\\test\\resources\\executables\\chromedriver_win32\\chromedriver.exe");

			// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\Lenovo\\eclipse-workspace\\DataDrivenFrameWork\\src\\test\\resources\\executables\\chromedriver_win32\\chromedriver.exe");

			driver = new ChromeDriver();

		} else if (config.getProperty("browser").equals("firefox")) {

			// System.setProperty("webriver.geko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver_win32\\chromedriver.exe");
			driver = new FirefoxDriver();

		}

		else if (config.getProperty("browser").equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			

		}
		driver.get(config.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);

	}

	@AfterMethod
	@AfterSuite
	public void tearDown() {

		if (driver != null)
			driver.quit();

	}

}
