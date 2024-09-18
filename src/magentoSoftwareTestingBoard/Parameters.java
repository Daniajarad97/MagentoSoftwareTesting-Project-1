package magentoSoftwareTestingBoard;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String URLSite = "https://magento.softwaretestingboard.com/";
	String singOutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String password = "12345678#Test";
	String emailAddressToSignInPage = "";

	String[] firs_tNames = { "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Helen", "Ivan", "Judy",
			"Kathy", "Leo", "Mona", "Nina", "Oscar" };
	String[] last_Names = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez",
			"Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson" };
	String[] email_Address = { "@Gmail.com", "@Yahoo.com", "@Outlook.com" };

	int randItems = rand.nextInt();
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

	String expectedMessageAsAccount = "Thank you for registering with Main Website Store.";
	String expectedSignOut = "You are signed out";
	String expectedItems = "3";
	boolean expectedMessageAsSignIn = true;
	boolean expectedaddMen = true;

	public void GeneralSetup() {

		driver.get(URLSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

}
