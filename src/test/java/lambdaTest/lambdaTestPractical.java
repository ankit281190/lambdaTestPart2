package lambdaTest;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
public class lambdaTestPractical {
	
	//WebDriver driver;

	public RemoteWebDriver driver = null;
 String username = "maheshwari-a";
	String accessKey = "QN7yGTHDnKPAKLUs6SqZkcxaO46MbmST5ESWw3EAgtvLMbKKKx";

 
	@BeforeTest
  public void setUp() throws Exception {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("platform", "Windows 10");
	     capabilities.setCapability("browserName", "Chrome");
	    // capabilities.setAcceptInsecureCerts(true);
	     capabilities.setCapability("version", "87.0"); // If this cap isn't specified, it will just get the any available one
      capabilities.setCapability("resolution","1024x768");
      capabilities.setCapability("build", "First Test");
      capabilities.setCapability("name", "Sample Test");
      capabilities.setCapability("network", true); // To enable network logs
      capabilities.setCapability("visual", true); // To enable step by step screenshot
      capabilities.setCapability("video", true); // To enable video recording
      capabilities.setCapability("console", true); // To capture console logs
      
    
      ChromeOptions options = new ChromeOptions();
      options.addArguments("disable-notifications");
      capabilities.setCapability(ChromeOptions.CAPABILITY,options);
  
      try {       
			driver= new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"), capabilities); 
			
      } catch (MalformedURLException e) {
          System.out.println("Invalid grid URL");
      }
  }
	

	@Test(enabled = true)
	public void testScript() throws Exception {
				try {
					
					driver.manage().window().maximize();
					driver.get("https://www.lambdatest.com/automation-demos/");
					driver.findElement(By.cssSelector("#username")).sendKeys("lambda");;
					driver.findElement(By.cssSelector("#password")).sendKeys("lambda123");
					driver.findElement(By.xpath("//form[@id='newapply']/div[3]/button")).click();
					Thread.sleep(2000);
					//Form Fill
					WebDriverWait wait= new WebDriverWait(driver,5);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#developer-name")));
					driver.findElement(By.cssSelector("#developer-name")).sendKeys("ankit.yit@gmail.com");
					Thread.sleep(2000);
					driver.findElement(By.cssSelector("#populate")).click();
			//driver.switchTo().alert().accept();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[@class='text-right mt-15']/span")).click();
				
					
					Thread.sleep(2000);
					
				//Handle radio buttons
					driver.findElement(By.xpath("//input[@value='Once in 3 months']")).click();
					
				//Select customer service, Discounts & Delivery time
					driver.findElement(By.cssSelector("#customer-service")).click();
					driver.findElement(By.cssSelector("#discounts")).click();
					driver.findElement(By.cssSelector("#delivery-time")).click();
					
				//Handle dropdowns
			Select paymentPreference= new Select(driver.findElement(By.cssSelector("#preferred-payment")));
			paymentPreference.selectByVisibleText("Cash on delivery");
							
					
			//Enable rating scale and feedback things
			driver.findElement(By.cssSelector("#tried-ecom")).click();
			
			//xpath of slider
			
			WebElement slider= driver.findElement(By.xpath("//*[@class='disablebar']/div/div/div/div[12]"));
			Actions action= new Actions(driver);
			int width=slider.getSize().width;
			action.dragAndDropBy(slider, width*12, 0).build().perform();
			
			//validation if slide bar is at 9 rating 
			System.out.println(driver.findElement(By.xpath("//*[@class='disablebar']/div/div/div/div[12]")).getAttribute("style").contains("translate(529px, -6px)"));
		//	driver.findElement(By.cssSelector("div.relative.progress_bar.progress0")).click();
			//*[@class='disablebar']/div/div/div/div[12]
			
			Thread.sleep(3000);
			
			//Store Parent window handle before switching
			String parent= driver.getWindowHandle();
			//Open new Browser
			driver.switchTo().newWindow(WindowType.TAB);
			Thread.sleep(1000);
			driver.get("https://www.lambdatest.com/selenium-automation");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@title='Jenkins']")));
			
			//Go back to parent window
			driver.switchTo().window(parent);
			//Upload Image now
			driver.findElement(By.cssSelector("#img")).click();
			Thread.sleep(1000);
			String imageURL= System.getProperty("user.dir")+"\\Resource\\Jenkins.png";
			System.out.println(imageURL);
			String autoIT= System.getProperty("user.dir")+"\\Resource\\fileUpload.exe";
			Thread.sleep(2000);
			ProcessBuilder pb= new ProcessBuilder(autoIT,imageURL);
			{
			pb.start();
			Thread.sleep(4000);
			}
		//	driver.switchTo().alert().accept();
			driver.findElement(By.cssSelector("#submit-button")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='py-50 text-center']/p")).getText().contains("successfully submitted the form"));
			   Thread.sleep(3000);
			   
			
			driver.quit();					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
	}
}
