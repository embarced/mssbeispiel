package de.embarc.msssc.trainer.backend;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class RessourceBundleTrainerBackendTest {

    @Test
    public void testAllIds() {
        RessourceBundleTrainerBackend backend = new RessourceBundleTrainerBackend();
        Set<String> ids = backend.alleIds();
        Assert.assertNotNull(ids);

        System.out.println(backend.trainerDetails("mv").getNachname());
    }
}
