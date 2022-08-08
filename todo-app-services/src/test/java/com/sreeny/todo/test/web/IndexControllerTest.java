package com.sreeny.todo.test.web;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IndexControllerTest {

	public static  WebDriver driver;
	
	private static String TODO_URL = "http://localhost:8080/todo/";
	
	@BeforeTest
    public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sreeny.chintha\\Downloads\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(65));
		driver.manage().window().maximize();
		
        
    }
	
	@AfterTest
    public void cleanUp() {
		driver.close();
    }
	
	@Test
	public void testLogin_success_when_username_password_correct() throws InterruptedException {
		driver.get(TODO_URL);
		
		WebElement userName = driver.findElement(By.id("userName"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.id("submit"));
		
		userName.sendKeys("Sreeny");
		password.sendKeys("password");
		new Actions(driver)
        .moveToElement(submit)
        .pause(Duration.ofSeconds(3))
        .click().perform();
		
		assertEquals(driver.getCurrentUrl(), TODO_URL+"login");
		String welomeText = driver.findElement(By.id("welcomeMessage")).getText();
		assertTrue( welomeText.contains("Welcome Sreeny!!!"));
		
	}
	
	@Test
	public void testLogin_fail_when_username_password_incorrect() throws InterruptedException {
		driver.get(TODO_URL);
		
		WebElement userName = driver.findElement(By.id("userName"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.id("submit"));
		
		userName.sendKeys("Sreeny123");
		password.sendKeys("password");
		new Actions(driver)
        .moveToElement(submit)
        .pause(Duration.ofSeconds(3))
        .click().perform();
		
		String errorMessage = driver.findElement(By.id("errorMessage")).getText();
		assertTrue( errorMessage.contains("Invalid Username or password"));
		
	}
	
	@Test
	public void testLogin_fail_when_username_password_blank() throws InterruptedException {
		driver.get(TODO_URL);
		
		WebElement userName = driver.findElement(By.id("userName"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.id("submit"));
		
		userName.sendKeys("");
		password.sendKeys("");
		new Actions(driver)
        .moveToElement(submit)
        .pause(Duration.ofSeconds(3))
        .click().perform();
		
		String usernameError = driver.findElement(By.id("userName.errors")).getText();
		assertTrue( usernameError.contains("Please enter userName."));
		
		String passwordError = driver.findElement(By.id("password.errors")).getText();
		assertTrue( passwordError.contains("Please enter password."));
		
	}
	
	
	
}
