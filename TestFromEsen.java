package test;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestFromEsen {
	WebDriver d;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:/seleniumDependancy/drivers/chromedriver.exe");
		d = new ChromeDriver();

	}

	@BeforeMethod
	public void get() {
		d.get("http://opensource.demo.orangehrmlive.com");
	}

	@Test
	public void linkCheck() {
		int i=0;
		List<WebElement> link = d.findElements(By.xpath("//a"));
		for (WebElement link1 : link) {
			// System.out.println(link1);
			link1.click();
			
			String curTab = d.getWindowHandle();
			Set<String> windows = d.getWindowHandles();
			 i=windows.size()-1;
			System.out.println(i);
				
			}
		

		Assert.assertTrue(i==5);
	}

	@Test
	public void loginIn() throws InterruptedException {
		Thread.sleep(3000);
		WebElement username = d.findElement(By.id("txtUsername"));
		WebElement password = d.findElement(By.id("txtPassword"));
		WebElement login = d.findElement(By.id("btnLogin"));
		username.sendKeys("Admin");
		Thread.sleep(3000);
		password.sendKeys("admin");
		login.click();
		d.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b")).click();
		d.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys("John Smith");
		d.findElement(By.xpath("//input[@id='searchBtn']")).click();
		String name=d.findElement(By.xpath("(//td[@class='left']/a)[2]")).getText();
		//System.out.println(name);
		String last= d.findElement(By.xpath("(//td[@class='left']/a)[3]")).getText();
		//System.out.println(last);
		String both=name+" "+last;
		String actually="John Smith";
		Assert.assertEquals(both,actually);;
}
	@AfterClass
	public void close() {
	d.quit();	
	}
	}
