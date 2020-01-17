package com.qa.webapp.testscripts;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class SpringBootSmokeTest {
	public static WebDriver driver;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	
	
	
	@BeforeClass
	public void reportSettings(){
		Date date = new Date();
		String filename = System.getProperty("user.dir") + "\\ExtentReports\\BG_SmokeTestResults" + date.getTime()
				+ ".html";
		htmlReporter = new ExtentHtmlReporter(filename);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setReportName("BG Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);

		extent.setSystemInfo("Username", "Ravi");
		extent.setSystemInfo("Windows", "10");
		extent.setSystemInfo("Selenium", "3.11");
		extent.setSystemInfo("Java", "1.8");
	}
	
	
	@AfterClass
	public void afterClass(){
		extent.flush();
	}
	
	@AfterMethod
	public void reportTearDown(ITestResult result){
		if(result.getStatus()==result.FAILURE){
			test.log(Status.FAIL, result.getName() +  "       "  +  result.getThrowable());
		}
		
		if(result.getStatus()==result.SUCCESS){
			test.log(Status.PASS, result.getName() + " PASSED ");
		}
		
		if(result.getStatus()==result.SKIP){
			test.log(Status.SKIP, result.getName() + " SKIPPED ");
		}
	}
	
	@Test
	public void LaunchSpringBoot() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		test=extent.createTest("LaunchSpringBoot");
		test.log(Status.INFO, "Test Case Execution started");
		
		driver.get("http://localhost:8888/");
		boolean bgcontent = driver.findElement(By.xpath("//body[text()='Hello blueGreen Vacations']")).isDisplayed();
		System.out.println(bgcontent);
		Thread.sleep(5000);
		driver.quit();
	}
	

}
