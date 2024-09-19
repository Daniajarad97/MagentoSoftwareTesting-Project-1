package magentoSoftwareTestingBoard;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagentoSoftwareTesting extends Parameters {

	@BeforeTest
	public void setup() {

		GeneralSetup();
	}

	@Test(priority = 1, enabled = true)
	public void createAnAccountTest() throws InterruptedException {

		WebElement creatAnAccountButton = driver.findElement(By.linkText("Create an Account"));
		creatAnAccountButton.click();

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
		String actualMessageAsAccount = messageAsWebElement.getText();
		Assert.assertEquals(actualMessageAsAccount, expectedMessageAsAccount);

	}

	@Test(priority = 2, enabled = true)
	public void signUp() {
		driver.get(singOutPage);

		WebElement signOutAsElement = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
		String actualSignOut = signOutAsElement.getText();
		Assert.assertEquals(actualSignOut, expectedSignOut);

	}

	@Test(priority = 3, enabled = true)
	public void signIn() throws InterruptedException {

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
		boolean actualMessageAsSignIn = messageAsSingIn.contains("Welcome");
		Assert.assertEquals(actualMessageAsSignIn, expectedMessageAsSignIn);

	}

	@Test(priority = 4, enabled = true)
	public void addWomenItem() throws InterruptedException {

		WebElement womenSection = driver
				.findElement(By.cssSelector("a[id='ui-id-4'] span[class='ui-menu-icon ui-icon ui-icon-carat-1-e']"));
		womenSection.click();

		WebElement teesSection = driver.findElement(By.xpath("//a[contains(text(),'Tees')]"));
		teesSection.click();

		WebElement itemsContainer = driver.findElement(By.cssSelector(".products.wrapper.grid.products-grid"));
		List<WebElement> items = itemsContainer.findElements(By.tagName("li"));

		// Loop to add first 3 items
		for (int i = 0; i < 3 && i < items.size(); i++) {
			// Store item URL before clicking
			String itemUrl = items.get(i).findElement(By.tagName("a")).getAttribute("href");

			// Open item in a new tab
			((JavascriptExecutor) driver).executeScript("window.open('" + itemUrl + "', '_blank');");

			// Switch to the new tab
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));

			// Select size
			WebElement sizeContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
			List<WebElement> sizes = sizeContainer.findElements(By.tagName("div"));

			if (!sizes.isEmpty()) {
				sizes.get(0).click(); // Select the first available size
			}

			// Select color
			WebElement colorsContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
			List<WebElement> colors = colorsContainer.findElements(By.tagName("div"));

			if (!colors.isEmpty()) {
				colors.get(0).click(); // Select the first available color
			}

			// Add to cart
			WebElement addToCart = driver.findElement(By.cssSelector(".action.tocart.primary"));
			addToCart.click();

			Thread.sleep(2000);

			// Close the current tab
			driver.close();

			// Switch back to the main tab
			driver.switchTo().window(tabs.get(0));

		}

		driver.navigate().refresh();
		Thread.sleep(1000);

		WebElement cartButton = driver.findElement(By.className("counter"));
		cartButton.click();

		WebElement totalItems = driver.findElement(By.cssSelector(".count"));
		String actualItems = totalItems.getText();
		Assert.assertEquals(actualItems, expectedItems);

		WebElement PricesContainer = driver
				.findElement(By.cssSelector("span[data-bind='html: cart().subtotal_excl_tax'] span[class='price']"));
		String actualPrice = PricesContainer.getText();

		List<WebElement> AllPrices = PricesContainer.findElements(By.className("price"));
		for (WebElement priceElement : AllPrices) {
			String priceText = priceElement.getText();

			// Remove the "$" sign and the decimal ".00"
			String priceWithoutDollar = priceText.replace("$", "").split("\\.")[0];

			// Convert the remaining string to an integer
			int price = Integer.parseInt(priceWithoutDollar);

			String expectedPrice = Integer.toString(price);
			Assert.assertEquals(actualPrice, expectedPrice);

		}

	}

	@Test(priority = 5, enabled = true)
	public void addMenItems() {

		WebElement menSection = driver.findElement(By.id("ui-id-5"));
		menSection.click();
		WebElement itemsContainer = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));

		List<WebElement> items = itemsContainer.findElements(By.tagName("li"));
		randItems = rand.nextInt(items.size());
		items.get(randItems).click();

		WebElement sizeContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> sizes = sizeContainer.findElements(By.tagName("div"));
		randItems = rand.nextInt(sizes.size());
		sizes.get(randItems).click();

		WebElement colorsContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> colors = colorsContainer.findElements(By.tagName("div"));

		randItems = rand.nextInt(colors.size());
		colors.get(randItems).click();

		WebElement addToCart = driver.findElement(By.id("product-addtocart-button"));
		addToCart.click();

		String addMenAsElement = driver.findElement(By.className("message-success")).getText();
		boolean actualaddMen = addMenAsElement.contains("You added");
		org.testng.Assert.assertEquals(actualaddMen, expectedaddMen);

	}

	@Test(priority = 6, enabled = true)
	public void addBagsItems() {
	}

}
