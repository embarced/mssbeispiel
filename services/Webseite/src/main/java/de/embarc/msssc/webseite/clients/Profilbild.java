package de.embarc.msssc.webseite.clients;

public class Profilbild {

    private String info;

    private byte[] daten;

    private int groesse;

    public Profilbild() {
    }

    public int getGroesse() {
        return groesse;
    }

    public void setGroesse(int groesse) {
        this.groesse = groesse;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getDaten() {
        return daten;
    }

    public void setDaten(byte[] daten) {
        this.daten = daten;
    }
}
