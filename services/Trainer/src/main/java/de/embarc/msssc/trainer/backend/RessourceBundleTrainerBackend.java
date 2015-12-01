package de.embarc.msssc.trainer.backend;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RessourceBundleTrainerBackend implements TrainerBackend {

    private ResourceBundle bundle;

    private List<TrainerInfo> trainerInfos;
    private Map<String, TrainerDetails> trainerDetails;

    public RessourceBundleTrainerBackend() {
        this.bundle = ResourceBundle.getBundle("trainerdata.all");

        trainerInfos = new ArrayList<>();
        trainerDetails = new HashMap<>();


        Set<String> ids = alleIds();
        for (String id: ids) {

            TrainerDetails details = new TrainerDetails();

            details.setId(id);
            details.setVorname(getString(id, "vorname"));
            details.setNachname(getString(id, "nachname"));
            details.setEmail(getString(id, "email"));
            details.setTwitter(getString(id, "twitter"));
            details.setHomepage(getString(id, "homepage"));
            details.setFirma(getString(id, "firma"));
            details.setBeschreibung(getString(id, "beschreibung"));

            String name = getString(id, "name");
            if (name == null) {
                name = details.getVorname() + " " + details.getNachname();
            }
            details.setName(name);

            trainerDetails.put(id, details);
            trainerInfos.add(details.getInfo());
        }

    }

    @Override
    public List<TrainerInfo> alleTrainer() {
        return trainerInfos;
    }

    @Override
    public TrainerDetails trainerDetails(String id) {
        return trainerDetails.get(id);
    }

    protected Set<String> alleIds() {
        Set<String> ids = new TreeSet<String>();
        for (String key: bundle.keySet()) {
            String[] token = key.split("\\.");
            ids.add(token[0]);
        }
        return ids;
    }

    protected String getString(String id, String propertyName) {
        String key = id + "." + propertyName;

        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        } else {
            return null;
        }
    }
}
