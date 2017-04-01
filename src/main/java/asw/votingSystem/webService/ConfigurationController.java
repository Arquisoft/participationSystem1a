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

	@RequestMapping("/edit")
	public String editSuggestion(@RequestParam("sugerencia") Long id, Model model) {
		Suggestion sugerencia = suggestionService.getSuggestionById(id);
		model.addAttribute("sugerencia", sugerencia);
		// Enviar aviso a kafka
		return "edit";
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

}
