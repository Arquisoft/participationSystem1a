package asw.votingsystem.webservice;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbupdate.ParticipantService;
import asw.dbupdate.SuggestionService;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;
import asw.reportwriter.kafka.KafkaProducer;

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
		else {
			model.addAttribute("mensaje", "");
			// Enviar aviso a kafka
			new KafkaProducer().sendPositiveSuggestion(id);
			Suggestion s = suggestionService.getSuggestionById(id);
			if(s.getVotosPositivos() == s.getMinVotos())
				new KafkaProducer().sendMinVotesReached(id);
		}
		return "redirect:/index";
	}

}
