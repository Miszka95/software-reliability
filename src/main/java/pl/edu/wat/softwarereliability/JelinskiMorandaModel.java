package pl.edu.wat.softwarereliability;

import java.util.List;

class JelinskiMorandaModel {

    private final List<Integer> times;
    private final int SIZE;
    private final double epsilon;

    private int N;
    private double fi;
    private double ET;

    JelinskiMorandaModel(ModelInput modelInput) {
        epsilon = modelInput.getEpsilon();
        times = modelInput.getTimes();
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

    private void calculate() {
        N = calculateN();
        fi = calculateFi();
        ET = 1d / (fi * (N - SIZE));
    }

    private int calculateN() {
        int N = SIZE;
        double diff;
        do {
            N += 1;
            double left = calculateLeft(N);
            double right = calculateRight(N);
            diff = Math.abs(left - right);
        } while (diff > epsilon);
        return N;
    }

    private double calculateFi() {
        return SIZE / calculateLowerPart(N);
    }

    private double calculateLeft(int N) {
        double value = 0;
        for (int i = 1; i <= SIZE; i++) {
            value += 1d / (N - (i - 1));
        }
        return value;
    }

    private double calculateRight(int N) {
        return calculateUpperSum() / calculateLowerPart(N);
    }

    private double calculateUpperSum() {
        return sumTimes() * SIZE;
    }

    private double calculateLowerPart(int N) {
        double firstValue = sumTimes();

        double secondValue = 0;
        for (int i = 1; i <= SIZE; i++) {
            secondValue += (i - 1) * times.get(i - 1);

        }
        return (firstValue * N) - secondValue;
    }

    private double sumTimes() {
        double sum = 0;
        for (int i = 1; i <= SIZE; i++) {
            sum += times.get(i - 1);
        }
        return sum;
    }
}
