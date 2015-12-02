package de.embarc.msssc.profilbild;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfilbildService {

    @RequestMapping("/profilbild")
    public Profilbild bild(@RequestParam(value = "email", defaultValue = "") String email) {
        Profilbild result = new GravatarCommand(email).execute();
        return result;
    }
}