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

public class ConfigurateurSteps {

	public static WebDriver driver;

	@Before
	public void beforeScenario() {
		System.setProperty("webdriver.chrome.driver","/Library/Java/JUNIT/chromedriver");
		driver = new ChromeDriver();
		// Seems no more working in last Chrome versions
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

// walid
	@Given("^je suis sur la page de la model S$")
public void je_suis_sur_la_page_de_la_model_S() throws Throwable {
    driver.get("https://www.tesla.com/fr_fr/models");
}

// walid mon gato
@When("^je clique sur sur le bouton commander$")
public void je_clique_sur_sur_le_bouton_commander() throws Throwable {
    WebElement buttonOrder = driver.findElement(By.xpath("//a[@data-button-text-desktop='Commander']"));
	buttonOrder.click();
}

//walid mon gato
@Then("^l'url doit être \"([^\"]*)\"$")
public void l_url_doit_être(String arg1) throws Throwable {
	assertTrue(driver.getCurrentUrl().contains(arg1));
}

// walid mon gato
@Then("^le prix de LOA est de \"([^\"]*)\"$")
public void le_prix_de_LOA_est_de(String arg1) throws Throwable {
	WebElement loa = driver.findElement(By.cssSelector(".finance-type--lease"));
	// System.out.println("==================================");
	// System.out.println(loa.getText());
	// System.out.println("==================================");
	assertTrue(loa.getText().contains(arg1));
}

@Given("^je suis sur la page de configuration de la model S$")
public void je_suis_sur_la_page_de_configuration_de_la_model_S() throws Throwable {
	driver.get("https://www.tesla.com/fr_FR/models/design");
}

@When("^je selectionne le modele \"([^\"]*)\"$")
public void je_selectionne_le_modele(String arg1) throws Throwable {
	WebElement plaid = driver.findElement(By.xpath("//label[@for='$MTS11-Plaid']"));
	WebElement plaidPlus = driver.findElement(By.xpath("//label[@for='$MTS09-Plaid+']"));
	if (arg1 == "PLaid"){
		plaid.click();
	}
	else {
		plaidPlus.click();
	}
}

@Then("^le prix de la LOA est de \"([^\"]*)\"$")
public void le_prix_de_la_LOA_est_de(String arg1) throws Throwable {
	WebElement prixLOA = driver.findElement(By.cssSelector("span.finance-type"));
	assertTrue(prixLOA.getText().contains(arg1));
}

@Then("^l'économie du carburant estimé est de \"([^\"]*)\"$")
public void l_économie_du_carburant_estimé_est_de(String arg1) throws Throwable {
	WebElement economie = driver.findElement(By.cssSelector("div.summary-options div + div div span"));
	assertTrue(economie.getText().contains(arg1));
}

@Then("^le prix total avec achat est de \"([^\"]*)\"$")
public void le_prix_total_avec_achat_est_de(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    // throw new PendingException();
}

@When("^je descends et je selectionne autonomie$")
public void je_descends_et_je_selectionne_autonomie() throws Throwable {
	System.out.println("COUCOU MON GATO");
    System.out.println(driver.getCurrentUrl());
	List<WebElement> goodDiv = driver.findElements(By.cssSelector(".tds-btn--blue"));
    goodDiv.get(1).click();
    
    
}

@Then("^le prix augmente de \"([^\"]*)\"$")
public void le_prix_augmente_de(String arg1) throws Throwable {
    WebElement price = driver.findElement(By.cssSelector("span.finance-type"));
    assertTrue(price.getText().contains("936"));
}

@After
public void afterScenario() {
    driver.quit();
}

}
