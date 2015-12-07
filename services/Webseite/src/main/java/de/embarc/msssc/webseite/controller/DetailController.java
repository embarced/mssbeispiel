package de.embarc.msssc.webseite.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
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

        TrainerDetailsCommand cmd = new TrainerDetailsCommand(id);
        Trainer trainer = cmd.execute();
        model.addAttribute("trainer", trainer);

        return "detail";
    }

    private class TrainerDetailsCommand extends HystrixCommand<Trainer> {

        private String id;

        TrainerDetailsCommand(String id) {
            super(HystrixCommandGroupKey.Factory.asKey("DefaultGroup"));
            this.id = id;
        }

        @Override
        protected Trainer run() throws Exception {
            return trainerClient.getTrainerById(id);
        }
    }
}