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

import asw.dbUpdate.CommentService;
import asw.dbUpdate.ParticipantService;
import asw.dbUpdate.SuggestionService;
import asw.dbUpdate.model.Participant;
import asw.dbUpdate.model.Suggestion;

@Controller
public class SuggestionController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private ParticipantService participantService;

	@RequestMapping("/createSuggestion")
	public String viewFormCreateSuggestion(Model model) {
		return "createSuggestion";
	}

	@RequestMapping("/create")
	public String createSuggestion(@RequestParam String suggestion_title, @RequestParam String suggestion_description,
			@RequestParam String fechaFinPropuesta, HttpSession session, Model model) {
		if (fechaFinPropuesta.equals("yyyy-MM-dd") || suggestion_description.equals("")
				|| suggestion_description.equals("")) {
			model.addAttribute("mensajes", "No puedes dejar los campos de texto vacios");
			return "createSuggestion";
		} else {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = "2013-09-25";
			Date fechaFin = null;
			try {
				fechaFin = sdf.parse(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			suggestionService.saveSuggestion(new Suggestion(suggestion_title, suggestion_description,
					(Participant) session.getAttribute("usuario"), fechaFin));
			List<Suggestion> sugerencias = suggestionService.getVotables();
			model.addAttribute("sugerencias", sugerencias);
			return "index";
		}
	}
}
