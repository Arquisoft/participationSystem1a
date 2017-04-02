package asw.votingSystem.webService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbUpdate.CategoryService;
import asw.dbUpdate.SuggestionService;
import asw.dbUpdate.WordService;
import asw.dbUpdate.model.Category;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;
import asw.dbUpdate.model.Word;

@Controller
public class SuggestionController {

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private WordService wordService;

	@RequestMapping("/createSuggestion")
	public String viewFormCreateSuggestion(Model model) {
		return "createSuggestion";
	}

	@RequestMapping("/create")
	public String createSuggestion(@RequestParam String suggestion_title, @RequestParam String suggestion_description,
			@RequestParam String fechaFinPropuesta, @RequestParam String suggestion_category, HttpSession session,
			Model model) {
		if (fechaFinPropuesta.equals("yyyy-MM-dd") || suggestion_description.equals("")
				|| suggestion_description.equals("") || suggestion_category.equals("")) {
			model.addAttribute("mensajes", "No puedes dejar los campos de texto vacios");
			return "createSuggestion";
		} else {
			Category categoria = categoryService.getCategoryByName(suggestion_category);
			if (categoria == null) {
				model.addAttribute("mensajes", "No existe esa categoria");
				return "createSuggestion";
			}
			List<Word> words = wordService.getAllWords();
			for (int i = 0; i<words.size(); i++){
				if (suggestion_title.contains(words.get(i).getWord())){
					model.addAttribute("mensajes", "El titulo de la propuesta contiene palabras prohibidas");
					return "createSuggestion";
				}
				if (suggestion_description.contains(words.get(i).getWord())){
					model.addAttribute("mensajes", "La descripción de la propuesta contiene palabras prohibidas");
					return "createSuggestion";
				}
			}
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = "2013-09-25";
			Date fechaFin = null;
			try {
				fechaFin = sdf.parse(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			suggestionService.saveSuggestion(new Suggestion(suggestion_title, suggestion_description,
					(Participant) session.getAttribute("usuario"), fechaFin, categoria));
			List<Suggestion> sugerencias = suggestionService.getVotables();
			model.addAttribute("sugerencias", sugerencias);
			return "index";
		}
	}
}
