package de.embarc.msssc.webseite.controller;

import de.embarc.msssc.webseite.clients.Profilbild;
import de.embarc.msssc.webseite.clients.ProfilbildClient;
import de.embarc.msssc.webseite.clients.Trainer;
import de.embarc.msssc.webseite.clients.TrainerClient;
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

        Trainer trainer = trainerClient.getTrainerById(id);
        Profilbild bild = profilbildClient.getProfilbild(trainer.getEmail());

        response.setContentType("image/jpeg");

        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        bos.write(bild.getDaten());
        bos.flush();
        bos.close();
    }
}