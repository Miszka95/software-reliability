package pl.edu.wat.softwarereliability;

import java.util.List;

abstract class ReliabilityModel {
    final List<Integer> times;
    final int SIZE;
    final double epsilon;

    int N;
    double fi;
    double ET;

    ReliabilityModel(ModelInput input) {
        epsilon = input.getEpsilon();
        times = input.getTimes();
        SIZE = times.size();

        calculate();
    }

    ModelOutput solution() {
        return ModelOutput.builder()
                .N(N)
                .fi(fi)
                .ET(ET)
                .build();
    }

    protected abstract void calculate();
}
