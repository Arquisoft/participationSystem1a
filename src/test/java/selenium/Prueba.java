package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import asw.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Prueba {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@Test
	public void testVerPropuestas() throws Exception {
		loginUser();
		assertEquals("Propuestas activas", driver.findElement(org.openqa.selenium.By.cssSelector("h1")).getText());
		assertEquals("Crear una propuesta", driver.findElement(By.linkText("Crear una propuesta")).getText());
	}

	@Test
	public void testVerComentarios() throws Exception {
		loginUser();
		driver.findElement(By.id("apoyar${iter.id}")).click();
		driver.findElement(By.id("comentarios${iter.id}")).click();
		assertEquals("Comentarios de la propuesta", driver.findElement(By.cssSelector("h1")).getText());
		assertEquals("Comentar", driver.findElement(By.id("login")).getText());
		assertEquals("Comente aquí", driver.findElement(By.cssSelector("form > p")).getText());
		driver.findElement(By.id("positivo${iter.id}")).click();
	}

	@Test
	public void testCrearPropuesta() throws Exception {
		loginUser();
		driver.findElement(By.linkText("Crear una propuesta")).click();
		assertEquals("Crear propuesta", driver.findElement(By.cssSelector("h1")).getText());
		assertEquals("Titulo de la propuesta", driver.findElement(By.cssSelector("li > p")).getText());
		assertEquals("Descripción de la propuesta", driver.findElement(By.xpath("//li[2]/p")).getText());
		assertEquals("Categoria de la propuesta", driver.findElement(By.xpath("//li[3]/p")).getText());
		assertEquals("", driver.findElement(By.name("crearPropuesta")).getText());
	}

	@Test
	public void testVerPaginaAdministrador() throws Exception {
		loginAdmin();
		assertEquals("Administration Page", driver.findElement(By.cssSelector("h1")).getText());
		assertEquals("Parameter configuration", driver.findElement(By.linkText("Parameter configuration")).getText());
		assertEquals("Accepted Suggestions", driver.findElement(By.linkText("Accepted Suggestions")).getText());
		assertEquals("Rejected Suggestions", driver.findElement(By.linkText("Rejected Suggestions")).getText());
		assertEquals("Suggestions taking support",
				driver.findElement(By.linkText("Suggestions taking support")).getText());
		assertEquals("Suggestions on vote", driver.findElement(By.linkText("Suggestions on vote")).getText());
	}

	@Test
	public void testRealizarComentario() throws Exception {
		loginUserJualo();
		String comentario = "me parece bien";
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td[6]/a")).click();
		driver.findElement(By.id("comment")).clear();
		driver.findElement(By.id("email")).sendKeys(comentario);
		driver.findElement(By.id("login")).click();
		
		boolean esta = false;
		for(int i=1; i < 10; i++) {
			String text = driver.findElement(By.xpath("/html/body/table/tbody/tr["+i+"]/td[1]")).getText();
			if(text.equals("me parece bien"))
				esta = true;
		}
		
		assertEquals(esta, true);
	}

	private void loginUser() {
		driver.get(baseUrl);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("pepe@participant.es");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("12345");
		driver.findElement(By.id("login")).click();
	}
	
	private void loginUserJualo() {
		driver.get(baseUrl);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("jualo@participant.es");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("jualo123");
		driver.findElement(By.id("login")).click();
	}

	private void loginAdmin() {
		driver.get(baseUrl);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("admin@domain.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("admin123");
		driver.findElement(By.id("login")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
