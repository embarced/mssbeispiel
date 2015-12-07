package de.embarc.msssc.webseite.clients;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by stefanz on 07.12.15.
 */
public class ProfilbildFactory {

    public static Profilbild erzeugeAusDatei(String dateiname) {

        byte[] daten = bildLaden(dateiname);
        Profilbild bild = new Profilbild();
        bild.setData(daten);
        bild.setInfo(dateiname);

        return bild;
    }

    private static byte[] bildLaden(String dateiname) {
        try {
            InputStream stream = ClassLoader.getSystemResourceAsStream(dateiname);
            BufferedInputStream bis = new BufferedInputStream(stream);

            ByteArrayOutputStream target = new ByteArrayOutputStream(32000);

            int d = 0;
            while ((d = bis.read()) != -1) {
                target.write(d);
            }

            bis.close();
            target.close();
            return target.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
