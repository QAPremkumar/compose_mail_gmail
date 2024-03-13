package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Gmail {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"D:\\Projects\\compose_mail_gmail\\Driverchromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://mail.google.com/mail/u/0/#inbox/FMfcgzGxRxCPkfpkGZjNLlfGVgtScQbV");

		WebElement compose = driver.findElement(By.xpath("//div[@class='aic']/descendant::div[@role='button']"));
		compose.click();

		WebElement subject = driver.findElement(By.xpath("//input[@placeholder='Subject']"));
		subject.sendKeys("Incubyte");

		WebElement messageBody = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
		messageBody.sendKeys("Automation QA test for Incubyte");

	}

}
