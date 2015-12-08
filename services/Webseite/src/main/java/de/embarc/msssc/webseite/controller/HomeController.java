package de.embarc.msssc.webseite.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import de.embarc.msssc.webseite.clients.Trainer;
import de.embarc.msssc.webseite.clients.TrainerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TrainerClient trainerClient;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model) {

        AlleTrainerCommand cmd = new AlleTrainerCommand();
        List<Trainer> trainers = cmd.execute();
        model.addAttribute("allTrainers", trainers);

        return "home";
    }

    private class AlleTrainerCommand extends HystrixCommand<List<Trainer>> {

        AlleTrainerCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("DefaultGroup"));
        }

        @Override
        protected List<Trainer> run() {
            return trainerClient.getAll();
        }

        @Override
        protected List<Trainer> getFallback() {
            return Collections.emptyList();
        }
    }
}