package de.embarc.msssc.webseite.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("trainer")
public interface TrainerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/trainer/{id}")
    public Trainer getTrainerById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/alleTrainer/")
    public List<Trainer> getAll();
}
