package pl.edu.wat.softwarereliability;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class Output {
    private ModelOutput jelinskiMorandaOutput;
    private ModelOutput schickWolvertonOutput;
}
