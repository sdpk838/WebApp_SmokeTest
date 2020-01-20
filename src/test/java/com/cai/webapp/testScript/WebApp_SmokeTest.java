package com.cai.webapp.testScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebApp_SmokeTest {
	
	public static WebDriver driver;
	
	@Test
	public void Launch() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://localhost:8888/");
		boolean bgcontent = driver.findElement(By.xpath("//body[text()='Hello blueGreen Vacations']")).isDisplayed();
		System.out.println(bgcontent);
		Thread.sleep(5000);
		driver.quit();
	}
}
