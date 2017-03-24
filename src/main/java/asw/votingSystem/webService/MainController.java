package asw.votingSystem.webService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import asw.reportWriter.kafka.KafkaProducer;
import ejemplo.Message;

@Controller
public class MainController {

//    @Autowired
//    private KafkaProducer kafkaProducer;

    @RequestMapping("/")
    public String landing(Model model) {
        //model.addAttribute("message", new Message());
        return "index";
    }
    
//    @RequestMapping("/send")
//    public String send(Model model, @ModelAttribute Message message) {
//        kafkaProducer.send("exampleTopic", message.getMessage());
//        return "redirect:/";
//    }

}