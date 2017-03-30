package asw.votingSystem.webService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import asw.dbUpdate.ServicesFactory;
import asw.dbUpdate.model.Suggestion;
import asw.reportWriter.ReportWriter;

@Controller
public class VoteController {

//	@RequestMapping("/support")
//	public String votingUp(@RequestParam Long id, Model mode) {
//		Suggestion suggestion = ServicesFactory.getSuggestionService().getSuggestionById(id);
//		suggestion.incrementVotes();
//		ReportWriter.getInstance().getWriteReport().log("", ""); // Se envia un
//																	// aviso a
//																	// kafka
//		ServicesFactory.getSuggestionService().saveSuggestion(suggestion);
//		return "index";
//	}


}
