package asw.votingSystem.webService;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	@RequestMapping("/parameters")
	public String parameters(Model model){
		return "parameters";
	}
	
	@RequestMapping("/accepted")
	public String findAcceptedSuggestions(Model model) {
		List<Suggestion> acceptedSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.Aceptada);
		model.addAttribute("suggestions", acceptedSuggestions);

		return "accepted";
	}

	@RequestMapping("/rejected")

	public String findRejectedSuggestions(Model model){
		List<Suggestion> rejectedSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.Rechazada);
		model.addAttribute("suggestions", rejectedSuggestions);
		return "rejected";
	}

	@RequestMapping("/transact")

	public String findTrasactSuggestions(Model model) {
		List<Suggestion> trasactSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.EnVotacion);
		model.addAttribute("suggestions", trasactSuggestions);
		return "transact";
	}

	@RequestMapping("/find")
	public String findSuggestion(@RequestParam("suggestion_name") String title,
				HttpSession session, Model model) {
		List<Suggestion> suggestions = suggestionService
				.getSuggestionByTitle(title);
		model.addAttribute("suggestions", suggestions);
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
	public String setDays(@RequestParam("suggestion_duration") int dias, HttpSession session, Model model) {
		Suggestion.DIAS_ABIERTA = dias;
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "parameters";
	}

	@RequestMapping("/addCategories")
	public String addCategory(@RequestParam("acategory") String nombre, HttpSession session, Model model) {
		Category category = categoryService.getCategoryByName(nombre);
		if (category == null) {
			Category categoria = new Category(nombre);
			categoryService.saveCategory(categoria);
			model.addAttribute("mensaje", "Category " + nombre + " has been added");
		}
		else
			model.addAttribute("mensaje", "Category " + nombre + " already exist");
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "parameters";
	}

	@RequestMapping("/removeCategories")
	public String removeCategory(@RequestParam("rmcategory") String nombre, HttpSession session, Model model) {
		Category category = categoryService.getCategoryByName(nombre);
		if (category != null && category.getSuggestions().isEmpty()) {
			
			categoryService.deleteCategory(category);
			model.addAttribute("mensaje", "Category " + nombre + " has been removed");
		}
		else
			model.addAttribute("mensaje", "Category " + nombre +" doesn't exist or there are suggestion in it");
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "parameters";
	}
	
	/*@RequestMapping("/addWords")
	public String  removeWord*/

	@RequestMapping("/rejectSuggestion")
	public String rejectSuggestion(@RequestParam("transacSuggestion") Long id, Model model) {
		Suggestion suggestion = suggestionService.getSuggestionById(id);
		suggestion.setEstado(SuggestionState.Rechazada);
		suggestionService.saveSuggestion(suggestion);
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "transact";
	}
}
