package de.embarc.msssc.trainer.backend;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Eine sehr naive Implementierung des Trainer-Backends mit nur einem Trainer.
 */
public class SimpleTrainerBackendImpl implements TrainerBackend {

    private final TrainerDetails einzigerTrainer;

    public SimpleTrainerBackendImpl() {
        this.einzigerTrainer = new TrainerDetails();

        einzigerTrainer.setId("pp");
        einzigerTrainer.setVorname("Peter");
        einzigerTrainer.setNachname("Pan");
        einzigerTrainer.setName("Peter Pan");
        einzigerTrainer.setFirma("Neverland Inc.");
        einzigerTrainer.setHomepage("https://de.wikipedia.org/wiki/Peter_Pan_(1953)");
        einzigerTrainer.setTwitter("@Disney");
        einzigerTrainer.setEmail("ppan@neverland.com");
        einzigerTrainer.setBeschreibung("Peter Pan ist die Hauptfigur einiger Kindergeschichten von James Matthew Barrie und dort das einzige Kind, das niemals erwachsen wird. Peter Pan lebt im „Nimmerland“ (engl. Neverland), einer fiktiven Insel. Er ist Anführer der „verlorenen Jungs“ (engl. The Lost Boys), einer Gruppe von Jungen. Peter Pans Gegenspieler ist der Anführer der Piraten, Captain Hook. (Wikipedia)");
    }

    public List<TrainerInfo> alleTrainer() {
        return Collections.singletonList(einzigerTrainer.getInfo());
    }

    public TrainerDetails trainerDetails(String id) {
        if (id.equals(einzigerTrainer.getId())) {
            return einzigerTrainer;
        } else {
            return null;
        }
    }
}
