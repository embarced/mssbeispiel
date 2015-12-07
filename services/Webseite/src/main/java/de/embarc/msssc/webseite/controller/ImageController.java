package de.embarc.msssc.webseite.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import de.embarc.msssc.webseite.clients.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    private TrainerClient trainerClient;

    @Autowired
    private ProfilbildClient profilbildClient;

    @RequestMapping(value = "/trainer_{id}.jpg", method = RequestMethod.GET)
    public void trainerBild(@PathVariable String id, HttpServletResponse response) throws IOException {

        ProfilbildCommand cmd = new ProfilbildCommand(id);
        Profilbild bild = cmd.execute();

        response.setContentType("image/jpeg");
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        bos.write(bild.getData());
        bos.flush();
        bos.close();
    }

    private class ProfilbildCommand extends HystrixCommand<Profilbild> {

        private String id;

        ProfilbildCommand(String id) {
            super(HystrixCommandGroupKey.Factory.asKey("DefaultGroup"));
            this.id = id;
        }

        @Override
        protected Profilbild run() throws Exception {
            Trainer trainer = trainerClient.getTrainerById(id);
            return profilbildClient.getProfilbild(trainer.getEmail());
        }

        @Override
        protected Profilbild getFallback() {
            Profilbild bild = ProfilbildFactory.erzeugeAusDatei("images/fallback_60x60.png");
            return bild;
        }
    }
}