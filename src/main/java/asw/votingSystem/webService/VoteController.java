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
import asw.reportWriter.kafka.KafkaProducer;
import asw.reportWriter.kafka.KafkaProducerFactory;

@Controller
public class VoteController {

	@Autowired
	private ParticipantService participantService;

	@Autowired
	private SuggestionService suggestionService;

	@RequestMapping("/support")
	public String votingUp(@RequestParam("sugerencia") Long id, HttpSession session, Model model) {
		if (!participantService.supportSuggestion(((Participant) session.getAttribute("usuario")).getId(), id))
			model.addAttribute("mensaje", "Ya has votado esta sugerencia anteriormente");
		else
			model.addAttribute("mensaje", "");
		// Enviar aviso a kafka
		List<Suggestion> sugerencias = suggestionService.getVotables();
		model.addAttribute("sugerencias", sugerencias);
		return "index";
	}

}
