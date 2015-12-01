package de.embarc.msssc.webseite.controller;

import de.embarc.msssc.webseite.clients.Trainer;
import de.embarc.msssc.webseite.clients.TrainerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DetailController {

    @Autowired
    private TrainerClient trainerClient;

    @RequestMapping(value = "/detail_{id}.html", method = RequestMethod.GET)
    public String detail(@PathVariable String id, ModelMap model) {

        Trainer trainer = trainerClient.getTrainerById(id);
        model.addAttribute("trainer", trainer);

        return "detail";
    }
}