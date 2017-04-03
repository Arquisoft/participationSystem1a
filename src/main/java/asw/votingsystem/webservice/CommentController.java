package asw.votingsystem.webservice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import asw.dbupdate.CommentService;
import asw.dbupdate.ParticipantService;
import asw.dbupdate.SuggestionService;
import asw.dbupdate.WordService;
import asw.dbupdate.model.Comment;
import asw.dbupdate.model.Participant;
import asw.dbupdate.model.Suggestion;
import asw.dbupdate.model.Word;
import asw.reportwriter.kafka.KafkaProducer;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private ParticipantService participantService;

	@Autowired
	private WordService wordService;

	@Autowired
	private KafkaProducer kafka;

	@RequestMapping("/comments")
	public String showComments(@RequestParam("sugerencia") Long id, HttpSession session, Model model) {
		session.setAttribute("idSuggestion", id);
		return "redirect:/listComments";
	}

	@RequestMapping("/listComments")
	public String listComments(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("idSuggestion");
		List<Comment> comentarios = commentService.getCommentsBySuggestion(suggestionService.getSuggestionById(id));
		model.addAttribute("comentarios", comentarios);
		return "comments";
	}

	@RequestMapping("/votarPositivo")
	public String votingPositive(@RequestParam("comentario") Long id, HttpSession session, Model model) {
		if (!participantService.supportCommentPositive(((Participant) session.getAttribute("usuario")).getId(), id))
			model.addAttribute("mensaje", "Ya has votado este comentario anteriormente");
		else {
			model.addAttribute("mensaje", "Ha votado like a este comentario");
			kafka.sendPositiveComment(id);
		}
		return "redirect:/listComments";
	}

	@RequestMapping("/votarNegativo")
	public String votingNegative(@RequestParam("comentario") Long id, HttpSession session, Model model) {
		if (!participantService.supportCommentNegative(((Participant) session.getAttribute("usuario")).getId(), id))
			model.addAttribute("mensaje", "Ya has votado este comentario anteriormente");
		else {
			model.addAttribute("mensaje", "Ha votado dislike a este comentario");
			kafka.sendNegativeComment(id);
		}
		return "redirect:/listComments";
	}

	@RequestMapping("/comment")
	public String comment(@RequestParam String comment, HttpSession session, Model model) {
		if (comment.equals("")) {
			model.addAttribute("mensaje", "No ha escrito nada");
		} else {
			List<Word> words = wordService.getAllWords();
			for (int i = 0; i < words.size(); i++) {
				if (comment.contains(words.get(i).getWord())) {
					model.addAttribute("mensaje", "El comentario contiene palabras prohibidas");
					return "redirect:listComments";
				}
			}
			Participant p = (Participant) session.getAttribute("usuario");
			Suggestion s = suggestionService.getSuggestionById((Long) session.getAttribute("idSugerencia"));
			Comment c = commentService.saveComment(new Comment(comment, p, s));
			kafka.sendNewComment(c.getId());

		}
		return "redirect:/listComments";
	}
}
