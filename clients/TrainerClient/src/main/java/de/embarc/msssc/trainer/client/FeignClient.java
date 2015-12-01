package de.embarc.msssc.trainer.client;

import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;

import java.util.List;

public class FeignClient {

    public interface TrainerClient {
        @RequestLine("GET /alleTrainer/")
        List<Trainer> getAll();

        @RequestLine("GET /trainer/{id}")
        Trainer getTrainerById(@Param("id") String id);
    }

    public static class Trainer {
        String id;
        String name;

        public String toString() {
            return id + " (" + name + ")";
        }
    }

    public static void main(String... args) {
        Decoder decoder = new GsonDecoder();
        TrainerClient client = Feign.builder()
                .decoder(decoder)
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC)
                .target(TrainerClient.class, "http://localhost:8080");

        List<Trainer> trainers = client.getAll();
        System.out.println(trainers);
        Trainer first = client.getTrainerById(trainers.get(0).id);
        System.out.println(first);
    }
}

