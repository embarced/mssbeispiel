package de.embarc.msssc.profilbild;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarDefaultImage;
import com.timgroup.jgravatar.GravatarRating;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GravatarCommand extends HystrixCommand<Profilbild> {

    private String email;

    public GravatarCommand(String email) {

        super(HystrixCommandGroupKey.Factory.asKey("DefaultGroup"));
        this.email = email;
    }

    @Override
    protected Profilbild run() throws Exception {

        try {
            Gravatar gravatar = new Gravatar();
            gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
            gravatar.setDefaultImage(GravatarDefaultImage.HTTP_404);

            byte[] data = gravatar.download(email);

            if (data == null) {
                throw new IllegalArgumentException("No Gravatar for " + email);
            }

            Profilbild id = new Profilbild();
            id.setData(data);

            return id;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    protected Profilbild getFallback() {
        Profilbild id = new Profilbild();


        try {
            InputStream stream = ClassLoader.getSystemResourceAsStream("images/standard_black_60x60.jpg");
            BufferedInputStream bis = new BufferedInputStream(stream);
            ByteArrayOutputStream target = new ByteArrayOutputStream(32000);

            int d = 0;
            while ((d = bis.read()) != -1) {
                target.write(d);
            }
            stream.close();

            id.setData(target.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}