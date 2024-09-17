package magentoSoftwareTestingBoard;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagentoSoftwareTesting {

	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String URLSite = "https://magento.softwaretestingboard.com/";
	String singOutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String password = "12345678#Test";
	String emailAddressToSignInPage = "";

	@BeforeTest
	public void setup() {

		driver.get(URLSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 1, enabled = true)
	public void createAnAccountTest() throws InterruptedException {

		WebElement creatAnAccountButton = driver.findElement(By.linkText("Create an Account"));
		creatAnAccountButton.click();

		String[] firs_tNames = { "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Helen", "Ivan", "Judy",
				"Kathy", "Leo", "Mona", "Nina", "Oscar" };
		String[] last_Names = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
				"Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson" };
		String[] email_Address = { "@Gmail.com", "@Yahoo.com", "@Outlook.com" };

		int randNamber = rand.nextInt(444);
		int randomIndexForFirstName = rand.nextInt(firs_tNames.length);
		int randomIndexForLastName = rand.nextInt(last_Names.length);
		int randomIndexForEmail = rand.nextInt(email_Address.length);
		String randFirstName = firs_tNames[randomIndexForFirstName];
		String randLastName = last_Names[randomIndexForLastName];
		String domanEmail = email_Address[randomIndexForEmail];
		String firstName = "firstname";
		String lastName = "lastname";
		String emailAddress = "email_address";
		String passwordInput = "password";
		String passwordConfirmation = "password-confirmation";

		WebElement firstNameButtom = driver.findElement(By.id(firstName));
		WebElement lastNameButton = driver.findElement(By.id(lastName));
		WebElement emailButton = driver.findElement(By.id(emailAddress));
		WebElement passwordButton = driver.findElement(By.id(passwordInput));
		WebElement passwordConfirmationButton = driver.findElement(By.id(passwordConfirmation));

		firstNameButtom.sendKeys(randFirstName);
		lastNameButton.sendKeys(randLastName);
		emailButton.sendKeys(randFirstName + randLastName + randNamber + domanEmail);
		emailAddressToSignInPage = randFirstName + randLastName + randNamber + domanEmail;
		passwordButton.sendKeys(password);
		passwordConfirmationButton.sendKeys(password);

		WebElement submitButton = driver.findElement(By.cssSelector("button[title='Create an Account']"));
		submitButton.click();

		Thread.sleep(1000);

		WebElement messageAsWebElement = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		String actualMessage = messageAsWebElement.getText();
		String expectedMessage = "Thank you for registering with Main Website Store.";
		Assert.assertEquals(actualMessage, expectedMessage);

	}

	@Test(priority = 2, enabled = true)
	public void signUp() {
		driver.get(singOutPage);

		WebElement signOutAsElement = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
		String actualSignOut = signOutAsElement.getText();
		String expectedSignOut = "You are signed out";
		Assert.assertEquals(actualSignOut, expectedSignOut);

	}

	@Test(priority = 3, enabled = true)
	public void login() throws InterruptedException {

		WebElement loginButton = driver.findElement(By.cssSelector("div[class='panel header'] li[data-label='or'] a"));
		loginButton.click();

		WebElement emailInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.id("pass"));
		WebElement signInButton = driver.findElement(By.id("send2"));

		emailInput.sendKeys(emailAddressToSignInPage);
		passwordInput.sendKeys(password);
		signInButton.click();

		Thread.sleep(1000);
		
		String messageAsSingIn = driver.findElement(By.cssSelector("div[class='panel header'] span[class='logged-in']"))
				.getText();
		boolean actualMessage = messageAsSingIn.contains("Welcome");
		boolean expectedMessage = true;
		Assert.assertEquals(actualMessage, expectedMessage);

	}

}
