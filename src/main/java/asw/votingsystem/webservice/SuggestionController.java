package asw.votingsystem.webservice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbupdate.CategoryService;
import asw.dbupdate.SuggestionService;
import asw.dbupdate.WordService;
import asw.dbupdate.model.Category;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;
import asw.dbupdate.model.Word;
import asw.reportwriter.kafka.KafkaProducer;

@Controller
public class SuggestionController {

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private WordService wordService;
	
	@Autowired
	private KafkaProducer kafka;

	@RequestMapping("/createSuggestion")
	public String viewFormCreateSuggestion(Model model) {
		model.addAttribute("categorias", categoryService.getAllCategories());
		return "createSuggestion";
	}

	@RequestMapping("/create")
	public String createSuggestion(@RequestParam String suggestion_title, @RequestParam String suggestion_description,
			@RequestParam("categoria") Long idcategoria, HttpSession session, Model model) {
		if (suggestion_description.equals("") || suggestion_description.equals("")) {
			model.addAttribute("mensajes", "No puedes dejar los campos de texto vacios");
			return "createSuggestion";
		} else {
			List<Word> words = wordService.getAllWords();
			for (int i = 0; i < words.size(); i++) {
				if (suggestion_title.toLowerCase().contains(words.get(i).getWord())) {
					model.addAttribute("mensajes", "El titulo de la propuesta contiene palabras prohibidas");
					return "createSuggestion";
				}
				if (suggestion_description.toLowerCase().contains(words.get(i).getWord())) {
					model.addAttribute("mensajes", "La descripción de la propuesta contiene palabras prohibidas");
					return "createSuggestion";
				}
			}
			Category categoria = categoryService.getCategoryById(idcategoria);
			Suggestion s = suggestionService.saveSuggestion(new Suggestion(suggestion_title, suggestion_description,
					(Participant) session.getAttribute("usuario"), categoria));
			kafka.sendNewSuggestion(s.getId());
			return "redirect:/index";
		}
	}
}
