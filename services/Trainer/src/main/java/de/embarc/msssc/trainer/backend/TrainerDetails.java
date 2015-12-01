package de.embarc.msssc.trainer.backend;

public class TrainerDetails extends TrainerInfo {

    private String vorname;
    private String nachname;
    private String firma;
    private String homepage;
    private String twitter;
    private String beschreibung;


    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public TrainerInfo getInfo() {
        TrainerInfo info = new TrainerInfo();
        info.setId(this.id);
        info.setName(this.name);
        info.setEmail(this.email);
        return info;
    }
}
