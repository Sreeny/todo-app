package com.sreeny.todo.test.web;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TodoControllerTest {

	public static  WebDriver driver;
	
	private static String TODO_URL = "http://localhost:4200/";
	
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
		//driver.close();
    }
	
	@Test
	public void testCreateTodo_success_when_all_data_correct() throws InterruptedException {
		driver.get(TODO_URL+"create");
		String welomeText1 = driver.findElement(By.id("message")).getText();
		
		WebElement taskName = driver.findElement(By.id("taskName"));
		WebElement description = driver.findElement(By.id("description"));
		WebElement dueDate = driver.findElement(By.id("dueDate"));
		WebElement status = driver.findElement(By.id("status"));
		
		WebElement submit = driver.findElement(By.id("submit"));
		
		taskName.sendKeys("My task1 ");
		description.sendKeys("my task description");
		dueDate.sendKeys("2022-01-01T00:00:00");
		new Actions(driver)
        .moveToElement(submit)
        .pause(Duration.ofSeconds(3))
        .click().perform();
		

		String welomeText = driver.findElement(By.id("message")).getText();
		assertTrue( welomeText.contains("Successfully Created Todo"));
		
	}
	
	
	@Test
	public void testCreateTodo_faile_when_all_data_not_provided() throws InterruptedException {
		driver.get(TODO_URL+"create");
		
		WebElement taskName = driver.findElement(By.id("taskName"));
		WebElement description = driver.findElement(By.id("description"));
		WebElement dueDate = driver.findElement(By.id("dueDate"));
		WebElement status = driver.findElement(By.id("status"));
		
		WebElement submit = driver.findElement(By.id("submit"));
		
		taskName.sendKeys("My task1 ");
		description.sendKeys("my task description");
		new Actions(driver)
        .moveToElement(submit)
        .pause(Duration.ofSeconds(3))
        .click().perform();
		
		String dueDateError = driver.findElement(By.id("dueDate.errors")).getText();
		assertTrue( dueDateError.contains("Please Enter task due date"));
		
	}
	
	@Test
	public void testUpdateTodo_success_when_all_data_correct() throws InterruptedException {
		driver.get(TODO_URL+"create");
		
		WebElement idKey = driver.findElement(By.id("id"));
		WebElement taskName = driver.findElement(By.id("taskName"));
		WebElement description = driver.findElement(By.id("description"));
		WebElement dueDate = driver.findElement(By.id("dueDate"));
		WebElement status = driver.findElement(By.id("status"));
		
		WebElement id = driver.findElement(By.xpath("(//span[@name='todoId'])[1]"));
		String idText = id.getText();
		
		String script = "document.getElementById('id').value = '"+idText+"';";
		((JavascriptExecutor) driver).executeScript(script);
		
		driver.get(TODO_URL+"/edit/"+idText);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement submit = driver.findElement(By.id("submit"));
		new Actions(driver)
        .moveToElement(submit)
        .pause(Duration.ofSeconds(3))
        .click().perform();
		
		String updatedText = driver.findElement(By.id("message")).getText();
		assertTrue( updatedText.contains("Successfully Updated Todo"));
		
	}
	
	
	@Test
	public void testUpdateTodo_fail_when_data_in_correct() throws InterruptedException {
		driver.get(TODO_URL+"create");
		
		WebElement idKey = driver.findElement(By.id("id"));
		WebElement taskName = driver.findElement(By.id("taskName"));
		WebElement description = driver.findElement(By.id("description"));
		WebElement dueDate = driver.findElement(By.id("dueDate"));
		WebElement status = driver.findElement(By.id("status"));
		
		WebElement submit = driver.findElement(By.id("submit"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;  

		WebElement id = driver.findElement(By.xpath("(//span[@name='todoId'])[1]"));
		String idText = id.getText();
		String script = "document.getElementsByName('id').item(0).value = '"+idText+"';";
		((JavascriptExecutor) driver).executeScript(script);
		
		//taskName.sendKeys("My task1 ");
		description.sendKeys("my task description updated");
		dueDate.sendKeys("2022-01-01T00:00:00");
		new Actions(driver)
        .moveToElement(submit)
        .pause(Duration.ofSeconds(3))
        .click().perform();
		
		String dueDateError = driver.findElement(By.id("taskName.errors")).getText();
		assertTrue( dueDateError.contains("Please enter task Name."));
		
	}
	
	
	@Test
	public void testGetAll_Todos_for_existing_user() throws InterruptedException {
		driver.get(TODO_URL+"create");
		String message = driver.findElement(By.id("myTodoListHeader")).getText();
		assertTrue( message.contains("My Todo List"));
		
	}
	
	@Test
	public void testRemoveTodo() throws InterruptedException {
		driver.get(TODO_URL+"create");
		driver.manage().window().maximize();
		WebElement id = driver.findElement(By.xpath("(//span[@name='todoId'])[1]"));
		String idText = id.getText();
		
		System.out.println("idText:::"+idText);
		WebElement removeElement1 = driver.findElement(By.xpath("(//button[@name='removeButton'])[1]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", removeElement1);
		
	
		driver.navigate().refresh();
		
		WebElement idAfterDelete = driver.findElement(By.xpath("(//span[@name='todoId'])[1]"));
		System.out.println("idAfterDelete:::"+idAfterDelete);
		assertNotEquals(idAfterDelete.getText(),idText);
	}
	
}
