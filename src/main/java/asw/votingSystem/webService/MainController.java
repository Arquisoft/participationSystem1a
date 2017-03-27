package asw.votingSystem.webService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import asw.dbUpdate.ServicesFactory;
import asw.dbUpdate.model.Suggestion;

@Controller
public class MainController {

	@RequestMapping("/")
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping("/main")
	public String mainPage(Model model) {
		List<Suggestion> sugerencias = ServicesFactory.getSuggestionService().getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "index";
	}

	// Aqui iria tambien un RequestMapping para la interfaz de configuracion
	// comprobando si el usuario es admin. Lleva a una pagina html con las
	// opciones
}