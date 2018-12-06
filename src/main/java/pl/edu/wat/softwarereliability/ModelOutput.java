package pl.edu.wat.softwarereliability;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
class ModelOutput {

    private int N;
    private double fi;
    private double ET;
}
