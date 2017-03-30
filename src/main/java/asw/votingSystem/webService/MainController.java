package asw.votingSystem.webService;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import asw.dbUpdate.ServicesFactory;
import asw.dbUpdate.SuggestionService;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;

@Controller
public class MainController {
	@Autowired
	SuggestionService suggestionsService;

	@RequestMapping("/login")
	public String loginPage(HttpSession session, Participant user) {
		session.setAttribute("usuario", user);
		return "login";
	}

	@RequestMapping("/")
	public String mainPage(Model model) {
		List<Suggestion> sugerencias = suggestionsService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "index";
	}

	// Aqui iria tambien un RequestMapping para la interfaz de configuracion
	// comprobando si el usuario es admin. Lleva a una pagina html con las
	// opciones
}