package de.embarc.msssc.trainer.backend;

import java.util.List;

public interface TrainerBackend {

    List<TrainerInfo> alleTrainer();

    TrainerDetails trainerDetails(String id);
}
