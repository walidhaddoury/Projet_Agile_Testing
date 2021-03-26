package test.acceptance;

import java.util.concurrent.TimeUnit;
import java.util.List;

import java.lang.*;

import org.junit.Test;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class HomepageSteps {

	public static WebDriver driver;

	@Before
	public void beforeScenario() {
		System.setProperty("webdriver.chrome.driver","/Library/Java/JUNIT/chromedriver");
		driver = new ChromeDriver();
		// Seems no more working in last Chrome versions
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Given("^je suis sur la homepage$")
	public void je_suis_sur_la_homepage() throws Throwable {
		driver.get("https://www.tesla.com/fr_FR/");
	}

	@Then("^le titre doit être \"([^\"]*)\"$")
	public void le_titre_doit_être(String arg1) throws Throwable {
		assertEquals(driver.getTitle(), arg1);
	}

	@Then("^la description contient \"([^\"]*)\"$")
	public void la_description_contient(String arg1) throws Throwable {
		WebElement description = driver.findElement(By.xpath("//meta[@name='description']"));
        String descText = description.getAttribute("content");
		assertEquals(descText, arg1);
	}

	@Then("^le titre h1 doit être \"([^\"]*)\"$")
	public void le_titre_h1_doit_être(String arg1) throws Throwable {
		List<WebElement> h1 = driver.findElements(By.cssSelector("h1"));
		Boolean found = false;
        for(WebElement e : h1) {
            if (e.getAttribute("textContent").contains(arg1)){
				found = true;
				break;
            }
        }
		assertTrue(found);
	}

	// walid
	@When("^je clique sur \"([^\"]*)\"$")
	public void je_clique_sur(String arg1) throws Throwable {
		List <WebElement> titleHomePage = driver.findElements(By.cssSelector("#block-mainheadernavigation ol li"));
		Boolean check = false;
		for (WebElement e : titleHomePage) {
			if (e.getAttribute("textContent").contains(arg1)) {
				check = true;
				break;
			}
		}
		assertEquals(check, true);
	}
	// walid
	@Then("^le lien renvoi vers \"([^\"]*)\"$")
	public void le_lien_renvoi_vers(String arg1) throws Throwable {
		List <WebElement> linkHomePage = driver.findElements(By.cssSelector("#block-mainheadernavigation ol li a"));
		Boolean check = false;
		for (WebElement e : linkHomePage) {
			if (e.getAttribute("href").contains(arg1)) {
				check = true;
				break;
			}
		}
		assertEquals(check, true);
	}

	@When("^je clique sur le burger menu$")
	public void je_clique_sur_le_burger_menu() throws Throwable {
		WebElement burgerButton = driver.findElement(By.cssSelector(".tds-menu-header-main--trigger_icon--placeholder"));
		burgerButton.click();
	}

	@Then("^le burger menu contient le lien \"([^\"]*)\"$")
	public void le_burger_menu_contient_le_lien(String arg1) throws Throwable {
		List <WebElement> linkBugerMenu = driver.findElements(By.cssSelector("#block-hamburgerdesktop ol li"));
		Boolean check = false;
		for (WebElement e : linkBugerMenu){
			if (e.getAttribute("textContent").contains(arg1)) {
				check = true;
				break;
			}
		}
		assertEquals(check, true);
	}



	@After
	public void afterScenario() {
		driver.quit();
	}

}
