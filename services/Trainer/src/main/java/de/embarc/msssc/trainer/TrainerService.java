package de.embarc.msssc.trainer;

import de.embarc.msssc.trainer.backend.TrainerBackend;
import de.embarc.msssc.trainer.backend.TrainerDetails;
import de.embarc.msssc.trainer.backend.TrainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainerService {

    @Autowired
    TrainerBackend backend;

    @RequestMapping("/alleTrainer")
    public List<TrainerInfo> alleTrainer() {
        return backend.alleTrainer();
    }

    @RequestMapping("/trainer/{id}")
    public TrainerDetails trainerDetails(@PathVariable String id) {
        TrainerDetails result = backend.trainerDetails(id);
        if (result == null) {
            throw new ResourceNotFoundException();
        }
        return result;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class ResourceNotFoundException extends RuntimeException {
    }
}