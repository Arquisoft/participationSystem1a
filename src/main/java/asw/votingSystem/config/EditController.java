package asw.votingSystem.config;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbUpdate.ParticipantService;
import asw.dbUpdate.SuggestionService;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;

@Controller
public class EditController {



	@Autowired
	private SuggestionService suggestionService;

	@RequestMapping("/edit")
	public String editSuggestion(@RequestParam("sugerencia") Long id, HttpSession session, Model model) {
		Suggestion sugerencia = suggestionService.getSuggestionById(id);
		model.addAttribute("sugerencia", sugerencia);
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "edit";
	}
}
