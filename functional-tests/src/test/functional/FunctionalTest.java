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

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    @Test
    public void testFicheMeetup() throws Exception {
        driver.get("https://www.meetup.com/fr-FR/promenades-et-randonnees/?_locale=fr-FR");

        // Controle si le titre existe et est cliquable
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement h1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1.groupHomeHeader-groupName")));
        assertTrue(h1 instanceof WebElement);
        // Controle si le lieu existe
        assertTrue(driver.findElements(By.cssSelector("ul.organizer-city a span")).size() > 0);
        // Controle si le total de membre existe
        assertTrue(driver.findElements(By.cssSelector("a.groupHomeHeaderInfo-memberLink span")).size() > 0);
        // Controle si l'organisateur existe
        assertTrue(driver.findElements(By.cssSelector("a.orgInfo-name-superGroup span + span")).size() > 0);
        // Controle si le bouton rejoindre le groupe existe
        assertTrue(driver.findElements(By.cssSelector("a#actionButtonLink")).size() > 0);
        // Controle si la photo de présentation existe
        assertTrue(driver.findElements(By.cssSelector("div.groupHomeHeader-banner")).size() > 0);

        List <WebElement> bandereauOnglet = driver.findElements(By.cssSelector(".groupAnchorBar div div div div div div ul li"));
        for(WebElement menus : bandereauOnglet){
            assertTrue(menus.getText().contains("À propos") || menus.getText().contains("Événements") || 
                       menus.getText().contains("Membres") || menus.getText().contains("Photos") || 
                       menus.getText().contains("Discussions") || menus.getText().contains("Plus"));
        }

        WebElement joinButton = driver.findElement(By.cssSelector("#actionButtonLink"));
        joinButton.click();

        WebElement logFacebook = driver.findElement(By.cssSelector(".signUpModal-facebook"));
        String fbRedir = logFacebook.getAttribute("href");
        assertTrue(fbRedir.contains("https://www.facebook.com/")); // Vérif du lien de connexion via Facebook

        WebElement logGoogle = driver.findElement(By.cssSelector(".signUpModal-google"));
        String ggRedir = logGoogle.getAttribute("href");
        assertTrue(ggRedir.contains("https://accounts.google.com/o/oauth2/"));

        WebElement logMeetup = driver.findElement(By.cssSelector("a.link.display--inlineBlock"));
        String meetRedir = logMeetup.getAttribute("href");
        assertTrue(meetRedir.contains("https://www.meetup.com/fr-FR/login/"));

        WebElement signup = driver.findElement(By.cssSelector(".signUpModal-email"));
        String signupRedir = signup.getAttribute("href");
        assertTrue(signupRedir.contains("/register/?method=email"));

        WebElement closeButton = driver.findElement(By.cssSelector(".modal-closeButton"));
        closeButton.click();

        WebElement contactButton = driver.findElement(By.cssSelector(".orgInfo-message"));
        contactButton.click();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("https://secure.meetup.com/fr-FR/login/"));
    }

    @Test
    public void testJobs() throws Exception{
        driver.get("https://www.meetup.com/fr-FR/careers/");

        String topPunchline = driver.findElement(By.cssSelector(".brandHero-slogan")).getText();
        assertEquals(topPunchline, "Join our team, find your people");

        String opportunitiesBtn = driver.findElement(By.cssSelector("._brandHero-module_brandHeroButton__37glx")).getText();
        assertEquals(opportunitiesBtn, "Explore Opportunities");

        List <WebElement> postOffers = driver.findElements(By.cssSelector("._ourTeams-module_ourTeams-list__1p-ci ul li"));
        WebElement linkOpportunities = driver.findElement(By.cssSelector("._stayInTouch-module_StayInTouch__3nOZP div span a"));
        assertEquals(linkOpportunities.getAttribute("href"), "https://www.meetup.com/careers/all-opportunities");
        assertTrue(postOffers.size() == 9);

        String perksPunchline = driver.findElement(By.cssSelector(".perksAndBenefits-title")).getText();
        List <WebElement> perksImgs = driver.findElements(By.cssSelector("._perksAndBenefits-module_perksAndBenefits__3LekN ul li"));
        assertTrue(perksImgs.size() == 6); 
        assertEquals(perksPunchline, "Perks and benefits");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
