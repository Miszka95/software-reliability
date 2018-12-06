package pl.edu.wat.softwarereliability;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
class ModelInput {

    private final List<Integer> times;
    private final double epsilon;
}
