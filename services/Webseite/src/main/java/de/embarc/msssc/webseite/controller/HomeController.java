package de.embarc.msssc.webseite.controller;

import de.embarc.msssc.webseite.clients.Trainer;
import de.embarc.msssc.webseite.clients.TrainerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TrainerClient trainerClient;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @ModelAttribute("allTrainers")
    public List<Trainer> populateTrainers() {
        List<Trainer> trainers = trainerClient.getAll();
        return trainers;
    }
}