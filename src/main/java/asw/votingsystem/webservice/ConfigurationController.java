package asw.votingsystem.webservice;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import asw.dbupdate.CategoryService;
import asw.dbupdate.SuggestionService;
import asw.dbupdate.WordService;
import asw.dbupdate.model.Category;
import asw.dbupdate.model.Suggestion;
import asw.dbupdate.model.SuggestionState;
import asw.dbupdate.model.Word;
import asw.reportwriter.kafka.KafkaProducer;

@Controller
public class ConfigurationController {

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private WordService wordService;

	@Autowired
	private KafkaProducer kafka;

	@RequestMapping("/parameters")
	public String parameters(Model model) {
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "parameters";
	}

	@RequestMapping("/accepted")
	public String findAcceptedSuggestions(Model model) {
		List<Suggestion> acceptedSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.Aceptada);
		model.addAttribute("suggestions", acceptedSuggestions);

		return "accepted";
	}

	@RequestMapping("/rejected")
	public String findRejectedSuggestions(Model model) {
		List<Suggestion> rejectedSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.Rechazada);
		model.addAttribute("suggestions", rejectedSuggestions);
		return "rejected";
	}

	@RequestMapping("/transact")
	public String findTrasactSuggestions(Model model) {
		List<Suggestion> trasactSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.BuscandoApoyo);
		model.addAttribute("suggestions", trasactSuggestions);
		return "transact";
	}

	@RequestMapping("/find")
	public String findSuggestion(@RequestParam("suggestion_name") String title,
			HttpSession session, Model model) {
		List<Suggestion> suggestions = suggestionService
				.getSuggestionByTitle(title);
		model.addAttribute("suggestions", suggestions);
		return "config";
	}

	@RequestMapping("/voting")
	public String voting(Model model) {
		List<Suggestion> votingSuggestions = suggestionService
				.getSuggestionByStatus(SuggestionState.EnVotacion);
		model.addAttribute("suggestions", votingSuggestions);
		return "voting";
	}

	@RequestMapping("/config")
	public String config(Model model) {
		List<Suggestion> sugerencias = suggestionService.getAllSuggestions();
		model.addAttribute("sugerencias", sugerencias);
		return "config";
	}

	// TODO No funciona, ya lo arreglare si al final permitimos edicion
	@RequestMapping("/save")
	public String saveSuggestion(@RequestParam("sugerencia") Long id,
			HttpSession session, Model model) {
		suggestionService.saveSuggestion((Suggestion) session
				.getAttribute("sugerencia"));
		// Enviar aviso a kafka
		kafka.sendNewSuggestion(id);
		return "redirect:/config";
	}

	@RequestMapping("/delete")
	public String deleteSuggestion(@RequestParam("sugerencia") Long id,
			Model model) {
		Suggestion s = suggestionService.getSuggestionById(id);
		suggestionService.deleteSuggestion(s);
		// Enviar aviso a kafka
		kafka.sendDeleteSuggestion(id);
		return "redirect:/config";
	}

	@RequestMapping("/days")
	public String setDays(@RequestParam("suggestion_duration") int dias,
			HttpSession session, Model model) {
		Suggestion.DIAS_ABIERTA = dias;
		// Enviar aviso a kafka
		kafka.send(KafkaProducer.DAYS_OPEN, "Dias abierta -> " + dias);
		return "redirect:/parameters";
	}

	@RequestMapping("/addCategories")
	public ModelAndView addCategory(@RequestParam("acategory") String nombre,
			HttpSession session, Model model) {
		Category category = categoryService.getCategoryByName(nombre);

		ModelAndView mav = new ModelAndView();

		if (category == null) {
			Category categoria = new Category(nombre);
			categoria = categoryService.saveCategory(categoria);

			mav.addObject("mensaje", "Category " + nombre + " has been added");
			// model.addAttribute("mensaje", "Category " + nombre
			// + " has been added");
			// Enviar aviso a kafka
			kafka.sendNewCategory(categoria.getId());
		} else
			mav.addObject("mensaje", "Category " + nombre + " already exist");
		// model.addAttribute("mensaje", "Category " + nombre
		// + " already exist");

		mav.setViewName("parameters");
		return mav;
	}

	@RequestMapping("/removeCategories")
	public ModelAndView removeCategory(
			@RequestParam("rmcategory") String nombre, HttpSession session,
			Model model) {
		Category category = categoryService.getCategoryByName(nombre);

		ModelAndView mav = new ModelAndView();

		if (category != null && category.getSuggestions().isEmpty()) {
			long catId = category.getId();
			categoryService.deleteCategory(category);
			// model.addAttribute("mensaje", "Category " + nombre
			// + " has been removed");
			mav.addObject("mensaje", "Category " + nombre + " has been removed");
			kafka.sendDeleteCategory(catId);
		} else
			mav.addObject("mensaje", "Category " + nombre
					+ " doesn't exist or there are suggestion in it");
		// model.addAttribute("mensaje", "Category " + nombre
		// + " doesn't exist or there are suggestion in it");
		// Enviar aviso a kafka
		mav.setViewName("parameters");
		return mav;
	}

	@RequestMapping("/addWords")
	public String addWord(@RequestParam("npw") String word2a, Model model) {
		Word word = wordService.getWordByName(word2a);
		if (word == null) {
			Word w = new Word(word2a);
			wordService.saveWord(w);
			model.addAttribute("mensaje", "Non-permitted word " + word2a
					+ " has been added");
		} else {
			model.addAttribute("mensaje", "Non-permitted word " + word2a
					+ " already exist");
		}
		return "parameters";
	}

	@RequestMapping("/removeWords")
	public String removeWord(@RequestParam("rpw") String word2r, Model model) {
		Word word = wordService.getWordByName(word2r);
		if (word != null) {
			wordService.deleteWord(word);
			model.addAttribute("mensaje", "Non-permitted word " + word2r
					+ " has been removed");
		} else {
			model.addAttribute("mensaje", "Non-permitted word " + word2r
					+ " doesn't exist");
		}
		return "parameters";
	}

	@RequestMapping("/rejectSuggestion")
	public String rejectSuggestion(@RequestParam("idPropuesta") Long id,
			Model model) {
		Suggestion suggestion = suggestionService.getSuggestionById(id);
		suggestion.setEstado(SuggestionState.Rechazada);
		suggestionService.saveSuggestion(suggestion);
		// Enviar aviso a kafka
		kafka.sendDeniedSuggestion(id);
		return "redirect:/transact";
	}

	@RequestMapping("/reject")
	public String reject(@RequestParam("idPropuesta") Long id, Model model) {
		Suggestion suggestion = suggestionService.getSuggestionById(id);
		suggestion.setEstado(SuggestionState.Rechazada);
		suggestionService.saveSuggestion(suggestion);
		// Enviar aviso a kafka
		kafka.sendDeniedSuggestion(id);
		return "redirect:/voting";
	}

	@RequestMapping("/updateMinVotes")
	public String updateMinVote(@RequestParam("suggestion") Long id,
			@RequestParam("minVotes") int newMinVotes, Model model) {
		Suggestion suggestion = suggestionService.getSuggestionById(id);
		if (newMinVotes > 0) {
			suggestion.setMinVotos(newMinVotes);
			suggestionService.saveSuggestion(suggestion);
		}
		// Enviar aviso a kafka
		return "redirect:/transact";
	}

	@RequestMapping("/accept")
	public String accept(@RequestParam("idPropuesta") Long id, Model model) {
		Suggestion suggestion = suggestionService.getSuggestionById(id);
		suggestion.setEstado(SuggestionState.Aceptada);
		suggestionService.saveSuggestion(suggestion);
		// Enviar aviso a kafka
		return "redirect:/voting";
	}

	@RequestMapping("/postponeEndDate")
	public String postponeEndDate(@RequestParam("suggestion") Long id,
			@RequestParam("endDate") int days, Model model) {
		if (days > 0) {
			Suggestion suggestion = suggestionService.getSuggestionById(id);
			Calendar c = Calendar.getInstance();
			c.setTime(suggestion.getFechaFin());
			c.add(Calendar.DAY_OF_MONTH, days);
			suggestion.setFechaFin(c.getTime());
			suggestionService.saveSuggestion(suggestion);
		}
		return "redirect:/transact";
	}
}
