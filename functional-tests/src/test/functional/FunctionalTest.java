package test.functional;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class FunctionalTest {

	private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","/Library/Java/JUNIT/chromedriver");
		driver = new ChromeDriver();
	    	// Seems no more working in last Chrome versions
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    // Test de la Story #1-homepage (https://trello.com/c/WKTneu9o/1-homepage)
	@Test
    public void testHomepage() throws Exception {
        // get page title
        driver.get("https://www.meetup.com/fr-FR/");
        assertEquals(driver.getTitle(), "Partagez vos passions | Meetup");
        
        // get h1 title
        WebElement h1 = driver.findElement(By.cssSelector(".exploreHome-hero-mainTitle"));
        String h1Content = h1.getText();
        assertEquals(h1Content, "Le monde vous tend les bras");

        // get h1 subtitle
        WebElement subTitle = driver.findElement(By.cssSelector(".exploreHome-hero-subTitle"));
        String subTitleContent = subTitle.getText();
        assertEquals(subTitleContent, "Rejoignez un groupe local pour rencontrer du monde, tester une nouvelle activité ou partager vos passions.");

        // get meta description 
        WebElement metaDescription = driver.findElement(By.cssSelector("meta[name=description]"));
        String metaDescriptionContent = metaDescription.getAttribute("content");
        assertTrue(metaDescriptionContent.contains("Partagez vos passions et faites bouger votre ville"));

        // get button 
        WebElement buttonDescription = driver.findElement(By.cssSelector("#exploreCTA a"));
        String buttonContent = buttonDescription.getAttribute("href");
        String buttonDescriptionContent = buttonDescription.getText();
        assertTrue(buttonContent.contains("https://www.meetup.com/fr-FR/register/"));
        assertEquals(buttonDescriptionContent, "Rejoindre Meetup");

		// TODO
		// To Be Completed By Coders From Coding Factory
    }

    // Test de la Story #2-recherche (https://www.meetup.com/fr-FR/find/outdoors-adventure/)
    @Test
    public void testSearchPage() throws Exception {
        // get page title
        driver.get("https://www.meetup.com/fr-FR/find/outdoors-adventure/");
        assertTrue(driver.getTitle().contains("Nature et aventure"));
        
        // get h1 title
        WebElement h1 = driver.findElement(By.cssSelector(".text--display1"));
        String h1Content = h1.getText();
        assertTrue(h1Content.contains("Nature et aventure"));

        // get navbar
        // System.out.println("++++++++++++++++");
        // System.out.println(driver.findElements(By.cssSelector("#findNavBar")));
        // System.out.println("++++++++++++++++");
        // div #findNavBar exist
        assertTrue(driver.findElements(By.cssSelector("#findNavBar")).size() > 0);

        // check if input
        assertTrue(driver.findElements(By.cssSelector("#mainKeywords")).size() > 0);

        // check if radius search
        assertTrue(driver.findElements(By.cssSelector("#simple-radius")).size() > 0);

        // get if city search
        assertTrue(driver.findElements(By.cssSelector("#simple-location")).size() > 0);

        // contain choice  Groupe & Calendrier
        List<WebElement> groupeCalendrier = driver.findElements(By.cssSelector("#findNavBar .lastUnit ul li"));
        for(WebElement e : groupeCalendrier) {
            String attribu = e.getAttribute("textContent");
            //System.out.println(e.getAttribute("textContent"));
            assertTrue(attribu.contains("Groupes") || attribu.contains("Calendrier"));
        }

        // get choix par défaut : pertinence
        assertTrue(driver.findElement(By.cssSelector("a.selected[data-value=default]")).getAttribute("textContent").toLowerCase().contains("pertinence"));

        // get 4 choix de tri possible
        List<WebElement> fourChoice = driver.findElements(By.cssSelector("#simple-find-order ul li"));
        for(WebElement e : fourChoice) {
            String attribu = e.getAttribute("textContent");
            if (!e.getAttribute("class").contains("display-none")){
                //System.out.println(e.getAttribute("textContent"));
                assertTrue(attribu.contains("pertinence") || attribu.contains("plus récents") || 
                attribu.contains("nombre de membres") || attribu.contains("proximité"));
            }
        }

        // Calendrier
        WebElement calendarClick = driver.findElement(By.cssSelector("#simple-view-selector-event"));
        calendarClick.click();
        WebElement divGroupe = driver.findElement(By.cssSelector("#simple-find-order"));
        WebElement divCalendar = driver.findElement(By.cssSelector("#simple-view"));
        assertTrue(!(divCalendar.getAttribute("class").contains("display-none")) && (divGroupe.getAttribute("class").contains("display-none")));

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
