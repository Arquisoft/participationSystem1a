package asw.votingSystem.webService;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.hadoop.mapred.gethistory_jsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbUpdate.CategoryService;
import asw.dbUpdate.SuggestionService;
import asw.dbUpdate.model.Category;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.model.SuggestionState;

@Controller
public class ConfigurationController {
	// Aqui van los diferentes mapping de todas las opciones de configuracion
	// disponibles para el sistema. Se llamara a metodos contenidos en el
	// paquete config situado debajo de votingSystem al mismo nivel que
	// webService (Al estar vacio git hub no lo sube)
	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/accepted")
	public String findAcceptedSuggestions(Model model){
		List<Suggestion> acceptedSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.Aceptada);
		model.addAttribute(acceptedSuggestions);
		return "accepted";
	}
	
	@RequestMapping("/rejected")
	public String findRejectedSuggestions(Model model){
		List<Suggestion> rejectedSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.Rechazada);
		model.addAttribute(rejectedSuggestions);
		return "rejected";
	}
	
	@RequestMapping("/transact")
	public String findTrasactSuggestions(Model model){
		List<Suggestion> trasactSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.EnVotacion);
		model.addAttribute(trasactSuggestions);
		return "transact";
	}
	
	@RequestMapping("/find")
	public String findSuggestion(@RequestParam("suggestion_name") String title, HttpSession session, Model model) {
		List<Suggestion> suggestions = suggestionService.getSuggestionByTitle(title);
		model.addAttribute("sugerencias", suggestions);
		return "config";
	}

	// TODO No funciona, ya lo arreglare si al final permitimos edicion
	@RequestMapping("/save")
	public String saveSuggestion(@RequestParam("sugerencia") Long id, HttpSession session, Model model) {
		suggestionService.saveSuggestion((Suggestion) session.getAttribute("sugerencia"));
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "config";
	}

	@RequestMapping("/delete")
	public String deleteSuggestion(@RequestParam("sugerencia") Long id, Model model) {
		Suggestion s = suggestionService.getSuggestionById(id);
		suggestionService.deleteSuggestion(s);
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "config";
	}
	@RequestMapping("/days")
	public String setDays(@RequestParam("days") int dias, HttpSession session, Model model) {
		Suggestion.DIAS_ABIERTA = dias;
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "config";
	}

	@RequestMapping("/addCategories")
	public String addCategory(@RequestParam("acategory") String nombre, HttpSession session, Model model) {
		Category category = categoryService.getCategoryByName(nombre);
		if (category == null) {
			Category categoria = new Category(nombre);
			categoryService.saveCategory(categoria);
		}
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "config";
	}

	@RequestMapping("/removeCategories")
	public String removeCategory(@RequestParam("rmcategory") String nombre, HttpSession session, Model model) {
		Category category = categoryService.getCategoryByName(nombre);
		if (category != null) {
			categoryService.deleteCategory(category);
		}
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "config";
	}

	@RequestMapping("/rejectSuggestion")
	public String rejectSuggestion(@RequestParam("transacSuggestion") Long id, Model model){
		Suggestion suggestion = suggestionService.getSuggestionById(id);
		suggestion.setEstado(SuggestionState.Rechazada);
		suggestionService.saveSuggestion(suggestion);
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "transact";
	}
}
