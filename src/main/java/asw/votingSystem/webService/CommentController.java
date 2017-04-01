package asw.votingSystem.webService;

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
import asw.dbUpdate.model.Comment;
import asw.dbUpdate.model.Participant;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private ParticipantService participantService;

	@RequestMapping("/comments")
	public String showComments(@RequestParam("sugerencia") Long id, HttpSession session, Model model) {
		List<Comment> comentarios = commentService.getCommentsBySuggestion(suggestionService.getSuggestionById(id));
		model.addAttribute("comentarios", comentarios);
		return "comments";
	}

	@RequestMapping("/votarPositivo")
	public String votingPositive(@RequestParam("comentario") Long id, HttpSession session, Model model) {
		if (!participantService.supportCommentPositive(((Participant) session.getAttribute("usuario")).getId(), id))
			model.addAttribute("mensaje", "Ya has votado esta sugerencia anteriormente");
		else
			model.addAttribute("mensaje", "");
		List<Comment> comentarios = commentService.getCommentsBySuggestion(suggestionService.getSuggestionById(id));
		model.addAttribute("comentarios", comentarios);
		return "comments";
	}

	@RequestMapping("/votarNegativo")
	public String votingNegative(@RequestParam("comentario") Long id, HttpSession session, Model model) {
		if (!participantService.supportCommentNegative(((Participant) session.getAttribute("usuario")).getId(), id))
			model.addAttribute("mensaje", "Ya has votado esta sugerencia anteriormente");
		else
			model.addAttribute("mensaje", "");
		List<Comment> comentarios = commentService.getCommentsBySuggestion(suggestionService.getSuggestionById(id));
		model.addAttribute("comentarios", comentarios);
		return "comments";
	}
}
