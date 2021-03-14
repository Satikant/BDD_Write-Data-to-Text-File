package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LoginStepDefinition{
	public static WebDriver driver;

	@Given("^user is already on Login Page$")
	public void user_is_already_on_Login_Page(){
		 System.setProperty("webdriver.chrome.driver","C:\\qa-login\\Resources\\chromedriver.exe");
		 driver = new ChromeDriver();
	    driver.get("https://qa-fake.herokuapp.com/");
	}

	@When("^title of login page is QA Fake$")
	public void title_of_login_page_is_QA_Fake() {
	    String actTitle=driver.getTitle();
	    System.out.println(actTitle);
	    String expTitle="QA Fake";
	    assertEquals(actTitle, expTitle);
	}

	@Then("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_and(String username, String password)  {
	   driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
	   driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
	}

	@Then("^user checked rember me$")
	public void user_checked_rember_me() {
	   driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	}

	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() {
		 driver.findElement(By.xpath("//button[text()='Login']")).click();
	}

	@Then("^user get the secret string on home screen$")
	public void user_get_the_secret_string_on_home_screen() throws Throwable  {
		Thread.sleep(4000);
		String Key=driver.findElement(By.xpath("//div[@id='result']")).getText();
		System.out.println(Key);
		//String str = driver.getPageSource();
		//System.out.println(str);
	   //driver.findElement(By.xpath("//div[@id='result']")).getAttribute(null);
		
		  String TestFile = "C:\\qa-login\\Resources\\DynamicKey.txt";
		  File FC = new File(TestFile);//Created object of java File class.
		  FC.createNewFile();//Create file.
		  
		  //Writing In to file.
		  //Create Object of java FileWriter and BufferedWriter class.
		  FileWriter FW = new FileWriter(TestFile);
		  BufferedWriter BW = new BufferedWriter(FW);
		  BW.write(Key); //Writing In To File.
		  BW.newLine();//To write next string on new line.
		  BW.write("This Is Second Line."); //Writing In To File.
		  BW.close();
		  
		  //Reading from file.
		  //Create Object of java FileReader and BufferedReader class.
		  FileReader FR = new FileReader(TestFile);
		  BufferedReader BR = new BufferedReader(FR);
		  String Content = "";
		  
		  //Loop to read all lines one by one from file and print It.
		  while((Content = BR.readLine())!= null){
		   System.out.println(Content);
		  }
	}

	@Then("^Close the browser$")
	public void close_the_browser() {
	//driver.quit();
	}

	 

}