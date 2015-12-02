package de.embarc.msssc.profilbild;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GravatarCommand extends HystrixCommand<Profilbild> {

    private String email;

    public GravatarCommand(String email) {

        super(HystrixCommandGroupKey.Factory.asKey("DefaultGroup"));
        this.email = email;
    }

    @Override
    protected Profilbild run() throws Exception {
        Gravatar gravatar = new Gravatar();
        gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
        gravatar.setDefaultImage(GravatarDefaultImage.HTTP_404);

        byte[] data = gravatar.download(email);
        Profilbild id = new Profilbild();

        if (data == null) {
            data = bildLaden("images/standard_black_60x60.jpg");
            id.setInfo("kein Bild bei Gravatar gefunden");
        }
        id.setData(data);

        return id;
    }

    @Override
    protected Profilbild getFallback() {
        Profilbild id = new Profilbild();
        id.setInfo("Fallback");
        id.setData(bildLaden("images/standard_red_60x60.jpg"));
        return id;
    }

    private byte[] bildLaden(String dateiname) {
        try {
            InputStream stream = ClassLoader.getSystemResourceAsStream(dateiname);
            BufferedInputStream bis = new BufferedInputStream(stream);
            ByteArrayOutputStream target = new ByteArrayOutputStream(32000);

            int d = 0;
            while ((d = bis.read()) != -1) {
                target.write(d);
            }
            stream.close();

            return target.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}