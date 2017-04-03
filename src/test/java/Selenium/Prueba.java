package Selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import scala.util.control.Exception.By;

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
		assertEquals("Propuestas activas", driver.findElement(By.cssSelector("h1")).getText());
		assertEquals("Crear una propuesta", driver.findElement(By.linkText("Crear una propuesta")).getText());
	}

	@Test
	public void testVoidVerComentarios() throws Exception {
		loginUser();
		driver.findElement(By.id("apoyar${iter.id}")).click();
		driver.findElement(By.id("comentarios${iter.id}")).click();
		assertEquals("Comentarios de la propuesta", driver.findElement(By.cssSelector("h1")).getText());
		assertEquals("Comentar", driver.findElement(By.id("login")).getText());
		assertEquals("Comente aquí", driver.findElement(By.cssSelector("form > p")).getText());
		driver.findElement(By.id("positivo${iter.id}")).click();
	}

	private void loginUser() {
		driver.get(baseUrl);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("pepe@participant.es");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("12345");
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
