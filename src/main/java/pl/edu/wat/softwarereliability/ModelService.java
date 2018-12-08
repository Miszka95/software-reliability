package pl.edu.wat.softwarereliability;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelService {

    private final InputMapper inputMapper;

    Output calculateModels(Input input) {
        ModelInput modelInput = inputMapper.map(input);
        ReliabilityModel jelinskiMorandaModel = JelinskiMorandaModel.create(modelInput);
        ReliabilityModel schickWolvertonModel = SchickWolvertonModel.create(modelInput);

        return Output.builder()
                .jelinskiMorandaOutput(jelinskiMorandaModel.solution())
                .schickWolvertonOutput(schickWolvertonModel.solution())
                .build();
    }
}
