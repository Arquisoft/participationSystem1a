package asw.votingSystem.config;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbUpdate.SuggestionService;
import asw.dbUpdate.model.Suggestion;

public class SaveController {
	@Autowired
	private SuggestionService suggestionService;

	@RequestMapping("/save")
	public void saveSuggestion(@RequestParam("sugerencia") Long id, HttpSession session, Model model) {
		suggestionService.saveSuggestion((Suggestion) session.getAttribute("sugerencia"));
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
	}
}
