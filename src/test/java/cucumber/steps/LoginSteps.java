package cucumber.steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class LoginSteps {

	private WebDriver driver=new HtmlUnitDriver();
	private String baseUrl = "http://localhost:8080/";
	
	private Map<String, String> jualo;
	
	@Before
	public void setUp() {
		driver.get(baseUrl);

		jualo = new HashMap<String, String>();
		jualo.put("user", "jualo@participant.es");
		jualo.put("pass", "jualo123");
		
	}

	@Given("^la lista de usuarios:$")
	public void la_lista_de_usuarios(List<Map<String, String>> arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		// throw new PendingException();
		assertEquals(jualo.get("user"), "jualo@participant.es");
		assertEquals(jualo.get("pass"), "jualo123");
	}

	@When("^introduzco el usuario \"([^\"]*)\" y la contraseña \"([^\"]*)\"$")
	public void introduzco_el_usuario_y_la_contraseña(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// throw new PendingException();
		String user = "jualo@participant.es";
		String pass = "jualo123";
		boolean isJualo = false;

		if (jualo.get("usuario").equals(user) && jualo.get("pass").equals(pass)) {
			isJualo = true;
		}
		
		assertEquals(isJualo, true);

		driver.findElement(By.id("email")).sendKeys(user);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.name("botonlogin")).click();
	}

	@Then("^entro en la pantalla de sugerencias$")
	public void entro_en_la_pantalla_de_sugerencias() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// throw new PendingException();
		
		String currentURL = driver.getCurrentUrl();
		assertEquals(currentURL, "http://localhost:8080/main");
	}

}
