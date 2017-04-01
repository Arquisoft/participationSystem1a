package asw.votingSystem.webService;

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
public class MainController {
	@Autowired
	SuggestionService suggestionService;

	@Autowired
	ParticipantService participantService;

	@RequestMapping("/")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/main")
	public String mainPage(@RequestParam String email, @RequestParam String password, HttpSession session,
			Model model) {
		Participant p = participantService.getParticipant(email, password);
		if (p == null)
			return "error";
		session.setAttribute("usuario", p);
		if (p.isAdmin())
			return "config";
		else {
			List<Suggestion> sugerencias = suggestionService.getVotables();
			model.addAttribute("sugerencias", sugerencias);
			return "index";
		}
	}
}