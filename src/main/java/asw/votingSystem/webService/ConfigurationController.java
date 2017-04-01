package asw.votingSystem.webService;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbUpdate.SuggestionService;
import asw.dbUpdate.model.Suggestion;

@Controller
public class ConfigurationController {
	// Aqui van los diferentes mapping de todas las opciones de configuracion
	// disponibles para el sistema. Se llamara a metodos contenidos en el
	// paquete config situado debajo de votingSystem al mismo nivel que
	// webService (Al estar vacio git hub no lo sube)
	@Autowired
	private SuggestionService suggestionService;
	
	@RequestMapping("/find")
	public String findSuggestion(@RequestParam String title, HttpSession session, Model model){
		List<Suggestion> suggestions = suggestionService.getSuggestionByTitle(title);
		model.addAttribute("sugerencias", suggestions);
		return "config";
	}
}
